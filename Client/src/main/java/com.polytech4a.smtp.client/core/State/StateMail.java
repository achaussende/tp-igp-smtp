package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;

/**
 * Created by Pierre on 08/04/2015.
 */
public class StateMail extends State{

    public StateMail(Mail mailToSend) {
        super(mailToSend);
        this.setNextState(new StateRcpt(mailToSend));
        //We send RCPT
        this.setMsgToSend(mailToSend.getUser());
    }

    @Override
    public boolean analyze(String message) {
        if(message == null && !SMTPMessage.matches(SMTPMessage.OK, message)){
            incrementNbTry();
            return false;
        }

        this.setNextState(new StateRcpt(this.getMailToSend()));
        return true;
    }
}
