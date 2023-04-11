package animals.herbivore;

import animals.Animal;
import animals.characteristics.TypeOfAnimal;

public abstract class Herbivore extends Animal {


    public Herbivore() {
         //ИСПРАВИТЬ
        type = TypeOfAnimal.type.HERBIVORE;
    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "hungerModule=" + hungerModule +
                ", type=" + type +
                ", species=" + species +
                ", ageOfAnimal=" + ageOfAnimal +
                ", stateAnimal=" + stateAnimal +
                ", isHungry=" + isHungry +
                '}';
    }
}
