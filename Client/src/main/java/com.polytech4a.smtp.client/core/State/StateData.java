package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;

/**
 * Created by Pierre on 14/04/2015.
 */
public class StateData extends State {

    public StateData(Mail mailToSend) {
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

        if(SMTPMessage.matches(SMTPMessage.START_MAIL_INPUT, message)){
            this.setMsgToSend(this.mailToSend.getMailToSend());
            this.setNextState(new StateDataConfirm(mailToSend));
            return true;
        }
        else
            return false;
    }
}
