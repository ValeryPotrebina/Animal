package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.entities.EntityIslandManager;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;
import playing.entities.dynamics.animal.animalModules.AnimalMove;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static Constants.Constants.Animal.Speed.WOLF_SPEED;
import static Constants.Constants.GameConstants.GRAVITY;
import static Constants.Constants.GameConstants.TEMP_GRAVITY;

public class WolfMove extends AnimalMove {
    final Wolf wolf;
    public WolfMove(Wolf wolf) {
        super(WOLF_SPEED);
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
        verticalMovement();
        horizontalMovement();
    }
    private void verticalMovement(){
        if (jump){ //  если игрок прыгает
            if (onFloor){ // если он еще на полу
                setOnFloor(false); // сделать так, что он не на полу
                ySpeed = speedJump;
            }
            if (TEMP_GRAVITY == 0.0f){
                GRAVITY = TEMP_GRAVITY;
            }
        }
        if (fall) {
            ySpeed = 1.5f;
            GRAVITY = 0.035f;
        }

        if (onFloor) { // если на полу
            if (!wolf.isPlayerOnFloor()) // а на самом деле не на полу
                setOnFloor(false); // то сделать на полу
        } else {  // если в воздухе
            if (wolf.canMoveHere(getNewHitBoxY())) { //если вок может пройти здесь
                updateYPos();
                setWolfStateMovingVert();
                ySpeed += GRAVITY; //гравитация, которая давит вниз
            } else {
                if (ySpeed >= 0) {
                    setOnFloor(true);
                    wolf.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
                }
                ySpeed = 0;
            }
        }
    }
    private void horizontalMovement(){
        if (left)
            xSpeed += speedWalk; //ИСПРАВИТЬ
        if (right)
            xSpeed -= speedWalk;
        if (wolf.canMoveHere(getNewHitBoxX())){
            updateXPos();
        } else {
            changeWalkDir();
        }
        setWolfStateMovingHor();
        xSpeed = 0;
    }
    private void setWolfStateMovingHor() {
        if (xSpeed == 0){
            if (wolf.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.RUNNING) {
                wolf.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
            }
        } else {
            if (wolf.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.DEAD){
                speedWalk = 0;
            }
            if (wolf.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.IDLE) {
                wolf.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.RUNNING);
            }
        }
    }
    private void setWolfStateMovingVert(){
        if (ySpeed > 0) //если двигается вниз
            wolf.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.FALLING);
        else if (ySpeed < 0)
            wolf.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.JUMP);
        else
            wolf.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
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
    //бежать к добыче
    //убегать от того кто может съесть
    //
    private void checkEnvironment() {
//        List<Animal> seenAnimals = wolf.getSeenAnimals(wolf);
//        if (seenAnimals != null && seenAnimals.size() != 0) {
//            List<Animal> eatenAnimals = wolf.getEatenAnimals(wolf, seenAnimals);
//            if (eatenAnimals != null && eatenAnimals.size() != 0) {
//                Animal otherAnimal = wolf.chooseOneAnimalWhichCanEat(eatenAnimals);
//                turnTowardsPlayer(otherAnimal);
                  //wolf.eatEnemyAnimal(wolf.getHitBoxTexture());
//            }
        /*} else */if (!right && !left) {
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
        } else {

        }
    }
    private Rectangle2D.Double getNewHitBoxY(){
        Rectangle2D.Double oldHitBox = wolf.getHitBox();
        return new Rectangle2D.Double(
                oldHitBox.x,
                oldHitBox.y + ySpeed,
                oldHitBox.width,
                oldHitBox.height
        );
    }
    private Rectangle2D.Double getNewHitBoxX(){
        Rectangle2D.Double oldHitBox = wolf.getHitBox();
        return new Rectangle2D.Double(
                oldHitBox.x + xSpeed,
                oldHitBox.y,
                oldHitBox.width,
                oldHitBox.height
        );
    }
    private void updateXPos() {
        wolf.setX(wolf.getX() + xSpeed);
    }

    private void updateYPos() {
        wolf.setY(wolf.getY() + ySpeed);
    }



}
