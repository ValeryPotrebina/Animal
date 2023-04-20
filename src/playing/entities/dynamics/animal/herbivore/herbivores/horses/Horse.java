package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.ProbabilityOfEating;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.herbivore.Herbivore;

import java.awt.*;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.HORSE_COUNT;
import static Constants.Constants.Animal.Range.HORSE_RANGE;
import static Constants.Constants.Animal.Speed.HORSE_SPEED;
import static Constants.Constants.Animal.Weight.HORSE_WEIGHT;
import static Constants.Constants.Animal.SaturationKilos.HORSE_SATURATION;
import static Constants.Constants.Animal.Weight.HORSE_WEIGHT;
import static Constants.Constants.Animal.Weight.HORSE_WEIGHT;
import static Constants.Constants.Animal.Weight.HORSE_WEIGHT;


public class Horse extends Herbivore implements PlayingInterface, ProbabilityOfEating {
    HorseAnimation horseAnimation;
    HorseMove horseMove;
    public Horse(double x, double y) {
        super(x, y, 107, 73);
        initModules();
    }

    private void initModules(){
        //hungerModule = new
        stateAnimal = new StateAnimal(HORSE_WEIGHT, HORSE_SATURATION, HORSE_SPEED, HORSE_COUNT, HORSE_RANGE);
        speciesOfAnimal = SpeciesOfAnimal.HORSE;
        horseMove = new HorseMove(this);
        horseAnimation = new HorseAnimation(this);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        horseAnimation.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        horseMove.update();
        horseAnimation.update();
    }

    @Override
    public int getProbabilityOfEating(Animal animal) {
        return 0;
    }

    public HorseAnimation getHorseAnimation() {
        return horseAnimation;
    }

    public HorseMove getHorseMove() {
        return horseMove;
    }
}
