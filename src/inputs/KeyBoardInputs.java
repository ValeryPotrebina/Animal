package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputs implements KeyListener {
    private final GamePanel gamePanel;
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        gamePanel.getGame().getGameDistribution().keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.getGame().getGameDistribution().keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.getGame().getGameDistribution().keyReleased(e);

    }
}
