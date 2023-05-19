package playing.entities.dynamics.animal.predator.predators.wolf;

import playing.PlayingInterface;
import playing.entities.EntityIslandManager;
import playing.entities.dynamics.animal.animalModules.Animal;
import playing.entities.dynamics.animal.animalModules.AnimalAnimation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static Constants.Constants.GameConstants.ANI_SPEED_ENEMY_WOLF;

public class WolfEat implements PlayingInterface {
    final Wolf wolf;
    protected Rectangle2D.Double attackBox;
    boolean attackChecked;
    private int aniTick, aniIndex;

    public WolfEat(Wolf wolf){
        this.wolf = wolf;
        initEatBox();
    }

    private void initEatBox(){
        Rectangle2D.Double hitBoxTexture = wolf.getHitBoxTexture();
        attackBox = new Rectangle2D.Double(hitBoxTexture.x , hitBoxTexture.y, hitBoxTexture.width, hitBoxTexture.height);
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        drawAttackBox(g, scale, x, y);
    }

    protected void drawAttackBox(Graphics g, float scale, int lvlOffsetX, int lvlOffsetY) {
        g.setColor(Color.RED);
        g.drawRect((int) ((attackBox.x - lvlOffsetX) * scale),
                (int) ((attackBox.y - lvlOffsetY) * scale),
                (int) (attackBox.width * scale),
                (int) (attackBox.height * scale));
    }
    @Override
    public void update() {
        updateEatBox();
        updateAnimationTick();
        checkEnvironment();
    }

    private void checkEnvironment(){
        //if there is intersect with other animal
        //if animal can eat this animal
        //
        List<Animal> seenAnimals = wolf.getSeenAnimals(wolf);
        if (seenAnimals != null && seenAnimals.size() != 0) {
            List<Animal> eatenAnimals = wolf.getEatenAnimals(wolf, seenAnimals);
            if (eatenAnimals != null && eatenAnimals.size() != 0) {
                Animal otherAnimal = wolf.chooseOneAnimalWhichCanEat(eatenAnimals);
                System.out.println("wolf can eat" +  otherAnimal);
                AnimalAnimation wolfAnimation = wolf.getAnimalAnimation();
                if (wolfAnimation.getAnimationState() != AnimalAnimation.AnimationState.EAT) {
                    wolfAnimation.setAnimationState(AnimalAnimation.AnimationState.EAT);
                }
                else if (wolfAnimation.getAnimationState() == AnimalAnimation.AnimationState.EAT) {
                        System.out.println(5);
                        if (wolf.checkPlayerHit(attackBox, otherAnimal.getHitBox())) {
                            System.out.println("wolf trying eat " + otherAnimal);
                            wolf.eatEnemyAnimal(attackBox);
                            attackChecked = true;
                            System.out.println("wold eated " + otherAnimal);
                        }
                }
            }
        }
        ///

        ///
//        if (wolf.canSeeAnyone(wolf)){
//            if (wolf.canEatAnimal(wolf)){
//                AnimalAnimation wolfAnimation = wolf.getAnimalAnimation();
//                if (wolfAnimation.getAnimationState() != AnimalAnimation.AnimationState.EAT)
//                    wolfAnimation.setAnimationState(AnimalAnimation.AnimationState.EAT);
//                else if (wolfAnimation.getAnimationState() == AnimalAnimation.AnimationState.EAT) {
//
//                    if (wolfAnimation.getAniIndex() == 5){
//                        if (wolf.checkPlayerHit(wolf.getHitBoxTexture(), otherAnimal)) {
//                            attackChecked = true;
//                            wolf.eatEnemyAnimal(wolf.getHitBoxTexture());
//                        }
//                    }
//                }
//            }
//        }
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
    private void updateEatBox(){
        Rectangle2D.Double hitBox = wolf.getHitBoxTexture();
        boolean right = wolf.getAnimalMove().isRight();
        boolean left = wolf.getAnimalMove().isLeft();

        if (right){
            attackBox.x = hitBox.x + hitBox.width - 20;
        } else if (left){
            attackBox.x = hitBox.x - hitBox.width + 160;
        }
        attackBox.y = hitBox.y + 10;
    }

    public Rectangle2D.Double getAttackBox() {
        return attackBox;
    }

    public void setAttackBox(Rectangle2D.Double attackBox) {
        this.attackBox = attackBox;
    }
}
