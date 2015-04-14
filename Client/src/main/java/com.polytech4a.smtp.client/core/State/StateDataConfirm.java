package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.messages.SMTPMessage;

/**
 * Created by Pierre on 14/04/2015.
 */
public class StateDataConfirm extends State {
    public StateDataConfirm() {
        super();
        this.setNextState(this);
        this.setMsgToSend(SMTPMessage.QUIT.toString());
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            return false;
        }

        if(SMTPMessage.matches(SMTPMessage.OK, message)){
            this.setNextState(new StateQuit());
            return true;
        }
        else
            return false;
    }
}
