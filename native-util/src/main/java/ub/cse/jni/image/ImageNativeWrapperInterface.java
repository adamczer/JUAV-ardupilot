package ub.cse.jni.image;

public interface ImageNativeWrapperInterface {
    int getHeight();
    int getWidth();
    Color getRGB(int x, int y);
}
