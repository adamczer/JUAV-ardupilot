package ub.cse.jni.image;

public class ImageNativeWrapper implements ImageNativeWrapperInterface{
    private int height;
    private int width;

    private native boolean loadImageFile(String path);
    public ImageNativeWrapper(String path) {
        boolean worked = loadImageFile(path);
        if(!worked)
            throw new IllegalArgumentException();
        populateInfo();
    }

    private void populateInfo() {
        this.width = pullWidth();
        this.height = pullHeight();
    }

    native private int pullWidth();
    native private int pullHeight();

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private native int readPixelRed(int x,int y);
    private native int readPixelGreen(int x,int y);
    private native int readPixelBlue(int x,int y);

    public Color getRGB(int x, int y) {
//        System.out.println("getting pixel x,y= "+x+","+"y");
        return new Color(readPixelRed(x,y),readPixelGreen(x,y),readPixelBlue(x,y));
    }

    public static void main(String[] args){

        System.out.println("Loading lib");
        System.loadLibrary("NativeUtil");
        System.out.println("lib loaded");
        for(int i = 0; i< 300; i++) {
            ImageNativeWrapper bi = new ImageNativeWrapper("png-images/ll.png");
            System.out.println(bi.getHeight());
            System.out.println(bi.getWidth());
            System.out.println(bi.getRGB(10, 10));

            bi = new ImageNativeWrapper("png-images/lr.png");
            System.out.println(bi.getHeight());
            System.out.println(bi.getWidth());
            System.out.println(bi.getRGB(10, 10));

            bi = new ImageNativeWrapper("png-images/ul.png");
            System.out.println(bi.getHeight());
            System.out.println(bi.getWidth());
            System.out.println(bi.getRGB(10, 10));

            bi = new ImageNativeWrapper("png-images/ur.png");
            System.out.println(bi.getHeight());
            System.out.println(bi.getWidth());
            System.out.println(bi.getRGB(10, 10));
        }
    }
}
