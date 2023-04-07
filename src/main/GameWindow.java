package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    public GameWindow(GamePanel gamePanel){
        JFrame jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        //setResizable запрещает изменять размер окна
        jFrame.setResizable(false);
        //pack() устанавливает такой минимальный размер контейнера, который достаточен для отображения всех компонентов.
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.windowFocusLost();
            }
        });
        jFrame.setVisible(true);
    }
}
