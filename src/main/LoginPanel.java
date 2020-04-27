package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton buttonLogin;
    private JPanel panel;
    private JProgressBar progressBar1;
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
        progressBar1.setStringPainted(true);


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
                startProgressBar();
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
    public void startProgressBar(){
        Runnable r1 = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                int x = 0;
                int total = CheckingMails.getTotalMCount();
                while (x <= total) {
                    x = CheckingMails.getProgress();
                    int percent = (int)((x * 100.0f) / total);
                    progressBar1.setValue(percent);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }// Setting incremental values
                    if (x == total) {
                        progressBar1.setString("Done");   // End message
                        try {
                            Thread.sleep(200);
                        } catch (Exception ex) { }
                    }
                }
            }
        };
        new Thread(r1).start();
    }

}
