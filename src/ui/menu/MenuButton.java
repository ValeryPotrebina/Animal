package ui.menu;

import Constants.LoadSave;
import static Constants.Constants.TextureConstants.Menu.*;
import static Constants.Constants.Buttons.Button.*;

import ui.Button;
import gamestates.EnumGameState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Constants.Constants.Buttons.MenuButtons.BUTTON_HEIGHT_DEFAULT;
import static Constants.Constants.Buttons.MenuButtons.BUTTON_WIDTH_DEFAULT;

public class MenuButton extends Button {
    private final EnumGameState state;
    private BufferedImage[] images;
    public MenuButton(int x, int y, int typeButton, EnumGameState state) {
        super(x - BUTTON_WIDTH_DEFAULT / 2, y, BUTTON_WIDTH_DEFAULT, BUTTON_HEIGHT_DEFAULT);
        this.typeButton = typeButton;
        this.state = state;
        loadImages();
    }

    @Override
    protected void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpireAtlas(MENU_LOCATION_TEXTURES, MENU_BUTTONS);
        for (int i = 0; i < images.length; i++) {
            //BufferedImage.getSubimage(int x, int y, int w, int h)
            //Возвращает подызображение, определенное указанной прямоугольной областью.
            images[i] = temp.getSubimage(i * BUTTON_WIDTH_DEFAULT, typeButton * BUTTON_HEIGHT_DEFAULT,
                    BUTTON_WIDTH_DEFAULT, BUTTON_HEIGHT_DEFAULT);
        }
    }

    @Override
    public void update() {
        stateButton = ON;
        if (isMouseOver){
            stateButton = OVER;
        }
        if (isMousePressed){
            stateButton = PRESSED;
        }
    }

    @Override
    public void draw(Graphics g, float scale) {
        int buttonWidth = (int)(BUTTON_WIDTH_DEFAULT * scale);
        int buttonHeight = (int)(BUTTON_HEIGHT_DEFAULT * scale);
        int buttonX = (int)(x* scale);
        int buttonY = (int)(y * scale);
        g.drawImage(images[stateButton], buttonX, buttonY, buttonWidth, buttonHeight, null);
    }
    public void applyGameState() {
        EnumGameState.state = state;
    }
}
