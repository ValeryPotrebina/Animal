package playing.entities.dynamics.animal.predator.predators;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.ProbabilityOfEating;
import playing.entities.dynamics.animal.animalsModules.hunger.predatorHungerModule.WolfHungerModule;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.predator.Predator;
import playing.entities.dynamics.rabbit.RabbitAnimation;
import playing.entities.dynamics.rabbit.RabbitEntity;
import playing.entities.dynamics.rabbit.RabbitMove;

import java.awt.*;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.WOLF_COUNT;
import static Constants.Constants.Animal.SaturationKilos.WOLF_SATURATION;
import static Constants.Constants.Animal.Speed.WOLF_SPEED;
import static Constants.Constants.Animal.Weight.WOLF_WEIGHT;

public class Wolf extends Predator implements ProbabilityOfEating, PlayingInterface {
    private WolfMove wolfMove;
    private WolfAnimation wolfAnimation;
    private final WolfEntity wolfEntity;
    public Wolf(WolfEntity wolfEntity){
        this.wolfEntity = wolfEntity;
        initModules();
    }

    private void initModules(){
        hungerModule = new WolfHungerModule(WOLF_SATURATION, this);
        stateAnimal = new StateAnimal(WOLF_WEIGHT, WOLF_SATURATION, WOLF_SPEED, WOLF_COUNT);
        speciesOfAnimal = SpeciesOfAnimal.WOLF;
        wolfMove = new WolfMove(this);
        wolfAnimation = new WolfAnimation(this);
    }



    @Override
    public int getProbabilityOfEating(Animal animal) {
        return 0;
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        wolfAnimation.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        wolfMove.update();
        wolfAnimation.update();
    }

    public WolfMove getWolfMove() {
        return wolfMove;
    }

    public WolfAnimation getWolfAnimation() {
        return wolfAnimation;
    }

    public WolfEntity getWolfEntity() {
        return wolfEntity;
    }
}
