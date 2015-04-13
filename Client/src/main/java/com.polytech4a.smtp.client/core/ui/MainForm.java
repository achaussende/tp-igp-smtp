package com.polytech4a.smtp.client.core.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.polytech4a.smtp.client.core.Client;
import com.polytech4a.smtp.client.core.Mail;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Antoine CARON on 13/04/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class MainForm extends JFrame implements Observer {
    private JPanel panelMain;
    private JLabel lbFROM;
    private JTextField tfFROM;
    private JTextField tfTO;
    private JLabel lbTO;
    private JTextField tfObject;
    private JTextArea tAMailContent;
    private JLabel lbMail;
    private JButton btReset;
    private JButton sendButton;
    private Client client;

    /**
     * Regex for Mail.
     */
    private final static String mailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

    private final static String mailsListRegex = "([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4};?)*";


    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p/>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     * * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     *
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public MainForm(final Client client) throws HeadlessException {
        super("SMTP Client");
        $$$setupUI$$$();
        this.setContentPane(panelMain);
        this.pack();
        this.setResizable(false);
        this.setMinimumSize(new Dimension(500, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        btReset.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tfFROM.setText("");
                tfObject.setText("");
                tfTO.setText("");
                tAMailContent.setText("");
            }
        });
        sendButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                String message = verifyForm();
                if (verifyForm().isEmpty()) {
                    ArrayList<String> dest = new ArrayList<String>();
                    for (String to : tfTO.getText().split(";")) {
                        dest.add(to);
                    }
                    Mail mail = new Mail(tfFROM.getText(), dest, tAMailContent.getText(), tfObject.getText());
                    client.setMail(mail);
                    Thread clientThread = new Thread(client);
                    clientThread.start();
                } else {
                    JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String verifyForm() {
        if (tfFROM.getText().matches(mailRegex)) {
            if (tfTO.getText().matches(mailsListRegex)) {
                if (!tfObject.getText().isEmpty()) {
                    if (!tAMailContent.getText().isEmpty()) {
                        return "";
                    } else return "Message is empty.";
                } else return "Object Missing.";
            } else return "TO is not a list of mail address splited by ;";
        } else return "FROM is not a good mail address.";
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    public void update(Observable o, Object arg) {

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new FormLayout("fill:d:noGrow,left:4dlu:noGrow,fill:d:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        lbFROM = new JLabel();
        lbFROM.setText("FROM : ");
        CellConstraints cc = new CellConstraints();
        panelMain.add(lbFROM, cc.xy(1, 3));
        tfFROM = new JTextField();
        tfFROM.setText("aaaaaa@aaaaa.com");
        panelMain.add(tfFROM, cc.xy(3, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        tfTO = new JTextField();
        tfTO.setText("antoine@localhost.com;adrien@localhost.com;antoine@laposte.net;pierre@gmail.com");
        panelMain.add(tfTO, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        lbTO = new JLabel();
        lbTO.setText("TO : ");
        panelMain.add(lbTO, cc.xy(1, 5));
        final JLabel label1 = new JLabel();
        label1.setText("Object :");
        panelMain.add(label1, cc.xy(1, 7));
        tfObject = new JTextField();
        tfObject.setText("Object");
        panelMain.add(tfObject, cc.xy(3, 7, CellConstraints.FILL, CellConstraints.DEFAULT));
        lbMail = new JLabel();
        lbMail.setText("Mail content :");
        panelMain.add(lbMail, cc.xy(1, 9));
        tAMailContent = new JTextArea();
        tAMailContent.setColumns(40);
        tAMailContent.setLineWrap(true);
        tAMailContent.setRows(14);
        tAMailContent.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        panelMain.add(tAMailContent, cc.xy(3, 9, CellConstraints.FILL, CellConstraints.FILL));
        btReset = new JButton();
        btReset.setHorizontalAlignment(0);
        btReset.setText("Reset");
        panelMain.add(btReset, cc.xy(3, 11));
        sendButton = new JButton();
        sendButton.setText("Send");
        panelMain.add(sendButton, cc.xy(3, 13));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 24));
        label2.setText("Client SMTP");
        panelMain.add(label2, cc.xy(1, 1));
        lbFROM.setLabelFor(tfFROM);
        lbTO.setLabelFor(tfTO);
        label1.setLabelFor(tfObject);
        lbMail.setLabelFor(tAMailContent);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }
}
