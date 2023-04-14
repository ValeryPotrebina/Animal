package gamestates;

import gamestates.playingstates.EnumPlayState;
import playing.PlayingGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing implements GamePanelInterface, GamePanelListenerInterface {
    private PlayingGame playingGame;

    public Playing(){
        initClasses();
    }
    private void initClasses(){
        playingGame = new PlayingGame();
    }
    @Override
    public void update() {
        switch (EnumPlayState.state){
            case PLAYING:
                playingGame.update();
                break;
            case PAUSED:
                break;
            case GAME_OVER:
                break;
            case LVL_COMPLETED:
                break;
            default:
                break;
        }
    }

    @Override
    public void draw(Graphics g, float scale) {
        playingGame.draw(g, scale);
        switch (EnumPlayState.state) {
            case PLAYING:
                break;
            case PAUSED:
//                pauseOverlay.draw(g, scale);
                break;
            case GAME_OVER:
//                gameOverOverlay.draw(g, scale);
                break;
            case LVL_COMPLETED:
//                levelCompletedOverlay.draw(g, scale);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e, float scale) {
        switch (EnumPlayState.state){
            case PLAYING:
                playingGame.mouseClicked(e);
                break;
            case PAUSED:
            case GAME_OVER:
            case LVL_COMPLETED:
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e, float scale) {

    }

    @Override
    public void mouseReleased(MouseEvent e, float scale) {

    }

    @Override
    public void mouseDragged(MouseEvent e, float scale) {

    }

    @Override
    public void mouseMoved(MouseEvent e, float scale) {

    }

    @Override
    public void keyPressed(KeyEvent e, float scale) {
        switch (EnumPlayState.state) {
            case PLAYING:
                playingGame.keyPressed(e);
                break;
            case PAUSED:
                //pauseOverlay.keyPressed(e, scale);
                break;
            case GAME_OVER:
                //gameOverOverlay.keyPressed(e, scale);
                break;
            case LVL_COMPLETED:
                //levelCompletedOverlay.keyPressed(e, scale);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e, float scale) {
        switch (EnumPlayState.state) {
            case PLAYING:
                playingGame.keyReleased(e);
                break;
            case PAUSED:
                //pauseOverlay.keyPressed(e, scale);
                break;
            case GAME_OVER:
                //gameOverOverlay.keyPressed(e, scale);
                break;
            case LVL_COMPLETED:
                //levelCompletedOverlay.keyPressed(e, scale);
                break;
            default:
                break;
        }
    }
    public void resetHorBooleans() {
        playingGame.resetHorBooleans();
    }
    public void resetVertBooleans() {
        playingGame.resetVertBooleans();
    }
    public void resetAll() {
        playingGame.resetAll();
    }

    public void setIsland() {
        playingGame.setIsland();
    }
}
