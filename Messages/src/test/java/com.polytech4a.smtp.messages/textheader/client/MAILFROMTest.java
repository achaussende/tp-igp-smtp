package com.polytech4a.smtp.messages.textheader.client;

import junit.framework.TestCase;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          TestCase for MAILFROM class.
 */
public class MAILFROMTest extends TestCase {

    public void testMatches() throws Exception {
        MAILFROM mailfrom = new MAILFROM("a@a.aaa");
        assertTrue("Building Failed", MAILFROM.matches(mailfrom.toString()));
        MAILFROM mailfrom1 = new MAILFROM((Object) "MAIL FROM:<aaa@aaa.aa>");
        assertTrue("Parser Failed", MAILFROM.matches(String.valueOf(mailfrom1)));
    }
}
