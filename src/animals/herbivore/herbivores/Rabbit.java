package animals.herbivore.herbivores;

import animals.*;
import animals.characteristics.TypeOfAnimal;
import animals.herbivore.Herbivore;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.RABBIT_COUNT;
import static Constants.Constants.Animal.SaturationKilos.RABBIT_SATURATION;
import static Constants.Constants.Animal.Speed.RABBIT_SPEED;
import static Constants.Constants.Animal.Weight.RABBIT_WEIGHT;

public class Rabbit extends Herbivore implements ProbabilityOfEating {
    public Rabbit() {
        //hungerModule = new WolfHungerModule(RABBIT_SATURATION, new Wolf());
        stateAnimal = new StateAnimal(RABBIT_WEIGHT, RABBIT_SATURATION, RABBIT_SPEED, RABBIT_COUNT);
        species = TypeOfAnimal.species.RABBIT;
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
        super.dead();
        System.out.println("rabbit is dead");
    }

    @Override
    protected void move() {

    }

    @Override
    public int getProbabilityOfEating(Animal animal) {
        return 0;
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "type=" + type +
                ", species=" + species +
                ", ageOfAnimal=" + ageOfAnimal +
                ", stateAnimal=" + stateAnimal +
                ", isHungry=" + isHungry +
                '}';
    }
}
