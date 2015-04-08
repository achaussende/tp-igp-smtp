package com.polytech4a.smtp.messages;

/**
 * Created by Antoine CARON on 03/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Enum of simple SMTP messages.
 */
public enum SMTPMessage {
    OK("250 OK"),
    NO_SUCH_USER("550 no such user"),
    QUIT("QUIT"),
    BAD_SEQUENCE_OF_COMMANDS("503 Bad sequence of commands"),
    START_MAIL_INPUT("354 Start mail input; End data with <CR><LF>.<CR><LF>");

    private String message;

    SMTPMessage(String message) {
        this.message = message;
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param smtpMessage Message define for type of STMPMessage to Test.
     * @param message     message receive to test.
     * @return true/false
     */
    public static boolean matches(SMTPMessage smtpMessage, String message) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("^");
        sbf.append(smtpMessage.toString());
        sbf.append("$");
        return message.matches(sbf.toString());
    }

    @Override
    public String toString() {
        return message;
    }
}
