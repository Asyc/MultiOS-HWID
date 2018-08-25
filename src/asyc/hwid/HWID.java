package asyc.hwid;

import asyc.hwid.exception.UnsupportedOSException;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWID {

    public static String getHWID() throws IOException, UnsupportedOSException, NoSuchAlgorithmException {
        String hwid = "";
        if(OSUtil.getCurrentOS().equals(OS.WINDOWS)){
            hwid =  runCommand("wmic baseboard get serialNumber");
        }else if(OSUtil.getCurrentOS().equals(OS.MAC)){
            String result = runCommand("system_profiler SPHardwareDataType | awk '/Serial/ {print $4}'");
            hwid = result.substring(result.indexOf("Hardware UUID: ") + 15);
        }else if(OSUtil.getCurrentOS().equals(OS.UNKNOWN)){
            throw new UnsupportedOSException();
        }
        if(hwid.isEmpty()){
            throw new NullPointerException();
        }else{
            return hash(hwid);
        }
    }

    private static String hash(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(text.getBytes());
        byte[] bytes = digest.digest();
        return DatatypeConverter.printHexBinary(bytes);
    }

    private static String runCommand(String command) throws IOException{
        String result = "";
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null)
        {
            result += line;
        }
        reader.close();
        if (result.equalsIgnoreCase(" ") || result.isEmpty()) {
            throw new IOException();
        } else
        {
            return result;
        }
    }
}
