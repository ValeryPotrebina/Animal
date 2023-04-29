package playing.entities.dynamics.animal.animalModules;

import playing.PlayingInterface;

import static Constants.Constants.GameConstants.GRAVITY;

public abstract class AnimalMove implements PlayingInterface {
    protected boolean left, right, jump, fall;
    protected boolean onFloor;
    protected float speedJump = -2.65f + GRAVITY;

    protected float speedWalk;
    protected float ySpeed;
    protected float xSpeed;

    protected AnimalMove(float speedWalk) {
        this.speedWalk = speedWalk;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    protected void setOnFloor(boolean onFloor){
        this.onFloor = onFloor;
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
