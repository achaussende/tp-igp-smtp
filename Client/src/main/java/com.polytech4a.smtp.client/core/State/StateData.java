package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.mailmanager.FacadeClient;
import com.polytech4a.smtp.mailmanager.exceptions.MailManagerException;
import com.polytech4a.smtp.mailmanager.exceptions.MalFormedMailException;
import com.polytech4a.smtp.messages.SMTPMessage;
import org.apache.log4j.Logger;

/**
 * Created by Pierre on 14/04/2015.
 */
public class StateData extends State {
    private static final Logger logger = Logger.getLogger(StateData.class);

    public StateData(Mail mailToSend) {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            this.setMsgToSend(SMTPMessage.QUIT.toString());
            this.setNextState(new StateQuit(mailToSend));
            return true;
        }

        if(SMTPMessage.matches(SMTPMessage.START_MAIL_INPUT, message)){
            try {
                String mail = FacadeClient.createMail(mailToSend.getUser(), mailToSend.getReceivers().get(0), mailToSend.getMailToSend(), mailToSend.getObject());
                FacadeClient.saveMail(mail, mailToSend.getUser() ,"OUTPUT/Client/");
                this.setMsgToSend(mail);
                this.setNextState(new StateDataConfirm(mailToSend));
                return true;
            } catch (MalFormedMailException e) {
                logger.error("Wrong message to send", e);
                this.setMsgToSend(SMTPMessage.QUIT.toString());
                this.setNextState(new StateQuit(mailToSend));
                return true;
            } catch (MailManagerException e) {
                logger.warn("Error in the mail manager", e);
                this.setNextState(new StateDataConfirm(mailToSend));
                return true;
            }
        }
        else
            return false;
    }
}
