package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.numberheader.server.EHLOAnswer;
import com.polytech4a.smtp.messages.textheader.client.EHLO;
import com.polytech4a.smtp.server.Server;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Starting state of the SMTP Server.
 */
public class StateStart extends State {


    @Override
    public boolean analyze(String message) {
        if (EHLO.matches(message)) {
            setNextState(new StateWaitMail());
            setMsgToSend(new EHLOAnswer(Server.SERVER_NAME).toString());
            return true;
        } else {
            setNextState(new StateStart());
            setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
            return true;
        }
    }
}
