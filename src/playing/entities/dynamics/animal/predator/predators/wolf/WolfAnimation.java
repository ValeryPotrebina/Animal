package playing.entities.dynamics.animal.predator.predators.wolf;

import Constants.LoadSave;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_WOLF;
import static Constants.Constants.TextureConstants.Wolf.WOLF_LOCATION_TEXTURES;
import static Constants.Constants.TextureConstants.Wolf.WOLF_SPRITES;
import static playing.entities.dynamics.animal.animalModules.AnimalAnimation.AnimationState.DEAD;
import static playing.entities.dynamics.animal.animalModules.AnimalAnimation.AnimationState.IDLE;

//todo можно сделать абстрактный класс animation для всех животных
public class WolfAnimation extends AnimalAnimation{
    final Wolf wolf;

    protected WolfAnimation(Wolf wolf){
        this.wolf = wolf;
        loadImages();
    }

    private void loadImages() {
        BufferedImage image = LoadSave.getSpireAtlas(WOLF_LOCATION_TEXTURES, WOLF_SPRITES);
        animations = new BufferedImage[7][5];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 83, j * 53, 83, 53);
            }
        }
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        BufferedImage bufferedImage = animations[animationState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((wolf.getHitBox().x  + 35 - x + flipX) * scale),
                (int) ((wolf.getHitBox().y -4 - y) * scale),          //сравнить с wolfAnimation
                (int) (wolf.getHitBox().width * flipW * scale * 1.25), //не менять scale!!!
                (int) (wolf.getHitBox().height * scale * 1.25),
                null);
        wolf.drawHitBox(g, scale, x, y);
    }

    @Override
    public void update() {
        updateAnimationBox();
        updateAnimationTick();
    }
    private void updateAnimationBox() {
        boolean right = wolf.getWolfMove().isRight();
        boolean left = wolf.getWolfMove().isLeft();
        BufferedImage bufferedImage = animations[animationState.ordinal()][aniIndex];
        if (left) {
            flipW = 1;
            flipX = 0;
        } else if (right) {
            flipW = -1;
            flipX = bufferedImage.getWidth()  - 25;
        }
    }
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED_ENEMY_WOLF) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount()) {
                if (animationState == DEAD) {
                    wolf.setActive(false);
                }

                animationState = IDLE;
                aniIndex = 0;
            }
        }
    }


    private int getSpriteAmount() {
        switch (animationState) {
            case IDLE:
            case EAT:
                return 4;
            case DEAD:
            case RUNNING:
            case JUMP:
            case SLEEP:
            case FALLING:
            default:
                return 5;
        }
    }

}
