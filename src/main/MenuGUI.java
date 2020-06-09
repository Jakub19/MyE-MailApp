package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MenuGUI extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textFieldNewPath;
    private JButton saveChangesButton;
    private String setPane;
    private static String fSize = "14";
    private JPanel settingsPanel;
    private JPanel preferencesPanel;
    private JPanel helpPanel;
    private JPanel aboutPanel;
    private JTextPane textPaneAbout;
    private JComboBox cBFont;

    public MenuGUI(){
        add(panel1);


        setTitle("E-Mail App");
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
//        Color backgroundColor = new Color(220,220,220);
//        tabbedPane1.setBackground(backgroundColor);


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
        //TODO implement preferences tab
//        String[] fontSizes = {"12", "13", "14", "15"};
//        for(String fontS: fontSizes) {
//            cBFont.addItem(fontS);
//        }
//        cBFont.setSelectedIndex(1);
//        cBFont.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                setFSize(cBFont.getSelectedItem().toString());
//                GUI.refresh();
//            }
//        });
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter myWriter = new FileWriter("./fileSavePath.txt");
                    myWriter.write(textFieldNewPath.getText());
                    myWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public static String getFSize() {
        return fSize;
    }
    public void setFSize(String fSize) {
        this.fSize = fSize;
    }
}
