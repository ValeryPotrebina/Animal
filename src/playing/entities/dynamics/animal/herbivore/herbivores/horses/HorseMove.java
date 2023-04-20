package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.herbivore.herbivores.rabbits.Rabbit;
import playing.entities.dynamics.animal.herbivore.herbivores.rabbits.RabbitAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static Constants.Constants.GameConstants.GRAVITY;

public class HorseMove implements PlayingInterface {
    final Horse horse;
    private boolean left, right;
    private boolean onFloor;
    private final float speedWalk = 0.65f;
    private float xSpeed = 0;
    private float ySpeed = 0;
    public HorseMove(Horse horse) {
        this.horse = horse;
    }
    @Override
    public void update() {
        checkEnvironment();
        updatePos();
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
    }

    private void updatePos(){
        if (left)
            xSpeed += speedWalk;
        if (right)
            xSpeed -= speedWalk;
        if (onFloor){
            if (!horse.isPlayerOnFloor())
                onFloor = false;
        } else {
            Rectangle2D.Double oldHitBox = horse.getHitBox();
            Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                    oldHitBox.x,
                    oldHitBox.y + ySpeed,
                    oldHitBox.width,
                    oldHitBox.height
            );
            if (horse.canMoveHere(newHitBox)){
                updateYPos(ySpeed);
                if (ySpeed > 0) {
                    horse.getHorseAnimation().setAnimationState(HorseAnimation.HorseAnimationState.FALLING);
                } else if (ySpeed < 0) {
                    horse.getHorseAnimation().setAnimationState(HorseAnimation.HorseAnimationState.JUMP);
                } else {
                    horse.getHorseAnimation().setAnimationState(HorseAnimation.HorseAnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0){
                    onFloor = true;
                    horse.getHorseAnimation().setAnimationState(HorseAnimation.HorseAnimationState.IDLE);
                }
                ySpeed = 0;
            }
        }

        Rectangle2D.Double oldHitBox = horse.getHitBox();
        Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                oldHitBox.x + xSpeed, oldHitBox.y,
                oldHitBox.width, oldHitBox.height);
        //справлено!!!!!!!!!!!
        if (horse.canMoveHere(newHitBox)){
            updateXPos(xSpeed);
        } else {
            changeWalkDir();
        }
        if (xSpeed == 0){
            if (horse.getHorseAnimation().getAnimationState() == HorseAnimation.HorseAnimationState.RUNNING) {
                horse.getHorseAnimation().setAnimationState(HorseAnimation.HorseAnimationState.IDLE);
            }
        } else {
            if (horse.getHorseAnimation().getAnimationState() == HorseAnimation.HorseAnimationState.IDLE) {
                horse.getHorseAnimation().setAnimationState(HorseAnimation.HorseAnimationState.RUNNING);
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
        if (horse.canSeePlayer(horse)) {
            turnTowardsPlayer();
        } else if (!right && !left) {
            left = true;
        }
    }

    private void turnTowardsPlayer() {
        right = false;
        left = false;
        if (horse.wherePlayerX() > 0) {
            right = true;
        } else if (horse.wherePlayerX() < 0) {
            left = true;
        }
    }

    private void updateXPos(double xSpeed) {
        horse.setX(horse.getX() + xSpeed);
    }

    private void updateYPos(double ySpeed) {
        horse.setY(horse.getY() + ySpeed);
    }
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
