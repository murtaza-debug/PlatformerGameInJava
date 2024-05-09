package MAINGAME;

import Collectables.CollectableManager;
import Loader.Load;
import UserInput.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;

import static MAINGAME.Panel.GAME_HEIGHT;
import static MAINGAME.Panel.GAME_WIDTH;

public class GameOver extends MenuPanel{

    public boolean won = false ;

    private BufferedImage win ;
    private BufferedImage lose ;

    public GameOver(Keyboard keyboard, Panel main) {
        super(keyboard, main);
        win = Load.Image("Win.png");
        lose = Load.Image("Lose.png");
    }


    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        if (won)
        {
            g.drawImage(win , GAME_WIDTH / 2  - 200, GAME_HEIGHT / 2 - 300,400,200,null);
        }
        else
        {
            g.drawImage(lose , GAME_WIDTH / 2  - 200, GAME_HEIGHT / 2 - 300,400,150,null);
        }


    }
}
