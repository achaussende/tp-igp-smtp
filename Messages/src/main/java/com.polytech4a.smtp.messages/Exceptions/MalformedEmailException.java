package com.polytech4a.smtp.messages.exceptions;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          exceptions for malformed email structure.
 */
public class MalformedEmailException extends Exception {
    public MalformedEmailException(String message) {
        super(message);
    }
}
