package playing.entities.animalsAnimation;

import playing.PlayingKeyListenerInterface;
import playing.PlayingMouseListenerInterface;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class WolfListener extends WolfModule implements PlayingKeyListenerInterface, PlayingMouseListenerInterface {
//todo разобраться с этим классом

    public WolfListener(WolfModuleManager wolfModuleManager) {
        super(wolfModuleManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        wolfModuleManager.getPlayerMove().keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        wolfModuleManager.getPlayerMove().keyReleased(e);
    }

    //todo ИЗМЕНИТЬ!!!
    @Override
    public void mouseClicked(MouseEvent e) {
        wolfModuleManager.getWolfAttack().mouseClicked(e);
    }
}
