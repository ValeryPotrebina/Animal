package playing.entities.dynamics.animal.animalModules;

import playing.PlayingInterface;

import java.awt.image.BufferedImage;

import static playing.entities.dynamics.animal.animalModules.AnimalAnimation.AnimationState.DEAD;

public abstract class AnimalAnimation implements PlayingInterface {
    protected BufferedImage[][] animations;
    protected int aniTick, aniIndex;
    protected int flipW = 1;
    protected int flipX = 0;
    protected boolean dead;
    protected AnimationState animationState = AnimationState.IDLE;

    public enum AnimationState {  //вынести в отдельный енум!!!
        IDLE,
        RUNNING,
        JUMP,
        FALLING,
        EAT,
        DEAD,
        SLEEP;

    }
    public int getAniIndex() {
        return aniIndex;
    }

    public AnimationState getAnimationState() {
        return animationState;
    }

    public void setAnimationState(AnimationState animationState) {
        if (dead) {
            return;
        }
        if (animationState == DEAD) {
            dead = true;
        }
        this.animationState = animationState;
        aniIndex = 0;
    }
}
