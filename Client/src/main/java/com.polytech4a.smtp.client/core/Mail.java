package com.polytech4a.smtp.client.core;

/**
 * Created by Pierre on 08/04/2015.
 * Class
 */
public class Mail {
    private String user;
    private String[] receivers;
    private String mailToSend;

    public Mail(String user, String[] receivers, String mailToSend) {
        this.user = user;
        this.receivers = receivers;
        this.mailToSend = mailToSend;
    }

    public String getUser() {
        return user;
    }

    public String getMailToSend() {
        return mailToSend;
    }

    public String[] getReceivers() {
        return receivers;
    }
}
