package UserInput;

import MAINGAME.Panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static GameStates.StateConstants.PLAYING;
import static MAINGAME.MenuPanel.*;
import static MAINGAME.Panel.*;

public class Mouse implements MouseListener , MouseMotionListener {


    public Mouse ()
    {

    }
    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getX() > playHitBox.x  && e.getX() < playHitBox.x + 168
                && e.getY() >  playHitBox.y && e.getY() < playHitBox.y + playHitBox.height)
        {
            playCurrent = 2 ;
            currentState = PLAYING ;
        }
        if (e.getX() > quitHitBox.x  - 88 && e.getX() < quitHitBox.x + 168
                && e.getY() > quitHitBox.y  && e.getY() < quitHitBox.y + quitHitBox.height)
        {
            System.exit(3);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getX() > playHitBox.x  && e.getX() < playHitBox.x + 168
                && e.getY() >  playHitBox.y && e.getY() < playHitBox.y + playHitBox.height)
        {
            playCurrent = 1 ;
        }
        else {
            playCurrent = 0 ;
        }


    }
}
