package com.polytech4a.smtp.mailmanager;

import com.polytech4a.smtp.mailmanager.exceptions.MailManagerException;

/**
 * Created by Dimitri on 09/03/2015.
 */
public class UserClient extends User {
    public UserClient(String login, String path) throws MailManagerException {
        super(login, "", path);
    }
}
