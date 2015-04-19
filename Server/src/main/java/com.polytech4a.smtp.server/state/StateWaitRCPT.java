package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.mailmanager.FacadeServer;
import com.polytech4a.smtp.mailmanager.exceptions.MailManagerException;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.server.SigningOff;
import com.polytech4a.smtp.messages.textheader.client.RCPTTO;
import com.polytech4a.smtp.server.Server;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p>
 *          Waiting reception message state of the SMTP Server.
 */
public class StateWaitRCPT extends State {

    private String fromUser;

    private ArrayList<String> toUsers;

    public StateWaitRCPT(String fromUser, ArrayList<String> toUsers) {
        this.fromUser = fromUser;
        this.toUsers = toUsers;
    }

    @Override
    public boolean analyze(String message) {
        boolean keepConnection = handleQuit(message);
        if (!keepConnection)
            return keepConnection;
        if (RCPTTO.matches(message)) {
            try {
                RCPTTO rcpt = new RCPTTO((Object) message);
                String toAdress = rcpt.getAddress();
                if (FacadeServer.existsUser(toAdress, Server.MAIL_DIRECTORY)) {
                    toUsers.add(toAdress);
                    setMsgToSend(SMTPMessage.OK.toString());
                    setNextState(new StateWaitRCPT(fromUser, toUsers));
                    return true;

                } else {
                    setMsgToSend(SMTPMessage.NO_SUCH_USER.toString());
                    setNextState(new StateWaitRCPT(fromUser, toUsers));
                    return true;
                }

            } catch (MalformedMessageException e) {
                //TODO : Handle these exceptions
                e.printStackTrace();
            } catch (MailManagerException e) {
                e.printStackTrace();
            }

        } else if (SMTPMessage.matches(SMTPMessage.DATA, message)) {
            //If usr is valid
            if (toUsers != null && toUsers.size() > 0) {
                setMsgToSend(SMTPMessage.START_MAIL_INPUT.toString());
                setNextState(new StateReception());
                return true;
            } else {
                setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
                setNextState(new StateInit());
                return true;
            }
        } else {
            setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
            setNextState(this);
            return true;
        }
        return true;
    }
}
