package animals.herbivore;

import animals.Animal;
import animals.characteristics.TypeOfAnimal;

public class Herbivore extends Animal {


    public Herbivore() {
         //ИСПРАВИТЬ
        type = TypeOfAnimal.type.HERBIVORE;
    }

    @Override
    public void eat(Animal animal) {
        System.out.println("hrum, hrum");
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
