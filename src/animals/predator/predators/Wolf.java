package animals.predator.predators;


import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.animalsModules.hunger.predatorHungerModules.WolfHungerModule;
import animals.predator.Predator;

import java.util.ArrayList;

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

    public static ArrayList<Wolf> createWolf(int n) throws Exception {
        ArrayList<Wolf> wolves = new ArrayList<>();
        if (n == 0) {
            System.err.println("Нельзя создать 0 волков");
            throw new Exception();
        }
        for (int i = 0; i < n; i++) {
            wolves.add(new Wolf());
        }
        return wolves;
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
            case MOUSE:
                return 80;
            case WOLF:
            default:
                return -1;
        }
    }

    @Override
    public void eat(Animal animal){
        if (this.isHungry()){
            if (isEatable(animal)){
                System.out.println("wolf eaten " + animal);
                hungerModule.gettingLessHunger(animal);
                animal.dead();
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

}
