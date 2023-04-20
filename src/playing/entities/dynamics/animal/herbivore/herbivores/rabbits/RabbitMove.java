package playing.entities.dynamics.animal.herbivore.herbivores.rabbits;

import playing.PlayingInterface;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static Constants.Constants.GameConstants.GRAVITY;
import static Constants.Constants.TextureConstants.Entity.RABBIT.RABBIT_VIEW_RANGE;

public class RabbitMove implements PlayingInterface {
    final Rabbit rabbit;
    private boolean left, right;
    private boolean onFloor;
    private final float speedWalk = 0.15f;
    private float xSpeed = 0;
    private float ySpeed = 0;
    public RabbitMove(Rabbit rabbit) {
        this.rabbit = rabbit;
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
            xSpeed -= speedWalk;
        if (right)
            xSpeed += speedWalk;
        if (onFloor){
            if (!rabbit.isPlayerOnFloor())
                onFloor = false;
        } else {
            Rectangle2D.Double oldHitBox = rabbit.getHitBox();
            Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                    oldHitBox.x,
                    oldHitBox.y + ySpeed,
                    oldHitBox.width,
                    oldHitBox.height
            );
            if (rabbit.canMoveHere(newHitBox)){
                updateYPos(ySpeed);
                if (ySpeed > 0) {
                    rabbit.getRabbitAnimation().setAnimationState(RabbitAnimation.RabbitAnimationState.FALLING);
                } else if (ySpeed < 0) {
                    rabbit.getRabbitAnimation().setAnimationState(RabbitAnimation.RabbitAnimationState.JUMP);
                } else {
                    rabbit.getRabbitAnimation().setAnimationState(RabbitAnimation.RabbitAnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0){
                    onFloor = true;
                    rabbit.getRabbitAnimation().setAnimationState(RabbitAnimation.RabbitAnimationState.IDLE);
                }
                ySpeed = 0;
            }
        }

        Rectangle2D.Double oldHitBox = rabbit.getHitBox();
        Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                oldHitBox.x + xSpeed, oldHitBox.y,
                oldHitBox.width, oldHitBox.height);
        //справлено!!!!!!!!!!!
        if (rabbit.canMoveHere(newHitBox)){
            updateXPos(xSpeed);
        } else {
            changeWalkDir();
        }
        if (xSpeed == 0){
            if (rabbit.getRabbitAnimation().getAnimationState() == RabbitAnimation.RabbitAnimationState.RUNNING) {
                rabbit.getRabbitAnimation().setAnimationState(RabbitAnimation.RabbitAnimationState.IDLE);
            }
        } else {
            if (rabbit.getRabbitAnimation().getAnimationState() == RabbitAnimation.RabbitAnimationState.IDLE) {
                rabbit.getRabbitAnimation().setAnimationState(RabbitAnimation.RabbitAnimationState.RUNNING);
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
        if (rabbit.canSeePlayer(rabbit)) {
            turnTowardsPlayer();
        } else if (!right && !left) {
            left = true;
        }
    }

    private void turnTowardsPlayer() {
        right = false;
        left = false;
        if (rabbit.wherePlayerX() > 0) {
            right = true;
        } else if (rabbit.wherePlayerX() < 0) {
            left = true;
        }
    }

    private void updateXPos(double xSpeed) {
        rabbit.setX(rabbit.getX() + xSpeed);
    }

    private void updateYPos(double ySpeed) {
        rabbit.setY(rabbit.getY() + ySpeed);
    }
    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
