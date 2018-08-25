package asyc.hwid.exception;

public class UnsupportedOSException extends Exception{

    public UnsupportedOSException() {
        super("Current OS is not supported");
    }
}
