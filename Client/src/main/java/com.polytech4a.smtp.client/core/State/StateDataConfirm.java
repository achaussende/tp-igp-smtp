package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;

/**
 * Created by Pierre on 14/04/2015.
 */
public class StateDataConfirm extends State {
    public StateDataConfirm(Mail mailToSend) {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            this.setMsgToSend(SMTPMessage.QUIT.toString());
            this.setNextState(new StateQuit(mailToSend));
            return true;
        }

        if(SMTPMessage.matches(SMTPMessage.OK, message)){
            this.setMsgToSend(SMTPMessage.QUIT.toString());
            this.setNextState(new StateQuit(mailToSend));
            return true;
        }
        else
            return false;
    }
}
