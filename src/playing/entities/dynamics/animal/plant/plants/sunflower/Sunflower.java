package playing.entities.dynamics.animal.plant.plants.sunflower;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.characteristics.SpeciesOfAnimal;
import playing.entities.dynamics.animal.characteristics.StateAnimal;
import playing.entities.dynamics.animal.plant.Plant;

import java.awt.*;

import static Constants.Constants.Animal.MaxCountOnTheSameCell.SUNFLOWER_COUNT;
import static Constants.Constants.Animal.Range.SUNFLOWER_RANGE;
import static Constants.Constants.Animal.SaturationKilos.SUNFLOWER_SATURATION;
import static Constants.Constants.Animal.Weight.SUNFLOWER_WEIGHT;

public class Sunflower extends Plant implements PlayingInterface {

        public Sunflower(double x, double y) {
                super(x, y - 12, 35, 45); //Изменить 3 4 параметры
                initModules();
        }

        private void initModules(){
                stateAnimal = new StateAnimal(SUNFLOWER_WEIGHT ,SUNFLOWER_SATURATION, SUNFLOWER_COUNT, SUNFLOWER_RANGE);
                speciesOfAnimal = SpeciesOfAnimal.SUNFLOWER;
                animalAnimation = new SunflowerAnimation(this);
                animalHealth = new SunflowerHealth(this);
        }



        @Override
        public void draw(Graphics g, float scale, int x, int y) {
                animalAnimation.draw(g, scale, x, y);
                drawHitBox(g, scale, x, y);
                //drawHitBoxTexture(g, scale, x, y);
        }

        @Override
        public void update() {
                animalAnimation.update();
        }


        @Override
        public boolean isEatable(Animal animal) {
                return false;
        }
        @Override
        public int getProbabilityOfEating(Animal animal) {
                return 0;
        }
        @Override
        public String toString() {
                return speciesOfAnimal + "";
        }
}
