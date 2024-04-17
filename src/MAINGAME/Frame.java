package MAINGAME;

import javax.swing.*;
import java.awt.*;

import static MAINGAME.Panel.GAME_HEIGHT;
import static MAINGAME.Panel.GAME_WIDTH;

public class Frame extends JFrame {

    Panel panel;
    Frame(Game game)
    {
        panel = new Panel(game) ;
        add(panel);
        addKeyListener(panel.player.getKeyboard());
        panel.requestFocus();
        pack();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
