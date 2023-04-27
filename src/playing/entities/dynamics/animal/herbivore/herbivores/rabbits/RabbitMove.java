package playing.entities.dynamics.animal.herbivore.herbivores.rabbits;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static Constants.Constants.GameConstants.GRAVITY;

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
                    rabbit.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.FALLING);
                } else if (ySpeed < 0) {
                    rabbit.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.JUMP);
                } else {
                    rabbit.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0){
                    onFloor = true;
                    rabbit.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
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
            if (rabbit.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.RUNNING) {
                rabbit.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
            }
        } else {
            if (rabbit.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.IDLE) {
                rabbit.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.RUNNING);
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
        List<Animal> seenAnimals = rabbit.getSeenAnimals(rabbit);
        if (seenAnimals != null && seenAnimals.size() != 0){
            List<Animal> eatenAnimals = rabbit.getEatenAnimals(rabbit, seenAnimals);
            if (eatenAnimals != null && eatenAnimals.size() != 0){
                Animal otherAnimal = rabbit.chooseOneAnimalWhichCanEat(eatenAnimals);
                turnTowardsPlayer(otherAnimal);
            }
        } else if (!right && !left) {
            left = true;
        } else {
//            setJump(false);
        }
//        if (rabbit.canSeeAnyone(rabbit)) {
//            turnTowardsPlayer();
//        } else if (!right && !left) {
//            left = true;
//        }
    }

    private void turnTowardsPlayer(Animal otherAnimal) {
        right = false;
        left = false;
        if (rabbit.wherePlayerX(rabbit, otherAnimal) > 0) {
            right = true;
        } else if (rabbit.wherePlayerX(rabbit, otherAnimal) < 0) {
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
