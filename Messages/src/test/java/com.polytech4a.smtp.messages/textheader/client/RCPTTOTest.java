package com.polytech4a.smtp.messages.textheader.client;

import junit.framework.TestCase;

/**
 * Created by Antoine CARON on 10/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class RCPTTOTest extends TestCase {

    public void testMatches() throws Exception {
        RCPTTO rcptto = new RCPTTO("a@a.aaa");
        assertTrue("Building Failed", RCPTTO.matches(rcptto.toString()));
        RCPTTO rcptto1 = new RCPTTO((Object) "RCPT TO:<aaa@aaa.aa>");
        assertTrue("Parser Failed", RCPTTO.matches(String.valueOf(rcptto1)));
    }
}