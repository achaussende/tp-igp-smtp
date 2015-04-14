package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.SMTPMessage;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Reception state of the SMTP Server.
 */
public class StateReception extends State {

    @Override
    public boolean analyze(String message) {
        boolean keepConnection = handleQuit(message);
        if(!keepConnection)
            return keepConnection;
        //if ('5chars'Message.matches(message))
            setNextState(new StateWaitEnd());
            setMsgToSend(SMTPMessage.OK.toString());
            return true;
        //  Save mail
        //elseif (MailFromUser.matches(message))
            //setNextState(this);
            //setMsgToSend("");
        //elseif (QUIT.matches(message)
    }
}
