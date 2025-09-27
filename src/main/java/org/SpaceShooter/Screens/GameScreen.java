package org.SpaceShooter.Screens;

import org.SpaceShooter.Components.Buttons.GameScreenPauseButton;
import org.SpaceShooter.Components.Buttons.GameScreenQuitButton;
import org.SpaceShooter.Components.OtherComponents.AlienShip;
import org.SpaceShooter.Components.OtherComponents.Flare;
import org.SpaceShooter.Components.OtherComponents.ScoreLabels;
import org.SpaceShooter.Components.Panels.GameScreenDashboard;
import org.SpaceShooter.Components.Rockets.GameScreenRocket;
import org.SpaceShooter.Components.OtherComponents.RocketMissile;
import org.SpaceShooter.Frames.Frame;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GameScreen {

    private static List<JPanel> missiles;
    private static List<JPanel> alienShips;
    private static List<Queue<JPanel>> alienShipsList;
    private static List<Flare> flares;
    private static int speed;
    private static int[] dx;
    private static int time;
    private static int timeGap;
    private static int livesLeft;
    private static Timer timer;
    private static int delay;
    private static int score;
    private static JLabel lastLabel;
    private static boolean pauseButtonCheck;

    public GameScreen() {
        initialise();
        JFrame frame = Frame.getFrame();
        JPanel dashboard = GameScreenDashboard.getDashboard(frame);
        JPanel rocketPanel = GameScreenRocket.getRocketImagePanel(frame);
        managePauseButton(dashboard,frame);
        setMoves(rocketPanel,frame);
        initialiseAlienShipsList(frame,rocketPanel);
        startGame(rocketPanel,frame,dashboard);
        {
            frame.add(dashboard);
            frame.add(rocketPanel);
        }

        frame.revalidate();
        frame.repaint();
    }

    private void managePauseButton(JPanel dashboard, JFrame frame) {
        JButton pauseButton = GameScreenPauseButton.getPauseButton();
        pauseButton.setIcon(GameScreenPauseButton.getIcon(!pauseButtonCheck));
        pauseButton.setBackground(Color.BLACK);
        dashboard.add(pauseButton);
        pauseButton.setBounds(275,5,45,45);
        final JButton[] quitButton = new JButton[1];
        pauseButton.addActionListener(e -> {
            if (pauseButtonCheck) {
                timer.stop();
                pauseButton.setIcon(GameScreenPauseButton.getIcon(pauseButtonCheck));
                pauseButtonCheck = false;
                quitButton[0] = addQuitButton(frame);
            }
            else {
                timer.start();
                pauseButton.setIcon(GameScreenPauseButton.getIcon(pauseButtonCheck));
                pauseButtonCheck = true;
                removeQuitButton(quitButton[0],frame);
            }
        });
        frame.revalidate();
        frame.repaint();
    }

    private void removeQuitButton(JButton jButton, JFrame frame) {
        frame.remove(jButton);
        frame.revalidate();
        frame.repaint();
    }

    private JButton addQuitButton(JFrame frame) {
        JButton quitButton = GameScreenQuitButton.getQuitButton();
        quitButton.addActionListener(e -> quitGame(frame));
        quitButton.setBounds(260,280,100,100);
        frame.add(quitButton);
        frame.revalidate();
        frame.repaint();
        return quitButton;
    }

    private void initialise() {
        missiles = new ArrayList<>();
        alienShips = new ArrayList<>();
        alienShipsList = new ArrayList<>();
        flares = new ArrayList<>();
        speed = 5;
        dx = new int[]{0};
        time = 0;
        timeGap = 80;
        livesLeft = 3;
        delay = 20;
        score = 0;
        pauseButtonCheck = true;
    }

    private void initialiseAlienShipsList(JFrame frame, JPanel rocketPanel) {
        for (int i=0; i<(frame.getWidth()/ rocketPanel.getWidth()); i++) {
            alienShipsList.add(new LinkedList<>());
        }
    }

    private void startGame(JPanel panel, JFrame frame, JPanel dashboard) {
        timer = new Timer(delay, e -> {
            time++;
            timeGapChange();
            GameScreenRocket.moveRocket(panel,frame,dx[0]);
            createALienShips(panel,frame);
            moveMissiles(frame);
            moveAlienShip(frame);
            checkFlares(frame);
            updateScore(score,dashboard,frame);
            checkLives(frame);
        });
        timer.start();
    }

    private void updateScore(int score, JPanel dashboard, JFrame frame) {
        if (lastLabel!=null) dashboard.remove(lastLabel);
        lastLabel = ScoreLabels.getScoreLabel(score);
        dashboard.add(lastLabel);
        frame.revalidate();
        frame.repaint();
    }

    private void checkFlares(JFrame frame) {
        Iterator<Flare> flareIterator = flares.iterator();
        while (flareIterator.hasNext()) {
            Flare flarePojo = flareIterator.next();
            if (flarePojo.getTimeLeft()<=0) {
                flareIterator.remove();
                frame.remove(flarePojo.getFlare());
                frame.revalidate();
                frame.repaint();
            }
        }
    }

    private void checkLives(JFrame frame) {
        if (livesLeft<=0) {
            quitGame(frame);
        }
    }

    private void quitGame(JFrame frame) {
        timer.stop();
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        new HomeScreen();
    }

    private void timeGapChange() {
        if (time<1500 && time>750) {
            timeGap = 60;
            return;
        }
        if (time<3000 && time>=1500) {
            timeGap = 45;
            return;
        }
        if (time>=3000) {
            timeGap = 30;
        }
    }

    private void moveAlienShip(JFrame frame) {
        if (time%5!=0) return;
        Iterator<JPanel> alienShipsIterator = alienShips.iterator();
        while (alienShipsIterator.hasNext()) {
            JPanel alienShip = alienShipsIterator.next();
            alienShip.setLocation(alienShip.getX(),alienShip.getY()+5);
            if (alienShip.getY()+alienShip.getHeight()>=frame.getHeight()) {
                alienShipsIterator.remove();
                removeAlienShip(alienShip,frame);
                livesLeft--;
            }
        }
    }

    private void removeAlienShip(JPanel alienShip, JFrame frame) {
        int x = X(alienShip);
        alienShipsList.get(x).poll();
        frame.remove(alienShip);
        frame.revalidate();
        frame.repaint();
    }

    private void createALienShips(JPanel panel, JFrame frame) {
        if (time%timeGap!=0) return;
        JPanel alienShip = AlienShip.getAlienShip(panel,frame);
        putAlienInList(alienShip);
        alienShips.add(alienShip);
        frame.add(alienShip);
        frame.revalidate();
        frame.repaint();
    }

    private void putAlienInList(JPanel alienShip) {
        int x = X(alienShip);
        alienShipsList.get(x).add(alienShip);
    }

    private void addFlare(JPanel alienShip, JFrame frame) {
        Flare flarePOJO = new Flare(delay);
        flares.add(flarePOJO);
        flarePOJO.getFlare().setBounds(alienShip.getX(),alienShip.getY(),alienShip.getWidth(),alienShip.getHeight());
        frame.add(flarePOJO.getFlare());
        frame.revalidate();
        frame.repaint();
    }

    private void moveMissiles(JFrame frame) {
        Iterator<JPanel> missileIterator = missiles.iterator();
        while (missileIterator.hasNext()) {
            JPanel missile = missileIterator.next();
            missile.setLocation(missile.getX(),missile.getY()-10);
            if (!alienShipsList.get(missileX(missile)).isEmpty() && alienShipsList.get(missileX(missile)).peek().getY()+10>=missile.getY()) {
                score++;
                JPanel alienShip = alienShipsList.get(missileX(missile)).peek();
                addFlare(alienShip,frame);
                alienShips.remove(alienShip);
                removeAlienShip(alienShip,frame);
                removeMissile(missileIterator,missile,frame);
            }
            if (missile.getY()<=0) {
                removeMissile(missileIterator,missile,frame);
            }
        }
    }

    private void removeMissile(Iterator<JPanel> missileIterator, JPanel missile, JFrame frame) {
        missileIterator.remove();
        frame.remove(missile);
        frame.revalidate();
        frame.repaint();
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

        final boolean[] buttonSPressed = {false};

        Action createMissile = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!buttonSPressed[0]) {
                    JPanel missile = RocketMissile.getMissile(panel);
                    frame.add(missile);
                    frame.revalidate();
                    frame.repaint();
                    missiles.add(missile);
                    buttonSPressed[0] = true;
                }
            }
        };

        Action releasedS = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                buttonSPressed[0] = false;
            }
        };

        InputMap im = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = frame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("pressed LEFT"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("released LEFT"), "stopMoveLeft");
        im.put(KeyStroke.getKeyStroke("pressed RIGHT"), "moveRight");
        im.put(KeyStroke.getKeyStroke("released RIGHT"), "stopMoveRight");
        im.put(KeyStroke.getKeyStroke("pressed S"), "createMissile");
        im.put(KeyStroke.getKeyStroke("released S"), "releasedS");
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
        am.put("releasedS", releasedS);

    }

    private static int X(JPanel panel) {
        return panel.getX()/panel.getWidth();
    }
    private static int missileX(JPanel panel) {
        return (panel.getX()+(panel.getWidth()/2))/panel.getWidth();
    }
}
