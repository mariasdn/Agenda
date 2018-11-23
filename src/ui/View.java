package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel {
    private Schedule schedule;

    public View(MainAgenda mainAgenda){
        super();
        schedule = mainAgenda.getSchedule();
        JButton viewAllScheduleButton = new JButton("View the entire schedule");
        viewAllScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Home");
            }
        });
        this.add(viewAllScheduleButton);
        this.add(viewDayScheduleButton);
        this.add(viewItemButton);
        this.add(backButton);
    }
}
