package com.polytech4a.smtp.messages.textheader.client;

import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.textheader.TextHeaderMessage;

/**
 * Created by Antoine CARON on 10/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          RCPT TO <adress> message form SMTP Protocol.
 */
public class RCPTTO extends TextHeaderMessage {

    /**
     * Regex of a RCPTTO Message.
     */
    private static final String regex = "^RCPT TO:<[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+>$";

    /**
     * Regex for Mail.
     */
    private final static String mailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+";

    /**
     * Address of the sender.
     */
    private String address;

    /**
     * Builder of a RCPTTO Message.
     *
     * @param address mail address of the sender.
     */
    public RCPTTO(String address) throws MalformedEmailException {
        super("RCPT");
        if (address.matches(mailRegex)) {
            this.address = address;
            this.construct();
        } else
            throw new MalformedEmailException(address + "is not a well structure email address expect: " + mailRegex);
    }

    /**
     * Parser of a RCPTTO Message.
     *
     * @param messageToParse message receive to parse.
     */
    public RCPTTO(Object messageToParse) throws MalformedMessageException {
        super("RCPT");
        if (RCPTTO.matches((String) messageToParse)) {
            this.address = ((String) messageToParse).
                    substring(((String) messageToParse).indexOf("<") + 1, ((String) messageToParse).indexOf(">"));
            construct();
        } else throw new MalformedMessageException(MAILFROM.class.getName(), regex);
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param message message to test.
     * @return true if the message matches RCPT TO structure.
     */
    public static boolean matches(String message) {
        return message.matches(RCPTTO.regex);
    }

    @Override
    protected void construct() {
        super.construct();
        this.message.append("TO:<");
        this.message.append(address);
        this.message.append(">");
    }
}
