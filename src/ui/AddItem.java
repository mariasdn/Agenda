package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItem extends JPanel {
    private Schedule schedule;

    public AddItem (MainAgenda mainAgenda) {
        super();
        schedule = mainAgenda.getSchedule();

        //JTextField itemName = new JTextField(20);
        //JLabel nameItemLable = new JLabel("Enter the name");

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Edit");
            }
        });
        this.add(backButton);
    }
}
