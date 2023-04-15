package playing.entities.animalsAnimation;

import Constants.LoadSave;
import gamestates.playingstates.EnumPlayState;
import playing.PlayingInterface;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY;
import static Constants.Constants.TextureConstants.Wolf.*;
import static playing.entities.animalsAnimation.WolfAnimation.AnimationState.*;

public class WolfAnimation extends WolfModule implements PlayingInterface {
    //todo разобраться с этим классом
    //private BufferedImage wolf;
    private BufferedImage[][] wolf = new BufferedImage[7][5];




    enum AnimationState {
        IDLE,
        RUNNING,
        JUMP,
        FALLING,
        EAT,
        DEAD,
        SLEEP;

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
        BufferedImage img = LoadSave.getSpireAtlas(WOLF_LOCATION_TEXTURES, WOLF_SPRITES);
        for (int j = 0; j < wolf.length; j++)//столбцы
            for (int i = 0; i < wolf[j].length; i++)//строки
                wolf[j][i] = img.getSubimage(i * 83, j * 53, 83, 53);
        System.out.println(wolf);
    }

    @Override
    public void update() {
        updateAnimationBox();
        updateAnimationTick();
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED_ENEMY) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount()) {
                if (animationState == DEAD) {
                    EnumPlayState.state = EnumPlayState.GAME_OVER;
                }
                animationState = IDLE;
                aniIndex = 0;
            }
        }
    }

    //todo изменено!
    private void updateAnimationBox() {
        boolean right = wolfModuleManager.getPlayerMove().isRight();
        boolean left = wolfModuleManager.getPlayerMove().isLeft();
        BufferedImage bufferedImage = wolf[animationState.ordinal()][aniIndex];

        if (right) {
            flipW = 1;
            flipX = 0;
        } else if (left) {
            flipW = -1;
            flipX = bufferedImage.getWidth();
        }
    }

//    ordinal() - возвращает порядковый номер
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        Rectangle2D.Double hitBox = wolfModuleManager.getHitBox();
        BufferedImage bufferedImage = wolf[animationState.ordinal()][aniIndex];
        g.drawImage(bufferedImage,
                (int) ((hitBox.x - 21 - x + flipX) * scale),
                (int) ((hitBox.y - 4 - y) * scale),
                (int) (bufferedImage.getWidth() * flipW * scale),
                (int) (bufferedImage.getHeight() * scale),
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

    private static int GetSpriteAmount() {

        switch (animationState) {
            case DEAD:
            case RUNNING:
            case IDLE:
            case EAT:
            case JUMP:
            case SLEEP:
            case FALLING:
            default:
                return 5;
        }
    }
}

