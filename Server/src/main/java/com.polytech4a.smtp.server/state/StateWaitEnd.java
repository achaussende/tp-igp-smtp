package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.server.SigningOff;
import com.polytech4a.smtp.messages.textheader.client.MAILFROM;
import com.polytech4a.smtp.server.Server;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p>
 *          "Waiting end of connection" state for the SMTP Server.
 */
public class StateWaitEnd extends State {
    @Override
    public boolean analyze(String message) {
        boolean keepConnection = handleQuit(message);
        if (!keepConnection)
            return keepConnection;
        if (MAILFROM.matches(message)) {
            try {
                MAILFROM mailFromMessage = new MAILFROM((Object) message);
                setMsgToSend(SMTPMessage.OK.toString());
                setNextState(new StateWaitRCPT(mailFromMessage.getAddress(), new ArrayList<String>()));
                return true;
            } catch (MalformedMessageException e) {
                // TODO : Handle this
                e.printStackTrace();
            }
        } else {
            setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
            setNextState(new StateWaitEnd());
            return true;
        }
        return false;
    }
}
