package ub.cse.juav.mavlink.messages;

public class CommandSetPositionTargetLocalNed {
      public static byte[] getMessage(int systemId, int componentId, float x, float y, float z) {
          byte[] ret = new byte[53];
          CommandLong.write(0,ret,0,4);
          CommandLong.write(x,ret,4,8);
          CommandLong.write(y,ret,8,12);
          CommandLong.write(z,ret,12,16);
          CommandLong.write(0,ret,16,20);
          CommandLong.write(0,ret,20,24);
          CommandLong.write(0,ret,24,28);
          CommandLong.write(0,ret,28,32);
          CommandLong.write(0,ret,32,36);
          CommandLong.write(0,ret,36,40);
          CommandLong.write(0,ret,40,44);
          CommandLong.write(0,ret,44,48);
          CommandLong.write(4088,ret,48,50);// mask just pos x y z
          CommandLong.write(systemId,ret,50,51);
          CommandLong.write(componentId,ret,51,52);
          // ned relative to frame orientation NED: +x -> forward, +y -> to the right, +z -> down
          CommandLong.write(9,ret,52,53);
          return ret;
      }
}
