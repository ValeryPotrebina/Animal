package playing.entities.statics;

import playing.PlayingGame;

import java.awt.geom.Rectangle2D;
import static Constants.Constants.GameWindowConstants.*;
public class EntityIslandManager {
    private PlayingGame playingGame;
//todo разобраться с этим классом!!!!!!!!!!!!!!!!!!!!!!!!
    //TODO 1. Добавить озера
    //TODO Животное может передвигаться не только по полу
    //TODO Животное может прыгать
    //TODO Животное может определять, что за другое животное перед ним и из этого действовать
    //TODO
    //
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
    private boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) { //заполненная чем-то плитка
        int value = lvlData[yTile][xTile];
        return value != 11; //11 - пустота
    }
    public boolean canSeePlayer(Rectangle2D.Double hitBox, float range) {
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        Rectangle2D.Double playerHitBox = playingGame.getPlayerHitBox();
        int playerTileY = (int) (playerHitBox.y / TILE_SIZE_DEFAULT);
        int enemyTileY = (int) (hitBox.y / TILE_SIZE_DEFAULT);

        if (playerTileY == enemyTileY) {
            if (isPlayerInRange(hitBox, range)) {
                if (IsSightClear(lvlData, hitBox, playerHitBox, enemyTileY))
                    return true;
            }
        }

        return false;
    }

    public int wherePlayerX(Rectangle2D.Double hitBox) {//передаем краба
        Rectangle2D.Double playerHitBox = playingGame.getPlayerHitBox();
        return (int) (playerHitBox.x - hitBox.x);
    }
    public boolean isPlayerInRange(Rectangle2D.Double hitBox, float range) { //зона видимости/реагирования игрока
        Rectangle2D.Double playerHitBox = playingGame.getPlayerHitBox();
        int absValue = (int) Math.abs(playerHitBox.x - hitBox.x);
        return absValue <= range;
    }
    private boolean IsSightClear(int[][] lvlData, Rectangle2D.Double firstHitbox, Rectangle2D.Double secondHitbox, int yTile) { //проверка может ли сущность 1 дойти дойти до сущности 2
        int firstXTile = (int) (firstHitbox.x / TILE_SIZE_DEFAULT);
        int secondXTile = (int) (secondHitbox.x  / TILE_SIZE_DEFAULT);

        if (firstXTile > secondXTile)
            return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
        else
            return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
    }
    private boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {// проверка, что между а и б пусто, а также что есть земля под ногами
        if (IsAllTilesClear(xStart, xEnd, y, lvlData))
            for (int i = 0; i < xEnd - xStart; i++) {
                if (!IsTileSolid(xStart + i, y + 1, lvlData))
                    return false;
            }
        return true;
    }
    private boolean IsAllTilesClear(int xStart, int xEnd, int y, int[][] lvlData) { //проверка что между а и б пусто
        for (int i = 0; i < xEnd - xStart; i++)
            if (IsTileSolid(xStart + i, y, lvlData))
                return false;
        return true;
    }
}
