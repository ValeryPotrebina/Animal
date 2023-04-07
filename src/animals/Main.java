package animals;

import animals.characteristics.AgeOfAnimal;
import animals.herbivore.herbivores.Rabbit;
import animals.predator.Predator;
import animals.predator.predators.Fox;
import animals.predator.predators.Wolf;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        Fox fox = new Fox();
        Wolf wolf = new Wolf();
        Rabbit rabbit = new Rabbit();
        animals.add(wolf);
        wolf.isHungry(true);
        fox.isHungry(true);
        animals.add(fox);
        animals.add(rabbit);
        Rabbit rabbit1 = new Rabbit();
        for (Animal animal : animals) {
            animal.eat(rabbit1);
            System.out.println("---------");
        }
    }

}
