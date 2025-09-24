package org.SpaceShooter.Components.Buttons;

import org.SpaceShooter.Screens.GameScreen;

import javax.swing.*;
import java.awt.*;

public class HomeScreenStartButton {
    public static JButton getStarButton(JFrame frame) {
        ImageIcon startIcon = new ImageIcon("src/main/images/StartButton.jpg");
        Image img = startIcon.getImage();
        final int width = 200;
        final int height = 200;
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JButton startButton = new JButton(new ImageIcon(scaledImg));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBounds(200,280,width,height);
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            new GameScreen();
        });
        return startButton;
    }

}
