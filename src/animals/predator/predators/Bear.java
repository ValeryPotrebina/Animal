package animals.predator.predators;

import animals.*;
import animals.characteristics.AgeOfAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.predator.Predator;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.BEAR_COUNT;
import static Constants.Constants.Animal.SaturationKilos.BEAR_SATURATION;
import static Constants.Constants.Animal.Speed.BEAR_SPEED;
import static Constants.Constants.Animal.Weight.BEAR_WEIGHT;

public class Bear extends Predator implements ProbabilityOfEating {
    public Bear() {
        stateAnimal = new StateAnimal(BEAR_WEIGHT, BEAR_SATURATION, BEAR_SPEED, BEAR_COUNT);
        species = TypeOfAnimal.species.BEAR;
    }

    @Override
    public int getProbabilityOfEating(Animal animal) {
        return 0;
    }
}
