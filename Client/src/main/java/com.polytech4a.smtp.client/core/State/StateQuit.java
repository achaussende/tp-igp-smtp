package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
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
     * Constructor with the mail to send.
     *
     * @param mailToSend Mail to send to server.
     */
    public StateQuit(Mail mailToSend) {
        super(mailToSend);
    }

    /**
     * Analyzes the message and routes the action. Defines next state.
     *
     * @param message String
     */
    @Override
    public boolean analyze(String message) {
        this.setMsgToSend("");
        this.setNextState(this);
        return false;
    }
}
