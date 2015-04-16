package com.polytech4a.smtp.client.core.State;

import com.polytech4a.smtp.client.core.Mail;
import com.polytech4a.smtp.messages.SMTPMessage;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import com.polytech4a.smtp.messages.textheader.client.RCPTTO;

/**
 * Created by Pierre on 08/04/2015.
 */
public class StateRcpt extends State {
    private boolean oneValid = false;

    //Le premier a d�j� �t� envoy� lors de la transition entre l'�tat pr�c�dent et celui-ci
    private int indexReceivers = 1;


    public StateRcpt(Mail mailToSend) {
        super(mailToSend);
        setNextState(this);
    }

    /**
     * Several cases, to got to the next state, we must have received one confirmation at least and have no other user
     */
    @Override
    public boolean analyze(String message) {
        if(message == null || !(SMTPMessage.matches(SMTPMessage.OK, message) || SMTPMessage.matches(SMTPMessage.NO_SUCH_USER, message))){
            if(SMTPMessage.matches(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS, message)) {
                this.setMsgToSend(SMTPMessage.QUIT.toString());
                this.setNextState(new StateQuit(mailToSend));
                return true;
            }
            return false;
        }

        else{
            if(!oneValid && SMTPMessage.matches(SMTPMessage.OK, message)){
                oneValid = true;
            }
            if((indexReceivers == this.mailToSend.getReceivers().size()) && oneValid){
                this.setNextState(new StateData(this.mailToSend));
                this.setMsgToSend(SMTPMessage.DATA.toString());
                return true;
            }
            if((indexReceivers == this.mailToSend.getReceivers().size()) && !oneValid){
                this.setNextState(new StateQuit(mailToSend));
                this.setMsgToSend(SMTPMessage.QUIT.toString());
                return true;
            }
            else{
                try {
                    this.setMsgToSend(new RCPTTO(this.mailToSend.getReceivers().get(indexReceivers)).toString());
                    indexReceivers++;
                    return true;
                } catch (MalformedEmailException e) {
                    return false;
                }
            }
        }
    }
}
