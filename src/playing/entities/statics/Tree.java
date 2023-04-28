package playing.entities.statics;

import Constants.LoadSave;
import playing.PlayingInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_TREE;
import static Constants.Constants.GameWindowConstants.TILE_SIZE_DEFAULT;
import static Constants.Constants.TextureConstants.Entity.ENTITY_LOCATION_TEXTURES;
import static Constants.Constants.TextureConstants.Entity.TREE_SPRITE_PNG;
//Разобраться с классом
public class Tree extends ObjectEntity implements PlayingInterface {
    private BufferedImage[] treeAnimation;
    private int aniTick, aniIndex;
    public Tree(double x, double y) {
        super(x, y - TILE_SIZE_DEFAULT, 304, 312);
        setHitBoxTexture(x, y - 273, 304, 312);
        loadImages();
    } //304/312

    private void loadImages(){
        BufferedImage img = LoadSave.getSpireAtlas(ENTITY_LOCATION_TEXTURES, TREE_SPRITE_PNG);
        treeAnimation = new BufferedImage[4];
        for (int i = 0; i < treeAnimation.length; i++) {
            treeAnimation[i] = img.getSubimage(i * 304, 0, 304, 312);
        }
    }
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED_ENEMY_TREE * 2) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= treeAnimation.length) {
                aniIndex = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        g.drawImage(treeAnimation[aniIndex],
                (int) ((hitBoxTexture.x - x) * scale),
                (int) ((hitBoxTexture.y - y) * scale),
                (int) (hitBoxTexture.width * scale),
                (int) (hitBoxTexture.height * scale),
                null);
        drawHitBoxTexture(g, scale, x, y);
    }

    @Override
    public void update() {
        updateAnimationTick();
    }
}
