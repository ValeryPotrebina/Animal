package playing.entities.dynamics.rabbit;

import playing.entities.dynamics.DynamicEntity;

public class RabbitEntity extends DynamicEntity {
    //super(x, y - 50, 40, 40); // можно менять размеры животного

    public RabbitEntity(double x, double y) {
        super(x, y - 50, 40, 40);
    }

}
