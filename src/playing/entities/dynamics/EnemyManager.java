package playing.entities.dynamics;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.herbivore.herbivores.horses.Horse;
import playing.entities.dynamics.animal.herbivore.herbivores.rabbits.Rabbit;
import playing.entities.dynamics.animal.predator.predators.wolf.Wolf;
import playing.entities.statics.EntityIslandManager;
import playing.island.Island;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class EnemyManager implements PlayingInterface {
    private EntityIslandManager entityIslandManager;

    private ArrayList<Rabbit> rabbits;
    private ArrayList<Wolf> wolves;
    private ArrayList<Horse> horses;


    public EnemyManager(EntityIslandManager entityIslandManager, Island island) {
        this.entityIslandManager = entityIslandManager;
        loadObjects(island);
    }

    private void loadObjects(Island island) {
        rabbits = island.getRabbits();
        wolves = island.getWolves();
        horses = island.getHorses();
        for (Rabbit rabbit : rabbits){
            rabbit.setEnemyManager(this);
        }
        for (Wolf wolf : wolves){
            wolf.setEnemyManager(this);
        }
        for (Horse horse : horses){
            horse.setEnemyManager(this);
        }
    }

    private void updateObjects() {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.isActive()) { //isActive ? What is that
                rabbit.update();
            }
        }
        for (Wolf wolf : wolves) {
            if (wolf.isActive()) { //isActive ? What is that
                wolf.update();
            }
        }
        for (Horse horse : horses) {
            if (horse.isActive()) { //isActive ? What is that
                horse.update();
            }
        }
    }

    private void drawObjects(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY) {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.isActive()) {
                rabbit.draw(g, scale, lvlOffsetX, lvlOffsetY);
            }
        }
        for (Wolf wolf : wolves) {
            if (wolf.isActive()) {
                wolf.draw(g, scale, lvlOffsetX, lvlOffsetY);
            }
        }
        for (Horse horse : horses) {
            if (horse.isActive()) { //isActive ? What is that
                horse.draw(g, scale, lvlOffsetX, lvlOffsetY);
            }
        }
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawObjects(g, scale, x, y);
    }

    @Override
    public void update() {
        updateObjects();
    }

    public boolean isPlayerOnFloor(Rectangle2D.Double hitbox){
        return entityIslandManager.IsPlayerOnFloor(hitbox);
    }
    public boolean CanMoveHere(Rectangle2D.Double hitBox) {
        return entityIslandManager.CanMoveHere(hitBox);
    }
    public boolean canMoveFloor(Rectangle2D.Double hitBox) {
        return entityIslandManager.canMoveFloor(hitBox);
    }
    public boolean canSeePlayer(Animal animal) {
        return entityIslandManager.canSeePlayer(animal);
    }
    public int wherePlayerX(Rectangle2D.Double hitBox) {
        return entityIslandManager.wherePlayerX(hitBox);
    }

}
