package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.PlayingInterface;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WolfEat implements PlayingInterface {
    final Wolf wolf;
    protected boolean attackChecked;
    protected Rectangle2D.Double attackBox;

    public WolfEat(Wolf wolf){
        this.wolf = wolf;
        initEatBox();
    }

    private void initEatBox(){
        Rectangle2D.Double hitBoxTexture = wolf.getHitBoxTexture();
        attackBox = new Rectangle2D.Double(hitBoxTexture.x,
                hitBoxTexture.y,
                hitBoxTexture.width,
                hitBoxTexture.height - 10);
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawAttackBox(g, scale, x, y);
    }

    protected void drawAttackBox(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY) {
        g.setColor(Color.BLUE);
        g.drawRect((int) ((attackBox.x - lvlOffsetX) * scale),
                (int) ((attackBox.y - lvlOffsetY) * scale),
                (int) (attackBox.width * scale),
                (int) (attackBox.height * scale));
    }
    @Override
    public void update() {
        updateAttackBox();
        checkEnvironment();
    }

    private void checkEnvironment(){
        if (wolf.canSeeAnyone(wolf)){
            if (wolf.canEatAnimal(wolf)){
                AnimalAnimation wolfAnimation = wolf.getAnimalAnimation();
                if (wolfAnimation.getAnimationState() != AnimalAnimation.AnimationState.EAT)
                    wolfAnimation.setAnimationState(AnimalAnimation.AnimationState.EAT);
                else if (wolfAnimation.getAnimationState() == AnimalAnimation.AnimationState.EAT) {
                    if (wolfAnimation.getAniIndex() == 0){
                        attackChecked = false;
                    }
                    if (wolfAnimation.getAniIndex() == 5 && !attackChecked){
                        if (wolf.checkPlayerHit(attackBox)) {
                            attackChecked = true;
                            wolf.eatAnimal();
                        }
                    }
                }
            }
        }
    }
    private void updateAttackBox(){
        Rectangle2D.Double hitBoxTexture = wolf.getHitBoxTexture();
        attackBox.x = hitBoxTexture.x;
        attackBox.y = hitBoxTexture.y + 10;
    }
}
