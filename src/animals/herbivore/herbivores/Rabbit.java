package animals.herbivore.herbivores;

import animals.*;
import animals.characteristics.AgeOfAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.herbivore.Herbivore;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.RABBIT_COUNT;
import static Constants.Constants.Animal.SaturationKilos.RABBIT_SATURATION;
import static Constants.Constants.Animal.Speed.RABBIT_SPEED;
import static Constants.Constants.Animal.Weight.RABBIT_WEIGHT;

public class Rabbit extends Herbivore implements ProbabilityOfEating {
    public Rabbit() {
        stateAnimal = new StateAnimal(RABBIT_WEIGHT, RABBIT_SATURATION, RABBIT_SPEED, RABBIT_COUNT);
        species = TypeOfAnimal.species.RABBIT;
    }

    @Override
    public int getProbabilityOfEating(Animal animal) {
        return 0;
    }
}
