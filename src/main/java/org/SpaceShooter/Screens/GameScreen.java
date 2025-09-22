package org.SpaceShooter.Screens;

import org.SpaceShooter.Frame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameScreen {
    public void show() {
        JFrame frame = Frame.getFrame();
        JPanel panel = new JPanel() {
            final Image background = new ImageIcon("src/main/images/GameAreaBackground.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        panel.setBounds(100,100,300,400);
        panel.setLayout(null);
        {
            JButton button = new JButton("Click Me");
            button.addActionListener(e -> System.out.println(e.toString()));
            button.setBounds(50, 50, 100, 10);
            panel.setBackground(Color.BLACK);
            panel.add(button);
        }
        panel.setBorder(new LineBorder(Color.GRAY,2));
        frame.add(panel);
        frame.setMinimumSize(frame.getSize());
        frame.revalidate(); // re-do layout
        frame.repaint();
    }
}
