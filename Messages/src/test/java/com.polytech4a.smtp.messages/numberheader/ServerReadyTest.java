package com.polytech4a.smtp.messages.numberheader;

import com.polytech4a.smtp.messages.numberheader.server.ServerReady;
import junit.framework.TestCase;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          TestCase for ServerReady class.
 */
public class ServerReadyTest extends TestCase {


    public void testMatches() throws Exception {
        ServerReady test = new ServerReady("toto");
        ServerReady test1 = new ServerReady((Object) "250 titi SMTP Server Ready");
        assertTrue("Building Failed", ServerReady.matches(test.toString()));
        assertTrue("Parsing Failed", ServerReady.matches(test1.toString()));
        assertEquals("toto", test.getServerName());
        assertEquals("titi", test1.getServerName());
    }
}
