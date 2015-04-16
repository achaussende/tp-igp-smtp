package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.textheader.client.RCPTTO;
import org.apache.log4j.Logger;

/**
 * Created by Pierre on 08/04/2015.
 */
public class StateMail extends State{

    /**
     * Logger.
     */
    private static final Logger logger=Logger.getLogger(StateMail.class);

    public StateMail(Mail mailToSend) throws MalformedEmailException {
        super(mailToSend);
    }

    @Override
    public boolean analyze(String message) {
        if(message == null || !SMTPMessage.matches(SMTPMessage.OK, message)){
            if(SMTPMessage.matches(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS, message)) {
                this.setMsgToSend(SMTPMessage.QUIT.toString());
                this.setNextState(new StateQuit(mailToSend));
                return true;
            }
            return false;
        }else{

            try {
                this.setMsgToSend(new RCPTTO(mailToSend.getReceivers().get(0)).toString());
                this.setNextState(new StateRcpt(mailToSend));
            } catch (MalformedEmailException e) {
                logger.error(e);
            }
            return true;
        }
    }
}
