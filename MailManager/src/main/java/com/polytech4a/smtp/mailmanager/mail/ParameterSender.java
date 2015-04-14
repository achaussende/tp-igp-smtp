package com.polytech4a.smtp.mailmanager.mail;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class ParameterSender extends Parameter {

    /**
     * Constructor of the sender parameter
     *
     * @param content : content of the parameter
     */
    public ParameterSender(String content) {
        super(content, "FROM:");
    }
}
