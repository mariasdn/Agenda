package ui;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;

public class MainAgenda extends JFrame {
    private JPanel cards;
    private CardLayout cardLayout;
    private Schedule schedule;

    public MainAgenda() {
        super("Agenda");
        schedule = new Schedule();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        cardLayout = new CardLayout();
        Container homePane = this.getContentPane();
        cards = new JPanel(cardLayout);
        cards.add(new Home(this), "Home");
        cards.add(new Edit(this),"Edit");
        cards.add(new View(this), "View");




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

    public static void main (String[] args) {
        new MainAgenda();
    }
}
