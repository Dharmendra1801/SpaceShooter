package org.SpaceShooter.Screens;

import org.SpaceShooter.Components.Buttons.HomeScreenStartButton;
import org.SpaceShooter.Components.Panels.HomeScreenTitlePanel;
import org.SpaceShooter.Components.Rockets.HomeScreenRocket;
import org.SpaceShooter.Frames.Frame;

import javax.swing.*;
import java.awt.*;

public class HomeScreen {
    public HomeScreen() {
        JFrame frame = Frame.getFrame();
        JPanel rocketImagePanel = HomeScreenRocket.getRocketImagePanel(frame);
        JPanel titlePanel = HomeScreenTitlePanel.getTitlePanel();
        JButton startButton = HomeScreenStartButton.getStarButton(frame);
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
}
