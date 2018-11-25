package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    public Home(MainAgenda mainAgenda){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Edit");
            }
        });
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "View");
            }
        });
        this.add(Box.createVerticalGlue());
        this.add(viewButton);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(editButton);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());

    }
}
