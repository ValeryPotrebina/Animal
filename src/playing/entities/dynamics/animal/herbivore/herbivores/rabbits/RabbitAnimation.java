package playing.entities.dynamics.animal.herbivore.herbivores.rabbits;

import Constants.LoadSave;
import playing.PlayingInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_RABBIT;
import static Constants.Constants.TextureConstants.Entity.ENTITY_LOCATION_TEXTURES;
import static Constants.Constants.TextureConstants.Entity.RABBIT_SPRITE_PNG;
import static playing.entities.dynamics.animal.herbivore.herbivores.rabbits.RabbitAnimation.RabbitAnimationState.*;

public class RabbitAnimation implements PlayingInterface {
    private BufferedImage[][] animations;
    final Rabbit rabbit; //modificator!!!
    public enum RabbitAnimationState {  //вынести в отдельный енум!!!
        IDLE,
        RUNNING,
        JUMP,
        FALLING,
        EAT,
        DEAD,
        SLEEP;

        public static RabbitAnimationState animationState = IDLE;
    }
    private int aniTick, aniIndex;
    private int flipW = 1;
    private int flipX = 0;
    private boolean dead;

    protected RabbitAnimation(Rabbit rabbit) {
        this.rabbit = rabbit;
        loadImages();
    }
    private void loadImages() {
        BufferedImage image = LoadSave.getSpireAtlas(ENTITY_LOCATION_TEXTURES, RABBIT_SPRITE_PNG);
        animations = new BufferedImage[7][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 66, j * 66, 66, 66);
            }
        }
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        BufferedImage bufferedImage = animations[animationState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((rabbit.getHitBox().x - x + flipX) * scale),
                (int) ((rabbit.getHitBox().y - y) * scale),          //сравнить с wolfAnimation
                (int) (rabbit.getHitBox().width * flipW * scale), //не менять scale!!!
                (int) (rabbit.getHitBox().height * scale),
                null);
        rabbit.drawHitBox(g, scale, x, y);
    }

    @Override
    public void update() {
        updateAnimationTick();
        updateAnimationBox();
    }
    private void updateAnimationBox() {
        boolean right = rabbit.getRabbitMove().isRight();
        boolean left = rabbit.getRabbitMove().isLeft();
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
        if (aniTick >= ANI_SPEED_ENEMY_RABBIT) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount()) {
                if (animationState == DEAD) {
                    rabbit.setActive(false);
                }

                animationState = IDLE;
                aniIndex = 0;
            }
        }
    }
    public void setAnimationState(RabbitAnimationState state) {
        if (dead) {
            return;
        }
        if (state == DEAD) {
            dead = true;
        }
        animationState = state;
        aniIndex = 0;
    }
    public RabbitAnimationState getAnimationState() {
        return animationState;
    }
    public int getAniIndex() {
        return aniIndex;
    }

    private static int getSpriteAmount() {

        switch (animationState) {
            case RUNNING:
                return 4;
            case IDLE:
            case EAT:
            case JUMP:
            case SLEEP:
            case FALLING:
            case DEAD:
            default:
                return 2;
        }
    }
}
