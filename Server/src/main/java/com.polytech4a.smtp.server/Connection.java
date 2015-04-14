package com.polytech4a.smtp.server;

import com.polytech4a.smtp.server.state.State;
import com.polytech4a.smtp.server.state.StateInit;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLSocket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * Created by Adrien CHAUSSENDE on 30/03/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Connection between the client and the server.
 */
public class Connection implements Runnable {

    /**
     * Logger of the server.
     */
    private static Logger logger = Logger.getLogger(Server.class);

    /**
     * Socket when the connection was established.
     */
    private SSLSocket socket;

    /**
     * Current state of the connection.
     */
    private State state;

    /**
     * Output stream towards client.
     */
    private BufferedOutputStream out;
    /**
     * Input stream incoming from the client.
     */
    private BufferedInputStream in;

    /**
     * Mail manager for the server. Will handle interactions between server and files about client informations and mails.
     */
    //private ServerMailManager manager;

    /**
     * Constructor of the connection. It will be in initialization state. Initialize the two streams (output and input).
     */
    public Connection(SSLSocket socket) {
        try {
            this.socket = socket;
            this.state = new StateInit();
            this.out = new BufferedOutputStream(socket.getOutputStream());
            this.in = new BufferedInputStream(socket.getInputStream());
            //this.manager = new ServerMailManager(Server.SERVER_DIRECTORY);
        } catch (IOException e) {
            logger.error("Error while creating the connection.");
            logger.error(e.getMessage());
        }
    }

    public void run() {
        processing();
    }

    /**
     * Connection main process. It is where the server receive and send messages.
     */
    public void processing() {
        boolean runConnection = true;
        //Server is in init state. We have to send the first message and then update in authorization state.
        logger.info("Connection opened");
        sendMessage();
        updateState();
        logger.info("First message sent");
        while (runConnection) {
            StringBuffer message = new StringBuffer();
            try {
                logger.info("Waiting for a message from client.");
                byte[] buf = new byte[124];
                message.append(((char) in.read()));
                while (in.available() != 0) {
                    message.append(((char) in.read()));
                }
                logger.info("Client : " + message);
                runConnection = state.analyze(message.toString()); //route the request to next state of the server
                sendMessage();
                updateState();

            } catch (IOException e) {
                logger.error("Can't read incoming input from client.\n" + e.getMessage());
                runConnection = false;
            }
        }
        try {
            logger.info("CLOSING CONNECTION ... ...");
            in.close();
            out.close();
            logger.info("CONNECTION CLOSED");
        } catch (IOException e) {
            logger.error("Can't close streams\n" + e.getMessage());
        }
    }

    /**
     * Update the connection current state.
     */
    public void updateState() {
        if (state.getNextState() == null) {
            throw new NullPointerException("Next state is set to null");
        } else {
            this.state = state.getNextState();
        }
    }

    /**
     * Send the message to the client.
     */
    public void sendMessage() {
        try {
            logger.info("Server : " + state.getMsgToSend());
            out.write(state.getMsgToSend().getBytes("UTF-8"));
            out.flush();
        } catch (IOException e) {
            logger.error("Error during writing through server output\n" + e.getMessage());
        }
    }
}
