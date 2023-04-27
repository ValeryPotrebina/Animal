package playing.entities.dynamics.animal.animalsModules.hunger.predatorHungerModule;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalsModules.hunger.HungerModule;

public class WolfHungerModule extends HungerModule {
    public WolfHungerModule(float saturation, Animal animal) {
        super(saturation, animal);
    }

    @Override
    public void gettingLessHunger(Animal animal) {

    }
}
