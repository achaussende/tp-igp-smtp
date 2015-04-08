package com.polytech4a.smtp.messages;

import junit.framework.TestCase;

/**
 * Created by Antoine CARON on 06/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Test Case for SMTPMessage Enum.
 */
public class SMTPMessageTest extends TestCase {

    public void testMatches() throws Exception {
        assertTrue(SMTPMessage.matches(SMTPMessage.OK, "250 OK"));
        assertTrue(SMTPMessage.matches(SMTPMessage.NO_SUCH_USER, "550 no such user"));
        assertTrue(SMTPMessage.matches(SMTPMessage.BAD_SEQUENCE_OF_COMMANDS, "503 Bad sequence of commands"));
        assertTrue(SMTPMessage.matches(SMTPMessage.QUIT, "QUIT"));
        assertTrue(SMTPMessage.matches(SMTPMessage.START_MAIL_INPUT, "354 Start mail input; End data with <CR><LF>.<CR><LF>"));
    }
}