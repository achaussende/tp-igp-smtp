package com.polytech4a.smtp.server.state;

import org.apache.log4j.Logger;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.numberheader.server.SigningOff;
import com.polytech4a.smtp.server.Server;

/**
 * Created by Adrien CHAUSSENDE on 30/03/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p>
 *          State of the SMTP Server.
 */
public abstract class State {

    /**
     * Logger
     */
    public static Logger logger = Logger.getLogger(State.class);

    /**
     * Next state in which the object will be.
     */
    private State nextState;
    /**
     * Message to send before going in the next stage.
     */
    private String msgToSend;

    /**
     * Getter of the next state.
     *
     * @return nextState State.
     */
    public State getNextState() {
        return nextState;
    }

    /**
     * Setter of the next state.
     *
     * @param nextState State.
     */
    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    /**
     * Getter of the message to send.
     *
     * @return msgToSend String.
     */
    public String getMsgToSend() {
        return msgToSend;
    }

    /**
     * Setter of message to send.
     *
     * @param msgToSend String.
     */
    public void setMsgToSend(String msgToSend) {
        this.msgToSend = msgToSend;
    }

    /**
     * Blank constructor.
     */
    public State() {

    }

    /**
     * Analyzes the message and routes the action. Defines next state.
     *
     * @param message String
     */
    public abstract boolean analyze(String message);

    /**
     * Handle reception of a Quit message. Return true if the connection can continue, false if not.
     *
     * @param message Received message.
     * @return True if the connection can continue. False if the client disconnected.
     */
    protected boolean handleQuit(String message) {
        if (SMTPMessage.matches(SMTPMessage.QUIT, message)) {
            setMsgToSend(new SigningOff(Server.SERVER_NAME).toString());
            setNextState(new StateInit());
            return false;
        } else {
            return true;
        }
    }
}
