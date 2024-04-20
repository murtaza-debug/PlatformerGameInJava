package UserInput;

import Entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public boolean Space, Down , Left , Right ;
    Player player;
    public Keyboard (Player player)
    {
        this.player = player ;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode())
            {
                case KeyEvent.VK_SPACE :
                    System.out.println("W");
                    Space = true ;
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
            case KeyEvent.VK_SPACE :
                Space = false ;
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

}
