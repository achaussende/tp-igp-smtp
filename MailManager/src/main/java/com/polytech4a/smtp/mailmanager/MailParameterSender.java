package com.polytech4a.smtp.mailmanager;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class MailParameterSender extends MailParameter {

    /**
     * Constructor of the sender parameter
     *
     * @param content : content of the parameter
     */
    public MailParameterSender(String content) {
        super(content, "FROM:");
    }
}
