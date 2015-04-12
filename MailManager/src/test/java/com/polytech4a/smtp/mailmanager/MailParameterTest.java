package com.polytech4a.smtp.mailmanager;

import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by Dimitri on 06/04/2015.
 */
public class MailParameterTest extends TestCase {
    private MailParameter dateParameter;
    private MailParameter subjectParameter;
    private MailParameter senderParameter;
    private MailParameter receiverParameter;

    @org.junit.Before
    public void setUp() throws Exception {
        this.dateParameter = new MailParameterDate(new Date().toString());
        this.subjectParameter = new MailParameterSubject("test_subject");
        this.senderParameter = new MailParameterSender("test sender with a very long mail address to check the line length building with the constant");
        this.receiverParameter = new MailParameterReceiver("test_receiver");
    }

    @org.junit.Test
    public void testParseParameter() throws Exception {
        String date = dateParameter.content,
                subject = subjectParameter.content,
                sender = senderParameter.content,
                receiver = receiverParameter.content;
        assertEquals("Test parse date : 'ORIG-DATE:" + date + "' must return true", true, dateParameter.parseParameter("ORIG-DATE:" + date + MailParameter.END_LINE));
        assertEquals("Test parse date : 'DATEORIG:" + date + "' must return false (wrong parser)", false, dateParameter.parseParameter("DATEORIG:" + date + MailParameter.END_LINE));

        assertEquals("Test parse subject : 'SUBJECT:" + subject + "' must return true", true, subjectParameter.parseParameter("SUBJECT:" + subject + MailParameter.END_LINE));
        assertEquals("Test parse subject : 'SUBJECT:" + subject + "' must return false (no endline)", false, subjectParameter.parseParameter("SUBJECT:" + subject));

        assertEquals("Test parse sender : 'FROM:" + sender + "' must return true", true, senderParameter.parseParameter("FROM:" + sender + MailParameter.END_LINE));
        assertEquals("Test parse sender : 'FROM:' must return false (no content)", false, senderParameter.parseParameter("FROM:" + MailParameter.END_LINE));

        assertEquals("Test parse receiver : 'TO:" + receiver + "' must return true", true, receiverParameter.parseParameter("TO:" + receiver + MailParameter.END_LINE));
        assertEquals("Test parse receiver :  must return false (null)", false, receiverParameter.parseParameter(null));
    }

    @org.junit.Test
    public void testBuildParameter() throws Exception {
        assertEquals("Test build date error", "ORIG-DATE:" + dateParameter.content + MailParameter.END_LINE, dateParameter.buildParameter().toString());
        assertEquals("Test build subject error", "SUBJECT:" + subjectParameter.content + MailParameter.END_LINE, subjectParameter.buildParameter().toString());
        assertEquals("Test build sender error", "FROM:test sender with a very long mail address to check the line length building" + MailParameter.END_LINE + " with the constant" + MailParameter.END_LINE, senderParameter.buildParameter().toString());
        assertEquals("Test build receiver error", "TO:" + receiverParameter.content + MailParameter.END_LINE, receiverParameter.buildParameter().toString());
    }
}