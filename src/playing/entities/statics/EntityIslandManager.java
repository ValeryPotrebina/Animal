package playing.entities.statics;

import playing.PlayingGame;

import java.awt.geom.Rectangle2D;
import static Constants.Constants.GameWindowConstants.*;
public class EntityIslandManager {
    private PlayingGame playingGame;
//todo разобраться с этим классом
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

    public boolean CanMoveHere(Rectangle2D.Double hitBox) {
        int[][] lvlData = playingGame.getLevelManager().getIslandData();
        if (!IsSolid(hitBox.x, hitBox.y, lvlData)) {
            if (!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height, lvlData)) {
                if (!IsSolid(hitBox.x + hitBox.width, hitBox.y, lvlData)) {
                    if (!IsSolid(hitBox.x, hitBox.y + hitBox.height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean IsSolid(double x, double y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * TILE_SIZE_DEFAULT;
        int maxHeight = lvlData.length * TILE_SIZE_DEFAULT;

        if (x < 0 || x >= maxWidth)
            return true;
        if (y < 0 || y >= maxHeight)
            return true;

        double xIndex = x / TILE_SIZE_DEFAULT;
        double yIndex = y / TILE_SIZE_DEFAULT;

        return IsTileSolid((int) xIndex, (int) yIndex, lvlData);
    }
    private boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
        int value = lvlData[yTile][xTile];

        return value != 11;
    }
}
