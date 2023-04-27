package playing.entities.dynamics.animal.herbivore;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Herbivore extends Animal{
    public Herbivore(double x, double y, double width, double height){
        super(x, y, width, height);
        typeOfAnimal = TypeOfAnimal.HERBIVORE;
    }

    @Override
    public String toString() {
        return  "typeOfAnimal= " + typeOfAnimal +
                ", speciesOfAnimal=" + speciesOfAnimal;
    }
}
