package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.numberheader.server.ServerReady;
import com.polytech4a.smtp.messages.textheader.client.EHLO;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateStarted extends State {
    public StateStarted(Mail mailToSend) {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        String serverName;
        if(message == null || !ServerReady.matches(message)){
            incrementNbTry();
            setNextState(new StateQuit(mailToSend));
            setMsgToSend(SMTPMessage.QUIT.toString());
            return true;
        }

        if(ServerReady.matches(message)) {
            try {
                setNextState(new StateEhloConfirm(mailToSend));
                setMsgToSend(new EHLO(InetAddress.getLocalHost().getHostName()).toString());
            } catch (UnknownHostException e) {
                setMsgToSend(new EHLO("Unknown").toString());
            }
            return true;
        }
        return false;
    }
}
