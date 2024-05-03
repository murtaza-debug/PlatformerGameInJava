package MAINGAME;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import Loader.Load;
import UserInput.Keyboard;
import UserInput.Mouse;

import static GameStates.StateConstants.PLAY;
import static GameStates.StateConstants.QUIT;
import static MAINGAME.Panel.*;

public class MenuPanel extends JPanel {

    Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    Mouse mouse ;
    Keyboard keyboard;
    Panel main;
    public static int playCurrent ;

    BufferedImage[] playButtons ;
    public static Rectangle playHitBox ;

    BufferedImage[] quitButtons ;
    public static Rectangle quitHitBox ;

    BufferedImage[] bg ;
    public MenuPanel (Keyboard keyboard , Panel main)
    {
        playButtons = new BufferedImage[3];
        playButtons[0] = Load.Image("Play.png") ;
        playButtons[1] = Load.Image("Play1.png") ;
        playButtons[2] = Load.Image("Play2.png") ;

        quitButtons = new BufferedImage[3 ];
        quitButtons[0] = Load.Image("Quit.png") ;
        bg = new BufferedImage[4] ;
        bg[0] = Load.Image("Skye1.png");
        bg[1] = Load.Image("Skye2.png");
        bg[2] = Load.Image("Skye3.png");
        bg[3] = Load.Image("Skye4.png");
        this.main = main ;
        this.keyboard = keyboard;
        mouse = new Mouse();

        playHitBox = new Rectangle(GAME_WIDTH / 2  - 88, GAME_HEIGHT / 2 - 88 , 168 ,80);
        quitHitBox = new Rectangle(GAME_WIDTH / 2  - 88, GAME_HEIGHT / 2 + 30,168,80);

        setBackground(Color.BLACK);
        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        setAllSize();
    }



    private void setAllSize() {
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }



    public void draw(Graphics2D g)
    {
        for (int i = 0 ; i < bg.length ; i ++)
            g.drawImage(bg[i],0,0,null);
        g.drawImage(playButtons[playCurrent] , GAME_WIDTH / 2  - 88, GAME_HEIGHT / 2 - 88 , null);
        g.drawImage(quitButtons[0] , GAME_WIDTH / 2  - 88, GAME_HEIGHT / 2 + 30 , null);
    }

}
