package playing.entities.dynamics.animal.herbivore;

import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Herbivore extends Animal{
    public Herbivore(){
        typeOfAnimal = TypeOfAnimal.HERBIVORE;
    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "hungerModule=" + hungerModule +
                ", typeOfAnimal=" + typeOfAnimal +
                ", speciesOfAnimal=" + speciesOfAnimal +
                ", ageOfAnimal=" + ageOfAnimal +
                ", stateAnimal=" + stateAnimal +
                ", isHungry=" + isHungry +
                '}';
    }
}
