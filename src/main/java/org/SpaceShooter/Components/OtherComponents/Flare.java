package org.SpaceShooter.Components.OtherComponents;

import javax.swing.*;
import java.awt.*;

public class Flare {

    private int timeLeft;
    private JPanel flare;

    public int getTimeLeft() {
        return timeLeft--;
    }

    public Flare(int delay) {
        timeLeft = 200/delay;
    }

    public  JPanel getFlare() {

        if(flare!=null) return flare;

        flare = new JPanel(null){
            final Image background = new ImageIcon("src/main/images/flare.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // fill panel
            }
        };
        flare.setOpaque(false);
        return flare;
    }
}
