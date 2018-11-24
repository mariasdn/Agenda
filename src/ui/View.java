package ui;

import exceptions.InvalidWeekDayException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JPanel {
    private Schedule schedule;

    public View(MainAgenda mainAgenda){
        super();
        schedule = mainAgenda.getSchedule();

        JTextArea text = new JTextArea(20,20);
        JScrollPane textPane = new JScrollPane(text);
        textPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text.setSize(300,300);
        text.setEditable(false);
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

        String[] dayOptions = {"m","t","w","th","f"};
        JComboBox<String> daySelection = new JComboBox<String>(dayOptions);

        JButton viewDayScheduleButton = new JButton("View the schedule on a certain day");
        viewDayScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDay = dayOptions[daySelection.getSelectedIndex()];
                text.setText(selectedDay);
                text.setText("");
                try{
                    ArrayList<String> allScheduleArray = schedule.viewItemsOnDay(selectedDay);
                    for (String s: allScheduleArray){
                        text.append(s + "\n");
                    }
                } catch(InvalidWeekDayException ex){
                    text.setText("ERROR");
                }
            }
        });

        JTextField itemSearch = new JTextField(20);
        JLabel viewItemLable = new JLabel("View a certain item in your schedule");

        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemInput = itemSearch.getText();
                text.setText(schedule.viewItemsByName(itemInput));
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Home");
            }
        });

        JPanel viewDayPanel = new JPanel();
        viewDayPanel.add(daySelection);
        viewDayPanel.add(viewDayScheduleButton);

        JPanel itemSearchPanel = new JPanel();
        JPanel itemSearchPanel2 = new JPanel();
        itemSearchPanel.setLayout(new BoxLayout(itemSearchPanel, BoxLayout.Y_AXIS));
        itemSearchPanel.add(viewItemLable);
        itemSearchPanel2.add(itemSearch);
        itemSearchPanel2.add(enter);
        itemSearchPanel.add(itemSearchPanel2);

        JPanel viewOptionsPanel = new JPanel();
        viewOptionsPanel.setLayout(new BoxLayout(viewOptionsPanel, BoxLayout.Y_AXIS));
        viewOptionsPanel.add(viewAllScheduleButton);
        viewAllScheduleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewOptionsPanel.add(Box.createVerticalGlue());
        viewOptionsPanel.add(viewDayPanel);
        viewDayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewOptionsPanel.add(Box.createVerticalGlue());
        viewOptionsPanel.add(itemSearchPanel);
        itemSearchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(textPane);
        this.add(viewOptionsPanel);
        this.add(backButton);
    }
}
