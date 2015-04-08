package com.polytech4a.smtp.client.core.State;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.numberheader.server.ServerReady;
import com.polytech4a.smtp.messages.textheader.client.EHLO;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateStarted extends State {
    public StateStarted(Mail mailToSend){
        super(mailToSend);
        this.setNextState(new StateEhloConfirm(mailToSend));

        String computerName;
        try {
            computerName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            computerName = "Unknown";
        }
        this.setMsgToSend(new EHLO(computerName).getHeader());
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            return false;
        }

        return ServerReady.matches(message);
    }
}
