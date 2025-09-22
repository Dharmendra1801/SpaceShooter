package org.SpaceShooter.Screens;

import org.SpaceShooter.Frame;

import javax.swing.*;
import java.awt.*;

public class HomeScreen {
    public HomeScreen() {

        JFrame frame = Frame.getFrame();
        JPanel rocketImagePanel = getRocketImagePanel(frame);
        JPanel titlePanel = getTitlePanel();
        JButton startButton = getStarButton();
        {
            frame.add(rocketImagePanel);
            frame.add(titlePanel);
            frame.add(startButton);
        }
        {
            frame.revalidate();
            frame.repaint();
        }
    }
    private JPanel getTitlePanel() {
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
    private JButton getStarButton() {
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
        startButton.addActionListener(e -> System.out.println("click!!!!"));
        return startButton;
    }
    private JPanel getRocketImagePanel(JFrame frame) {
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
    private void movingRocketImagePanel(JPanel panel, JFrame frame) {
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
