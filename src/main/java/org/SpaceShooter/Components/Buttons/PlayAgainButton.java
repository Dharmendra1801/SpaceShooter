package org.SpaceShooter.Components.Buttons;

import javax.swing.*;
import java.awt.*;

public class PlayAgainButton {
    public static JButton getPlayAgainButton() {
        JButton button = new JButton();
        ImageIcon quit = new ImageIcon(PlayAgainButton.class.getClassLoader().getResource("images/replay.png"));
        Image img = quit.getImage();
        Image quit_resized = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(quit_resized));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }
}
