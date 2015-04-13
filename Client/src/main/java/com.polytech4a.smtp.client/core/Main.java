package com.polytech4a.smtp.client.core;

import com.polytech4a.smtp.client.core.ui.MainForm;
import org.apache.log4j.*;

import javax.swing.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by Pierre on 01/04/2015.
 */
public class Main {
    /**
     * Logger.
     */
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Main.class);

    public static void main(String[] args){
        defineLogger();
        Client client = new Client();
        MainForm mainForm=new MainForm(client);
        client.addObserver(mainForm);
    }

    private static void defineLogger(){
        HTMLLayout layout = new HTMLLayout();
        DailyRollingFileAppender appender = null;
        try {
            appender = new DailyRollingFileAppender(layout, "./Client_Log/log.html", "yyyy-MM-dd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConsoleAppender ca=new ConsoleAppender();
        ca.setLayout(new SimpleLayout());
        ca.activateOptions();
        logger.addAppender(appender);
        logger.addAppender(ca);
        logger.setLevel(Level.DEBUG);
    }
}
