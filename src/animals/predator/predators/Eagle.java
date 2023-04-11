package animals.predator.predators;

import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.hunger.predatorHungerModules.EagleHungerModule;
import animals.predator.Predator;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.EAGLE_COUNT;
import static Constants.Constants.Animal.SaturationKilos.EAGLE_SATURATION;
import static Constants.Constants.Animal.Speed.EAGLE_SPEED;
import static Constants.Constants.Animal.Weight.EAGLE_WEIGHT;

public class Eagle extends Predator implements ProbabilityOfEating {
    public Eagle() {
        hungerModule = new EagleHungerModule(EAGLE_SATURATION, this);
        stateAnimal = new StateAnimal(EAGLE_WEIGHT, EAGLE_SATURATION, EAGLE_SPEED, EAGLE_COUNT);
        species = TypeOfAnimal.species.EAGLE;
    }

    @Override
    protected void eat(Animal animal) {

    }

    @Override
    protected void sleep(Animal animal) {

    }

    @Override
    protected void breed(Animal animal) {

    }

    @Override
    public void dead() {

    }

    @Override
    protected void move() {

    }

    @Override
    public int getProbabilityOfEating(Animal animal) {
        switch (animal.getSpecies()) {
            case WOLF:
            case EAGLE:
            case BEAR:
                return 0;
            case FOX:
                return 10;
            case RABBIT:
                return 80;
            case MOUSE:
                return 90;
            default:
                System.out.println("unknown animal");
                return -1;

        }
    }
}
