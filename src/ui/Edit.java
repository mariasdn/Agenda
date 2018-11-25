package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edit extends JPanel {
    private Schedule schedule;

    public Edit(MainAgenda mainAgenda){
        super();
        schedule = mainAgenda.getSchedule();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton addItemButton = new JButton("Add an Item to your schedule");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "AddItem");
            }
        });
        JButton deleteItemButton = new JButton("Delete an Item from your schedule");
        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "DeleteItem");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Home");
            }
        });
        this.add(Box.createVerticalGlue());
        this.add(addItemButton);
        addItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.add(deleteItemButton);
        deleteItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.add(backButton);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
    }
}
