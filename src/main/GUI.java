package main;

import org.apache.commons.io.FilenameUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GUI extends JFrame{
    private JPanel panel1;
    private JButton loginButton;
    private JTree tree1;
    private JButton refreshButton;
    private String selectedTNode;
    private static String setPane;
    private static String filePath;
    private JMenuBar menuBar;
    private JMenu menuFile, menuHelp;
    private JMenuItem menuItemP, menuItemS, menuItemH, menuItemA;


    public GUI(){
        add(panel1);


        setTitle("E-Mail App");
        setSize(800, 500);
        Color backgroundColor = new Color(220,220,220);
        panel1.setBackground(backgroundColor);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuItemS = new JMenuItem("Settings");
        menuFile.add(menuItemS);
        menuItemP = new JMenuItem("Preferences");
        menuFile.add(menuItemP);

        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);

        menuItemH = new JMenuItem("Help");
        menuHelp.add(menuItemH);
        menuItemA = new JMenuItem("About");
        menuHelp.add(menuItemA);

        setJMenuBar(menuBar);

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

        loginButton.addActionListener(e -> {
            try {
                Main.createLoginPanel();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
                ex.printStackTrace();
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
                            String extension;
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
        tree1.addTreeSelectionListener(e -> {
            selectedTNode = "";
            TreePath treePath = e.getPath();
            Object[] elements = treePath.getPath();
            for (Object element : elements) {
                selectedTNode += ("\\" + element);
            }
        });

        //When "Refresh" button was pressed starts method that refreshes JTree
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateJTree();
            }
        });

        //ActionListener for "Preferences" menu item
        menuItemP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                try {
                    showMenuGUI(e.getActionCommand());
                } catch ( Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //ActionListener for "Settings" menu item
        menuItemS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                try {
                    showMenuGUI(e.getActionCommand());
                } catch ( Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //ActionListener for "Help" menu item
        menuItemH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                try {
                    showMenuGUI(e.getActionCommand());
                } catch ( Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //ActionListener for "About" menu item
        menuItemA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                try {
                    showMenuGUI(e.getActionCommand());
                } catch ( Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /**
     * Update JTree files and dir view
     */
    public void updateJTree () {
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

    public void showMenuGUI(String setPane) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        this.setPane = setPane;
        Main.createMenuGUI();
    }

    public static String getSetPane() {
        return setPane;
    }

    public static String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

