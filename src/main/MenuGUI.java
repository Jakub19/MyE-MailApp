package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuGUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel settingsPanel;
    private JPanel preferencesPanel;
    private JPanel helpPanel;
    private JPanel aboutPanel;
    private JTextPane textPaneAbout;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField1;
    private JButton saveChangesButton;
    private String setPane;

    public MenuGUI(){
        add(panel1);


        setTitle("E-Mail App");
        setSize(600, 300);
//        Color backgroundColor = new Color(220,220,220);
//        tabbedPane1.setBackground(backgroundColor);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            setIconImage(ImageIO.read(new File("./images/16x16.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPane = GUI.getSetPane();
        if(setPane.equals("Settings")){
            tabbedPane1.setSelectedIndex(0);
        }
        if(setPane.equals("Preferences")){
            tabbedPane1.setSelectedIndex(1);
        }
        if(setPane.equals("Help")){
            tabbedPane1.setSelectedIndex(2);
        }
        if(setPane.equals("About")){
            tabbedPane1.setSelectedIndex(3);
        }
    }
}
