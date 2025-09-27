package org.SpaceShooter.Components.OtherComponents;

import javax.swing.*;
import java.awt.*;

public class LivesLeftHeart {
    public static JPanel getHeart() {
        ImageIcon heartImage = new ImageIcon(LivesLeftHeart.class.getClassLoader().getResource("images/heart.png"));
        Image img = heartImage.getImage();
        Image heartImage_resized = img.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        JPanel heart = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(heartImage_resized, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        heart.setOpaque(false);
        return heart;
    }
}
