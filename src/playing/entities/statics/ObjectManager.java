package playing.entities.statics;

import Constants.Constants;
import playing.PlayingInterface;
import playing.island.Island;
import playing.island.IslandManager;

import java.awt.*;
import java.util.ArrayList;

public class ObjectManager implements PlayingInterface {
    private ArrayList<Tree> trees;

    public ObjectManager(Island island){
        loadObjects(island);
    }

    private void loadObjects(Island island){
        trees = island.getTrees();
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawTrees(g, scale, x, y);
    }

    @Override
    public void update() {
        updateObjects();
    }

    private void updateObjects(){
        for (Tree tree : trees){
            tree.update();
        }
    }

    private void drawTrees(Graphics g, float scale, int x, int y){
        for (Tree tree : trees) {
            tree.draw(g, scale, x, y);
        }
    }
}
