package org.SpaceShooter.Components.OtherComponents;

import javax.swing.*;
import java.awt.*;

public class RocketMissile {

    public static JPanel getMissile(JPanel rocketPanel) {
        JPanel missilePanel = new JPanel(null){
            final Image background = new ImageIcon("src/main/images/missile.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        missilePanel.setOpaque(false);
        missilePanel.setBounds(rocketPanel.getX(),
                               rocketPanel.getY(),
                               rocketPanel.getWidth(),
                        rocketPanel.getHeight()/2);
        return missilePanel;
    }
}
