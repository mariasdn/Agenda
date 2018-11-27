package ui;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainAgenda extends JFrame {
    private JPanel cards;
    private CardLayout cardLayout;
    private Schedule schedule;

    public MainAgenda() {
        super("Agenda");
        schedule = new Schedule();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,440);
        cardLayout = new CardLayout();
        Container homePane = this.getContentPane();
        cards = new JPanel(cardLayout);
        cards.add(new Home(this), "Home");
        cards.add(new Edit(this),"Edit");
        cards.add(new View(this), "View");
        cards.add(new AddItem(this),"AddItem");
        cards.add(new DeleteItem(this), "DeleteItem");

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Save.txt")));
            readInFile(br);
        } catch (FileNotFoundException e){
            File f = new File("Save.txt");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("Fatal error: could not create Save.txt");
                System.exit(-1);
            }
        }



        homePane.add(cards);
        this.setVisible(true);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCards() {
        return cards;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void saveEdits () {
        try {
            PrintWriter pw = new PrintWriter(new File("Save.txt"));
            ArrayList<String> items = schedule.itemsAsString();
            for (String si: items) {
                pw.println(si);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
    }

    public void readInFile(BufferedReader br) {
        try {
            String st;
            while ((st = br.readLine()) != null) {
                String[] data = st.split(",");
                this.schedule.addItem(data);
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public static void main (String[] args) {
        new MainAgenda();
    }
}
