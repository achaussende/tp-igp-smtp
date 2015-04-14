package com.polytech4a.smtp.mailmanager;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Dimitri on 14/04/2015.
 */
public class MailManagerTest {
    MailManagerClient client;
    MailManagerServer server;

    @Before
    public void setUp() throws Exception {
        client = new MailManagerClient("a.caron@a.fr", "../OUTPUT/Client/");
        server = new MailManagerServer("../OUTPUT/Server/");
    }

    @Test
    public void testInitUsers() throws Exception {
        testSaveMails();
        UserServer user = new UserServer("d.rodarie@a.fr", "test2", "../OUTPUT/Server/Server_mails/");
        UserServer user2 = new UserServer("a.caron@a.fr", "test", "../OUTPUT/Server/Server_mails/");

        //Test InitUsers
        ArrayList<UserServer> server_expected = new ArrayList<UserServer>();
        server_expected.add(user);
        server_expected.add(user2);
        server.initUsers();

        //Remove all the common mail to see differences
        assert (server.getUsers().size() == server_expected.size());
        for (int i = 0; i < server.getUsers().size(); i++) {
            assert (server.getUsers().get(i).path.equals(server_expected.get(i).path) &&
                    server.getUsers().get(i).login.equals(server_expected.get(i).login) &&
                    server.getUsers().get(i).password.equals(server_expected.get(i).password));
        }
    }

    @Test
    public void testSaveMails() throws Exception {
        String mail = "TO:test_receiver\t\nFROM:test sender with a very long mail address to check the line length building\t\n" +
                " with the constant\t\nSUBJECT:test_subject\t\nORIG-DATE:Tue Apr 14 17:22:35 CEST 2015\t\n\t\n" +
                "This is an email content test with an email incredibly long but I have no\t\n" +
                " more ideas to lengthen so I'll just add random words. Hoover, Pineapple,\t\n" +
                " Chameleon, Zephyr.\nThank you for your attention.\nBest Regards,\n\nPatrick Henry\t\n\t\n";
        UserServer user = new UserServer("d.rodarie@a.fr", "test2", "../OUTPUT/Server/Server_mails/");
        UserServer user2 = new UserServer("a.caron@a.fr", "test", "../OUTPUT/Server/Server_mails/");

        user.addMail(mail);
        server.saveMails(user);
        server.saveMails(user2);
    }
}