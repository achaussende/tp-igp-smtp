package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.mailmanager.FacadeServer;
import com.polytech4a.smtp.mailmanager.exceptions.MailManagerException;
import com.polytech4a.smtp.mailmanager.exceptions.MalFormedMailException;
import com.polytech4a.smtp.mailmanager.exceptions.UnknownUserException;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.server.Server;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p>
 *          Reception state of the SMTP Server.
 */
public class StateReception extends State {

    private ArrayList<String> toUsers;

    public StateReception(ArrayList<String> toUsers) {
        this.toUsers = toUsers;
    }

    @Override
    public boolean analyze(String message) {
        boolean keepConnection = handleQuit(message);
        if (!keepConnection)
            return keepConnection;
        //Maybe need to test if their are the 5 end mail's char.
            try {
                for(String toUser : toUsers) {
                    FacadeServer.saveMail(message, Server.MAIL_DIRECTORY, toUser);
                }
            } catch (MailManagerException e) {
                //TODO : Handle this.
                e.printStackTrace();
            } catch (MalFormedMailException e) {
                logger.error(e.getMessage());
                setMsgToSend(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS.toString());
                setNextState(new StateReception(toUsers));
                return true;
            } catch (UnknownUserException e) {
                logger.error(e.getMessage());
                setMsgToSend(SMTPMessage.NO_SUCH_USER.toString());
                setNextState(new StateReception(toUsers));
                return true;
            }
        setNextState(new StateWaitEnd());
        setMsgToSend(SMTPMessage.OK.toString());
        return true;
    }
}
