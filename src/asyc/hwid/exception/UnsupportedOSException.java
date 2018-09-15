package asyc.hwid.exception;

public final class UnsupportedOSException extends Exception{

    public UnsupportedOSException() {
        super("Current OS is not supported");
    }
}
