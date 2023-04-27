package playing.entities.dynamics.animal.predator;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Predator extends Animal {
    public Predator(double x, double y, double width, double height){
        super(x, y, width, height);
        typeOfAnimal = TypeOfAnimal.PREDATOR;
    }

    @Override
    public String toString() {
        return "typeOfAnimal= " + typeOfAnimal +
                ", speciesOfAnimal=" + speciesOfAnimal;
    }
}
