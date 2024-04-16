package MAINGAME;

import UserInput.Keyboard;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public int x , y ;
    Keyboard keyboard ;
    Dimension dimension = new Dimension(1280,720);
    Panel (Game game)
    {
        keyboard = new Keyboard(this) ;
        setBackground(Color.BLACK);
        addKeyListener(keyboard);
        setAllSize();
    }

    private void setAllSize() {
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }


    public void update ()
    {
        keyboard.updateXY();
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 64, 64);
    }
}
