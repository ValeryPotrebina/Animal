package playing.entities.animalsAnimation;

import playing.PlayingInterface;
import playing.PlayingKeyListenerInterface;
import playing.PlayingMouseListenerInterface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class WolfModuleManager implements PlayingInterface, PlayingKeyListenerInterface, PlayingMouseListenerInterface {
//todo разобраться с этим классом

    private PlayerWolf playerWolf;
    private WolfAnimation wolfAnimation;
    private WolfMove wolfMove;
    private WolfListener wolfListener;
    private WolfAttack wolfAttack;

    public WolfModuleManager(PlayerWolf playerWolf){
        this.playerWolf = playerWolf;
        initClasses();
    }

    private void initClasses(){
        wolfListener = new WolfListener(this);
        wolfAnimation = new WolfAnimation(this);
        wolfMove = new WolfMove(this);
        wolfAttack = new WolfAttack(this,
                (int) (playerWolf.getHitBox().x + playerWolf.getHitBox().width) + 3,
                (int) playerWolf.getHitBox().y, 20 ,20);
    }
//todo что это???
    public void updateHitBoxX(double x) {
        playerWolf.setX(playerWolf.getX() + x);
    }

    public void updateHitBoxY(double y) {
        playerWolf.setY(playerWolf.getY() + y);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        playerWolf.drawHitBox(g, scale, x, y);
        wolfAttack.draw(g, scale, x, y);
        wolfAnimation.draw(g, scale, x, y);

    }

    @Override
    public void update() {
        wolfMove.update();
        wolfAttack.update();
        wolfAnimation.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        wolfListener.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        wolfListener.keyReleased(e);

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        wolfListener.mouseClicked(e);

    }
    public WolfMove getPlayerMove() {
        return wolfMove;
    }
    public WolfAnimation getWolfAnimation() {
        return wolfAnimation;
    }
    public void resetHorBooleans() {
        wolfMove.resetHorBooleans();
    }

    public void resetVertBooleans() {
        wolfMove.resetVertBooleans();
    }
    public boolean IsPlayerWolfOnFloor() {
        return playerWolf.IsPlayerWolfOnFloor(playerWolf.getHitBox());
    }
    public boolean CanMoveHere(Rectangle2D.Double hitBox) {
        return playerWolf.CanMoveHere(hitBox);
    }

    public Rectangle2D.Double getHitBox() {
        return playerWolf.getHitBox();
    }

    public void eatEnemy(Rectangle2D.Double attackBox){
        playerWolf.eatEnemy(attackBox);
    }

    public WolfAttack getWolfAttack() {
        return wolfAttack;
    }
}
