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

    SMTPMessage(String s) {
    }

    /**
     * Static function to know if a message matches the structure.
     *
     * @param smtpMessage type of SMTP Message to test.
     * @param message     message receive to test.
     * @return true/false
     */
    public static boolean matches(SMTPMessage smtpMessage, String message) {
        StringBuffer sbf = new StringBuffer(smtpMessage.getMessage());
        sbf.insert(0, "^");
        sbf.append("$");
        return message.matches(sbf.toString());
    }

    /**
     * Get the real SMTP Message.
     *
     * @return real SMTP Message.
     */
    public String getMessage() {
        return message;
    }
}
