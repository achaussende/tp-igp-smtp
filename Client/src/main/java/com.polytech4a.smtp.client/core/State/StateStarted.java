package com.polytech4a.smtp.client.core.State;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.server.ServerReady;
import com.polytech4a.smtp.messages.textheader.client.EHLO;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateStarted extends State {
    public StateStarted(Mail mailToSend) throws MalformedEmailException {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        String serverName;
        if(message == null){
            incrementNbTry();
            return false;
        }

        try {
            //Server ready message
            serverName = new ServerReady((Object)message).getServerName();
            this.setNextState(new StateEhloConfirm(mailToSend));
            return true;
        } catch (MalformedMessageException e) {
            return false;
        }
    }
}
