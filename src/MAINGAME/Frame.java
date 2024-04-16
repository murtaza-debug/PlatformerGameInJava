package MAINGAME;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Panel panel;
    Frame(Game game)
    {
        panel = new Panel(game) ;
        add(panel);

        addKeyListener(panel.keyboard);
        panel.requestFocus();
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
