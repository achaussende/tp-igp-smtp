package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;

/**
 * Created by Pierre on 01/04/2015.
 */
public abstract class State {

    /**
     * Next state in which the object will be.
     */
    private State nextState;
    /**
     * Message to send before going in the next stage.
     */
    private String msgToSend;
    /**
     * Number of tries for sending a message
     */
    private int nbTry;

    /**
     * Informations of the mail we want to send
     */
    protected Mail mailToSend;

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



    public void incrementNbTry() {
        nbTry++;
    }

    /**
     * Construxtor with the mail to send.
     * @param mailToSend Mail to send to server.
     */
    public State(Mail mailToSend) {
        mailToSend=mailToSend;
    }

    /**
     * Analyzes the message and routes the action. Defines next state.
     *
     * @param message String
     */
    public abstract boolean analyze(String message);

}