package animals;

import animals.herbivore.herbivores.Rabbit;
import animals.predator.predators.Fox;
import animals.predator.predators.Wolf;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        Fox fox = new Fox();
        ArrayList<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        rabbits.add(new Rabbit());
        for (Rabbit rabbit : rabbits) {
            wolf.eat(rabbit);
            fox.eat(rabbit);
        }
    }

}
