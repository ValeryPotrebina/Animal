package playing.entities.dynamics.animal;

import playing.entities.dynamics.animal.predator.predators.wolf.WolfAnimation;

import java.awt.image.BufferedImage;

public abstract class AnimalAnimation {
    protected BufferedImage[][] animations;
    protected int aniTick, aniIndex;
    protected int flipW = 1;
    protected int flipX = 0;
    protected boolean dead;

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


}
