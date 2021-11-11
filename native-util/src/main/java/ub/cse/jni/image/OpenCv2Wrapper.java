package ub.cse.jni.image;

public class OpenCv2Wrapper {

    private final boolean isSimulation;
    private int height;
    private int width;

    public OpenCv2Wrapper(boolean isSimulation, int binerizeThresholdLow, int binerizeThresholdHigh) {
        this.isSimulation = isSimulation;
        if(!isSimulation) {
            System.out.println("Attempting to initialize camera");
            while (!initializeCamera())
                System.out.println("will try opening camera again");
            System.out.println("Successfully initialized camera");
        } else {
            System.out.println("This is simulation, nothing to initialize");
        }
        setBinerizeThreshold(binerizeThresholdLow,binerizeThresholdHigh);
    }

    private native void setBinerizeThreshold(int binerizeThresholdLow, int binerizeThresholdHigh);

    native private boolean initializeCamera();

    public void nextCameraImage() {
        takePicture();
        height = pullHeight();
//        BUG in camera firmware causing white stripe on right most pixels
        width = pullWidth()-1;
    }

    native private int pullWidth();
    native private int pullHeight();
    private native boolean takePicture();

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void loadFile(String pathToImage) {
        loadImageFile(pathToImage);
        height = pullHeight();
        width = pullWidth();
    }

    native private boolean loadImageFile(String pathToImage);

    public int getPixelVal(int x, int y) {
        return getBinerizeValue(x,y);
    }

    private native int getBinerizeValue(int x, int y);

    public int[] getCentroid() {
        int[] xy = new int[2];
        xy[0] = getCentroidX();
        xy[1] = getCentroidY();
        return xy;
    }

    private native int getCentroidX();
    private native int getCentroidY();
}
