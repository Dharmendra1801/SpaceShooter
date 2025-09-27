package org.SpaceShooter.Components.Buttons;

import javax.swing.*;
import java.awt.*;

public class GameScreenPauseButton {
    public static JButton getPauseButton() {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }
    public static ImageIcon getIcon(boolean check) {
        ImageIcon play_2 = new ImageIcon("src/main/images/play-2.png");
        Image img = play_2.getImage();
        Image play_2_resized = img.getScaledInstance(45, 45, Image.SCALE_SMOOTH);

        ImageIcon pause = new ImageIcon("src/main/images/pause.png");
        Image img2 = pause.getImage();
        Image pause_resized = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);

        if (!check) return new ImageIcon(pause_resized);
        return new ImageIcon(play_2_resized);
    }
}
