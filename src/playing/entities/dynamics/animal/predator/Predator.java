package playing.entities.dynamics.animal.predator;

import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Predator extends Animal {
    public Predator(){
        typeOfAnimal = TypeOfAnimal.PREDATOR;
    }

    @Override
    public String toString() {
        return "Predator{" +
                "hungerModule=" + hungerModule +
                ", typeOfAnimal=" + typeOfAnimal +
                ", speciesOfAnimal=" + speciesOfAnimal +
                ", ageOfAnimal=" + ageOfAnimal +
                ", stateAnimal=" + stateAnimal +
                ", isHungry=" + isHungry +
                '}';
    }
}
