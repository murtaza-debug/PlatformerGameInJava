package UserInput;

import Entities.Player;
import MAINGAME.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static GameStates.StateConstants.MENU;
import static GameStates.StateConstants.PLAYING;

public class Keyboard implements KeyListener {

    public boolean Space, Attack1 , Attack2 , Left , Right ;
    Player player1;

    public Keyboard (Player player1)
    {
        this.player1 = player1 ;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (Panel.currentState == MENU)
                    Panel.currentState = PLAYING ;
                else Panel.currentState = MENU ;
                break ;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode())
            {
                case KeyEvent.VK_SPACE :
                    Space = true ;
                    break;
                case KeyEvent.VK_F :
                    Attack1 = true ;
                    break;
                case KeyEvent.VK_G :
                    Attack2 = true ;
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
            case KeyEvent.VK_F :
                Attack1 = false ;
                break;
            case KeyEvent.VK_G :
                Attack2 = false ;
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
