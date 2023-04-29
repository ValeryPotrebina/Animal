package playing.entities.dynamics.animal.animalModules;

import playing.entities.dynamics.DynamicEntity;
import playing.entities.dynamics.animal.animalsModules.hunger.HungerModule;
import playing.entities.dynamics.animal.characteristics.AgeOfAnimal;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

import static java.lang.Math.abs;

public abstract class Animal extends DynamicEntity {
    protected HungerModule hungerModule; //пока не нужен
    protected TypeOfAnimal typeOfAnimal;
    protected SpeciesOfAnimal speciesOfAnimal;
    protected AgeOfAnimal ageOfAnimal; //пока не нужен
    protected StateAnimal stateAnimal;
    protected boolean isHungry;
    protected AnimalAnimation animalAnimation;
    protected AnimalHealth animalHealth;
    protected AnimalMove animalMove;
    public Animal(double x, double y, double width, double height) {
        super(x, y, width, height);
        initModules();
    }

    private void initModules(){
        animalHealth = new AnimalHealth(this);
    }

    public boolean isSameAnimal(Animal animal){
        return this.typeOfAnimal == animal.typeOfAnimal;
    }

    public abstract boolean isEatable(Animal animal);
    public abstract int getProbabilityOfEating(Animal animal);

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

    public void eatEnemy() {
        animalHealth.eatEnemy();
    }

    public AnimalAnimation getAnimalAnimation() {
        return animalAnimation;
    }

    public AnimalMove getAnimalMove() {
        return animalMove;
    }
}
