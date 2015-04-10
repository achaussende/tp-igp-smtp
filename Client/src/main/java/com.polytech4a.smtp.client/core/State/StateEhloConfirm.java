package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.server.EHLOAnswer;
import com.polytech4a.smtp.messages.textheader.client.MAILFROM;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateEhloConfirm extends State{
    private String serverName;

    public StateEhloConfirm(Mail mailToSend, String serverName) throws MalformedEmailException {
        super(mailToSend);
        this.serverName = serverName;
        this.setNextState(this);
        this.setMsgToSend(new MAILFROM(mailToSend.getUser()).getHeader());
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            return false;
        }

        try {
            String oldServerName = new EHLOAnswer((Object)message).getServerName();
            this.setNextState(new StateMail(this.getMailToSend()));
            return serverName == oldServerName;
        } catch (MalformedMessageException e) {
            return false;
        }
    }
}
