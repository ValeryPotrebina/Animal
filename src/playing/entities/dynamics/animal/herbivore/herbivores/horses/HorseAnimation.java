package playing.entities.dynamics.animal.herbivore.herbivores.horses;

import Constants.LoadSave;
import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_HORSE;
import static Constants.Constants.TextureConstants.Entity.*;
import static playing.entities.dynamics.animal.animalModules.AnimalAnimation.AnimationState.*;

//todo вернуть нормального коня!!
public class HorseAnimation extends AnimalAnimation implements PlayingInterface {
    final Horse horse;
    private AnimationState horseAnimalState = IDLE;


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
        BufferedImage bufferedImage = animations[horseAnimalState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((horse.getHitBox().x - x + flipX) * scale),
                (int) ((horse.getHitBox().y + 10 - y) * scale),          //сравнить с wolfAnimation
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
        BufferedImage bufferedImage = animations[horseAnimalState.ordinal()][aniIndex];
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
                if (horseAnimalState == DEAD) {
                    horse.setActive(false);
                }

                horseAnimalState = IDLE;
                aniIndex = 0;
            }
        }
    }
    public void setAnimationState(AnimationState state) {
        if (dead) {
            return;
        }
        if (state == DEAD) {
            dead = true;
        }
        horseAnimalState = state;
        aniIndex = 0;
    }


    private int getSpriteAmount() {
        switch (horseAnimalState) {
            case IDLE:
                return 4;
            case FALLING:
            case RUNNING:
            case JUMP:
            case DEAD:
            case EAT:
            case SLEEP:
            default:
                return 6;
        }
    }

    public AnimationState getAnimationState() {
        return horseAnimalState;
    }
}
