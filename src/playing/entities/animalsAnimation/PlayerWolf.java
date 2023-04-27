package playing.entities.animalsAnimation;

import playing.entities.statics.Entity;
import playing.PlayingInterface;
import playing.PlayingKeyListenerInterface;
import playing.PlayingMouseListenerInterface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class PlayerWolf extends Entity implements PlayingInterface, PlayingMouseListenerInterface, PlayingKeyListenerInterface {
//todo разобраться с этим классом

    private WolfManager wolfManager;
    private WolfModuleManager wolfModuleManager;
    public PlayerWolf(WolfManager wolfManager, int x, int y) {
        super(x, y, 85, 50);
        this.wolfManager = wolfManager;
        initClasses();
    }

    private void initClasses(){
        wolfModuleManager = new WolfModuleManager(this);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        wolfModuleManager.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        wolfModuleManager.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        wolfModuleManager.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        wolfModuleManager.keyReleased(e);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wolfModuleManager.mouseClicked(e);
    }
    public void resetHorBooleans() {
        wolfModuleManager.resetHorBooleans();
    }

    public void resetVertBooleans() {
        wolfModuleManager.resetVertBooleans();
    }
//    public void kill() {
//        wolfModuleManager.kill();
//    }
    public boolean IsPlayerWolfOnFloor(Rectangle2D.Double hitBox) {
        return wolfManager.IsPlayerOnFloor(hitBox);
    }
    public boolean CanMoveHere(Rectangle2D.Double hitBox) {
        return wolfManager.CanMoveHere(hitBox);
    }

    public void eatEnemy(Rectangle2D.Double attackBox){
        wolfManager.eatEnemy(attackBox);
    }
}
