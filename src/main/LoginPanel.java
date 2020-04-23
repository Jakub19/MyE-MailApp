package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton buttonLogin;
    private JPanel panel;
    private JLabel progressLabel;
    private boolean isRunning = true;
    private String host = "imap.gmail.com";
    private String mailStoreType = "imap";

    public LoginPanel(){
        add(panel);


        setTitle("login");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();

//        Main.updateProgressLabel(progressLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopRunning();
            }
        });

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = new String(passwordField1.getPassword());

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Runnable r = new Runnable() {
                    public void run() {
                        CheckingMails.check(host, mailStoreType, username, password);
                    }
                };
                new Thread(r).start();
                stopRunning();
                setCursor(null);
            }
        });

        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String username = textField1.getText();
                    String password = new String(passwordField1.getPassword());

                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Runnable r = new Runnable() {
                        public void run() {
                            CheckingMails.check(host, mailStoreType, username, password);
                        }
                    };
                    new Thread(r).start();

                    stopRunning();
                    setCursor(null);
                }
            }
        });
    }
    public void stopRunning() {
        isRunning = false;
    }

}
