package com.polytech4a.smtp.server.state;

import com.polytech4a.smtp.messages.numberheader.server.ServerReady;
import com.polytech4a.smtp.server.Server;

/**
 * Created by Adrien CHAUSSENDE on 01/04/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Initialization state of the SMTP Server.
 */
public class StateInit extends State {

    public StateInit() {
        super();
        setNextState(new StateStart());
        setMsgToSend(new ServerReady(Server.SERVER_NAME).toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean analyze(String message) {
        return true;
    }
}
