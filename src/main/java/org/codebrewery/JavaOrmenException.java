package org.codebrewery;

/**
 * Created by ejeserl on 9/19/15.
 */
public class JavaOrmenException extends Exception {
    private static final long serialVersionUID = -2687061472385947476L;
    public JavaOrmenException() { super(); }
    public JavaOrmenException(String message) { super(message); }
    public JavaOrmenException(String message, Throwable cause) { super(message, cause); }
    public JavaOrmenException(Throwable cause) { super(cause); }
}