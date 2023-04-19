package playing.entities.dynamics.animal.predator.predators;

import playing.PlayingInterface;
import playing.entities.dynamics.rabbit.RabbitAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static Constants.Constants.GameConstants.GRAVITY;
import static Constants.Constants.TextureConstants.Entity.WOLF.WOLF_VIEW_RANGE;

public class WolfMove implements PlayingInterface {
    final Wolf wolf;
    private boolean left, right, jump, fall;
    private boolean onFloor;
    private float speedJump = -2.65f + GRAVITY;

    private final float speedWalk = 0.5f;
    private float ySpeed = 0;
    private float xSpeed = 0;

    public WolfMove(Wolf wolf){
        this.wolf = wolf;
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {}

    @Override
    public void update() {
        checkEnvironment();
        updatePos();
    }

    private void updatePos(){
        if (left)
            xSpeed += speedWalk; //ИСПРАВИТЬ
        if (right)
            xSpeed -= speedWalk; //ИСПРАВИТЬ
        if (onFloor){
            if (!wolf.getWolfEntity().isPlayerOnFloor())
                onFloor = false;
        } else {
            Rectangle2D.Double oldHitBox = wolf.getWolfEntity().getHitBox();
            Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                    oldHitBox.x,
                    oldHitBox.y + ySpeed,
                    oldHitBox.width,
                    oldHitBox.height
            );
            if (wolf.getWolfEntity().canMoveHere(newHitBox)){
                updateYPos(ySpeed);
                if (ySpeed > 0) {
                    wolf.getWolfAnimation().setAnimationState(WolfAnimation.WolfAnimationState.FALLING);
                } else if (ySpeed < 0) {
                    wolf.getWolfAnimation().setAnimationState(WolfAnimation.WolfAnimationState.JUMP);
                } else {
                    wolf.getWolfAnimation().setAnimationState(WolfAnimation.WolfAnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0){
                    onFloor = true;
                    wolf.getWolfAnimation().setAnimationState(WolfAnimation.WolfAnimationState.IDLE);
                }
                ySpeed = 0;
            }
        }

        Rectangle2D.Double oldHitBox = wolf.getWolfEntity().getHitBox();
        Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                oldHitBox.x + xSpeed, oldHitBox.y,
                oldHitBox.width, oldHitBox.height);
        //справлено!!!!!!!!!!!
        if (wolf.getWolfEntity().canMoveHere(newHitBox)){
            updateXPos(xSpeed);
        } else {
            changeWalkDir();
        }
        if (xSpeed == 0){
            if (wolf.getWolfAnimation().getAnimationState() == WolfAnimation.WolfAnimationState.RUNNING) {
                wolf.getWolfAnimation().setAnimationState(WolfAnimation.WolfAnimationState.IDLE);
            }
        } else {
            if (wolf.getWolfAnimation().getAnimationState() == WolfAnimation.WolfAnimationState.IDLE) {
                wolf.getWolfAnimation().setAnimationState(WolfAnimation.WolfAnimationState.RUNNING);
            }
        }
        xSpeed = 0;
    }

    private void changeWalkDir() {
        if (left) {
            left = false;
            right = true;
        } else if (right) {
            left = true;
            right = false;
        } else {
            left = true;
        }
    }

    private void checkEnvironment() {
        if (wolf.getWolfEntity().canSeePlayer(WOLF_VIEW_RANGE)) {
            turnTowardsPlayer();
        } else if (!right && !left) {
            left = true;
        }
    }

    private void turnTowardsPlayer() {
        right = false;
        left = false;
        if (wolf.getWolfEntity().wherePlayerX() > 0) {
            right = true;
        } else if (wolf.getWolfEntity().wherePlayerX() < 0) {
            left = true;
        }
    }

    private void updateXPos(double xSpeed) {
        wolf.getWolfEntity().setX(wolf.getWolfEntity().getX() + xSpeed);
    }

    private void updateYPos(double ySpeed) {
        wolf.getWolfEntity().setY(wolf.getWolfEntity().getY() + ySpeed);
    }
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
