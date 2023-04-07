package animals.predator;

import animals.characteristics.AgeOfAnimal;
import animals.Animal;
import animals.characteristics.TypeOfAnimal;

public class Predator extends Animal{
    public Predator() {
        type = TypeOfAnimal.type.PREDATOR;
    }


    @Override
    public void eat(Animal animal) {
        super.eat(animal);
        System.out.println("i'm eatiiing");
    }
    @Override
    protected void sleep(Animal animal) {

    }

    @Override
    protected void breed(Animal animal) {
        System.out.println(this  + " fucked " + animal);
    }

    @Override
    protected void dead() {

    }

    @Override
    protected void move() {

    }

    @Override
    public String toString() {
        return "Predator{" +
                "type=" + type +
                ", species=" + species +
                ", ageOfAnimal=" + ageOfAnimal +
                ", stateAnimal=" + stateAnimal +
                ", isHungry=" + isHungry +
                '}';
    }
}

