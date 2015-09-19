package org.codebrewery;

/**
 * Created by ejeserl on 9/19/15.
 */
public class JavaOrmenException extends Exception {
    public JavaOrmenException() { super(); }
    public JavaOrmenException(String message) { super(message); }
    public JavaOrmenException(String message, Throwable cause) { super(message, cause); }
    public JavaOrmenException(Throwable cause) { super(cause); }
}