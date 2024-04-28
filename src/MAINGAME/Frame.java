package MAINGAME;

import javax.swing.*;

public class Frame extends JFrame {

    Panel panel;

    Frame(Game game)
    {
        panel = new Panel(game) ;
        add(panel);

        addKeyListener(panel.player1.getKeyboard());
        addMouseListener(panel.menuPanel.mouse);
        addMouseMotionListener(panel.menuPanel.mouse);

        panel.requestFocus();
        pack();

        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
