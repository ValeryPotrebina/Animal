package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalsModules.hunger.predatorHungerModule.WolfHungerModule;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.predator.Predator;

import java.awt.*;
import java.util.Random;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.WOLF_COUNT;
import static Constants.Constants.Animal.Range.WOLF_RANGE;
import static Constants.Constants.Animal.SaturationKilos.WOLF_SATURATION;
import static Constants.Constants.Animal.Weight.WOLF_WEIGHT;

public class Wolf extends Predator implements PlayingInterface {
    private WolfEat wolfEat;
    int num;
    public Wolf(double x, double y, int num){
        super(x, y, 83, 53);
        setHitBoxTexture(x - 20, y + 10, 20, 25); //кушац
        this.num = num;
        initModules();
    }

    private void initModules(){
        hungerModule = new WolfHungerModule(WOLF_SATURATION, this);
        stateAnimal = new StateAnimal(WOLF_WEIGHT, WOLF_SATURATION, WOLF_COUNT, WOLF_RANGE);
        speciesOfAnimal = SpeciesOfAnimal.WOLF;
        animalMove = new WolfMove(this);
        animalAnimation = new WolfAnimation(this);
        wolfEat = new WolfEat(this);
        animalHealth = new WolfHealth(this);
    }


    @Override
    public boolean isEatable(Animal animal){
        //todo if random == 0?
        int random = new Random().nextInt(100);
        int probability = this.getProbabilityOfEating(animal);
        System.out.println(this + " CAN EAT " + animal + " --> \n random - " + random + "\n probability - " + probability );
        System.out.println(random < probability);
        return random < probability;
    }
    @Override
    public int getProbabilityOfEating(Animal animal) {
        switch (animal.getSpeciesOfAnimal()){
            case RABBIT:
                return 3;
            case HORSE:
                return 3;
            case SUNFLOWER:
                return 20;
            case WOLF:
            default:
                return 0;
        }
    }


    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        animalAnimation.draw(g, scale, x, y);
        drawHitBox(g, scale, x, y);
        drawHitBoxTexture(g, scale, x, y);
        wolfEat.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        animalMove.update();
        animalAnimation.update();
        wolfEat.update();
    }

    @Override
    public String toString() {
        return speciesOfAnimal +
                " NUMBER = " + num;
    }
}
