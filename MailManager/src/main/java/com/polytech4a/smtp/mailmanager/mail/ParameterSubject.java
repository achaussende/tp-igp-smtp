package com.polytech4a.smtp.mailmanager.mail;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class ParameterSubject extends Parameter {
    /**
     * Constructor of Subject Parameter
     *
     * @param content : content of the parameter
     */
    public ParameterSubject(String content) {
        super(content, "SUBJECT:");
    }
}
