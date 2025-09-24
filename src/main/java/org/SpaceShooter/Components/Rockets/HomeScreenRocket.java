package org.SpaceShooter.Components.Rockets;

import javax.swing.*;
import java.awt.*;

public class HomeScreenRocket {
    public static JPanel getRocketImagePanel(JFrame frame) {
        JPanel panel = new JPanel() {
            final Image background = new ImageIcon("src/main/images/SpaceShip.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        panel.setOpaque(false);
        panel.setBounds(50, 550, 50, 80);
        movingRocketImagePanel(panel,frame);
        return panel;
    }
    private static void movingRocketImagePanel(JPanel panel, JFrame frame) {
        final int[] pixel = {2};
        Timer timer = new Timer(10, e -> {
            int x = panel.getX();
            int y = panel.getY();

            x += pixel[0];

            if (x < 50 || x + panel.getWidth() > frame.getWidth()-50) {
                pixel[0] = -pixel[0]; // reverse direction
            }

            panel.setLocation(x, y);
        });
        timer.start();
    }
}
