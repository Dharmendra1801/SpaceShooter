package org.SpaceShooter.Components.Panels;

import javax.swing.*;
import java.awt.*;

public class GameScreenDashboard {
    public static JPanel getDashboard(JFrame frame) {
        JPanel dashboard = new JPanel(null);
        dashboard.setBackground(Color.WHITE);
        dashboard.setBounds(0,0,frame.getWidth(),100);
        return dashboard;
    }
}
