package com.polytech4a.smtp.messages.TextHeader;

import com.polytech4a.smtp.messages.Exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.Message;

/**
 * Created by Antoine CARON on 01/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Abstract Text Headed Message for SMTP Protocol.
 */
public abstract class TextHeaderMessage extends Message {

    /**
     * Regex of a NumHeaderMessage.
     */
    private static String regex = "\\d{1,4} .*";
    /**
     * String header of the messsage.
     */
    private String header;

    /**
     * Constructor with parameters.
     *
     * @param header header Text.
     */
    public TextHeaderMessage(String header) {
        this.header = header;
        construct();
    }

    /**
     * Constructor with a message to Parse.
     *
     * @param message message to Parse. (String)
     * @throws com.polytech4a.smtp.messages.Exceptions.MalformedMessageException
     */
    public TextHeaderMessage(Object message) throws MalformedMessageException {
        if (TextHeaderMessage.matches((String) message)) {
            this.header = ((String) message).split(" ")[0];
            construct();
        } else throw new MalformedMessageException(TextHeaderMessage.class.getName(), regex);
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

    public String getHeader() {
        return header;
    }

    @Override
    public void construct() {
        message.append(header);
        message.append(" ");
    }
}
