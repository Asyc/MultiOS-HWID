package asyc.hwid;

import asyc.hwid.exception.UnsupportedOSException;

final class OSUtil {

    private static String osName = System.getProperty("os.name").toLowerCase();

    public static OS getCurrentOS() throws UnsupportedOSException {
        if(osName.contains("win")){
            return OS.WINDOWS;
        }else if(osName.contains("mac")){
            return OS.MAC;
        }else if(osName.contains("nix") || osName.contains("nux") || osName.contains("aix")){
            return OS.UNIX;
        }else{
            throw new UnsupportedOSException();
        }
    }
}
enum OS{
    WINDOWS, MAC, UNIX
}
