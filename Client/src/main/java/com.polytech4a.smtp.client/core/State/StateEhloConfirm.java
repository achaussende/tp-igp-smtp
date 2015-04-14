package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.numberheader.server.EHLOAnswer;
import com.polytech4a.smtp.messages.textheader.client.MAILFROM;
import org.apache.log4j.Logger;

/**
 * Created by Pierre on 01/04/2015.
 */
public class StateEhloConfirm extends State {

    /**
     * Logger.
     */
    private static Logger logger = Logger.getLogger(StateEhloConfirm.class);

    /**
     * Construxtor with the mail to send.
     *
     * @param mailToSend Mail to send to server.
     */
    public StateEhloConfirm(Mail mailToSend) {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        if (message != null) {
            if (EHLOAnswer.matches(message)) {
                try {
                    this.setMsgToSend(new MAILFROM(mailToSend.getUser()).toString());
                    this.setNextState(new StateMail(mailToSend));
                    return true;
                } catch (MalformedEmailException e) {
                    logger.error("Mail From user is Malformed", e);
                }
            } else {
                this.setMsgToSend(SMTPMessage.QUIT.toString());
                this.setNextState(new StateQuit(mailToSend));
                return true;
            }
        }
        return false;
    }
}
