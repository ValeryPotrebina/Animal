package playing.entities.dynamics.animal;

import playing.entities.dynamics.animal.animalsModules.hunger.HungerModule;
import playing.entities.dynamics.animal.characteristics.AgeOfAnimal;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Animal {
    protected HungerModule hungerModule;
    protected TypeOfAnimal typeOfAnimal;
    protected SpeciesOfAnimal speciesOfAnimal;
    protected AgeOfAnimal ageOfAnimal;
    protected StateAnimal stateAnimal;
    protected boolean isHungry;

}
