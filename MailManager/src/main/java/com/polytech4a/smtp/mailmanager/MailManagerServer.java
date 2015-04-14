package com.polytech4a.smtp.mailmanager;

import com.polytech4a.smtp.mailmanager.exceptions.MailManagerException;
import com.polytech4a.smtp.mailmanager.exceptions.MalFormedMailException;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Dimitri on 07/03/2015.
 *
 * @version 1.1
 *          <p/>
 *          Server Mail Manager for POP3.
 */
public class MailManagerServer extends MailManager {

    /**
     * List of users of the MailManager
     */
    protected ArrayList<UserServer> users;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initDirectory() throws MailManagerException {
        File userFolder = new File(path),
                userMailFolder = new File(path + "Server_mails/"),
                userLoginsFile = new File(path + "Server_mails/logins.txt");
        try {
            if (userFolder.exists() && !userMailFolder.exists()) {
                if (!(userMailFolder.mkdir() && userLoginsFile.createNewFile())) {
                    throw new MailManagerException("MailManager.initDirectory : Could not init directories at " + path);
                }
            } else if (userFolder.exists() && userMailFolder.exists()) {
                if (!userLoginsFile.exists() && !userLoginsFile.createNewFile()) {
                    throw new MailManagerException("MailManager.initDirectory : Could not init directories at " + path);
                }
            } else if (!userFolder.exists())
                if (!(userFolder.mkdirs() && userMailFolder.mkdir() && userLoginsFile.createNewFile())) {
                    throw new MailManagerException("MailManager.initDirectory : Could not init directories at " + path);
                }
        } catch (SecurityException se) {
            throw new MailManagerException("MailManager.initDirectory : Could not create folders at " + path);
        } catch (IOException e) {
            throw new MailManagerException("MailManager.initDirectory : Could not create logins.txt at " + path + "Server_mails/");
        }
        path += "Server_mails/";
    }

    /**
     * Initialize the list of Users in a directory
     *
     * @return list of Users
     */
    protected ArrayList<UserServer> initUsers() throws MailManagerException {
        ArrayList<UserServer> users = new ArrayList<UserServer>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path + "logins.txt")));
            String line;
            String[] identification;

            while ((line = br.readLine()) != null) {
                identification = line.split(" ");
                if (identification.length == 2) {
                    users.add(new UserServer(identification[0], identification[1], path));
                }
            }
            br.close();
            return users;
        } catch (IOException e) {
            throw new MailManagerException("MailManager.getUsers : Can't open file : " + path + "logins.txt");
        } catch (MailManagerException e) {
            throw e;
        }
    }

    /**
     * Constructor of ServerMailManager
     */
    public MailManagerServer(String path) throws MailManagerException {
        super(path);
        this.users = initUsers();
    }

    /**
     * Get user's mails
     *
     * @param login    : String of the user's login
     * @param password : String of the user's password
     * @return Initialized user.
     */
    public UserServer getUser(String login, String password) {
        for (UserServer u : users) {
            if (u.getLogin().equals(login)) {
                try {
                    u.initMails();
                    return u;
                } catch (MalFormedMailException e) {
                    System.out.println("MailManager.getUserMails : couldn't get user " + u.getLogin() + " mails : " + e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * Save user into logins.txt
     *
     * @param user User to save
     * @throws MailManagerException
     */
    public void saveUser(UserServer user) throws MailManagerException {
        try {
            FileWriter file = new FileWriter(path + "/logins.txt", true);
            file.write(user.getLogin() + " " + user.getPassword() + "\n");
            file.close();
            users.add(user);
        } catch (IOException e) {
            throw new MailManagerException("MailManagerServer.saveMails : can't open/write in " + path + "/logins.txt");
        }
    }

    /**
     * Save user's mails and add him into logins.txt if necessary
     *
     * @param user user to save
     * @throws MailManagerException
     */
    @Override
    public void saveMails(User user) throws MailManagerException {
        if (user instanceof UserServer) {
            UserServer user2 = (UserServer) user;
            user2.saveMails();
            if (getUser(user2.getLogin(), user2.getPassword()) == null) {
                saveUser(user2);
            }
        } else {
            throw new MailManagerException("MailManagerServer.saveMail : user must be an UserServer");
        }

    }

    public ArrayList<UserServer> getUsers() {
        return users;
    }
}
