package asyc.hwid;

public class OSUtil {

    private static String osName = System.getProperty("os.name").toLowerCase();

    public static OS getCurrentOS(){
        if(osName.contains("win")){
            return OS.WINDOWS;
        }else if(osName.contains("mac")){
            return OS.MAC;
        }else if(osName.contains("nix") || osName.contains("nux") || osName.contains("aix")){
            return OS.UNIX;
        }else{
            return OS.UNKNOWN;
        }
    }
}
enum OS{
    WINDOWS, MAC, UNIX, SOLARIS, UNKNOWN
}