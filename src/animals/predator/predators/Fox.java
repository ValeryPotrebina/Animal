package animals.predator.predators;

import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.hunger.animalHungerModules.FoxHungerModule;
import animals.hunger.animalHungerModules.WolfHungerModule;
import animals.predator.Predator;

import java.util.Random;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.FOX_COUNT;
import static Constants.Constants.Animal.SaturationKilos.FOX_SATURATION;
import static Constants.Constants.Animal.SaturationKilos.WOLF_SATURATION;
import static Constants.Constants.Animal.Speed.FOX_SPEED;
import static Constants.Constants.Animal.Weight.FOX_WEIGHT;

public class Fox extends Predator implements ProbabilityOfEating {

    public Fox() {
        hungerModule = new FoxHungerModule(FOX_SATURATION, this);
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

    @Override
    public void eat(Animal animal) {
        if (this.isHungry()){
            int a = new Random().nextInt(100);
            int b = this.getProbabilityOfEating(animal);
            System.out.println("random - " + a + " | " + "probability - " + b);
            if (a<b){
                System.out.println("fox eaten rabbit" );
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
    protected void dead() {

    }

    @Override
    protected void move() {

    }
}
