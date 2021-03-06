package com.polytech4a.smtp.client.core;

import com.polytech4a.smtp.client.core.State.State;
import com.polytech4a.smtp.client.core.State.StateInit;
import com.polytech4a.smtp.client.core.State.StateStarted;
import com.polytech4a.smtp.messages.exceptions.MalformedEmailException;
import org.apache.log4j.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Created by Pierre on 01/04/2015.
 * Connection class for the client
 */
public class Connection {
    private static Logger logger = Logger.getLogger(Main.class);

    /**
     * Timeout in seconds
     */
    private static final int TIMEOUT = 5;

    private SSLSocket socket;
    private BufferedOutputStream out;
    private BufferedInputStream in;

    private InetAddress serverDestAddress;
    private int port;
    private Mail mailToSend;

    private State currentState;

    public Mail getMailToSend() {
        return mailToSend;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getServerDestAddress() {
        return serverDestAddress;
    }

    public Connection() {
    }

    public Connection(InetAddress address, int port, Mail mailToSend){
        this.serverDestAddress=address;
        this.port=port;
        this.mailToSend=mailToSend;
    }

    /**
     * Initialise the connection with the server and different objects with it, like the input and output streams
     *
     */
    public void createConnection() throws IOException {
        logger.info("OPENING CONNECTION  to "+serverDestAddress+" ... ...");
        SocketFactory factory = SSLSocketFactory.getDefault();
        this.socket = (SSLSocket) factory.createSocket(serverDestAddress, port);
        this.socket.setSoTimeout(this.TIMEOUT * 1000);
        String[] suites = {"SSL_DH_anon_WITH_RC4_128_MD5"};
        this.socket.setEnabledCipherSuites(suites);
        this.out = new BufferedOutputStream(this.socket.getOutputStream());
        this.in = new BufferedInputStream(this.socket.getInputStream());
        logger.info("Connection opened ... ...");
        this.currentState = new StateInit(mailToSend);
    }

    /**
     * End the connection with the server by closing the socket
     */
    private void closeConnection() throws IOException {
        this.socket.close();
    }


    /**
     * Send the message to the server through the created streams
     */
    private void sendMessage(){
        logger.info("Client : " + currentState.getMsgToSend());
        try {
            this.out.write(currentState.getMsgToSend().getBytes());
            this.out.flush();
        } catch (IOException e) {
            logger.error("Error sending the message: " + currentState.getMsgToSend());
        }
    }

    /**
     * Wait for the response from the server and send back the response with a string format
     */
    private String waitForResponse() throws IOException {
        StringBuilder response = new StringBuilder();
        response.append(((char) in.read()));
        while (in.available() != 0) {
            response.append(((char) in.read()));
        }
        logger.info("Server : " + response.toString());
        return response.toString();
    }

    /**
     * Connection main process. It is where the server receive and send messages.
     */
    public void processing() {
        boolean runConnection = true;
        // Client is in init state. It's the entry point for processing loop.
        updateState();
        while (runConnection) {
            String message = null;
            try {
                message = waitForResponse();
                runConnection = currentState.analyze(message); //route the request to next state of the server
                sendMessage();
                updateState();
            } catch (IOException e) {
                logger.error("Can't read incoming input from client.\n" + e.getMessage());
                runConnection = false;
            }
        }
        try {
            //send QUIT message in every case
            logger.info("CLOSING CONNECTION ... ...");
            in.close();
            out.close();
            logger.info("CONNECTION CLOSED");
        } catch (IOException e) {
            logger.error("Can't close streams\n" + e.getMessage());
        }
    }


    private void updateState() {
        if (currentState.getNextState() == null) {
            throw new NullPointerException("Next state is set to null");
        } else {
            this.currentState = currentState.getNextState();
        }
    }
}