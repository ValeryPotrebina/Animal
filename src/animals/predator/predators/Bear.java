package animals.predator.predators;

import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.animalsModules.hunger.predatorHungerModules.BearHungerModule;
import animals.predator.Predator;

import java.util.Random;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.BEAR_COUNT;
import static Constants.Constants.Animal.SaturationKilos.BEAR_SATURATION;
import static Constants.Constants.Animal.Speed.BEAR_SPEED;
import static Constants.Constants.Animal.Weight.BEAR_WEIGHT;

public class Bear extends Predator implements ProbabilityOfEating {
    public Bear() {
        hungerModule = new BearHungerModule(BEAR_SATURATION, this);
        stateAnimal = new StateAnimal(BEAR_WEIGHT, BEAR_SATURATION, BEAR_SPEED, BEAR_COUNT);
        species = TypeOfAnimal.species.BEAR;
    }

    @Override
    protected void eat(Animal animal) {
        if (this.isHungry()){
            int a = new Random().nextInt(100);
            int b = this.getProbabilityOfEating(animal);
            System.out.println("random - " + a + " | " + "probability - " + b);
            if (a<b){
                System.out.println("bear eaten rabbit" );
                System.out.println(animal);
                hungerModule.gettingLessHunger(animal);
            } else {
                System.out.println("OK");
            }
        }
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
            case FOX:
            case EAGLE:
            case BEAR:
                return 0;
            case RABBIT:
                return 80;
            case MOUSE:
                return 90;
            default:
                return -1;
        }
    }
}
