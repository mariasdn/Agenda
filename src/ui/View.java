package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel {
    private Schedule schedule;

    public View(MainAgenda mainAgenda){
        super();
        schedule = mainAgenda.getSchedule();
        JTextArea text = new JTextArea("Hello");
        text.setSize(300,300);
        text.setVisible(true);
        JButton viewAllScheduleButton = new JButton("View the entire schedule");
        viewAllScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
                String[] allScheduleArray = schedule.viewSchedule();
                for (String s: allScheduleArray){
                    text.append(s + "\n");
                }
            }
        });
        JButton viewDayScheduleButton = new JButton("View the schedule on a certain day");
        viewDayScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton viewItemButton = new JButton("View a certain item in your schedule");
        viewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("Hello");
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Home");
            }
        });
        this.add(text);
        this.add(viewAllScheduleButton);
        this.add(viewDayScheduleButton);
        this.add(viewItemButton);
        this.add(backButton);
    }
}
