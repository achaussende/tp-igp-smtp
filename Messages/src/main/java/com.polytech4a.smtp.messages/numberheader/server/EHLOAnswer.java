package com.polytech4a.smtp.messages.numberheader.server;

import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.NumHeaderMessage;

/**
 * Created by Antoine CARON on 08/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          EHLO Answer from server to client in SMTP Protocol.
 */
public class EHLOAnswer extends NumHeaderMessage {

    private static final String regex = "^250 \\S$";
    /**
     * Name of the server.
     */
    private String serverName;

    public EHLOAnswer(String serverName) {
        super(250, " ");
        this.serverName = serverName;
        this.construct();
    }

    public EHLOAnswer(Object messageToParse) throws MalformedMessageException {
        super(250, " ");
        if (EHLOAnswer.matches((String) messageToParse)) {
            this.serverName = ((String) messageToParse).split(" ")[1];
            this.construct();
        } else throw new MalformedMessageException(EHLOAnswer.class.getName(), regex);
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param message message to test.
     * @return true if the message match EHLOAnswer structure.
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
    }
}
