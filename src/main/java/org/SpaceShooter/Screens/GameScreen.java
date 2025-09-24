package org.SpaceShooter.Screens;

import org.SpaceShooter.Components.Rockets.GameScreenRocket;
import org.SpaceShooter.Components.MovingParts.RocketMissile;
import org.SpaceShooter.Frames.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameScreen {

    private static final List<JPanel> missiles = new ArrayList<>();
    private static final int speed = 5;
    private static final int[] dx = {0};

    public GameScreen() {
        JFrame frame = Frame.getFrame();
        JPanel topPanel = getTopPanel(frame);
        JPanel rocketPanel = GameScreenRocket.getRocketImagePanel(frame);
        setMoves(rocketPanel,frame);
        startGame(rocketPanel,frame);

        {
            frame.add(topPanel);
            frame.add(rocketPanel);
        }

        frame.revalidate();
        frame.repaint();
    }

    private void startGame(JPanel panel, JFrame frame) {
        Timer timer = new Timer(20, e -> {
            shootMissiles(frame);
            GameScreenRocket.moveRocket(panel,frame,dx[0]);
        });
        timer.start();
    }

    private void shootMissiles(JFrame frame) {
        Iterator<JPanel> missileIterator = missiles.iterator();
        while (missileIterator.hasNext()) {
            JPanel missile = missileIterator.next();
            missile.setLocation(missile.getX(),missile.getY()-10);
            if (missile.getY()<=0) {
                missileIterator.remove();
                frame.remove(missile);
                frame.revalidate();
                frame.repaint();
            }
        }
    }

    private JPanel getTopPanel(JFrame frame) {
        JPanel panel = new JPanel(null);
//        panel.setBackground(Color.BLACK);
//        panel.setBounds(0,0,frame.getWidth(),150);
        return panel;
    }

    private static void setMoves(JPanel panel, JFrame frame) {

        Action moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dx[0] = -speed;
            }
        };

        Action stopMoveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dx[0] = 0;
            }
        };

        Action moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dx[0] = speed;
            }
        };

        Action stopMoveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dx[0] = 0;
            }
        };

        Action createMissile = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel missile = RocketMissile.getMissile(panel);
                frame.add(missile);
                frame.revalidate();
                frame.repaint();
                missiles.add(missile);
            }
        };

        InputMap im = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = frame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("pressed LEFT"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("released LEFT"), "stopMoveLeft");
        im.put(KeyStroke.getKeyStroke("pressed RIGHT"), "moveRight");
        im.put(KeyStroke.getKeyStroke("released RIGHT"), "stopMoveRight");
        im.put(KeyStroke.getKeyStroke("pressed S"), "createMissile");
        im.put(KeyStroke.getKeyStroke("pressed A"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("released A"), "stopMoveLeft");
        im.put(KeyStroke.getKeyStroke("pressed D"), "moveRight");
        im.put(KeyStroke.getKeyStroke("released D"), "stopMoveRight");
        im.put(KeyStroke.getKeyStroke("ENTER"), "createMissile");

        am.put("moveLeft", moveLeft);
        am.put("stopMoveLeft", stopMoveLeft);
        am.put("moveRight", moveRight);
        am.put("stopMoveRight", stopMoveRight);
        am.put("createMissile", createMissile);

    }
}
