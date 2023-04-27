package playing.entities.dynamics.animal.animalModules;

public class AnimalHealth {
    final Animal animal;

    public AnimalHealth(Animal animal) {
        this.animal = animal;
    }

    public void eatEnemy(){
        animal.getAnimalAnimation().setAnimationState(AnimalAnimation.AnimationState.DEAD);
    }
}
