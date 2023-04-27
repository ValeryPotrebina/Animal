package playing.entities.dynamics;

import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.statics.Entity;

import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class DynamicEntity extends Entity {
    private EnemyManager enemyManager;
    private boolean isActive = true;
    public DynamicEntity(double x, double y, double width, double height) {
        super(x, y, width, height);
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
    public boolean canSeeAnyone(Animal animal) {
        return enemyManager.canSeeAnyone(animal);
    }
    public int wherePlayerX(Animal animal, Animal otherAnimal) {
        return enemyManager.wherePlayerX(animal, otherAnimal);
    }

    public boolean canEatAnimal(Animal animal){
        return enemyManager.canEatAnimal(animal);
    }
    public void eatAnimal() {
        enemyManager.eatAnimal();
    }

    public boolean checkPlayerHit(Rectangle2D.Double attackBox) {
        return enemyManager.checkPlayerHit(attackBox);
    }
    public List<Animal> getSeenAnimals(Animal animal) {
        return enemyManager.getSeenAnimals(animal);
    }
    public List<Animal> getEatenAnimals(Animal animal, List<Animal> seenAnimals) {
        return enemyManager.getEatenAnimals(animal, seenAnimals);
    }
    public Animal chooseOneAnimalWhichCanEat(List<Animal> animals){
        return enemyManager.chooseOneAnimalWhichCanEat(animals);
    }
    public boolean isActive(){
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
