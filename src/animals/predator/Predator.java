package animals.predator;

import animals.Animal;
import animals.characteristics.TypeOfAnimal;

public abstract class Predator extends Animal{
    public Predator() {
        type = TypeOfAnimal.type.PREDATOR;
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

