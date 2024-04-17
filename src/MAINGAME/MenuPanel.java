package MAINGAME;

import javax.swing.*;
import java.awt.*;
import UserInput.Mouse;

import static MAINGAME.Panel.GAME_HEIGHT;
import static MAINGAME.Panel.GAME_WIDTH;

public class MenuPanel extends JPanel {

    Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    Mouse mouse ;

    public MenuPanel ()
    {
        mouse = new Mouse();
        setBackground(Color.BLACK);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        setAllSize();
    }



    private void setAllSize() {
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }



    public void draw(Graphics g)
    {
        g.drawString("Click To Play", GAME_WIDTH/2, GAME_HEIGHT/2);
    }
}
