package playing.entities.dynamics.animal.plant.plants.sunflower;

import Constants.LoadSave;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_SUNFLOWER;
import static Constants.Constants.TextureConstants.Entity.ENTITY_LOCATION_TEXTURES;
import static Constants.Constants.TextureConstants.Entity.SUNFLOWER_SPRITE_PNG;
import static playing.entities.dynamics.animal.animalModules.AnimalAnimation.AnimationState.DEAD;
import static playing.entities.dynamics.animal.animalModules.AnimalAnimation.AnimationState.IDLE;

public class SunflowerAnimation extends AnimalAnimation {
    final Sunflower sunflower;

    public SunflowerAnimation(Sunflower sunflower) {
        this.sunflower = sunflower;
        loadImages();
    }

    private void loadImages(){
        BufferedImage image = LoadSave.getSpireAtlas(ENTITY_LOCATION_TEXTURES, SUNFLOWER_SPRITE_PNG);
        animations = new BufferedImage[1][7];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 35, j * 45, 35, 45);
            }
        }
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        BufferedImage bufferedImage = animations[animationState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((sunflower.getHitBox().x  - x + flipX) * scale),
                (int) ((sunflower.getHitBox().y  - y) * scale),          //сравнить с wolfAnimation
                (int) (sunflower.getHitBox().width * scale ), //не менять scale!!!
                (int) (sunflower.getHitBox().height * scale ),
                null);
        sunflower.drawHitBox(g, scale, x, y);
    }

    @Override
    public void update() {
        updateAnimationTick();
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED_ENEMY_SUNFLOWER) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount()) {
                if (animationState == DEAD) {
                    sunflower.setActive(false);
                }

                animationState = IDLE;
                aniIndex = 0;
            }
        }
    }
    private int getSpriteAmount() {
        switch (animationState) {
            case IDLE:
                return 7;
            default:
                return 0;
        }
    }
}
