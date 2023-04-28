package gamestates;

import ui.menu.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Constants.LoadSave;
import static Constants.Constants.GameWindowConstants.*;
import static Constants.Constants.TextureConstants.Menu.*;
import static Constants.Constants.Buttons.MenuButtons.COUNT_BUTTONS;
import static Constants.Constants.Buttons.MenuButtons.PLAY;

public class Menu extends GameState implements GamePanelInterface, GamePanelListenerInterface {
    private final MenuButton[] buttons = new MenuButton[COUNT_BUTTONS];
    private BufferedImage menuImg, backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(){
        loadBackgroundImg();
        loadButtons();
        calcBorder();
    }
    private void loadBackgroundImg() {
        //menuImg = LoadSave.getSpireAtlas(MENU_LOCATION_TEXTURES, MENU_ATLAS);
        backgroundImg = LoadSave.getSpireAtlas(MENU_LOCATION_TEXTURES, MENU_BACKGROUND);
    }
    private void loadButtons() {
        buttons[0] = new MenuButton(
                GAME_WIDTH_DEFAULT / 2,
                GAME_HEIGHT_DEFAULT / 2,
                PLAY, EnumGameState.PLAYING);
    }

    private void calcBorder() {
        menuWidth = (int) (backgroundImg.getWidth()*1.25);
        menuHeight = (int) (backgroundImg.getHeight()*1.25);
        menuX = GAME_WIDTH_DEFAULT / 2 - menuWidth / 2;
        menuY = GAME_HEIGHT_DEFAULT / 2 - menuHeight / 2;
    }
    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g, float scale) {
        g.drawImage(backgroundImg, 0 , 0,
                (int) (GAME_WIDTH_DEFAULT * scale),
                (int) (GAME_HEIGHT_DEFAULT * scale),
                null);
        g.drawImage(menuImg,
                (int) (menuX * scale),
                (int) (menuY * scale),
                (int) (menuWidth * scale),
                (int) (menuHeight * scale),
                null);

        for (MenuButton mb : buttons) {
            mb.draw(g, scale);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e, float scale) {

    }

    @Override
    public void mousePressed(MouseEvent e, float scale) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb, scale)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, float scale) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb, scale)) {
                if (mb.isMousePressed()) {
                    mb.applyGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBool();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, float scale) {

    }

    @Override
    public void mouseMoved(MouseEvent e, float scale) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }

        for (MenuButton mb : buttons) {
            if (isIn(e, mb, scale)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e, float scale) {

    }

    @Override
    public void keyReleased(KeyEvent e, float scale) {

    }
}
