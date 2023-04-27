package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

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
                    horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.FALLING);
                } else if (ySpeed < 0) {
                    horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.JUMP);
                } else {
                    horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
                }
                ySpeed += GRAVITY;
            } else {
                if (ySpeed > 0){
                    onFloor = true;
                    horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
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
            if (horse.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.RUNNING) {
                horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
            }
        } else {
            if (horse.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.IDLE) {
                horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.RUNNING);
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
        java.util.List<Animal> seenAnimals = horse.getSeenAnimals(horse);
        if (seenAnimals != null && seenAnimals.size() != 0){
            List<Animal> eatenAnimals = horse.getEatenAnimals(horse, seenAnimals);
            if (eatenAnimals != null && eatenAnimals.size() != 0){
                Animal otherAnimal = horse.chooseOneAnimalWhichCanEat(eatenAnimals);
                turnTowardsPlayer(otherAnimal);
            }
        } else if (!right && !left) {
            left = true;
        } else {
//            setJump(false);
        }
//        if (horse.canSeeAnyone(horse)) {
//            turnTowardsPlayer();
//        } else if (!right && !left) {
//            left = true;
//        }
    }

    private void turnTowardsPlayer(Animal otherAnimal) {
        right = false;
        left = false;
        if (horse.wherePlayerX(horse, otherAnimal) > 0) {
            right = true;
        } else if (horse.wherePlayerX(horse, otherAnimal) < 0) {
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
