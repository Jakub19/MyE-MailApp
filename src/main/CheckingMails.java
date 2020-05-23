package main;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.swing.*;

public class CheckingMails {

    public static void check(String host, String storeType, String user, String password) {

        try {
            File dir = new File(GUI.getSavePath());

            if (!dir.exists()) {
                if (dir.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try {

            // create properties field
            Properties props = new Properties();

            props.put("mail.store.protocol", "imaps");
            props.put("mail.imaps.port", "993");
            props.put("mail.imaps.starttls.enable", "true");
            props.put("mail.imaps.fetchsize", "819200");
            props.put("mail.imaps.partialfetch", "false");

            Session emailSession = Session.getDefaultInstance(props);

            // create the IMAPS store object and connect with the pop server
            Store store = emailSession.getStore("imaps");

            try{
            store.connect(host, user, password);
            }catch (Exception exception){
                System.out.println(exception);
                JOptionPane.showMessageDialog(null, "Login failure!", "Error", 1);
            }

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            setTotalMCount(messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {

                Message message = messages[i];
                setProgress(i);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd HH;mm", Locale.ENGLISH);
                String mDate = sdf2.format(message.getReceivedDate());
                String dirName = "";
                dirName = (mDate + " " + message.getSubject().replaceAll("/", ""));

                /**
                 * Creates a new directory to store email content with attachments
                 */
                try {
                    File dir = new File(GUI.getSavePath()+"\\"+dirName);
                    if(dir.exists()){
                        System.out.println("Already downloaded");
                        continue; //skips download if already downloaded
                    }

                    if (!dir.exists()) {
                        if (dir.mkdir()) {
                            System.out.println("Directory is created!");
                        } else {
                            System.out.println("Failed to create directory!");
                            File dir2 = new File(GUI.getSavePath()+"\\error");
                        }
                    }

                    /**
                     * Writes content do file
                     */
                    FileWriter myWriter = new FileWriter(GUI.getSavePath()+"\\"+dirName+File.separator+message.getSubject()+".txt");
                    String messageContent = "";
                    String contentType = message.getContentType();
                    String attachFiles = "";

                    if (contentType.contains("multipart")) {
                        // content may contain attachments
                        Multipart multiPart = (Multipart) message.getContent();
                        int numberOfParts = multiPart.getCount();
                        for (int partCount = 0; partCount < numberOfParts; partCount++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                // this part is attachment
                                String fileName = part.getFileName();
                                attachFiles += fileName + ", ";
                                part.saveFile(GUI.getSavePath()+"\\"+dirName + File.separator + fileName);
                            } else {
                                // this part may be the message content
                                messageContent = part.getContent().toString();
                            }
                        }

                        if (attachFiles.length() > 1) {
                            attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                        }
                    } else if (contentType.contains("text/plain")
                            || contentType.contains("text/html")) {
                        Object content = message.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    }

                    myWriter.write(messageContent);
                    myWriter.close();


                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                System.out.println("Received date: " + mDate);

                if(LoginPanel.getStopRunning()){
                    break;
                }
            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static int progress;
    static int totalMCount;
    public static void setProgress(int number){
        progress = number;
    }
    public static int getProgress(){
        return progress;
    }
    public static void setTotalMCount(int number){
        totalMCount = number;
    }
    public static int getTotalMCount(){
        return totalMCount;
    }



}