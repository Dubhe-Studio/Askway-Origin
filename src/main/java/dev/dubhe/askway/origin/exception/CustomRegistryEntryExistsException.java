package dev.dubhe.askway.origin.exception;

public class CustomRegistryEntryExistsException extends RuntimeException {
    public CustomRegistryEntryExistsException() {
        super("The registry not contains this entry !");
    }
}
