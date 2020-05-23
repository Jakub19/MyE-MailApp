package main;
import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setUIFont (new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,Integer.parseInt(MenuGUI.getFSize())), null);
        SwingUtilities.invokeLater(() -> {
                GUI newGUI = new GUI();
                newGUI.setVisible(true);
        });
    }

    public static void createLoginPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            LoginPanel loginP = new LoginPanel();
            loginP.setVisible(true);
        });

    }

    public static void createTextViewer() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            TextViewer textV = new TextViewer();
            textV.setVisible(true);
        });

    }

    public static void createMenuGUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> {
            MenuGUI menuGUI = new MenuGUI();
            menuGUI.setVisible(true);
        });

    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f, Component c){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
        if(c != null) {
            SwingUtilities.updateComponentTreeUI(c);

        }
    }
    public static void refreshPanel(Component c){
        SwingUtilities.updateComponentTreeUI(c);
    }

}