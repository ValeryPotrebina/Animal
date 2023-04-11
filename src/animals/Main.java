package animals;

import animals.herbivore.herbivores.Mouse;
import animals.herbivore.herbivores.Rabbit;
import animals.predator.predators.Fox;
import animals.predator.predators.Wolf;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        Rabbit rabbit = new Rabbit();
        Mouse mouse = new Mouse();
        Mouse mouse2 = new Mouse();
        Mouse mouse3 = new Mouse();
        wolf.eat(rabbit);
        wolf.eat(mouse);
        wolf.eat(mouse2);
        wolf.eat(mouse3);
    }

}
