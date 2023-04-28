package playing;

import gamestates.GamePanelInterface;
import playing.entities.EntityIslandManager;
import playing.entities.animalsAnimation.WolfManager;
import playing.entities.dynamics.EnemyManager;
import playing.entities.statics.ObjectManager;
import playing.island.Island;
import playing.island.IslandManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import static Constants.Constants.GameWindowConstants.*;

public class PlayingGame implements GamePanelInterface, PlayingMouseListenerInterface, PlayingKeyListenerInterface {
    private IslandManager islandManager;
    private WolfManager wolfManager;
    EntityIslandManager entityIslandManager;
    private Island island;
    ObjectManager objectManager;
    EnemyManager enemyManager;
    private int lvlOffsetX, lvlOffsetY;
    private int maxLvlOffsetX, maxLvlOffsetY;
    private float scale;

    public PlayingGame(){
        initClasses();
    }
    public void initClasses(){
        entityIslandManager = new EntityIslandManager(this);
        islandManager = new IslandManager();
        island = islandManager.getIsland();
        wolfManager = new WolfManager(entityIslandManager);
        enemyManager = new EnemyManager(entityIslandManager, island);
        objectManager = new ObjectManager(island);
        calcLvlOffset();
    }


    private void calcLvlOffset() {
        maxLvlOffsetX = islandManager.getLvlOffSetX();
        maxLvlOffsetY = islandManager.getLvlOffSetY();
    }


    @Override
    public void update() {
        islandManager.update();
        wolfManager.update();
        enemyManager.update();
        objectManager.update();
        checkCloseToBorder();
    }

    public void setIsland(){
        wolfManager = new WolfManager(entityIslandManager);
        wolfManager.setSpawnPlayer(100,  100);
        calcLvlOffset();
    }
    private void checkCloseToBorder() {
        int playerX = wolfManager.getPlayerWolfX();
        int diffX = playerX - lvlOffsetX;

        if (diffX > RIGHT_BORDER) {
            lvlOffsetX += diffX - RIGHT_BORDER;
        } else if (diffX < LEFT_BORDER) {
            lvlOffsetX += diffX - LEFT_BORDER;
        }

        if (lvlOffsetX > maxLvlOffsetX) {
            lvlOffsetX = maxLvlOffsetX;
        } else if( lvlOffsetX < 0) {
            lvlOffsetX = 0;
        }


        int playerY = wolfManager.getPlayerWolfY();
        int diffY = playerY - lvlOffsetY;

        if (diffY > DOWN_BORDER) {
            lvlOffsetY += diffY - DOWN_BORDER;
        } else if (diffY < TOP_BORDER) {
            lvlOffsetY += diffY - TOP_BORDER;
        }

        if (lvlOffsetY > maxLvlOffsetY) {
            lvlOffsetY = maxLvlOffsetY;
        } else if( lvlOffsetY < 0) {
            lvlOffsetY = 0;
        }

    }

    @Override
    public void draw(Graphics g, float scale) {
        this.scale = scale;
        islandManager.draw(g, scale, lvlOffsetX, lvlOffsetY);
        objectManager.draw(g, scale, lvlOffsetX, lvlOffsetY);
        wolfManager.draw(g, scale, lvlOffsetX, lvlOffsetY);
        enemyManager.draw(g, scale, lvlOffsetX, lvlOffsetY);
    }

    public void eatEnemy(Rectangle2D.Double attackBox){
        enemyManager.eatEnemy(attackBox);
    }

    public Island getIsland() {
        return island;
    }

    public IslandManager getLevelManager() {
        return islandManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        wolfManager.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        wolfManager.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wolfManager.mouseClicked(e);
    }
    public Rectangle2D.Double getPlayerHitBox() {
        return wolfManager.getPlayerHitBox();
    }

    public void resetAll() {
        wolfManager.resetAll();
    }

    public void resetHorBooleans() {
        wolfManager.resetHorBooleans();
    }
    public void resetVertBooleans() {
        wolfManager.resetVertBooleans();
    }

    public void eatAnimal(){

    }
//    public void attackEnemy(Rectangle2D.Double attackBox){
//        enemyManager.attackEnemy(attackBox);
//    }
}
