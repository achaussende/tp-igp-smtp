package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;

/**
 * Created by Pierre on 08/04/2015.
 */
public class StateMail extends State{


    public StateMail(Mail mailToSend) {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        return false;
    }
}
