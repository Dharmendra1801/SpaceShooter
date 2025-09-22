package org.SpaceShooter;

import javax.swing.*;
import java.awt.*;

public final class Frame {

    private static JFrame frame;

    private Frame() {}

    private static JFrame frame() {
        if (frame!=null) return frame;
        frame = new JFrame("Space Shooter");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.WHITE);
        frame.setSize(600,700);
        JLabel background = getBackground();
        frame.setContentPane(background);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    public static JFrame getFrame() {
        return frame();
    }

    private static JLabel getBackground() {
        ImageIcon background = new ImageIcon("src/main/images/GameAreaBackground.png");
        Image img = background.getImage();
        Image scaledImg = img.getScaledInstance(600, 800, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));
        backgroundLabel.setBounds(0, 0, 600, 800);

        return backgroundLabel;
    }
}
