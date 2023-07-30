package dev.dubhe.askway.origin.exception;

public class CustomRegistryEntryRepeatException extends RuntimeException {
    public CustomRegistryEntryRepeatException() {
        super("Registry contains duplicate registry entries !");
    }
}
