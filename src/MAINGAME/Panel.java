package MAINGAME;

import Entities.Player;
import UserInput.Keyboard;
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    //// Display Size Width . ////////
    public static final int DEFAULT_TILE_SIZE = 32 ;
    public final static float SCALE = 2f ;
    public final static int TILES_IN_WIDTH = 20 ;
    public final static int TILES_IN_HEIGHT = 12 ;
    public final static int TILES_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT);

    /// constructor /////
    Player player ;

    Panel (Game game)
    {
        player = new Player();
        setBackground(Color.BLACK);
        addKeyListener(player.getKeyboard());
        setAllSize();
    }

    private void setAllSize() {
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }


    public void update ()
    {
        player.update();
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        player.draw(g);
    }
}
