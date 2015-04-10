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
        this.setNextState(new StateRcpt(mailToSend));
        this.setMsgToSend(new RCPTTO(mailToSend.getReceivers()[0]).getHeader());
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
