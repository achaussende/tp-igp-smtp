package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.textheader.client.MAILFROM;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Waiting mail state of the SMTP Server.
 */
public class StateWaitMail extends State {
    @Override
    public boolean analyze(String message) {
        boolean keepConnection = handleQuit(message);
        if(!keepConnection)
            return keepConnection;
        if(MAILFROM.matches(message)) {
            try {
                MAILFROM mailFromMessage = new MAILFROM((Object) message);
                String adress = mailFromMessage.getAddress();
                //TODO : Pass adress to states that need it.
                setNextState(new StateWaitRCPT());
                setMsgToSend(SMTPMessage.OK.toString());
                return true;
            } catch (MalformedMessageException e) {
                e.printStackTrace();
            }
        } else {
            setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
            setNextState(this);
            return true;
        }
        return true;
    }
}
