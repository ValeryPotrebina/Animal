package playing.entities.dynamics;

import playing.PlayingInterface;
import playing.entities.dynamics.rabbit.Rabbit;
import playing.entities.statics.EntityIslandManager;
import playing.island.Island;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class EnemyManager implements PlayingInterface {
    private EntityIslandManager entityIslandManager;
    private ArrayList<Rabbit> rabbits;

    public EnemyManager(EntityIslandManager entityIslandManager, Island island) {
        this.entityIslandManager = entityIslandManager;
        loadObjects(island);
    }

    private void loadObjects(Island island) {
        rabbits = island.getRabbits();
        for (Rabbit rabbit : rabbits){
            rabbit.setEnemyManager(this);
        }
    }

    private void updateRabbits() {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.isActive()) { //isActive ? What is that
                rabbit.update();
            }
        }
    }

    private void drawRabbits(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY) {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.isActive()) {
                rabbit.draw(g, scale, lvlOffsetX, lvlOffsetY);
            }
        }
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawRabbits(g, scale, x, y);
    }

    @Override
    public void update() {
        updateRabbits();
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
