package playing.entities.dynamics;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.predator.predators.Wolf;
import playing.entities.dynamics.rabbit.Rabbit;
import playing.entities.statics.EntityIslandManager;
import playing.island.Island;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class EnemyManager implements PlayingInterface {
    private EntityIslandManager entityIslandManager;
    private ArrayList<Rabbit> rabbits;
    private ArrayList<Wolf> wolves;

    public EnemyManager(EntityIslandManager entityIslandManager, Island island) {
        this.entityIslandManager = entityIslandManager;
        loadObjects(island);
    }

    private void loadObjects(Island island) {
        rabbits = island.getRabbits();
        wolves = island.getWolves();
        for (Rabbit rabbit : rabbits){
            rabbit.getRabbitEntity().setEnemyManager(this);
        }
        for (Wolf wolf : wolves){
            wolf.getWolfEntity().setEnemyManager(this);
        }

    }

    private void updateObjects() {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.getRabbitEntity().isActive()) { //isActive ? What is that
                rabbit.update();
            }
        }
        for (Wolf wolf : wolves) {
            if (wolf.getWolfEntity().isActive()) { //isActive ? What is that
                wolf.update();
            }
        }
    }

    private void drawObjects(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY) {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.getRabbitEntity().isActive()) {
                rabbit.draw(g, scale, lvlOffsetX, lvlOffsetY);
            }
        }
        for (Wolf wolf : wolves) {
            if (wolf.getWolfEntity().isActive()) {
                wolf.draw(g, scale, lvlOffsetX, lvlOffsetY);
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
    public boolean canSeePlayer(Rectangle2D.Double hitBox, float range) {
        return entityIslandManager.canSeePlayer(hitBox, range);
    }
    public int wherePlayerX(Rectangle2D.Double hitBox) {
        return entityIslandManager.wherePlayerX(hitBox);
    }

}
