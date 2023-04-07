package ui;

import java.awt.*;

public abstract class Button {
    protected int x, y, width, height;
    protected Rectangle border;
    protected int typeButton;
    protected int stateButton;
    protected boolean isMouseOver, isMousePressed;

    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        calcBorder();
    }
    private void calcBorder(){
        border = new Rectangle(x, y, width, height);
    }
    public Rectangle getBorders() {
        return border;
    }

    protected abstract void loadImages();
    public abstract void update();
    public abstract void draw(Graphics g, float scale);
    public void setMouseOver(boolean mouseOver){
        isMouseOver = mouseOver;
    }
    public boolean isMousePressed() {
        return isMousePressed;
    }

    public void setMousePressed(boolean mousePressed){
        isMousePressed = mousePressed;
    }

    public void resetBool(){
        isMouseOver = false;
        isMousePressed = false;
    }

}
