package org.SpaceShooter.Components.Buttons;

import javax.swing.*;
import java.awt.*;

public class QuitButton {
    public static JButton getQuitButton() {
        JButton button = new JButton();
        ImageIcon quit = new ImageIcon(QuitButton.class.getClassLoader().getResource("images/quit.png"));
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
