package com.polytech4a.smtp.mailmanager;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class MailParameterDate extends MailParameter {
    /**
     * Constructor of the date parameter
     *
     * @param content : content of the parameter
     */
    public MailParameterDate(String content) {
        super(content, "ORIG-DATE:");
    }
}
