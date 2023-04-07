package gamestates;

import java.awt.*;

public interface GamePanelInterface {
    public abstract void update();

    public abstract void draw(Graphics g, float scale);
}
