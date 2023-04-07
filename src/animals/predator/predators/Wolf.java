package animals.predator.predators;


import animals.*;
import animals.characteristics.AgeOfAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.predator.Predator;
import static Constants.Constants.Animal.Speed.WOLF_SPEED;
import static Constants.Constants.Animal.MaxCountOnTheSameCell.WOLF_COUNT;
import static Constants.Constants.Animal.Weight.WOLF_WEIGHT;
import static Constants.Constants.Animal.SaturationKilos.WOLF_SATURATION;
public class Wolf extends Predator implements ProbabilityOfEating {
    Animal animal;
    public Wolf() {
        stateAnimal = new StateAnimal(WOLF_WEIGHT, WOLF_SATURATION, WOLF_SPEED, WOLF_COUNT);
        species = TypeOfAnimal.species.WOLF;
    }

    public Wolf(Animal animal){
        stateAnimal = new StateAnimal(WOLF_WEIGHT, WOLF_SATURATION, WOLF_SPEED, WOLF_COUNT);
        species = TypeOfAnimal.species.WOLF;
        this.animal = animal;
        this.isHungry = animal.isHungry();
        this.ageOfAnimal = animal.getAgeOfAnimal();
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
                return 60;
            case WOLF:
            default:
                return -1;
        }
    }
}
