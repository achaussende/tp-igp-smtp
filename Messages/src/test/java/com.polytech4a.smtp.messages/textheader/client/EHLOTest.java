package com.polytech4a.smtp.messages.textheader.client;

import junit.framework.TestCase;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          TestCase for EHLO class.
 */
public class EHLOTest extends TestCase {
    public void testMatches() throws Exception {
        EHLO ehlo1 = new EHLO("toto");
        EHLO ehlo2 = new EHLO((Object) "EHLO toto");
        assertTrue("Building Failed", EHLO.matches(ehlo1.toString()));
        assertTrue("Parsing Failed", EHLO.matches(ehlo2.toString()));
        assertEquals("toto", ehlo1.getClientName());
        assertEquals("toto", ehlo2.getClientName());
    }
}
