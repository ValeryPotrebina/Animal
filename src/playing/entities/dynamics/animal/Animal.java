package playing.entities.dynamics.animal;

import playing.entities.dynamics.DynamicEntity;
import playing.entities.dynamics.animal.animalsModules.hunger.HungerModule;
import playing.entities.dynamics.animal.characteristics.AgeOfAnimal;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Animal extends DynamicEntity {
    protected HungerModule hungerModule;
    protected TypeOfAnimal typeOfAnimal;
    protected SpeciesOfAnimal speciesOfAnimal;
    protected AgeOfAnimal ageOfAnimal;
    protected StateAnimal stateAnimal;
    protected boolean isHungry;

    public Animal(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public boolean isSameAnimal(Animal animal){
        return this.typeOfAnimal == animal.typeOfAnimal;
    }

    public HungerModule getHungerModule() {
        return hungerModule;
    }

    public TypeOfAnimal getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public SpeciesOfAnimal getSpeciesOfAnimal() {
        return speciesOfAnimal;
    }

    public AgeOfAnimal getAgeOfAnimal() {
        return ageOfAnimal;
    }

    public StateAnimal getStateAnimal() {
        return stateAnimal;
    }

    public boolean isHungry() {
        return isHungry;
    }
}
