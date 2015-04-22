package com.polytech4a.smtp.server;

import org.apache.log4j.*;

import java.io.IOException;

/**
 * Created by Adrien CHAUSSENDE on 30/03/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Main class of a SMTP server.
 */
public class Main {

    /**
     * Logger.
     */
    public static Logger logger = Logger.getLogger(Server.class);

    /**
     * Main function.
     *
     * @param args arg0 = port, arg1 = nbConnection
     */
    public static void main(String[] args) {
        defineLogger();
        Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        logger.info("SERVER STARTED");
        server.listen();
        server.close();
    }

    /**
     * Define the Logger Appender for LOG4j
     */
    private static void defineLogger() {
        HTMLLayout layout = new HTMLLayout();
        DailyRollingFileAppender appender = null;
        try {
            appender = new DailyRollingFileAppender(layout, "./Server_Log/log.html", "yyyy-MM-dd");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConsoleAppender ca = new ConsoleAppender();
        ca.setLayout(new SimpleLayout());
        ca.activateOptions();
        logger.addAppender(appender);
        logger.addAppender(ca);
        logger.setLevel(Level.DEBUG);
    }
}
