package playing.entities.animalsAnimation;

import Constants.LoadSave;
import playing.PlayingInterface;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static Constants.Constants.TextureConstants.Wolf.*;
import static playing.entities.animalsAnimation.WolfAnimation.AnimationState.DEAD;
import static playing.entities.animalsAnimation.WolfAnimation.AnimationState.animationState;

public class WolfAnimation extends WolfModule implements PlayingInterface {
    //todo разобраться с этим классом
    private BufferedImage wolf;




    enum AnimationState {
        IDLE, RUNNING, JUMP, FALLING, EAT, DEAD, SLEEP;

        public static AnimationState animationState = IDLE;
    }

    protected Rectangle2D.Double animationBox;
    private int aniTick, aniIndex;
    private int flipW = 1;
    private int flipX = 0;

    private boolean dead;

    public WolfAnimation(WolfModuleManager wolfModuleManager){
        super(wolfModuleManager);
        loadImages();
        initAnimationBox();
    }

    private void initAnimationBox() {
        animationBox = new Rectangle2D.Double();
    }
    private void loadImages(){
        wolf = LoadSave.getSpireAtlas(WOLF_LOCATION_TEXTURES, WOLF_SPRITES);
        System.out.println(wolf);
    }

    @Override
    public void update() {
        updateAnimationBox();
    }

    //todo изменено!
    private void updateAnimationBox() {
        boolean right = wolfModuleManager.getPlayerMove().isRight();
        boolean left = wolfModuleManager.getPlayerMove().isLeft();
        if (right) {
            flipW = 1;
            flipX = 0;
        } else if (left) {
            flipW = -1;
            flipX = wolf.getWidth();
        }
    }

//    ordinal() - возвращает порядковый номер
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        Rectangle2D.Double hitBox = wolfModuleManager.getHitBox();
        g.drawImage(wolf,
                (int) ((hitBox.x - 21 - x + flipX) * scale),
                (int) ((hitBox.y - 4 - y) * scale),
                (int) (wolf.getWidth() * flipW * scale),
                (int) (wolf.getHeight() * scale),
                null);
    }

    public void setAnimationState(AnimationState state) {
        if (dead) {
            return;
        }
        if (state == DEAD) {
            dead = true;
        }

        animationState = state;
        aniIndex = 0;
    }
    public AnimationState getAnimationState() {
        return animationState;
    }


}

