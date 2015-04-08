package com.polytech4a.smtp.messages.numberheader;

import com.polytech4a.smtp.messages.numberheader.server.SigningOff;
import junit.framework.TestCase;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          TestCase for SigningOff class.
 */
public class SigningOffTest extends TestCase {
    public void testMatches() throws Exception {
        SigningOff signingOff = new SigningOff("toto");
        SigningOff signingOff1 = new SigningOff((Object) "221 titi Service closing transmission channel");
        assertTrue(SigningOff.matches(signingOff.toString()));
        assertTrue(SigningOff.matches(signingOff1.toString()));
        assertEquals("toto", signingOff.getServerName());
        assertEquals("titi", signingOff1.getServerName());
    }
}
