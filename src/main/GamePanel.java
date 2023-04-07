package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;
import static Constants.Constants.GameWindowConstants.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final Game game;
    private MouseInputs mouseInputs;
    private KeyBoardInputs keyBoardInputs;

    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        initInputs();
        addInputsToPanel();
    }
    private void setPanelSize(){
        float scale = game.getScale();
        int GAME_WIDTH = (int) (GAME_WIDTH_DEFAULT * scale);
        int GAME_HEIGHT = (int) (GAME_HEIGHT_DEFAULT * scale);
        Dimension dimensionSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(dimensionSize);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }
    private void initInputs(){
        mouseInputs = new MouseInputs(this);
        keyBoardInputs = new KeyBoardInputs(this);
    }
    private void addInputsToPanel(){
        addKeyListener(keyBoardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.draw(g);
    }

    public Game getGame() {
        return game;
    }
    public void windowFocusLost(){
        game.windowFocusLost();
    }

}
