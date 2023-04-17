package playing.island;

import Constants.LoadSave;
import playing.PlayingInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class IslandManager implements PlayingInterface {
    private Island island;
    public IslandManager(){
        buildIsland();
    }

    private void buildIsland(){
        BufferedImage image = LoadSave.GetIsland();
        System.out.println("island - " + image);
        island = new Island(image);
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        island.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        island.update();
    }
    public int[][] getIslandData(){
        return island.getIslandData();
    }

    public int getLvlOffSetX(){
        return island.getMaxLvlOffsetX();
    }

    public int getLvlOffSetY(){
        return island.getMaxLvlOffsetY();
    }

    public Island getIsland() {
        return island;
    }
}
