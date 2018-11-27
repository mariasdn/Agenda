package ui;

import exceptions.InvalidArgumentException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItem extends JPanel {
    private Schedule schedule;

    public AddItem (MainAgenda mainAgenda) {
        super();
        schedule = mainAgenda.getSchedule();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JTextArea text = new JTextArea(10,40);
        JScrollPane textPane = new JScrollPane(text);
        textPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //text.setSize(50,50);
        text.setEditable(false);
        text.setVisible(true);
        text.setText("Please select information about an item \n you would like to add to your schedule.");

        JLabel typeItemLabel = new JLabel("Select item type: ");
        String[] itemTypes = {"Course","Lab","Activity"};
        JComboBox<String> itemTypeSelection = new JComboBox<String>(itemTypes);

        JLabel nameItemLabel = new JLabel("Enter the name: ");
        JTextField itemName = new JTextField(20);

        JLabel startTimeItemLabel = new JLabel("Select the start time: ");
        String[] startTimesString = {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm"};
        int[] startTimeInt = {8,9,10,11,12,13,14,15,16,17,18,19,20};
        JComboBox<String> startTimeSelection = new JComboBox<String>(startTimesString);

        JLabel lengthItemLabel = new JLabel("Select the length (in hours): ");
        Integer[] lengths = {1,2,3,4};
        JComboBox<Integer> lengthSelection = new JComboBox<Integer>(lengths);

        JLabel weekDaysLabel = new JLabel("Select what week days event is on: ");
        JCheckBox mCheck = new JCheckBox("Monday");
        JCheckBox tCheck = new JCheckBox("Tuesday");
        JCheckBox wCheck = new JCheckBox("Wednesday");
        JCheckBox thCheck = new JCheckBox("Thursday");
        JCheckBox fCheck = new JCheckBox("Friday");


        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemType = itemTypes[itemTypeSelection.getSelectedIndex()];
                String name = itemName.getText();
                int startTime = startTimeInt[startTimeSelection.getSelectedIndex()];
                int length = lengths[lengthSelection.getSelectedIndex()];
                boolean[] weekDays = {mCheck.isSelected(), tCheck.isSelected(), wCheck.isSelected(), thCheck.isSelected(), fCheck.isSelected()};

                try{
                    text.setText("An item you have added to your schedule is:\n");
                    String itemToAdd = schedule.addItem(itemType, name, startTime, length, weekDays);
                    text.append(itemToAdd);
                } catch (InvalidArgumentException ex) {
                    text.setText("ERROR");
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("Please select information about an item \n you would like to add to your schedule.");
                mainAgenda.getCardLayout().show(mainAgenda.getCards(), "Edit");
            }
        });


        JPanel addTypePanel = new JPanel();
        addTypePanel.setLayout(new BoxLayout(addTypePanel, BoxLayout.X_AXIS));
        addTypePanel.add(typeItemLabel);
        addTypePanel.add(itemTypeSelection);

        JPanel addNamePanel = new JPanel();
        addNamePanel.setLayout(new BoxLayout(addNamePanel, BoxLayout.X_AXIS));
        addNamePanel.add(nameItemLabel);
        addNamePanel.add(itemName);

        JPanel startTimePanel = new JPanel();
        startTimePanel.setLayout(new BoxLayout(startTimePanel, BoxLayout.X_AXIS));
        startTimePanel.add(startTimeItemLabel);
        startTimePanel.add(startTimeSelection);

        JPanel lengthPanel = new JPanel();
        lengthPanel.setLayout(new BoxLayout(lengthPanel, BoxLayout.X_AXIS));
        lengthPanel.add(lengthItemLabel);
        lengthPanel.add(lengthSelection);

        JPanel weekDaysCheckListPanel = new JPanel();
        weekDaysCheckListPanel.setLayout(new BoxLayout(weekDaysCheckListPanel, BoxLayout.Y_AXIS));
        weekDaysCheckListPanel.add(mCheck);
        weekDaysCheckListPanel.add(tCheck);
        weekDaysCheckListPanel.add(wCheck);
        weekDaysCheckListPanel.add(thCheck);
        weekDaysCheckListPanel.add(fCheck);

        JPanel weekDaysPanel = new JPanel();
        weekDaysPanel.setLayout(new BoxLayout(weekDaysPanel, BoxLayout.X_AXIS));
        weekDaysPanel.add(weekDaysLabel);
        weekDaysPanel.add(weekDaysCheckListPanel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(Box.createRigidArea(new Dimension(0,250)));
        textPanel.add(textPane);
        textPanel.add(Box.createRigidArea(new Dimension(0,250)));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0,200)));
        mainPanel.add(addTypePanel);
        mainPanel.add(addNamePanel);
        mainPanel.add(startTimePanel);
        mainPanel.add(lengthPanel);
        mainPanel.add(weekDaysPanel);
        mainPanel.add(enter);
        enter.setAlignmentX(Component.RIGHT_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0,200)));

        this.add(Box.createRigidArea(new Dimension(40,0)));
        this.add(textPanel);
        this.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(mainPanel);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(backButton);
        backButton.setAlignmentY(Component.TOP_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(40,0)));
    }
}
