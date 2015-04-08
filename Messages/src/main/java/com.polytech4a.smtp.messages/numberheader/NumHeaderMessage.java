package com.polytech4a.smtp.messages.numberheader;

import com.polytech4a.smtp.messages.Message;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;

/**
 * Created by Antoine CARON on 01/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Abstract Message with a numbered headed message.
 */
public abstract class NumHeaderMessage extends Message {

    /**
     * Regex of a NumHeaderMessage.
     */
    private static String regex = "^\\d{1,}+ |-.*?$";
    /**
     * Number contain in the Header of the message.
     */
    protected Integer number;

    protected String numberSeparator;

    /**
     * Constructor with parameters.
     *
     * @param number Number in the Header.
     */
    public NumHeaderMessage(Integer number, String numberSeparator) {
        this.number = number;
        this.numberSeparator = numberSeparator;
    }

    /**
     * Constructor with a message to Parse.
     *
     * @param message message to Parse.
     * @throws com.polytech4a.smtp.messages.exceptions.MalformedMessageException
     */
    public NumHeaderMessage(String message) throws MalformedMessageException {
        if (NumHeaderMessage.matches(message)) {
            this.number = Integer.parseInt(message.split(" |-")[0]);
            if (message.matches("^\\d{1,} .*$")) {
                numberSeparator = " ";
            } else {
                numberSeparator = "-";
            }
            construct();
        } else throw new MalformedMessageException(NumHeaderMessage.class.getName(), regex);
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param message message to test.
     * @return true if the message matche NumHeaderMessage structure.
     */
    public static boolean matches(String message) {
        return message.matches(regex);
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    protected void construct() {
        message.append(number);
        message.append(numberSeparator);
    }
}
