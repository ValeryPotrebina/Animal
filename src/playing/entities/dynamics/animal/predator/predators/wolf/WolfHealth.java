package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.entities.dynamics.animal.AnimalAnimation;

public class WolfHealth {
    final Wolf wolf;
    private final int maxHealth = 100;
    public WolfHealth(Wolf wolf){
        this.wolf = wolf;
    }
    public void attackWolf(){
        wolf.getWolfAnimation().setAnimationState(AnimalAnimation.AnimationState.DEAD);
    }
}
