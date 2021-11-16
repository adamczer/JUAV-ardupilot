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
            int[] center = bi.getCentroid();
            if (latestMode == 3) { //auto
                if (center.length>0) {
                    System.out.println("Found colored thing, switching to guided mode!");
                    lmc.guidedMode();
                }
            }
            //TODO smart movement towards target
            if (latestMode == 4) { //guided
                if (center.length > 0) {
                    if (overBox(center, bi.getHeight() / 2, bi.getWidth() / 2))
                        lmc.landMode((float) latestPosition.getLat() / 7f, (float) latestPosition.getLon() / 7f);
                    else {
                        setNextMovement(center, bi.getHeight() / 2, bi.getWidth() / 2);
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
    protected void setNextMovement(int[] center, int midH, int midW) {
        int boxMidX = center[0];
        int boxMidY = center[1];
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
        return box[0]-50 < midW && box[0]+50 > midW
                && box[1]-50 <midH && box[1]+50 > midH;
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
