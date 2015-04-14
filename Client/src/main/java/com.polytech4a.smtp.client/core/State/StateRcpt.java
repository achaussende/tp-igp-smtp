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

    //Le premier a déjà été envoyé lors de la transition entre l'état précédent et celui-ci
    private int indexReceivers = 1;


    public StateRcpt(Mail mailToSend) {
        super(mailToSend);
        setNextState(this);
    }

    @Override
    public boolean analyze(String message) {
        if(message == null){
            incrementNbTry();
            return false;
        }

        if(SMTPMessage.matches(SMTPMessage.OK, message) || SMTPMessage.matches(SMTPMessage.NO_SUCH_USER, message)){
            if(!oneValid && SMTPMessage.matches(SMTPMessage.OK, message)){
                oneValid = true;
            }
            if((indexReceivers == this.mailToSend.getReceivers().size()) && oneValid){
                //next State
                this.setNextState(new StateRcpt(this.mailToSend));
                //send DATA
                this.setMsgToSend("");
                return true;
            }
            if((indexReceivers == this.mailToSend.getReceivers().size()) && !oneValid){
                //send error then go to quit state
            }
            else{
                try {
                    this.setMsgToSend(new RCPTTO(this.mailToSend.getReceivers().get(indexReceivers)).getHeader());
                    indexReceivers++;
                    return true;
                } catch (MalformedEmailException e) {
                    return false;
                }
            }
        }
        return false;
    }
}
