package main;

import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;


public class TextViewer extends  JFrame{
    private JTextArea textArea1;
    private JPanel panel1;

    public TextViewer(){
        add(panel1);
        setTitle("E-Mail App");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        readFile();
        try {
            setIconImage(ImageIO.read(new File("./images/16x16.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        textArea1.setLineWrap(true);
    }

    public void readFile(){
        try
        {
            FileReader reader = new FileReader(GUI.getFilePath());
            BufferedReader br = new BufferedReader(reader);
            String htmlTxt = br.lines().collect(Collectors.joining());
            String txt = Jsoup.parse(htmlTxt).text(); //removes html tags
            textArea1.setText(txt);
            br.close();
            textArea1.requestFocus();
        }
        catch(Exception e2) { System.out.println(e2); }

    }


}
