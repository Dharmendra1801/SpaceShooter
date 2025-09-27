package org.SpaceShooter.Frames;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public final class Frame {

    private static int highScore = 0;

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
        setLogo();
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    private static void setLogo() {
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(Frame.class.getClassLoader().getResource("images/logo.png")));
        frame.setIconImage(logo.getImage());
        try {
            Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(logo.getImage());
        } catch (Exception e) {
            System.out.println("Taskbar icon not supported: " + e.getMessage());
        }
    }

    public static JFrame getFrame() {
        return frame();
    }

    private static JLabel getBackground() {
        ImageIcon background = new ImageIcon(Frame.class.getClassLoader().getResource("images/GameAreaBackground.png"));
        Image img = background.getImage();
        Image scaledImg = img.getScaledInstance(600, 800, Image.SCALE_SMOOTH);

        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));
        backgroundLabel.setBounds(0, 0, 600, 800);

        return backgroundLabel;
    }

    public static int getHighScore(int score) {
        if (score>highScore) {
            setHighScore(score);
        }
        return highScore;
    }

    public static void setHighScore(int score) {
        highScore = score;
    }
}
