package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItem extends JPanel {
    private Schedule schedule;

    public DeleteItem (MainAgenda mainAgenda) {
        super();
        schedule = mainAgenda.getSchedule();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JTextArea text = new JTextArea(20,20);
        text.setSize(100,50);
        text.setEditable(false);
        text.setVisible(true);
        text.setText("Please select an item \n you would like to delete from your schedule.");

        JLabel nameItemLabel = new JLabel("Enter the name: ");
        JTextField itemName = new JTextField(20);

        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
                text.append(schedule.deleteItem(itemName.getText()));
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Edit");
            }
        });


        this.add(text);
        this.add(nameItemLabel);
        this.add(itemName);
        this.add(enter);
        this.add(backButton);
    }
}
