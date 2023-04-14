package main;

import java.awt.*;
import static Constants.Constants.GameWindowConstants.*;

public class Game implements Runnable, MainGameInterface{
    private final float scale = 1.5f; //масштаб игры
    private GamePanel gamePanel;
    private boolean isGameExit;
    private GameDistribution gameDistribution;


    public Game(){
        initClasses();
        startGameLoop();
    }


    private void initClasses() {
        gameDistribution = new GameDistribution(this);
        gamePanel = new GamePanel(this);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        new GameWindow(gamePanel);
    }

    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void update() {
        gameDistribution.update();
    }

    @Override
    public void draw(Graphics g) {
        gameDistribution.draw(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();


        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (!isGameExit) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
            }
        }
    }
    public void setGameExit() {
        isGameExit = true;
    }

    public float getScale() {
        return scale;
    }
    public GameDistribution getGameDistribution() {
        return gameDistribution;
    }

    public void windowFocusLost() {
        gameDistribution.windowFocusLost();
    }
}
