package test.main;

import asyc.hwid.HWID;
import asyc.hwid.exception.UnsupportedOSException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main{

    public static void main(String[] args){
        try {
            System.out.println(HWID.getHWID());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (UnsupportedOSException e){
            System.out.println(e.getMessage());
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
    }
}
