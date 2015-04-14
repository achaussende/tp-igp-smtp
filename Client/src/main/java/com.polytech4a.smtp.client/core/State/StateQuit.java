package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.numberheader.server.SigningOff;

/**
 * Created by Antoine CARON on 14/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class StateQuit extends State{
    /**
     * Analyzes the message and routes the action. Defines next state.
     *
     * @param message String
     */
    @Override
    public boolean analyze(String message) {
        if(SigningOff.matches(message)){
            this.setMsgToSend(SMTPMessage.QUIT.toString());
        }else{
            this.setMsgToSend(SMTPMessage.QUIT.toString());
        }
        return false;
    }
}
