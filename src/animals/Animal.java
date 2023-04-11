package animals;

import animals.characteristics.AgeOfAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.herbivore.herbivores.Rabbit;
import animals.hunger.HungerModule;
//import animals.predator.predators.Bear;
//import animals.predator.predators.Eagle;
import animals.predator.predators.Fox;
import animals.predator.predators.Wolf;

import java.util.Random;


public abstract class Animal {
    protected HungerModule hungerModule;
    protected TypeOfAnimal.type type;
    protected TypeOfAnimal.species species;
    protected AgeOfAnimal ageOfAnimal;
    protected  StateAnimal stateAnimal;
    protected boolean isHungry;

    public Animal() {
        ageOfAnimal = AgeOfAnimal.MIDDLE;
        isHungry = true;
    }


    protected abstract void eat(Animal animal);
    protected abstract void sleep(Animal animal);
    protected abstract void breed(Animal animal);

    public void dead() {
        System.out.println("class Animal");
    }

    protected abstract void move();


    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public void isHungry(Boolean isHungry){
        this.isHungry = isHungry;
    }
    public TypeOfAnimal.type getType() {
        return type;
    }

    public TypeOfAnimal.species getSpecies() {
        return species;
    }

    public AgeOfAnimal getAgeOfAnimal() {
        return ageOfAnimal;
    }

    public void setAgeOfAnimal(AgeOfAnimal ageOfAnimal) {
        this.ageOfAnimal = ageOfAnimal;
    }

    public StateAnimal getStateAnimal() {
        return stateAnimal;
    }

    public boolean isHungry() {
        return isHungry;
    }
}
