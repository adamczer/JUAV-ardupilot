package ub.cse.jni.image;

public class ImageCameraWrapper implements ImageNativeWrapperInterface{

    private int height;
    private int width;

    public ImageCameraWrapper() {
        System.out.println("Attempting to initialize camera");
        while (!initializeCamera())
            System.out.println("will try opening camera again");

        System.out.println("Successfully initialized camera");
    }

    native private boolean initializeCamera();

    public void nextImage() {
//        System.out.println("taking picture");
        takePicture();
        height = pullHeight();
//        BUG in camera firmware causing white stripe on right most pixels
        width = pullWidth()-1;
//        System.out.println("picture is "+width+"x"+height);
    }

    native private int pullWidth();
    native private int pullHeight();
    private native boolean takePicture();

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public Color getRGB(int x, int y) {
        return new Color(readPixelRed(x,y),readPixelGreen(x,y),readPixelBlue(x,y));
    }

    private native int readPixelRed(int x,int y);
    private native int readPixelGreen(int x,int y);
    private native int readPixelBlue(int x,int y);
}
