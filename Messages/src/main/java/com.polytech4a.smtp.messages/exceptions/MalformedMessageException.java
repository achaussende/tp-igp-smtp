package com.polytech4a.smtp.messages.Exceptions;

/**
 * Created by Antoine CARON on 01/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Excpetion fired when a Message don't have the good structure.
 */
public class MalformedMessageException extends Exception {
    public MalformedMessageException(String nameofClass, String regex) {
        super(nameofClass + " don't have the good structure expected: " + regex);
    }
}
