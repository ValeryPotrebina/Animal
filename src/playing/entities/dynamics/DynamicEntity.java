package playing.entities.dynamics;

import playing.entities.statics.Entity;

import java.awt.geom.Rectangle2D;

public abstract class DynamicEntity extends Entity {
    private EnemyManager enemyManager;
    private boolean isActive = true;
    public DynamicEntity(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    public DynamicEntity(double x, double y) {
        super(x, y);
    }

    public void setEnemyManager(EnemyManager enemyManager) {
        this.enemyManager = enemyManager;
    }

    public boolean isPlayerOnFloor(){
        return enemyManager.isPlayerOnFloor(getHitBox());
    }
    public boolean canMoveHere(Rectangle2D.Double hitBox) {
        return enemyManager.CanMoveHere(hitBox);
    }
    public boolean canMoveFloor(Rectangle2D.Double hitBox) {
        return enemyManager.canMoveFloor(hitBox);
    }
    public boolean canSeePlayer(float range) {
        return enemyManager.canSeePlayer(getHitBox(), range);
    }
    public int wherePlayerX() {
        return enemyManager.wherePlayerX(getHitBox());
    }

    public boolean isActive(){
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}