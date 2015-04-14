package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedMessageException;
import com.polytech4a.smtp.messages.numberheader.server.SigningOff;
import com.polytech4a.smtp.messages.textheader.client.RCPTTO;
import com.polytech4a.smtp.server.Server;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Waiting reception message state of the SMTP Server.
 */
public class StateWaitRCPT extends State {

    //private User user;

    @Override
    public boolean analyze(String message) {
        //if(RCPTMessage.matches(message)
        //  RCPTMessage rcpt = new RCPTMessage(message);
        //  User usr = new User(rcpt.getUser());
        //  if (manager.userExists(user))
        //      manager.lockUser(usr);
        boolean keepConnection = handleQuit(message);
        if(!keepConnection)
            return keepConnection;
        boolean usr = true;
        if (RCPTTO.matches(message)) {
            if (usr) {
                try {
                    RCPTTO rcpt = new RCPTTO((Object) message);
                    setMsgToSend(SMTPMessage.OK.toString());
                    setNextState(new StateWaitRCPT());
                    return true;
                } catch (MalformedMessageException e) {
                    e.printStackTrace();
                }
            } else {
                setMsgToSend(SMTPMessage.NO_SUCH_USER.toString());
                setNextState(new StateWaitRCPT());
                return true;
            }
        } else if (SMTPMessage.matches(SMTPMessage.DATA, message)) {
            //If usr is valid
            if (usr) {
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
