package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItem extends JPanel {
    private Schedule schedule;

    public DeleteItem (MainAgenda mainAgenda) {
        super();
        schedule = mainAgenda.getSchedule();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JTextArea text = new JTextArea(10,40);
        JScrollPane textPane = new JScrollPane(text);
        textPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
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
                text.setText("Please select an item \n you would like to delete from your schedule.");
                itemName.setText("");
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Edit");
            }
        });

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(Box.createRigidArea(new Dimension(0,250)));
        textPanel.add(textPane);
        textPanel.add(Box.createRigidArea(new Dimension(0,250)));
        textPanel.setBackground(new Color(226,255,255));



        JPanel nameEntryPanel = new JPanel();
        nameEntryPanel.setLayout(new BoxLayout(nameEntryPanel, BoxLayout.Y_AXIS));
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(226,255,255));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(nameItemLabel);
        namePanel.add(itemName);
        nameEntryPanel.add(Box.createRigidArea(new Dimension(0,300)));
        nameEntryPanel.add(namePanel);
        nameEntryPanel.add(enter);
        nameEntryPanel.add(Box.createRigidArea(new Dimension(0,300)));
        nameEntryPanel.setBackground(new Color(226,255,255));

        this.add(Box.createRigidArea(new Dimension(40,0)));
        this.add(textPanel);
        this.add(Box.createRigidArea(new Dimension(40,0)));
        this.add(nameEntryPanel);
        this.add(Box.createRigidArea(new Dimension(40,0)));
        this.add(backButton);
        this.add(Box.createRigidArea(new Dimension(40,0)));
        this.setBackground(new Color(226,255,255));
    }
}
