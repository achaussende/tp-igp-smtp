package com.polytech4a.smtp.client.core.State;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateStarted extends State {
    public StateStarted(){
        super();
        this.setNextState(new StateEhloConfirm());
        //TODO Envoi de EHLO nom_client
        this.setMsgToSend("");
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            return false;
        }
        //TODO Reception de 250 Mail Protocol...
        if(true){
            return true;
        }
        return false;
    }
}
