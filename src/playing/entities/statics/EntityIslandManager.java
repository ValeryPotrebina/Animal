package playing.entities.statics;
import playing.PlayingGame;
import playing.entities.dynamics.animal.Animal;
import playing.entities.dynamics.animal.AnimalAnimation;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static Constants.Constants.GameWindowConstants.*;
import static java.lang.Math.abs;

//todo Добавить логирование

public class EntityIslandManager {
    private final PlayingGame playingGame;
    AnimalAnimation animalAnimation;
    private ArrayList<Animal> animals;
    private volatile Animal otherAnimal;
    //TODO 1. Добавить озера
    //TODO Животное может передвигаться не только по полу
    //TODO Животное может прыгать
    //TODO Животное может определять, что за другое животное перед ним и из этого действовать
    //TODO Животное пойдет к своей добыче (нужно понимать какая вероятность у животных на поедание, чтобы идти к еде)
    //TODO сначала увидит животное, далее анализ (можно ли есть). Если можно, то двигаться к пище (есть ее), иначе двигаться дальше
    public EntityIslandManager(PlayingGame playingGame){
        this.playingGame = playingGame;
    }

    public boolean IsPlayerOnFloor(Rectangle2D.Double hitBox) {
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        if (!IsSolid(hitBox.x, hitBox.y + hitBox.height + 1, lvlData)) {
            if (!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }

    public boolean CanMoveHere(Rectangle2D.Double hitBox) {//
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        if (!IsSolid(hitBox.x, hitBox.y, lvlData)) { //если по x и y плитка не твердая
            if (!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height, lvlData)) { //
                if (!IsSolid(hitBox.x + hitBox.width, hitBox.y, lvlData)) {
                    if (!IsSolid(hitBox.x, hitBox.y + hitBox.height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean canMoveFloor(Rectangle2D.Double hitBox) {
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        return CanMoveHere(hitBox) && IsSolid(hitBox.x,
                hitBox.y + hitBox.height + 1, lvlData);
    }
    private boolean IsSolid(double x, double y, int[][] lvlData) { //твердый ли блок (не проходимый)
        int maxWidth = lvlData[0].length * TILE_SIZE_DEFAULT;
        int maxHeight = lvlData.length * TILE_SIZE_DEFAULT;

        if (x < 0 || x >= maxWidth) //выходим за границу
            return true;
        if (y < 0 || y >= maxHeight) //выходим за границу
            return true;

        double xIndex = x / TILE_SIZE_DEFAULT;
        double yIndex = y / TILE_SIZE_DEFAULT;

        return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
    }
    private static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) { //заполненная чем-то плитка
        int value = lvlData[yTile][xTile];
        return value != 11; //11 - пустота
    }
    private ArrayList<Animal> uploadAnimals(){
        return playingGame.getIsland().getAnimals ();
    }

    public List<Animal> getSeenAnimals(Animal animal) { //хитбокс и зрение и кучу методов для проверки
        List<Animal> seenAnimals = new ArrayList<>();
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        Rectangle2D.Double hitBox = animal.getHitBox();
        ArrayList<Animal> animals = uploadAnimals();
        for (Animal otherAnimal : animals) {
            if (!animal.isSameAnimal(otherAnimal)){
                Rectangle2D.Double playerHitBox = otherAnimal.getHitBox();
                int playerTileY = (int) (playerHitBox.y / TILE_SIZE_DEFAULT); //1 - 10, 2 - 12    Abs(2 - 1) <=2
                int enemyTileY = (int) (hitBox.y / TILE_SIZE_DEFAULT);
                if (abs(playerTileY - enemyTileY) <= 4) { //todo добавить погрешность!!!         <<READY>>
                    if (isPlayerInRange(animal, otherAnimal)) {
                        if (IsSightClear(lvlData, hitBox, playerHitBox, enemyTileY)) {
//                                if (canEatAnimal(animal, otherAnimal)){
                            System.out.println(animal + " CAN SEE " + otherAnimal);
                            seenAnimals.add(otherAnimal);
//                            }
                        }
                    }
                }
            }
        }
        return seenAnimals;
    }
    public List<Animal> getEatenAnimals(Animal animal, List<Animal> seenAnimals){
        List<Animal> eatenAnimals = new ArrayList<>();
        for (Animal otherAnimal : seenAnimals) {
            if (!animal.isSameAnimal(otherAnimal)){
                if (animal.isEatable(otherAnimal)){
                    eatenAnimals.add(otherAnimal);
                }
            }
        }
        return eatenAnimals;
    }

    public Animal chooseOneAnimalWhichCanEat(List<Animal> animals){
        if (animals == null || animals.size() == 0){
            return null;
        }
        return animals.get(0);
    }
    //canEatAnimal - определяем можно ли есть другого животного
    public boolean canEatAnimal(Animal animal){
        if (!animal.isSameAnimal(otherAnimal)){ //если животные разных типов
            if (animal.isEatable(otherAnimal)){ //если вероятность поедания животного совпала
                System.out.println(animal + " CAN EAT  " + otherAnimal);
                return true;
            }
        }
        return false;
    }
    public boolean canSeeAnyone(Animal animal) { //хитбокс и зрение и кучу методов для проверки
        //todo изменить animals на private переменную
        //волк видит кого-то         (найти кого он видит)
        //вывод массива с животным которых он видит
        //беру одного животного и передаю его в canEatAnimal
        //animal.canSeePlayer(animal)
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        Rectangle2D.Double hitBox = animal.getHitBox();
        animals = uploadAnimals();
        for (Animal otherAnimal : animals) {
            if (!animal.isSameAnimal(otherAnimal)){
                    Rectangle2D.Double playerHitBox = otherAnimal.getHitBox();
                    int playerTileY = (int) (playerHitBox.y / TILE_SIZE_DEFAULT); //1 - 10, 2 - 12    Abs(2 - 1) <=2
                    int enemyTileY = (int) (hitBox.y / TILE_SIZE_DEFAULT);
                    if (abs(playerTileY - enemyTileY) <= 4) { //todo добавить погрешность!!!         <<READY>>
                        if (isPlayerInRange(animal, otherAnimal)) {
                            if (IsSightClear(lvlData, hitBox, playerHitBox, enemyTileY)) {
//                                if (canEatAnimal(animal, otherAnimal)){
                                System.out.println(animal + " CAN SEE " + otherAnimal);
                                this.otherAnimal = otherAnimal;
                                return true;
//                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public int wherePlayerX(Animal animal, Animal otherAnimal) {
        Rectangle2D.Double animalHitBox = animal.getHitBox();
        Rectangle2D.Double otherAnimalHitBox = otherAnimal.getHitBox();
        System.out.println("DESTINATION BETWEEN " + animal + " AND " + otherAnimal + " = " + (int) (otherAnimalHitBox.x - animalHitBox.x));
        return (int) (otherAnimalHitBox.x - animalHitBox.x);
    }
    public static boolean isPlayerInRange(Animal animal, Animal otherAnimal) { //зона видимости/реагирования игрока
        Rectangle2D.Double animalHitBox = animal.getHitBox();
        int range = animal.getStateAnimal().getRange();
        Rectangle2D.Double otherAnimalHitBox = otherAnimal.getHitBox();
        int absValue = (int) abs(otherAnimalHitBox.x - animalHitBox.x);
        return absValue <= range;
    }
    public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Double firstHitbox, Rectangle2D.Double secondHitbox, int yTile) { //проверка может ли сущность 1 дойти дойти до сущности 2
        int firstXTile = (int) (firstHitbox.x / TILE_SIZE_DEFAULT);
        int secondXTile = (int) (secondHitbox.x  / TILE_SIZE_DEFAULT);

        if (firstXTile > secondXTile)
            return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
        else
            return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
    }
    private static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {// проверка, что между а и б пусто, а также что есть земля под ногами
        if (IsAllTilesClear(xStart, xEnd, y, lvlData))
            for (int i = 0; i < xEnd - xStart; i++) {
                if (!IsTileSolid(xStart + i, y + 1, lvlData))
                    return false;
            }
        return true;
    }
    private static boolean IsAllTilesClear(int xStart, int xEnd, int y, int[][] lvlData) { //проверка что между а и б пусто
        for (int i = 0; i < xEnd - xStart; i++)
            if (IsTileSolid(xStart + i, y, lvlData))
                return false;
        return true;
    }
    public void eatAnimal( ) {
        //playingGame.eatAnimal();
        //playerAnimation.setAnimationState(PlayerAnimation.AnimationState.DEAD);
    }

    public boolean checkPlayerHit(Rectangle2D.Double attackBox) {
        Rectangle2D.Double playerHitBox = otherAnimal.getHitBox();
        return attackBox.intersects(playerHitBox);
    }
    public void attackEnemy(Rectangle2D.Double attackBox) {
        playingGame.attackEnemy(attackBox);
    }

    public Animal getOtherAnimal() {
        return otherAnimal;
    }

}
