package org.SpaceShooter.Components.OtherComponents;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AlienShip {
    public static JPanel getAlienShip(JPanel rocketPanel, JFrame frame) {
        JPanel alienShip = new JPanel(null){
            final Image background = new ImageIcon("src/main/images/ufo.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        alienShip.setOpaque(false);
        alienShip.setBounds((new Random().nextInt(frame.getWidth())/rocketPanel.getWidth())*rocketPanel.getWidth(),
                            0,
                               rocketPanel.getWidth(),
                        rocketPanel.getHeight()/2);
        return alienShip;
    }
}
