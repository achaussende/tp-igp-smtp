package com.polytech4a.smtp.messages.textheader.client;

import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.textheader.TextHeaderMessage;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          MAIL FROM Message define in SMTP Protocol.
 */
public class MAILFROM extends TextHeaderMessage {

    /**
     * Regex of a MAILFROM Message.
     */
    private static final String regex = "^MAIL FROM:<[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+>$";

    /**
     * Regex for Mail.
     */
    private final static String mailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+";

    /**
     * Address of the sender.
     */
    private String address;

    public String getAddress() {
        return address;
    }

    /**
     * Builder of a MAILFROM Message.
     *
     * @param address mail address of the sender.
     */
    public MAILFROM(String address) throws MalformedEmailException {
        super("MAIL");
        if (address.matches(mailRegex)) {
            this.address = address;
            this.construct();
        } else
            throw new MalformedEmailException(address + "is not a well structure email address expect: " + mailRegex);
    }

    /**
     * Parser of a MAILFROM Message.
     *
     * @param messageToParse message receive to parse.
     */
    public MAILFROM(Object messageToParse) throws MalformedMessageException {
        super("MAIL");
        if (MAILFROM.matches((String) messageToParse)) {
            this.address = ((String) messageToParse).
                    substring(((String) messageToParse).indexOf("<") + 1, ((String) messageToParse).indexOf(">"));
            construct();
        } else throw new MalformedMessageException(MAILFROM.class.getName(), regex);
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param message message to test.
     * @return true if the message matche MailFrom structure.
     */
    public static boolean matches(String message) {
        return message.matches(MAILFROM.regex);
    }

    @Override
    protected void construct() {
        super.construct();
        this.message.append("FROM:<");
        this.message.append(address);
        this.message.append(">");
    }
}
