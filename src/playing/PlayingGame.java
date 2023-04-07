package playing;

import gamestates.GamePanelInterface;
import playing.island.Island;
import playing.island.IslandManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class PlayingGame implements GamePanelInterface, PlayingMouseListenerInterface, PlayingKeyListenerInterface {
    private IslandManager islandManager;
    private Island island;
    private int lvlOffsetX, lvlOffsetY;
    private int maxLvlOffsetX, maxLvlOffsetY;
    private float scale;
    private Line2D.Double shotBox;

    public PlayingGame(){
        initClasses();
    }
    public void initClasses(){
        islandManager = new IslandManager();

    }
    private void calcLvlOffset() {
        maxLvlOffsetX = islandManager.getLvlOffSetX();
        maxLvlOffsetY = islandManager.getLvlOffSetY();
    }



    @Override
    public void update() {
        islandManager.update();
    }

    @Override
    public void draw(Graphics g, float scale) {
        this.scale = scale;
        islandManager.draw(g, scale, lvlOffsetX, lvlOffsetY);
    }

    public IslandManager getLevelManager() {
        return islandManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void ketReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
