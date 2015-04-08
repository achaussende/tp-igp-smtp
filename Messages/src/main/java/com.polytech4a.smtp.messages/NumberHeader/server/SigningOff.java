package com.polytech4a.smtp.messages.numberheader.server;

import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.NumHeaderMessage;

/**
 * Created by Antoine CARON on 03/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class SigningOff extends NumHeaderMessage {

    private static final String regex = "^221 \\S* Service closing transmission channel$";
    /**
     * Name of the server.
     */
    private String serverName;

    /**
     * Builder of SigningOff Message.
     *
     * @param serverName server name.
     */
    public SigningOff(String serverName) {
        super(221, " ");
        this.serverName = serverName;
        construct();
    }

    public SigningOff(Object messagetoParse) throws MalformedMessageException {
        super(221, " ");
        if (SigningOff.matches((String) messagetoParse)) {
            this.serverName = ((String) messagetoParse).split(" ")[1];
            construct();
        } else throw new MalformedMessageException(SigningOff.class.getName(), regex);
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param message message to test.
     * @return true if the message match ServerReady structure.
     */
    public static boolean matches(String message) {
        return message.matches(regex);
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    protected void construct() {
        super.construct();
        this.message.append(serverName);
        this.message.append(" ");
        this.message.append("Service closing transmission channel");
    }

}
