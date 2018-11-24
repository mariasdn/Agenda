package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edit extends JPanel {
    private Schedule schedule;

    public Edit(MainAgenda mainAgenda){
        super();
        schedule = mainAgenda.getSchedule();
        JButton backButton = new JButton("< Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Home");
            }
        });
        this.add(backButton);
    }
}
