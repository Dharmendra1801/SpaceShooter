package org.SpaceShooter.Components.Panels;

import org.SpaceShooter.Components.Buttons.GameScreenPauseButton;
import org.SpaceShooter.Components.OtherComponents.ScoreLabels;

import javax.swing.*;
import java.awt.*;

public class GameScreenDashboard {
    public static JPanel getDashboard(JFrame frame) {
        JPanel dashboard = new JPanel(null);
        dashboard.setBackground(Color.BLACK);
        dashboard.setBounds(0,0,frame.getWidth(),50);
        return dashboard;
    }
}
