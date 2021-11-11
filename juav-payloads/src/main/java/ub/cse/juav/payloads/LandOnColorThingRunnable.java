package ub.cse.juav.payloads;

import ub.cse.jni.image.OpenCv2Wrapper;
import ub.cse.juav.mavlink.LocalTcpMavlinkConnector;
import ub.cse.juav.mavlink.messages.GlobalPositionIntLight;

public class LandOnColorThingRunnable implements Runnable{
    private final boolean isSimulation;
    private LocalTcpMavlinkConnector lmc;
    private OpenCv2Wrapper imageProvider;
    private static final boolean debugging = false;
    private static final int binerizeThresholdLow = 100;
    private static final int binerizeThresholdHigh = 255;

    public LandOnColorThingRunnable(boolean isSimulation) {
        this.isSimulation = isSimulation;
        this.imageProvider = new OpenCv2Wrapper(isSimulation,binerizeThresholdLow,binerizeThresholdHigh);
    }

    @Override
    public void run() {
        //Lazy init
        while (lmc == null)
            lmc = LocalTcpMavlinkConnector.getConnection();
        while (true) {
            lmc.updateState();
            long startTime = System.currentTimeMillis();
            OpenCv2Wrapper bi = getLatestImage();
            long imageTime = System.currentTimeMillis();
            GlobalPositionIntLight latestPosition = lmc.getLatestPosition();
            long latestPosTime = System.currentTimeMillis();
            int latestMode = lmc.getLatestMode();
            int[] box = getBox(bi);
            if (latestMode == 3) { //auto
                if (box.length>0) {
                    System.out.println("Found colored thing, switching to guided mode!");
                    lmc.guidedMode();
                }
            }
            //TODO smart movement towards target
            if (latestMode == 4) { //guided
                if (box.length > 0) {
                    if (overBox(box, bi.getHeight() / 2, bi.getWidth() / 2))
                        lmc.landMode((float) latestPosition.getLat() / 7f, (float) latestPosition.getLon() / 7f);
                    else {
                        setNextMovement(box, bi.getHeight() / 2, bi.getWidth() / 2);
                    }
                } else {
                    firstDiscovery = true;
                    magnitudeMovement = 1f;
                    lmc.autoMode();
                }
            }
            if(debugging)
                System.out.println("took "+(imageTime-startTime)+"ms to get image"
                    +" and "+(latestPosTime-imageTime)+" for position." +
                    " and "+(System.currentTimeMillis()-latestPosTime)+" to process image.");
        }
    }

    private boolean firstDiscovery = true;
    private float magnitudeMovement = 1f;
    private float xDirection = 0;
    private float yDirection = 0;
    protected void setNextMovement(int[] box, int midH, int midW) {
        int boxMidX = box[0]+box[1]/2;
        int boxMidY = box[2]+box[3]/2;
        float deltaW = boxMidX - midW; //want positive to be right
        float deltaH = midH - boxMidY; // want positive to be up
        if(debugging) {
            System.out.println("center color (w,h) = " + boxMidX + ',' + boxMidY);
            System.out.println("delta w = " + deltaW);
            System.out.println("delta h = " + deltaH);
        }

        if(firstDiscovery) {
            xDirection = deltaH;
            yDirection = deltaW;
            firstDiscovery = false;
        } else {
            if (magnitudeMovement/2f > .2 &&
                    (xDirection < 0 && deltaH > 0 ||
                xDirection > 0 && deltaH < 0 ||
                yDirection < 0 && deltaW > 0 ||
                yDirection > 0 && deltaW < 0)
            ) {
                System.out.println("halving");
                magnitudeMovement = magnitudeMovement *.9f;
                xDirection = deltaH;
                yDirection = deltaW;
            }
        }
        float meters_in_x = (deltaH/(Math.abs(deltaH)+Math.abs(deltaW)))*magnitudeMovement;
        float meters_in_y = (deltaW/(Math.abs(deltaH)+Math.abs(deltaW)))*magnitudeMovement;
        if(debugging) {
            System.out.println("delta X = " + String.format("%.10f", meters_in_x));
            System.out.println("delta y = " + String.format("%.10f", meters_in_y));
        }
        lmc.moveInGuidedMode(meters_in_x,meters_in_y,0);
    }

    protected boolean overBox(int[] box, int midH, int midW) {
        return midW > box[0] && midW < box[1] && midH > box[2] && midH < box[3];
    }


    protected int[] getBox(OpenCv2Wrapper i) {
        int minX = Integer.MAX_VALUE;
        int maxX = -1;
        int minY = Integer.MAX_VALUE;
        int maxY = -1;
        int whiteCount = 0;
        for (int y = 0; y < i.getHeight(); y++) {
            for (int x = 0; x < i.getWidth(); x++) {
                //Retrieving contents of a pixel
                int val = i.getPixelVal(x, y);
                //checking region area
                if (val == binerizeThresholdHigh) {
                    whiteCount++;
                    if(x<minX)
                        minX = x;
                    if(x>maxX)
                        maxX = x;
                    if(y<minY)
                        minY = y;
                    if(y>maxY)
                        maxY = y;
                }
            }
        }
        if(minX > 99999 || minY > 99999 || maxX < 0 || maxY < 0) {
            return new int[]{};
        }
        int width = maxX-minX;
        int height = maxY-minY;
        if(debugging) {
            System.out.println("white width,height = " + width + ',' + height);
        }
        if(width<10||height<10||((float)whiteCount/(float)(height*width))<.6) {
            System.out.println("white width,height = " + width + ',' + height);
            System.out.println("white count = " + whiteCount + ", percent white = "+((float)whiteCount/(float)(height*width)));
            return new int[]{};
        } else {
            return new int[]{minX,maxX,minY,maxY};
        }
    }

    private OpenCv2Wrapper getLatestImage() {
        if (isSimulation)
            return generateSimulationImage();
        else {
            imageProvider.nextCameraImage();
            return imageProvider;
        }
    }

    private int simImageCount = 0;
    private OpenCv2Wrapper generateSimulationImage() {
        String juavSrc = System.getenv("JUAV_SRC");
        if(simImageCount>200) {
            imageProvider.loadFile(juavSrc+"/native-util/png-images/white.png");
        } else if(simImageCount>175){
            imageProvider.loadFile(juavSrc+"/native-util/png-images/ll.png");
        } else if(simImageCount>150){
            imageProvider.loadFile(juavSrc+"/native-util/png-images/lr.png");
        } else if(simImageCount>125){
            imageProvider.loadFile(juavSrc+"/native-util/png-images/ur.png");
        } else if(simImageCount>100){
            imageProvider.loadFile(juavSrc+"/native-util/png-images/ul.png");
        } else {
            imageProvider.loadFile(juavSrc + "/native-util/png-images/black.png");
        }
        simImageCount++;

        return imageProvider;
    }
}
