package main;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame{
    private JPanel panel1;
    private JButton loginButton;
    private JButton groupByButton;
    private JTree tree1;
    private JLabel progress;
    private JButton refreshButton;
    private String selectedTNode;
    private static String filePath;


    public GUI(){
        add(panel1);


        setTitle("E-Mail App");
        setSize(800, 500);
        Color backgroundColor = new Color(137,137,137);
        panel1.setBackground(backgroundColor);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        updateJTree();

        List<Image> images = new ArrayList<>();
        try {
            images.add(ImageIO.read(new File("./images/16x16.png")));
            images.add(ImageIO.read(new File("./images/32x32.png")));
            images.add(ImageIO.read(new File("./images/64x64.png")));
            images.add(ImageIO.read(new File("./images/128x128.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setIconImages(images);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.createLoginPanel();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });
        groupByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        /**
         * Opens dir by double clicking
         */
        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() >= 2){
                    try {
                        if (selectedTNode != null) {
                            setFilePath("D:" + selectedTNode);
                            File file = new File(filePath);
                            Desktop desktop = Desktop.getDesktop();
                            String extension = "";
                            extension = getExtension(filePath);

                            try {
                                if(extension.equals("txt")){
                                    showTextContent();

                                }
                                else {
                                    desktop.open(file);
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            File file = new File("D:\\e-mail_app");
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                desktop.open(file);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        tree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedTNode = "";
                TreePath treePath = e.getPath();
                Object elements[] = treePath.getPath();
                for (int i = 0, n = elements.length; i < n; i++) {
                    selectedTNode += ("\\"+elements[i]);
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateJTree();
            }
        });
    }

    /**
     * Update JTree files and dir view
     */
    public void updateJTree(){
        final File file = new File("D:\\e-mail_app");
        final MyFile mf = new MyFile(file);
        final TreeModel model = new FileTreeModel(mf);
        tree1.setModel(model);
        selectedTNode = null;
    }

    public String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public void showTextContent() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Main.createTextViewer();
    }

    public static String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}

