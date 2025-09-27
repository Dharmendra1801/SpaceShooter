package org.SpaceShooter.Components.OtherComponents;

import javax.swing.*;
import java.awt.*;

public class ScoreLabels {
    public static JLabel getScoreLabel(int score) {
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(15,5,200,40);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.PLAIN, 25));
        return scoreLabel;
    }
}
