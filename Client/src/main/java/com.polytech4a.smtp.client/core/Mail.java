package com.polytech4a.smtp.client.core;

import java.util.ArrayList;

/**
 * Created by Pierre on 08/04/2015.
 * Class
 */
public class Mail {
    private String user;
    private ArrayList<String> receivers;
    private String mailToSend;
    private String object;

    public Mail(String user, ArrayList<String> receivers, String mailToSend,String object) {
        this.user = user;
        this.receivers = receivers;
        this.mailToSend = mailToSend;
        this.object=object;
    }

    public String getUser() {
        return user;
    }

    public String getMailToSend() {
        return mailToSend;
    }

    public ArrayList<String> getReceivers() {
        return receivers;
    }

    public String getObject() {
        return object;
    }
}
