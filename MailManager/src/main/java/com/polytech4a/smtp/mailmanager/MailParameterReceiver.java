package com.polytech4a.smtp.mailmanager;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class MailParameterReceiver extends MailParameter {
    /**
     * Constructor of the receiver parameter
     *
     * @param content : content of the parameter
     */
    public MailParameterReceiver(String content) {
        super(content, "TO:");
    }
}
