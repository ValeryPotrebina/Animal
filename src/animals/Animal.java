package animals;

import animals.characteristics.AgeOfAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.herbivore.herbivores.Rabbit;
import animals.predator.predators.Bear;
import animals.predator.predators.Eagle;
import animals.predator.predators.Fox;
import animals.predator.predators.Wolf;

import java.util.Random;


public abstract class Animal {
    AnimalManager animalManager;
    protected TypeOfAnimal.type type;
    protected TypeOfAnimal.species species;
    protected AgeOfAnimal ageOfAnimal;
    protected StateAnimal stateAnimal;
    protected boolean isHungry;

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

    public Animal() {
        ageOfAnimal = AgeOfAnimal.MIDDLE;
        isHungry = false;
    }


    protected void eat(Animal animal){
        if (this.isHungry()){
            int a = new Random().nextInt(100);
            int b = this.getProbabilities(animal);
            System.out.println("random - " + a + " | " + "probability - " + b);
            if (a < b)
                System.out.println(this + " eaten " + animal);
            else
                System.out.println("OK");
        } else {
            System.out.println("not hungry");
        }
    }
    protected abstract void sleep(Animal animal);
    protected abstract void breed(Animal animal);
    protected abstract void dead();
    protected abstract void move();
    public void isHungry(Boolean isHungry){
        this.isHungry = isHungry;
    }
    protected boolean isSameType(Animal animal){
        return this.type == animal.type;
    }

    private int getProbabilities(Animal animal){
        switch (this.species){
            case WOLF: return new Wolf().getProbabilityOfEating(animal);
            case FOX: return new Fox().getProbabilityOfEating(animal);
            case BEAR: return new Bear().getProbabilityOfEating(animal);
            case EAGLE: return new Eagle().getProbabilityOfEating(animal);
            case RABBIT: return new Rabbit().getProbabilityOfEating(animal);
        }
        return 0;
    }

}
