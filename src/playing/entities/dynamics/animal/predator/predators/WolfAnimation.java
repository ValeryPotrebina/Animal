package playing.entities.dynamics.animal.predator.predators;

import Constants.LoadSave;
import playing.PlayingInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_WOLF;
import static Constants.Constants.TextureConstants.Wolf.WOLF_LOCATION_TEXTURES;
import static Constants.Constants.TextureConstants.Wolf.WOLF_SPRITES;
import static playing.entities.dynamics.animal.predator.predators.WolfAnimation.WolfAnimationState.*;

public class WolfAnimation implements PlayingInterface {
    private BufferedImage[][] animations;
    final Wolf wolf;
    public enum WolfAnimationState {  //вынести в отдельный енум!!!
        IDLE,
        RUNNING,
        JUMP,
        FALLING,
        EAT,
        DEAD,
        SLEEP;

        public static WolfAnimationState animationState = IDLE;
    }
    private int aniTick, aniIndex;
    private int flipW = 1;
    private int flipX = 0;
    private boolean dead;

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
        BufferedImage bufferedImage = animations[WolfAnimationState.animationState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((wolf.getWolfEntity().getHitBox().x - 18 - x + flipX) * scale),
                (int) ((wolf.getWolfEntity().getHitBox().y - 10 - y) * scale),          //сравнить с wolfAnimation
                (int) (wolf.getWolfEntity().getHitBox().width * flipW * scale * 1.25), //не менять scale!!!
                (int) (wolf.getWolfEntity().getHitBox().height * scale * 1.25),
                null);
        wolf.getWolfEntity().drawHitBox(g, scale, x, y);
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
                    wolf.getWolfEntity().setActive(false);
                }

                animationState = IDLE;
                aniIndex = 0;
            }
        }
    }
    public void setAnimationState(WolfAnimationState state) {
        if (dead) {
            return;
        }
        if (state == DEAD) {
            dead = true;
        }
        animationState = state;
        aniIndex = 0;
    }


    private static int getSpriteAmount() {
        switch (animationState) {
            case IDLE:
                return 4;
            case DEAD:
                return 3;
            case RUNNING:
            case EAT:
            case JUMP:
            case SLEEP:
            case FALLING:
            default:
                return 5;
        }
    }

    public WolfAnimationState getAnimationState() {
        return animationState;
    }
}
