package com.polytech4a.smtp.client.core.State;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateEhloConfirm extends State{

    @Override
    public boolean analyze(String message) {
        return false;
    }
}
