package com.polytech4a.smtp.mailmanager.mail;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class ParameterReceiver extends Parameter {
    /**
     * Constructor of the receiver parameter
     *
     * @param content : content of the parameter
     */
    public ParameterReceiver(String content) {
        super(content, "TO:");
    }
}
