package UserInput;

import MAINGAME.Panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static GameStates.StateConstants.MENU;
import static GameStates.StateConstants.PLAYING;

public class Mouse implements MouseListener , MouseMotionListener {



    @Override
    public void mouseClicked(MouseEvent e) {

        switch (e.getButton())
        {
            case MouseEvent.BUTTON1 :
                System.out.println("Clicked");
                if (Panel.currentState == MENU)
                    Panel.currentState = PLAYING ;
                else Panel.currentState = MENU ;
                break ;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

    }
}
