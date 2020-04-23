package main;
import javax.swing.*;
import java.awt.*;

public class Main{

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI newGUI = new GUI();
                newGUI.setVisible(true);
            }
        });
    }

    public static void createLoginPanel()throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPanel loginP = new LoginPanel();
                loginP.setVisible(true);
            }
        });

    }
//    public static void updateProgressLabel(final JLabel label) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    try {
//                        System.out.println(CheckingMails.getProgress());
//                        label.setText(CheckingMails.getProgress());
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
}