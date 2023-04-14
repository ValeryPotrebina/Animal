package animals.herbivore.herbivores;

import animals.Animal;
import animals.StateAnimal;
import animals.characteristics.TypeOfAnimal;
import animals.herbivore.Herbivore;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.MOUSE_COUNT;
import static Constants.Constants.Animal.SaturationKilos.MOUSE_SATURATION;
import static Constants.Constants.Animal.Speed.MOUSE_SPEED;
import static Constants.Constants.Animal.Weight.MOUSE_WEIGHT;

public class Mouse extends Herbivore {

    public Mouse(){
        stateAnimal = new StateAnimal(MOUSE_WEIGHT, MOUSE_SATURATION, MOUSE_SPEED, MOUSE_COUNT);
        species = TypeOfAnimal.species.MOUSE;
    }
    @Override
    protected void eat(Animal animal) {

    }

    @Override
    protected void sleep(Animal animal) {

    }

    @Override
    public void dead() {
        super.dead();
        System.out.println("Mouse is dead");
    }

    @Override
    protected void breed(Animal animal) {

    }

    @Override
    protected void move() {

    }
}
