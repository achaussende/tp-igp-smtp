package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.textheader.client.RCPTTO;

/**
 * Created by Pierre on 08/04/2015.
 */
public class StateMail extends State{

    public StateMail(Mail mailToSend) throws MalformedEmailException {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        if(message == null && !SMTPMessage.matches(SMTPMessage.OK, message)){
            incrementNbTry();
            return false;
        }
        this.setNextState(new StateRcpt(mailToSend));
        return true;
    }
}
