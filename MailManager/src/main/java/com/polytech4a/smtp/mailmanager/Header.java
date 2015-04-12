package com.polytech4a.smtp.mailmanager;

import com.polytech4a.smtp.mailmanager.exceptions.MalFormedMailException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dimitri on 04/03/2015.
 * * @version 1.1
 * <p/>
 * Header of POP3 email.
 */
public class Header {

    /**
     * String that contains the header that will be send.
     * Concatenation of the subject, the sending Date, the recipient and the sender of the mail
     */
    private StringBuffer output;

    /**
     * Array that contains all the required parameter for the header
     */
    private ArrayList<MailParameter> parameters;

    /**
     * Constructor for a mail's header
     *
     * @param receiver : String receiver of the mail
     * @param sender   : String sender of the mail
     * @param subject  : String subject of the mail
     */
    public Header(String receiver, String sender, String subject) {
        this.parameters = new ArrayList<MailParameter>();
        this.parameters.add(new MailParameterReceiver(receiver));
        this.parameters.add(new MailParameterSender(sender));
        this.parameters.add(new MailParameterSubject(subject));
        this.parameters.add(new MailParameterDate(new Date().toString()));
        this.output = new StringBuffer();
        buildHeader();
    }

    /**
     * Constructor for the mail Header
     * Initialize the header if the input string contains all the parameters required
     * else throw a MalFormedMailException
     *
     * @param input : String to initialize the header with
     * @throws MalFormedMailException
     */
    public Header(String input) throws MalFormedMailException {
        this.parameters = new ArrayList<MailParameter>();
        this.parameters.add(new MailParameterReceiver(""));
        this.parameters.add(new MailParameterSender(""));
        this.parameters.add(new MailParameterSubject(""));
        this.parameters.add(new MailParameterDate(""));
        this.output = new StringBuffer(input);
        for (MailParameter param : parameters) {
            if (!param.parseParameter(input))
                throw new MalFormedMailException("Header.Header(" + input + "): The input string does not match the mail format for " + param.getClass().toString());
        }
    }

    /**
     * Build the mail Header and all its parameters
     *
     * @return StringBuffer : header built
     */
    public StringBuffer buildHeader() {
        output = new StringBuffer();
        for (MailParameter param : parameters) {
            output.append(param.buildParameter());
        }
        output.append(MailParameter.END_LINE);
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Header) {
            Header header = (Header) o;
            for (int i = 0; i < header.parameters.size(); i++) {
                if (!header.parameters.get(i).equals(this.parameters.get(i))) return false;
            }
            return true;
        }
        return false;
    }
}
