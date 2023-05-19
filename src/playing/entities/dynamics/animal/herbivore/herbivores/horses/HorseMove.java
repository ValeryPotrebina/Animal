package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;
import playing.entities.dynamics.animal.animalModules.AnimalMove;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static Constants.Constants.Animal.Speed.HORSE_SPEED;
import static Constants.Constants.GameConstants.GRAVITY;
import static Constants.Constants.GameConstants.TEMP_GRAVITY;

public class HorseMove extends AnimalMove {
    //todo Вынести движение (методы)  в абстрактный класс AnimalMove
    //todo
    final Horse horse;
    public HorseMove(Horse horse) {
        super(HORSE_SPEED);
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
            if (!horse.isPlayerOnFloor()) // а на самом деле не на полу
                setOnFloor(false); // то сделать на полу
        } else {  // если в воздухе
            if (horse.canMoveHere(getNewHitBoxY())) { //если вок может пройти здесь
                updateYPos();
                setHorseStateMovingVert();
                ySpeed += GRAVITY; //гравитация, которая давит вниз
            } else {
                if (ySpeed >= 0) {
                    setOnFloor(true);
                    horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
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
        if (horse.canMoveHere(getNewHitBoxX())){
            updateXPos();
        } else {
            changeWalkDir();
        }
        setHorseStateMovingHor();
        xSpeed = 0;
    }
    private void setHorseStateMovingHor() {
        if (xSpeed == 0){
            if (horse.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.RUNNING) {
                horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
            }
        } else {
            if (horse.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.DEAD){
                speedWalk = 0;
            }
            if (horse.getAnimalAnimation().getAnimationState() == AnimalAnimation.AnimationState.IDLE) {
                horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.RUNNING);
            }
        }
    }
    private void setHorseStateMovingVert(){
        if (ySpeed > 0) //если двигается вниз
            horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.FALLING);
        else if (ySpeed < 0)
            horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.JUMP);
        else
            horse.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.IDLE);
    }
    private Rectangle2D.Double getNewHitBoxY(){
        Rectangle2D.Double oldHitBox = horse.getHitBox();
        return new Rectangle2D.Double(
                oldHitBox.x,
                oldHitBox.y + ySpeed,
                oldHitBox.width,
                oldHitBox.height
        );
    }
    private Rectangle2D.Double getNewHitBoxX(){
        Rectangle2D.Double oldHitBox = horse.getHitBox();
        return new Rectangle2D.Double(
                oldHitBox.x + xSpeed,
                oldHitBox.y,
                oldHitBox.width,
                oldHitBox.height
        );
    }
    private void updatePos(){
        verticalMovement();
        horizontalMovement();
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

    private void updateXPos() {
        horse.setX(horse.getX() + xSpeed);
    }

    private void updateYPos() {
        horse.setY(horse.getY() + ySpeed);
    }
}
