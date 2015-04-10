package com.polytech4a.smtp.messages.numberheader.server;

import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.NumHeaderMessage;

/**
 * Created by Antoine CARON on 01/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          ServerReady Message of SMTP protocol.
 */
public class ServerReady extends NumHeaderMessage {

    /**
     * Regex of ServerReady.
     */
    private static String regex = "^220 \\S* SMTP Server Ready$";
    /**
     * Server Name.
     */
    private String serverName;


    /**
     * ServerReady building.
     *
     * @param serverName name of the current server.
     */
    public ServerReady(String serverName) {
        super(220, " ");
        this.serverName = serverName;
        this.construct();
    }

    /**
     * Parsing a ServerReady message.
     *
     * @param message message to parse.
     * @throws MalformedMessageException if the message don't have the good structure.
     */
    public ServerReady(Object message) throws MalformedMessageException {
        super(220, " ");
        if (matches((String) message)) {
            this.serverName = ((String) message).split(" ")[1];
            this.construct();
        } else new MalformedMessageException(ServerReady.class.getName(), regex);
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
        message.append(serverName);
        message.append(" SMTP Server Ready");
    }
}
