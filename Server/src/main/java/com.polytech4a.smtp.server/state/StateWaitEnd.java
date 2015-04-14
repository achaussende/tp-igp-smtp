package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.numberheader.server.SigningOff;
import com.polytech4a.smtp.messages.textheader.client.MAILFROM;
import com.polytech4a.smtp.server.Server;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          "Waiting end of connection" state for the SMPT Server.
 */
public class StateWaitEnd extends State {
    @Override
    public boolean analyze(String message) {
        boolean keepConnection = handleQuit(message);
        if(!keepConnection)
            return keepConnection;
        if (MAILFROM.matches(message)) {
            setMsgToSend(SMTPMessage.OK.toString());
            setNextState(new StateWaitRCPT());
            return true;
        } else {
            setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
            setNextState(new StateInit());
            return false;
        }
    }
}
