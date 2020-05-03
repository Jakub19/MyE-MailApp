package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

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

    public static void createLoginPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPanel loginP = new LoginPanel();
                loginP.setVisible(true);
            }
        });

    }

    public static void createTextViewer() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TextViewer textV = new TextViewer();
                textV.setVisible(true);
            }
        });

    }

}