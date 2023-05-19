package playing.entities.dynamics.animal.plant;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.characteristics.TypeOfAnimal;

public abstract class Plant extends Animal {
    public Plant(double x, double y, double width, double height) {
        super(x, y, width, height);
        typeOfAnimal = TypeOfAnimal.PLANT;
    }

}
