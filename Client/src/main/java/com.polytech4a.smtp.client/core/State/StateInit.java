package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;

/**
 * Created by Adrien CHAUSSENDE on 14/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Client Initialization state.
 */
public class StateInit extends State {

    public StateInit(Mail mailToSend) {
        super(mailToSend);
        setNextState(new StateStarted(mailToSend));
        setMsgToSend("");
    }

    @Override
    public boolean analyze(String message) {
        return true;
    }
}
