package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.AnimalAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static Constants.Constants.GameConstants.GRAVITY;
import static Constants.Constants.GameConstants.TEMP_GRAVITY;

public class WolfMove implements PlayingInterface {
    final Wolf wolf;


    private boolean left, right, jump, fall;
    private boolean onFloor;
    private float speedJump = -2.65f + GRAVITY;

    private final float speedWalk = 0.5f;
    private float ySpeed = 0;
    private float xSpeed = 0;

    public WolfMove(Wolf wolf) {
        this.wolf = wolf;
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
    }

    @Override
    public void update() {
        checkEnvironment();
        updatePos();
    }

    private void updatePos() {
        if (jump) {
            if (onFloor) {
                onFloor = false;
                ySpeed = speedJump;
            }
            if (TEMP_GRAVITY == 0.0f)
                GRAVITY = TEMP_GRAVITY;
        }
        if (left)
            xSpeed += speedWalk; //ИСПРАВИТЬ
        if (right)
            xSpeed -= speedWalk; //ИСПРАВИТЬ
        if (fall) {
            ySpeed = 1.5f;
            GRAVITY = 0.035f;
        }
        if (onFloor) {
            if (!wolf.isPlayerOnFloor())
                onFloor = false;
        } else {
            Rectangle2D.Double oldHitBox = wolf.getHitBox();
            Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                    oldHitBox.x,
                    oldHitBox.y + ySpeed,
                    oldHitBox.width,
                    oldHitBox.height
            );
            if (wolf.canMoveHere(newHitBox)) {
                updateYPos(ySpeed);
                if (ySpeed > 0) {
                    wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.FALLING);
                } else if (ySpeed < 0) {
                    wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.JUMP);
                } else {
                    wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0) {
                    onFloor = true;
                    wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
                }
                ySpeed = 0;
            }
        }

        Rectangle2D.Double oldHitBox = wolf.getHitBox();
        Rectangle2D.Double newHitBox = new Rectangle2D.Double(
                oldHitBox.x + xSpeed, oldHitBox.y,
                oldHitBox.width, oldHitBox.height);
        //справлено!!!!!!!!!!!
        if (wolf.canMoveHere(newHitBox)) {
            updateXPos(xSpeed);
        } else {
            changeWalkDir();
        }
        if (xSpeed == 0) {
            if (wolf.getWolfAnimation().getAnimationState() == AnimalAnimation.AnimationState.RUNNING) {
                wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
            }
        } else {
            if (wolf.getWolfAnimation().getAnimationState() == AnimalAnimation.AnimationState.IDLE) {
                wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.RUNNING);
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
        List<Animal> seenAnimals = wolf.getSeenAnimals(wolf);
        if (seenAnimals != null && seenAnimals.size() != 0) {
            List<Animal> eatenAnimals = wolf.getEatenAnimals(wolf, seenAnimals);
            if (eatenAnimals != null && eatenAnimals.size() != 0) {
                Animal otherAnimal = wolf.chooseOneAnimalWhichCanEat(eatenAnimals);
                turnTowardsPlayer(otherAnimal);
            }
        } else if (!right && !left) {
            left = true;
        } else {
//            setJump(false);
        }
    }


    private void turnTowardsPlayer(Animal otherAnimal) {
        right = false;
        left = false;
        if (wolf.wherePlayerX(wolf, otherAnimal) > 0) {
            right = true;
        } else if (wolf.wherePlayerX(wolf, otherAnimal) < 0) {
            left = true;
        }
    }

    private void updateXPos(double xSpeed) {
        wolf.setX(wolf.getX() + xSpeed);
    }

    private void updateYPos(double ySpeed) {
        wolf.setY(wolf.getY() + ySpeed);
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
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
}
