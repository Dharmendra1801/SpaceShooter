package org.SpaceShooter.Components.Rockets;

import org.SpaceShooter.Components.OtherComponents.RocketMissile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class GameScreenRocket {

    private GameScreenRocket() {}

    public static JPanel getRocketImagePanel(JFrame frame) {
        JPanel gameRocketPanel = new JPanel() {
            final Image background = new ImageIcon("src/main/images/spaceship.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        gameRocketPanel.setOpaque(false);
        gameRocketPanel.setLayout(null);
        gameRocketPanel.setBounds(50, 580, 50, 80);
        return gameRocketPanel;
    }

    public static void moveRocket(JPanel panel, JFrame frame, int dx) {
        int newX = panel.getX() + dx;
        if (newX < 0) newX = 0;
        if (newX > frame.getWidth() - panel.getWidth()) newX = frame.getWidth() - panel.getWidth();
        panel.setLocation(newX, panel.getY());
    }


}
