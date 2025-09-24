package org.SpaceShooter.Components.Rockets;

import javax.swing.*;
import java.awt.*;

public class GameScreenRocket {

    private GameScreenRocket() {}

    private static JPanel gameRocketPanel;

    public static JPanel getRocketImagePanel(JFrame frame) {

        if (gameRocketPanel!=null) return gameRocketPanel;

        gameRocketPanel = new JPanel() {
            final Image background = new ImageIcon("src/main/images/SpaceShip.png").getImage();
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
