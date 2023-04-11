package animals;

import java.util.Random;

public interface ProbabilityOfEating {
    int getProbabilityOfEating(Animal animal);
    default boolean isEatable(Animal animal){
        int a = new Random().nextInt(100);
        int b = this.getProbabilityOfEating(animal);
        System.out.println("random - " + a + " | " + "probability - " + b);
        return a < b;
    }
}
