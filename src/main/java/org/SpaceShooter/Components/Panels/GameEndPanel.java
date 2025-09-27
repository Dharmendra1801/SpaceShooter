package org.SpaceShooter.Components.Panels;

import org.SpaceShooter.Components.Buttons.PlayAgainButton;
import org.SpaceShooter.Components.Buttons.QuitButton;
import org.SpaceShooter.Frames.Frame;
import org.SpaceShooter.Screens.GameScreen;

import javax.swing.*;
import java.awt.*;

public class GameEndPanel {
    public static JPanel getEndPanel(JFrame frame, int score) {
        JPanel panel = new JPanel(null);
        panel.setBounds(100,180,400,300);
        panel.setOpaque(false);
        panel.add(getScoreLabel(score));
        panel.add(getHighScoreLabel(Frame.getHighScore(score)));
        JButton replayButton = PlayAgainButton.getPlayAgainButton();
        JButton quitButton = QuitButton.getQuitButton();
        quitButton.addActionListener(e -> GameScreen.quitGame(frame));
        replayButton.addActionListener(e -> new GameScreen());
        replayButton.setBounds(50,200,100,100);
        quitButton.setBounds(250,200,100,100);
        panel.add(replayButton);
        panel.add(quitButton);
        return panel;
    }
    public static JLabel getScoreLabel(int score) {
        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setBounds(100,100,300,40);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.PLAIN, 30));
        return scoreLabel;
    }
    public static JLabel getHighScoreLabel(int score) {
        JLabel scoreLabel = new JLabel("High Score: " + score);
        scoreLabel.setBounds(100,0,300,40);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.PLAIN, 30));
        return scoreLabel;
    }
}
