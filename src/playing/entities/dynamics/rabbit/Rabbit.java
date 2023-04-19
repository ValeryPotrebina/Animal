package playing.entities.dynamics.rabbit;

import playing.PlayingInterface;
import playing.entities.dynamics.DynamicEntity;

import java.awt.*;

public class Rabbit implements PlayingInterface {
    private RabbitMove rabbitMove;
    private RabbitAnimation rabbitAnimation;
    private final RabbitEntity rabbitEntity;
    public Rabbit(RabbitEntity rabbitEntity) {
        this.rabbitEntity = rabbitEntity;
        initModules();
    }

    private void initModules(){
        rabbitMove = new RabbitMove(this);
        rabbitAnimation = new RabbitAnimation(this);
    }

    @Override
    public void draw(Graphics g, float scale, int x, int y) {
        rabbitAnimation.draw(g, scale, x, y);
    }

    @Override
    public void update() {
        rabbitMove.update();
        rabbitAnimation.update();
    }

    public RabbitMove getRabbitMove() {
        return rabbitMove;
    }

    public RabbitAnimation getRabbitAnimation() {
        return rabbitAnimation;
    }

    public RabbitEntity getRabbitEntity() {
        return rabbitEntity;
    }
}
