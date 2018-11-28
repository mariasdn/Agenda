package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

        try{
            BufferedImage myPicture = ImageIO.read(new File("agenda.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.add(picLabel);
            picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        catch(IOException ex){
            System.out.println("Error no file for picture");
        }

        this.add(Box.createVerticalGlue());
        this.add(viewButton);
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(editButton);
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.setBackground(new Color(226,255,255));

    }
}
