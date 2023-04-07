package animals.predator.predators;

import animals.*;
import animals.characteristics.AgeOfAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.predator.Predator;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.FOX_COUNT;
import static Constants.Constants.Animal.SaturationKilos.FOX_SATURATION;
import static Constants.Constants.Animal.Speed.FOX_SPEED;
import static Constants.Constants.Animal.Weight.FOX_WEIGHT;

public class Fox extends Predator implements ProbabilityOfEating {

    public Fox() {
        stateAnimal = new StateAnimal(FOX_WEIGHT, FOX_SATURATION, FOX_SPEED, FOX_COUNT);
        species = TypeOfAnimal.species.FOX;
    }

    @Override
    public String toString() {
        return "Fox{" +
                "type=" + type +
                ", species=" + species +
                ", ageOfAnimal=" + ageOfAnimal +
                ", stateAnimal=" + stateAnimal +
                ", isHungry=" + isHungry +
                '}';
    }

    @Override
    public int getProbabilityOfEating(Animal animal) {
        switch (animal.getSpecies()) {
            case WOLF:
            case BEAR:
            case EAGLE:
                return 0;
            case RABBIT:
                return 70;
            case FOX:
            default:
                return -1;
        }
    }
}
