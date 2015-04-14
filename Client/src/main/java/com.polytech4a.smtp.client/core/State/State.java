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
    private Mail mailToSend;

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

    public int getNbTry() {
        return nbTry;
    }

    public void setNbTry(int nbTry) {
        this.nbTry = nbTry;
    }

    public Mail getMailToSend() {
        return mailToSend;
    }

    public void setMailToSend(Mail mailToSend) {
        this.mailToSend = mailToSend;
    }

    public void incrementNbTry() {
        nbTry++;
    }


    public State(Mail mailToSend) {
        this.mailToSend = mailToSend;
        this.nbTry = 0;
    }

    /**
     * Blank constructor.
     */
    public State(){
        this.nbTry = 0;
    }

    /**
     * Analyzes the message and routes the action. Defines next state.
     *
     * @param message String
     */
    public abstract boolean analyze(String message);

}