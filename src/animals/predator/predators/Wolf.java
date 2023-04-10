package animals.predator.predators;


import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.hunger.animalHungerModules.WolfHungerModule;
import animals.predator.Predator;

import java.util.Random;

import static Constants.Constants.Animal.Speed.WOLF_SPEED;
import static Constants.Constants.Animal.MaxCountOnTheSameCell.WOLF_COUNT;
import static Constants.Constants.Animal.Weight.WOLF_WEIGHT;
import static Constants.Constants.Animal.SaturationKilos.WOLF_SATURATION;
public class Wolf extends Predator implements ProbabilityOfEating {

    //WolfHungerModule wolfHungerModule;
    public Wolf() {
        hungerModule = new WolfHungerModule(WOLF_SATURATION, this);
        stateAnimal = new StateAnimal(WOLF_WEIGHT, WOLF_SATURATION, WOLF_SPEED, WOLF_COUNT);
        species = TypeOfAnimal.species.WOLF;
    }


    @Override
    public String toString() {
        return "Wolf{" +
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
            case BEAR:
            case FOX:
            case EAGLE:
                return 0;
            case RABBIT:
                return 80;
            case WOLF:
            default:
                return -1;
        }
    }

    @Override
    public void eat(Animal animal){
        if (this.isHungry()){
            int a = new Random().nextInt(100);
            int b = this.getProbabilityOfEating(animal);
            System.out.println("random - " + a + " | " + "probability - " + b);
            if (a<b){
                System.out.println("wolf eaten rabbit" );
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
