package com.polytech4a.smtp.mailmanager;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class MailParameterSubject extends MailParameter {
    /**
     * Constructor of Subject Parameter
     *
     * @param content : content of the parameter
     */
    public MailParameterSubject(String content) {
        super(content, "SUBJECT:");
    }
}
