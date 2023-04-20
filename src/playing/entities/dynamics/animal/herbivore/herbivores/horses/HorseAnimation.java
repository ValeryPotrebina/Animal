package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import Constants.LoadSave;
import playing.PlayingInterface;
import playing.entities.dynamics.animal.predator.predators.wolf.Wolf;
import playing.entities.dynamics.animal.predator.predators.wolf.WolfAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_HORSE;
import static Constants.Constants.TextureConstants.Entity.*;
import static playing.entities.dynamics.animal.herbivore.herbivores.horses.HorseAnimation.HorseAnimationState.*;


public class HorseAnimation implements PlayingInterface {
    private BufferedImage[][] animations;
    final Horse horse;
    public enum HorseAnimationState {  //вынести в отдельный енум!!!
        IDLE,
        RUNNING,
        JUMP,
        FALLING,
        EAT,
        DEAD,
        SLEEP;

        public static HorseAnimationState animationState = IDLE;
    }
    private int aniTick, aniIndex;
    private int flipW = 1;
    private int flipX = 0;
    private boolean dead;

    protected HorseAnimation(Horse horse){
        this.horse = horse;
        loadImages();
    }

    private void loadImages() {
        BufferedImage image = LoadSave.getSpireAtlas(ENTITY_LOCATION_TEXTURES, HORSE_SPRITE_PNG);
        animations = new BufferedImage[7][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 108, j * 73, 108, 73);
            }
        }
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        BufferedImage bufferedImage = animations[animationState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((horse.getHitBox().x - x + flipX) * scale),
                (int) ((horse.getHitBox().y + 5 - y) * scale),          //сравнить с wolfAnimation
                (int) (horse.getHitBox().width * flipW * scale), //не менять scale!!!
                (int) (horse.getHitBox().height * scale),
                null);
        horse.drawHitBox(g, scale, x, y);
    }

    @Override
    public void update() {
        updateAnimationBox();
        updateAnimationTick();
    }
    private void updateAnimationBox() {
        boolean right = horse.getHorseMove().isRight();
        boolean left = horse.getHorseMove().isLeft();
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
        if (aniTick >= ANI_SPEED_ENEMY_HORSE) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount()) {
                if (animationState == DEAD) {
                    horse.setActive(false);
                }

                animationState = IDLE;
                aniIndex = 0;
            }
        }
    }
    public void setAnimationState(HorseAnimationState state) {
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
            case RUNNING:
            case JUMP:
            case DEAD:
            case EAT:
            case SLEEP:
            case FALLING:
            default:
                return 6;
        }
    }

    public HorseAnimationState getAnimationState() {
        return animationState;
    }
}
