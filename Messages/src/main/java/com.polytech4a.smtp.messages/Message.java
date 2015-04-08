package com.polytech4a.smtp.messages;

/**
 * Created by Antoine CARON on 01/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Abstract Message of SMTP Protocol.
 */
public abstract class Message {
    /**
     * Message buffer for SMTP Protocol.
     */
    protected StringBuffer message = new StringBuffer();

    /**
     * Construct the message.
     */
    protected abstract void construct();

    @Override
    public String toString() {
        return message.toString();
    }
}
