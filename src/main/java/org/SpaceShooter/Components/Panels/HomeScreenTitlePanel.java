package org.SpaceShooter.Components.Panels;

import javax.swing.*;
import java.awt.*;

public class HomeScreenTitlePanel {
    public static JPanel getTitlePanel() {
        JPanel panel = new JPanel() {
            final Image background = new ImageIcon("src/main/images/SpaceShooterTitle.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        panel.setOpaque(false);
        panel.setBounds(0, 0, 600, 300);
        return panel;
    }
}
