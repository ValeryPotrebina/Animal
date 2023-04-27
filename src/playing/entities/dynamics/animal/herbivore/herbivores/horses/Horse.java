package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.herbivore.Herbivore;

import java.awt.*;
import java.util.Random;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.HORSE_COUNT;
import static Constants.Constants.Animal.Range.HORSE_RANGE;
import static Constants.Constants.Animal.Speed.HORSE_SPEED;
import static Constants.Constants.Animal.Weight.HORSE_WEIGHT;
import static Constants.Constants.Animal.SaturationKilos.HORSE_SATURATION;


public class Horse extends Herbivore implements PlayingInterface{
    HorseMove horseMove;
    int num;
    public Horse(double x, double y, int num) {
        super(x, y, 107, 73);
        this.num = num;
        initModules();
    }

    private void initModules(){
        //hungerModule = new
        stateAnimal = new StateAnimal(HORSE_WEIGHT, HORSE_SATURATION, HORSE_SPEED, HORSE_COUNT, HORSE_RANGE);
        speciesOfAnimal = SpeciesOfAnimal.HORSE;
        horseMove = new HorseMove(this);
        animalAnimation = new HorseAnimation(this);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        animalAnimation.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        horseMove.update();
        animalAnimation.update();
    }
    public HorseMove getHorseMove() {
        return horseMove;
    }
    @Override
    public boolean isEatable(Animal animal) {
        int random = new Random().nextInt(100);
        int probability = this.getProbabilityOfEating(animal);
        System.out.println("HORSE --> \n random - " + random + "\n probability - " + probability);
        return random <= probability;
    }
    @Override
    public int getProbabilityOfEating(Animal animal) {
        switch (animal.getSpeciesOfAnimal()){
            case RABBIT:
                return 20;
            case WOLF:
            case HORSE:
            default:
                return 0;
        }
    }
    @Override
    public String toString() {
        return speciesOfAnimal +
                " NUMBER = " + num;
    }
}
