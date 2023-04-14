package animals.predator.predators;

import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.animalsModules.hunger.predatorHungerModules.EagleHungerModule;
import animals.predator.Predator;

import java.util.ArrayList;
import java.util.Random;

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

    public static ArrayList<Eagle> createEagle(int n) throws Exception {
        ArrayList<Eagle> eagles = new ArrayList<>();
        if (n == 0) {
            System.err.println("Нельзя создать 0 лис");
            throw new Exception();
        }
        for (int i = 0; i < n; i++) {
            eagles.add(new Eagle());
        }
        return eagles;
    }

    @Override
    protected void eat(Animal animal) {
        if (this.isHungry()){
            int a = new Random().nextInt(100);
            int b = this.getProbabilityOfEating(animal);
            System.out.println("random - " + a + " | " + "probability - " + b);
            if (a<b){
                System.out.println("eagle eaten " + animal );
                System.out.println(animal);
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
