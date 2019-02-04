package Server;

import GeneralClasses.Meme;
import GeneralClasses.MessageToClient;
import GeneralClasses.MessageToServer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;


public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;

    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
            MessageToServer messageToServer = (MessageToServer) input.readObject();
            MessageToClient messageToClient = handleMessage(messageToServer);
            output.writeObject(messageToClient);
            output.close();
            input.close();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private MessageToClient handleMessage(MessageToServer messageToServer) {

        String pseudo;
        DB db = new DB();
        switch (messageToServer.getCommand()) {
            case "create":
                try {
                    db.addMeme(createMeme(messageToServer));
                    return new MessageToClient("createsuccess");

                } catch (Exception e) {
                    e.printStackTrace();
                    return new MessageToClient("createerror");
                }
            case "signin":
                pseudo = db.signIn(messageToServer.getUsername(), messageToServer.getPassword());
                if (pseudo != null) {
                    MessageToClient msg = new MessageToClient("signinsuccess");
                    msg.setAutisticPseudo(pseudo);
                    return msg;
                } else {
                    return new MessageToClient("signinerror");
                }


            case "signup":
                pseudo = messageToServer.getAutisticPseudo();
                if (db.signUp(messageToServer.getUsername(), messageToServer.getPassword(), pseudo)) {
                    MessageToClient msg = new MessageToClient("signupsuccess");
                    msg.setAutisticPseudo(pseudo);
                    return msg;
                } else {
                    return new MessageToClient("signuperror");
                }


            case "searchall":
                try {
                    MessageToClient msg = new MessageToClient("searchallsuccess");
                    msg.setMemeList(db.getAll());
                    return msg;
                } catch (Exception e) {
                    e.printStackTrace();
                    return new MessageToClient("searchallerror");
                }


            case "searchauthor":
                try {
                    MessageToClient msg = new MessageToClient("searchauthorsuccess");
                    msg.setMemeList(db.getSelectedAuthor(messageToServer.getFilter()));
                    return msg;
                } catch (Exception e) {
                    return new MessageToClient("searchauthorerror");
                }
            case "searchtitle":
                try {
                    MessageToClient msg = new MessageToClient("searchtitlesuccess");
                    msg.setMemeList(db.getSelectedTitle(messageToServer.getFilter()));
                    return msg;
                } catch (Exception e) {
                    return new MessageToClient("searchtitleerror");
                }
            case "searchtag":
                try {
                    MessageToClient msg = new MessageToClient("searchtagsuccess");
                    msg.setMemeList(db.getSelectedTag(messageToServer.getFilter()));
                    return msg;
                } catch (Exception e) {
                    return new MessageToClient("searchtagerror");
                }

            default:
                return new MessageToClient("default");
        }
    }

    private Meme createMeme(MessageToServer messageToServer) throws IOException {
        Meme meme = messageToServer.getMeme();
        BufferedImage image = SwingFXUtils.fromFXImage(meme.getImage(), null);
        Graphics g = image.getGraphics();

        Image watermark = ImageIO.read(getClass().getResource("/watermark.png"));
        g.drawImage(watermark, image.getWidth() / 2, image.getHeight() / 2, null);


        FontMetrics fm = g.getFontMetrics();

        g.setFont(new Font("Impact", Font.BOLD, image.getHeight() / 17));
        g.setColor(Color.WHITE);
        fm = g.getFontMetrics();
        g.drawString(meme.getUpperText(), (image.getWidth() - fm.stringWidth(meme.getUpperText())) / 2, g.getFont().getSize());
        g.drawString(meme.getBottomText(), (image.getWidth() - fm.stringWidth(meme.getBottomText())) / 2, image.getHeight() - 6);
        g.dispose();
        return new Meme(meme.getTag(), meme.getAuthor(), meme.getTitle(), SwingFXUtils.toFXImage(image, null));
    }
}