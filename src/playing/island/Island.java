package playing.island;

import Constants.LoadSave;
import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.herbivore.herbivores.horses.Horse;
import playing.entities.dynamics.animal.herbivore.herbivores.rabbits.Rabbit;
import playing.entities.dynamics.animal.predator.predators.wolf.Wolf;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Constants.Constants.GameWindowConstants.*;
import static Constants.Constants.IslandConstants.Entity.Enemy.*;
import static Constants.Constants.TextureConstants.Island.*;

public class Island implements PlayingInterface {
    private ArrayList<Animal> animals;
    private final BufferedImage islandImg;
    private BufferedImage[] levelSprite;
    private int[][] islandData;
    private BufferedImage backgroundImg;
    private int maxLvlOffsetX, maxLvlOffsetY;

    public Island(BufferedImage island){
        this.islandImg = island;
        animals = new ArrayList<>();
        GetIslandData();
        loadBackGroundImages();
        calcLvlOffSet();
        importOutsideSprites();
    }
    private void GetIslandData(){
        int[][] islandData = new int[islandImg.getHeight()][islandImg.getWidth()];
        for (int j = 0; j < islandImg.getHeight(); j++) {
            for (int i = 0; i < islandImg.getWidth(); i++) {
                Color color = new Color(islandImg.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                islandData[j][i] = value;
            }
        }
        this.islandData = islandData;
    }
    private void loadBackGroundImages(){
        backgroundImg = LoadSave.getSpireAtlas(ISLAND_LOCATION_TEXTURES, LVL_BACKGROUND_PNG);
    }

    private void calcLvlOffSet(){
        int lvlTilesWideX = islandImg.getWidth();
        int maxTilesOffSetX = lvlTilesWideX - SIZE_WIDTH;
        maxLvlOffsetX = TILE_SIZE_DEFAULT * maxTilesOffSetX;

        int lvlTilesWideY = islandImg.getHeight();
        int maxTileOffSetY = lvlTilesWideY - SIZE_HEIGHT;
        maxLvlOffsetY = TILE_SIZE_DEFAULT * maxTileOffSetY;

    }

    private void importOutsideSprites(){
        BufferedImage image = LoadSave.getSpireAtlas(ISLAND_LOCATION_TEXTURES, LVL_TEXTURES_PNG);
        System.out.println("sprites - " + image);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = image.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawBackground(g, scale);
        drawLvlSprite(g, scale, x, y);
    }

    @Override
    public void update() {

    }


    private void drawBackground(Graphics g, float scale){
        g.drawImage(backgroundImg, 0, 0, (int)(GAME_WIDTH_DEFAULT * scale), (int) (GAME_HEIGHT_DEFAULT * scale), null);
    }
    private void drawLvlSprite(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY){
        for (int j = 0; j < islandData.length; j++) {
            for (int i = 0; i < islandData[0].length; i++) {
                int index = islandData[j][i];
                g.drawImage(levelSprite[index],
                        (int)((TILE_SIZE_DEFAULT * i - lvlOffsetX) * scale),
                        (int) ((TILE_SIZE_DEFAULT * j - lvlOffsetY) * scale),
                        (int) (TILE_SIZE_DEFAULT * scale), (int) (TILE_SIZE_DEFAULT * scale), null);
            }
        }
    }

    public ArrayList<Rabbit> getRabbits(){
        ArrayList<Rabbit> rabbits = new ArrayList<>();
        int count = 0;
        for (int j = 0; j < islandImg.getHeight(); j++){
            for (int i = 0; i < islandImg.getWidth(); i++) {
                Color color = new Color(islandImg.getRGB(i, j));
                int value = color.getGreen();
                if (value == ENEMY_INDEX_RABBIT){
                    count ++;
                    rabbits.add(new Rabbit(i * TILE_SIZE_DEFAULT, j * TILE_SIZE_DEFAULT, count));
                }
            }
        }
        animals.addAll(rabbits);

        return rabbits;
    }
    public ArrayList<Wolf> getWolves(){
        ArrayList<Wolf> wolves = new ArrayList<>();
        int count = 0;
        for (int j = 0; j < islandImg.getHeight(); j++){
            for (int i = 0; i < islandImg.getWidth(); i++) {
                Color color = new Color(islandImg.getRGB(i, j));
                int value = color.getGreen();
                if (value == ENEMY_INDEX_WOLF){
                    count++;
                    wolves.add(new Wolf(i * TILE_SIZE_DEFAULT, j * TILE_SIZE_DEFAULT, count));
                }
            }
        }
        animals.addAll(wolves);

        return wolves;
    }

    public ArrayList<Horse> getHorses(){
        ArrayList<Horse> horses = new ArrayList<>();
        int count = 0;
        for (int j = 0; j < islandImg.getHeight(); j++){
            for (int i = 0; i < islandImg.getWidth(); i++) {
                Color color = new Color(islandImg.getRGB(i, j));
                int value = color.getGreen();
                if (value == ENEMY_INDEX_HORSE){
                    count++;
                    horses.add(new Horse(i * TILE_SIZE_DEFAULT, j * TILE_SIZE_DEFAULT, count));
                }
            }
        }
        animals.addAll(horses);
        return horses;
    }
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public int[][] getIslandData() {
        return islandData;
    }

    public int getMaxLvlOffsetX() {
        return maxLvlOffsetX;
    }

    public int getMaxLvlOffsetY() {
        return maxLvlOffsetY;
    }
}
