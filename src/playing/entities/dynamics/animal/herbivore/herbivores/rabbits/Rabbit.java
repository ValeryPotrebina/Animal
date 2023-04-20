package playing.entities.dynamics.animal.herbivore.herbivores.rabbits;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalsModules.hunger.predatorHungerModule.WolfHungerModule;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.herbivore.Herbivore;
import playing.entities.dynamics.animal.predator.predators.wolf.WolfAnimation;
import playing.entities.dynamics.animal.predator.predators.wolf.WolfMove;

import java.awt.*;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.RABBIT_COUNT;
import static Constants.Constants.Animal.MaxCountOnTheSameCell.WOLF_COUNT;
import static Constants.Constants.Animal.Range.RABBIT_RANGE;
import static Constants.Constants.Animal.Range.WOLF_RANGE;
import static Constants.Constants.Animal.SaturationKilos.RABBIT_SATURATION;
import static Constants.Constants.Animal.SaturationKilos.WOLF_SATURATION;
import static Constants.Constants.Animal.Speed.RABBIT_SPEED;
import static Constants.Constants.Animal.Speed.WOLF_SPEED;
import static Constants.Constants.Animal.Weight.RABBIT_WEIGHT;
import static Constants.Constants.Animal.Weight.WOLF_WEIGHT;

public class Rabbit extends Herbivore implements PlayingInterface {
    private RabbitMove rabbitMove;
    private RabbitAnimation rabbitAnimation;

    public Rabbit(double x, double y) {
        super(x, y - 50, 40, 40);
        initModules();
    }

    private void initModules(){
        hungerModule = new WolfHungerModule(RABBIT_SATURATION, this);
        stateAnimal = new StateAnimal(RABBIT_WEIGHT, RABBIT_SATURATION, RABBIT_SPEED, RABBIT_COUNT, RABBIT_RANGE);
        speciesOfAnimal = SpeciesOfAnimal.RABBIT;
        rabbitMove = new RabbitMove(this);
        rabbitAnimation = new RabbitAnimation(this);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        rabbitAnimation.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        rabbitMove.update();
        rabbitAnimation.update();
    }

    public RabbitMove getRabbitMove() {
        return rabbitMove;
    }

    public RabbitAnimation getRabbitAnimation() {
        return rabbitAnimation;
    }

}
