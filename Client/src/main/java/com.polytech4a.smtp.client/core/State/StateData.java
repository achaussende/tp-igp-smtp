package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;

/**
 * Created by Pierre on 14/04/2015.
 */
public class StateData extends State {

    public StateData(Mail mailToSend) {
        super(mailToSend);
        this.setNextState(this);
        this.setMsgToSend(this.getMailToSend().getMailToSend());
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            return false;
        }

        if(SMTPMessage.matches(SMTPMessage.START_MAIL_INPUT, message)){
            this.setNextState(new StateDataConfirm());
            return true;
        }
        else
            return false;
    }
}