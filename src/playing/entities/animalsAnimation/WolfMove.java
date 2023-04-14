package playing.entities.animalsAnimation;

import playing.PlayingInterface;
import playing.PlayingKeyListenerInterface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import static Constants.Constants.GameConstants.GRAVITY;
import static Constants.Constants.GameConstants.TEMP_GRAVITY;

public class WolfMove extends WolfModule implements PlayingKeyListenerInterface, PlayingInterface {
//todo разобраться с этим классом

    private boolean left, right, jump, fall;
    private boolean onFloor;
    private float speedJump = -2.65f + GRAVITY;

    private final float speedWalk = 1.f;
    private float ySpeed = 0;
    private float xSpeed = 0;
    public WolfMove(WolfModuleManager wolfModuleManager){
        super(wolfModuleManager);
    }

    @Override
    public void update() {
        updatePos();
    }
    private void updatePos(){
        if (jump){
            System.out.println("jump");
            if (onFloor){
                onFloor = false;
                ySpeed = speedJump;
            }
            if (TEMP_GRAVITY == 0.0f)
                GRAVITY = TEMP_GRAVITY;
        }
        if (left){
            xSpeed -= speedWalk;
        }
        if (right){
            xSpeed += speedWalk;
        }
        if (fall){
            ySpeed = 1.5f;
            GRAVITY = 0.035f;
        }
        if (onFloor){
            if (!wolfModuleManager.IsPlayerWolfOnFloor())
                onFloor = false;
        } else {
            Rectangle2D.Double oldHitBox = wolfModuleManager.getHitBox();
            Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                    oldHitBox.x, oldHitBox.y + ySpeed,
                    oldHitBox.width, oldHitBox.height);
            if (wolfModuleManager.CanMoveHere(newHitBox)){
                updateYPos(ySpeed);
                if (ySpeed > 0){
                    wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.FALLING);
                } else if (ySpeed < 0){
                    wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.JUMP);
                } else {
                    wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0) {
                    onFloor = true;
                    wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.IDLE);
                }
                ySpeed = 0;
            }
        }
        Rectangle2D.Double oldHitBox = wolfModuleManager.getHitBox();
        Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                oldHitBox.x + xSpeed, oldHitBox.y,
                oldHitBox.width, oldHitBox.height);
        if (wolfModuleManager.CanMoveHere(newHitBox)) {
            updateXPos(xSpeed);
        }
        if (xSpeed == 0) {
            if (wolfModuleManager.getWolfAnimation().getAnimationState() == WolfAnimation.AnimationState.RUNNING) {
                wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.IDLE);
            }
        } else {
            if (wolfModuleManager.getWolfAnimation().getAnimationState() == WolfAnimation.AnimationState.IDLE) {
                wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.RUNNING);
            }
        }
        xSpeed = 0;
    }
    private void updateXPos(double xSpeed) {
        wolfModuleManager.updateHitBoxX(xSpeed);
    }

    private void updateYPos(double ySpeed) {
        wolfModuleManager.updateHitBoxY(ySpeed);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                setRight(true);
                break;
            case KeyEvent.VK_UP:
                setJump(true);
                break;
            case KeyEvent.VK_DOWN:
                setFall(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                setRight(false);
                break;
            case KeyEvent.VK_UP:
                setJump(false);
                break;
            case KeyEvent.VK_DOWN:
                setFall(false);
                break;
        }
    }
    public void resetVertBooleans() {
        setJump(false);
        setFall(false);
    }

    public void resetHorBooleans() {
        setRight(false);
        setLeft(false);
    }
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }


    @Override
    public void draw(Graphics g, float scale, int x, int y) {}
}
