package playing.entities.animalsAnimation;

import playing.PlayingInterface;
import playing.PlayingMouseListenerInterface;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_WOLF;

public class WolfAttack extends WolfModule implements PlayingInterface, PlayingMouseListenerInterface {
    protected Rectangle2D.Double attackBox;
    private int aniTick, aniIndex;
    public WolfAttack(WolfModuleManager wolfModuleManager,
                      int x, int y,
                      int width, int height) {
        super(wolfModuleManager);
        initAttackBox(x, y, width, height);
//        loadImages();
    }

    protected void initAttackBox(int x,  int y, int width, int height){
        attackBox = new Rectangle2D.Double(x, y, width, height);
    }
    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawAttackBox(g, scale, x, y);
    }

    protected void drawAttackBox(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY) {
        g.setColor(Color.red);
        g.drawRect((int) ((attackBox.x - lvlOffsetX) * scale),
                (int) ((attackBox.y - lvlOffsetY) * scale),
                (int) (attackBox.width * scale),
                (int) (attackBox.height * scale));
    }
    @Override
    public void update() {
        updateAttackBox();
        updateAnimationTick();
    }

    private void updateAttackBox(){
        Rectangle2D.Double hitBox = wolfModuleManager.getHitBox();
        boolean right = wolfModuleManager.getPlayerMove().isRight();
        boolean left = wolfModuleManager.getPlayerMove().isLeft();

        if (right){
            attackBox.x = hitBox.x + hitBox.width + 3;
        } else if (left) {
            attackBox.x = hitBox.x - hitBox.width - 3;
        }
        attackBox.y = hitBox.y + 10;
    }
    private void updateAnimationTick(){
        aniTick ++;
        if (aniTick >= ANI_SPEED_ENEMY_WOLF) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex > 1000){
                aniIndex =0;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wolfModuleManager.getWolfAnimation().setAnimationState(WolfAnimation.AnimationState.EAT);
        wolfModuleManager.eatEnemy(attackBox);
    }
}
