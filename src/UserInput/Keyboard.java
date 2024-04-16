package UserInput;

import MAINGAME.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public boolean Up , Down , Left , Right ;
    Panel panel ;
    public Keyboard (Panel panel)
    {
     this.panel = panel;
        System.out.println(1);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W :
                    System.out.println("W");
                    Up = true ;
                    break;
                case KeyEvent.VK_S :
                    System.out.println("D");
                    Down = true ;
                    break;
                case KeyEvent.VK_A :
                    Left = true ;
                    break;
                case KeyEvent.VK_D :
                    Right = true ;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W :
                Up = false ;
                break;
            case KeyEvent.VK_S :
                Down = false ;
                break;
            case KeyEvent.VK_A :
                Left = false ;
                break;
            case KeyEvent.VK_D :
                Right = false ;
                break;

        }
    }

    public void updateXY ()
    {
        if (Up) panel.y -= 10;
        if (Down) panel.y += 10;
        if (Left) panel.x -= 10;
        if (Right) panel.x += 10;
    }
}
