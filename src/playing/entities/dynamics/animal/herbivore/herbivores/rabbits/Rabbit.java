package playing.entities.dynamics.animal.herbivore.herbivores.rabbits;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.ProbabilityOfEating;
import playing.entities.dynamics.animal.animalsModules.hunger.predatorHungerModule.WolfHungerModule;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.herbivore.Herbivore;

import java.awt.*;
import java.util.Random;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.RABBIT_COUNT;
import static Constants.Constants.Animal.Range.RABBIT_RANGE;
import static Constants.Constants.Animal.SaturationKilos.RABBIT_SATURATION;
import static Constants.Constants.Animal.Speed.RABBIT_SPEED;
import static Constants.Constants.Animal.Weight.RABBIT_WEIGHT;

public class Rabbit extends Herbivore implements PlayingInterface, ProbabilityOfEating {
    private RabbitMove rabbitMove;
    int num;

    public Rabbit(double x, double y, int num) {
        super(x, y - 50, 40, 40);
        this.num = num;
        initModules();
    }

    private void initModules(){
        hungerModule = new WolfHungerModule(RABBIT_SATURATION, this);
        stateAnimal = new StateAnimal(RABBIT_WEIGHT, RABBIT_SATURATION, RABBIT_SPEED, RABBIT_COUNT, RABBIT_RANGE);
        speciesOfAnimal = SpeciesOfAnimal.RABBIT;
        rabbitMove = new RabbitMove(this);
        animalAnimation = new RabbitAnimation(this);
    }

    @Override
    public boolean isEatable(Animal animal) {
        int random = new Random().nextInt(100);
        int probability = this.getProbabilityOfEating(animal);
        System.out.println("RABBIT --> \n random - " + random + "\n probability - " + probability);
        return random < probability;
    }
    @Override
    public int getProbabilityOfEating(Animal animal) {
        switch (animal.getSpeciesOfAnimal()){
            case HORSE:
            case WOLF:
            case RABBIT:
            default:
                return 0;
        }
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        animalAnimation.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        rabbitMove.update();
        animalAnimation.update();
    }

    public RabbitMove getRabbitMove() {
        return rabbitMove;
    }


    @Override
    public String toString() {
        return speciesOfAnimal +
                " NUMBER = " + num;
    }
}
