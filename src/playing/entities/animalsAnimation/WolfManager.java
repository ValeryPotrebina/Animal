package playing.entities.animalsAnimation;

import playing.PlayingInterface;
import playing.PlayingKeyListenerInterface;
import playing.PlayingMouseListenerInterface;
import playing.entities.statics.EntityIslandManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class WolfManager implements PlayingInterface, PlayingKeyListenerInterface, PlayingMouseListenerInterface {
//todo разобраться с этим классом

    private EntityIslandManager entityIslandManager;
    private PlayerWolf playerWolf;

    public WolfManager(EntityIslandManager entityIslandManager){
        this.entityIslandManager = entityIslandManager;
        initClasses();
    }

    private void initClasses(){
        //задаем начальное положение волка
        playerWolf = new PlayerWolf(this, 100, 140);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        playerWolf.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        playerWolf.update();
    }
    public void resetAll() {
        playerWolf = new PlayerWolf(this, 100, 100);
    }

    public void resetHorBooleans() {
        playerWolf.resetHorBooleans();
    }

    public void resetVertBooleans() {
        playerWolf.resetVertBooleans();
    }

    public PlayerWolf getPlayerWolf(){
        return playerWolf;
    }


    public boolean IsPlayerOnFloor(Rectangle2D.Double hitBox) {
        return entityIslandManager.IsPlayerOnFloor(hitBox);
    }

    public boolean CanMoveHere(Rectangle2D.Double hitBox) {
        return entityIslandManager.CanMoveHere(hitBox);
    }

    public int getPlayerWolfX() {
        return (int) playerWolf.getX();
    }

    public int getPlayerWolfY() {
        return (int) playerWolf.getY();
    }
    public Rectangle2D.Double getPlayerHitBox() {
        return playerWolf.getHitBox();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        playerWolf.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerWolf.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        playerWolf.mouseClicked(e);
    }
    public void setSpawnPlayer(int x, int y) {
        playerWolf.setX(x);
        playerWolf.setY(y);
    }
}
