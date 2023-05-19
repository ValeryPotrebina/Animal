package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalHealth;

public class WolfHealth extends AnimalHealth {
    private final int maxHealth = 100;

    public WolfHealth(Animal animal) {
        super(animal);
    }
}
