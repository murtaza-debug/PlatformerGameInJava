package MAINGAME;

import Entities.Player;
import Tiles.TileManager;
import UserInput.Keyboard;
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    //// Display Size Width . ////////
    public static final int DEFAULT_TILE_SIZE = 32 ;
    public final static float SCALE = 2f ;
    public final static int TILES_IN_WIDTH = 20 ;
    public final static int TILES_IN_HEIGHT = 12 ;
    public final static int TILE_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;

    Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT);

    /// constructor /////
    Player player ;
    TileManager tileManager ;

    Panel (Game game)
    {
        tileManager = new TileManager() ;
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
        tileManager.draw(g);
        player.draw(g);
    }
}
