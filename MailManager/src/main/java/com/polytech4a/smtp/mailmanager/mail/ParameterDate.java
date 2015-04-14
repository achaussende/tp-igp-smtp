package com.polytech4a.smtp.mailmanager.mail;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class ParameterDate extends Parameter {
    /**
     * Constructor of the date parameter
     *
     * @param content : content of the parameter
     */
    public ParameterDate(String content) {
        super(content, "ORIG-DATE:");
    }
}
