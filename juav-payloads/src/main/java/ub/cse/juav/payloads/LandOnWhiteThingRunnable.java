package ub.cse.juav.payloads;

import ub.cse.jni.image.Color;
import ub.cse.jni.image.ImageCameraWrapper;
import ub.cse.jni.image.ImageNativeWrapper;
import ub.cse.jni.image.ImageNativeWrapperInterface;
import ub.cse.juav.mavlink.LocalTcpMavlinkConnector;
import ub.cse.juav.mavlink.messages.GlobalPositionIntLight;

public class LandOnWhiteThingRunnable implements Runnable{
    private final boolean isSimulation;
    private LocalTcpMavlinkConnector lmc;
    private final boolean landFromAutoMode;
    private ImageCameraWrapper camera;
    private static final boolean logColors = false;

    public LandOnWhiteThingRunnable(boolean landFromAutoMode, boolean isSimulation) {
        this.landFromAutoMode = landFromAutoMode;
        this.isSimulation = isSimulation;
        if(!isSimulation) {
            this.camera = new ImageCameraWrapper();
        }
    }

    private void doTheSleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //Lazy init
        while (lmc == null)
            lmc = LocalTcpMavlinkConnector.getConnection();
        while (true) {
            lmc.updateState();
            long startTime = System.currentTimeMillis();
            ImageNativeWrapperInterface bi = getLatestImage();
            long imageTime = System.currentTimeMillis();
            GlobalPositionIntLight latestPosition = lmc.getLatestPosition();
            long latestPosTime = System.currentTimeMillis();
            int latestMode = lmc.getLatestMode();
            int[] box = getBox(bi);
            if (latestMode == 3) { //auto
                if (box.length>0) {
                    if(!landFromAutoMode) {
                        lmc.guidedMode();
                        doTheSleep(5000);
                    } else { //cant really simulate feedback of motion in simulator
                        if (overBox(box, bi.getHeight() / 2, bi.getWidth() / 2)) {
                            lmc.landMode((float) latestPosition.getLat() / 7f, (float) latestPosition.getLon() / 7f);
                        }
                    }
                }
            }
            //TODO smart movement towards target
//            if (latestMode == 4) { //guided
//                if (box.length > 0) {
//                    if (overBox(box, bi.getHeight() / 2, bi.getWidth() / 2))
//                        lmc.landMode((float) latestPosition.getLat() / 7f, (float) latestPosition.getLon() / 7f);
//                    else {
//                        setNextWaypoint(box, bi.getHeight() / 2, bi.getWidth() / 2, latestPosition);
//                    }
//                } else {
//                    //TODO backtrack? this should not happen //restart Automode?
////                resume auto
//                    lmc.autoMode();
//                }
//            }
            System.out.println("took "+(imageTime-startTime)+"ms to get image"
                    +" and "+(latestPosTime-imageTime)+" for position." +
                    " and "+(System.currentTimeMillis()-latestPosTime)+" to process image.");
            doTheSleep(1000);
        }
    }

    protected void setNextWaypoint(int[] box, int midH, int midW, GlobalPositionIntLight craftPosition) {
        int boxMidX = box[0]+box[1]/2;
        int boxMidY = box[2]+box[3]/2;
        int deltaW = boxMidX - midW;
        int deltaH = boxMidY - midH;
        double radians = Math.atan2(deltaW, deltaH);
//        relitive to right side so we need to subtract 90 to make up match top of frame
        double mydegrees = Math.toDegrees(radians);
        mydegrees = mydegrees-90;

        double pixelDistance = Math.sqrt(Math.pow(deltaW,2)+Math.pow(deltaH,2));

        System.out.println("Heading = "+ (mydegrees-90));
        System.out.println("pixelDistance = "+pixelDistance);

        //TODO compute target lat lon

//        lmc.setWayPoint(lat,lon,2); //2 meters
    }

    protected boolean overBox(int[] box, int midH, int midW) {
        return midW > box[0] && midW < box[1] && midH > box[2] && midH < box[3];
    }


    protected int[] getBox(ImageNativeWrapperInterface bi) {
        int[] box = findWhiteBoundingRectangle(bi);
        if(box.length>0) {
            int pixelArea = (box[1]-box[0]) * (box[3]-box[2]);
            if(pixelArea > 25*25) {
                if (dominantlyWhite(bi,box[0],
                        box[2], box[1] - box[0], box[3] - box[2]))
                    return box;
            }
            return new int[]{};
        } else {
            return box;
        }
    }

    private boolean dominantlyWhite(ImageNativeWrapperInterface subImage, int boxMinY, int boxMinX, int width, int height) {
        int totalCount = 0;
        int whiteCount = 0;
        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;
        for (int y = boxMinY; y < boxMinY+height; y++) {
            for (int x = boxMinX; x < boxMinX+width; x++) {
                //Retrieving contents of a pixel
                Color color = subImage.getRGB(x, y);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if (red > 225 && green > 225 && blue > 225) {
                    whiteCount++;
                }
                totalCount++;
                if(maxRed<red)
                    maxRed = red;
                if(maxGreen<green)
                    maxGreen = green;
                if(maxBlue<blue)
                    maxBlue = blue;
            }
        }
        float percetWhite = (float)whiteCount/(float)totalCount;
        if (logColors)
            System.out.println("Max R,G,B = "+maxRed+","+maxGreen+","+maxBlue);
        return ( percetWhite > .8);
    }

    private int[] findWhiteBoundingRectangle(ImageNativeWrapperInterface i) {
        int minX = Integer.MAX_VALUE;
        int maxX = -1;
        int minY = Integer.MAX_VALUE;
        int maxY = -1;

        for (int y = 0; y < i.getHeight(); y++) {
            for (int x = 0; x < i.getWidth(); x++) {
                //Retrieving contents of a pixel
                Color color = i.getRGB(x, y);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if (red > 225 && green > 225 && blue > 225) {
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
        if(minX > 99999 || minY > 99999 || maxX < 0 || maxY < 0)
            return new int[]{};
        else
            return new int[]{minX,maxX,minY,maxY};
    }

    private ImageNativeWrapperInterface getLatestImage() {
        if (isSimulation)
            return generateSimulationImage();
        else {
            camera.nextImage();
            return camera;
        }
    }

    private int simImageCount = 0;
    private ImageNativeWrapper generateSimulationImage() {
        String juavSrc = System.getenv("JUAV_SRC");
        ImageNativeWrapper image = null;
        if(simImageCount++>100) {
//            System.out.println("Loading Image "+simImageCount);
            image = new ImageNativeWrapper(juavSrc+"/native-util/png-images/white.png");
        } else {
            image = new ImageNativeWrapper(juavSrc+"/native-util/png-images/black.png");
        }
        return image;
    }

//    public static void main(String[] args) {
//        LandOnWhiteThingRunnable lowr = new LandOnWhiteThingRunnable(true, true);
//
////        for(File f : new File("/home/adamczer/phd/white-things").listFiles()) {
////            System.out.println("**************************");
////            System.out.println(f.getName());
////            System.out.println("--------------------------");
////            BufferedImage image = UtilImageIO.loadImage(f.getAbsolutePath());
////            int[] box = lowr.getBox(image);
////            System.out.println("--------------------------");
////            System.out.println("Has Target : "+lowr.has_target(image));
////            System.out.println("--------------------------");
////            lowr.setNextWaypoint(box, image.getWidth()/2, image.getHeight()/2, null);
////            System.out.println("**************************");
////        }
//        BufferedImage bi = lowr.generateSimulationImage();
//        lowr.writeImage(bi,"test");
//
//
//    }
}
