package com.polytech4a.smtp.client.core;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Pierre on 01/04/2015.
 */
public class Client extends Observable implements Runnable {

    private static final int PORT=500;
    private static Logger logger = Logger.getLogger(Main.class);
    private Mail mailToSend;
    private List<Connection> connections = new LinkedList<Connection>();

    public Client() {

    }

    public void run() {
        initConnections();
        for(Connection c:connections){
            try {
                c.createConnection();
                c.processing();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initConnections() {
        connections.clear();
        LinkedList<InetAddress> dest = new LinkedList<InetAddress>();
        for (String to : mailToSend.getReceivers()) {
            String hostName = to.substring(to.indexOf("@") + 1);
            if (hostName.contains("localhost")) hostName="localhost";
            try {
                final InetAddress address = Inet4Address.getByName(hostName);
                if (dest.contains(address)) {
                    Connection connection = connections.stream().filter(c -> c.getServerDestAddress().equals(address)).findAny().get();
                    connection.getMailToSend().getReceivers().add(to);
                } else {
                    dest.add(address);
                    ArrayList<String> mailto=new ArrayList<>();
                    mailto.add(to);
                    Mail mail=new Mail(mailToSend.getUser(),mailto,mailToSend.getMailToSend(),mailToSend.getObject());
                    Connection connection=new Connection(address,PORT,mail);
                    connections.add(connection);
                }
            } catch (UnknownHostException e) {
                String msg = "Host " + hostName + " do not exist.";
                logger.error(msg);
                notifyObservers(msg);
            }
        }
    }


    public Mail getMail() {
        return mailToSend;
    }

    public void setMail(Mail mail) {
        this.mailToSend = mail;
    }
}
