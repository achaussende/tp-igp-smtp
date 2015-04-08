package com.polytech4a.smtp.messages.textheader.client;

import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.textheader.TextHeaderMessage;

/**
 * Created by Antoine CARON on 01/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          EHLO message of SMTP protocol.
 */
public class EHLO extends TextHeaderMessage {
    private final static String regex = "^EHLO \\S*$";
    /**
     * Client Name.
     */
    private String clientName;

    /**
     * @param clientName
     */
    public EHLO(String clientName) {
        super("EHLO");
        this.clientName = clientName;
        construct();
    }

    public EHLO(Object message) throws MalformedMessageException {
        super("EHLO");
        if (matches((String) message)) {
            this.clientName = ((String) message).split(" ")[1];
            construct();
        } else throw new MalformedMessageException(EHLO.class.getName(), regex);
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

    public String getClientName() {
        return clientName;
    }

    @Override
    protected void construct() {
        super.construct();
        this.message.append(clientName);
    }
}
