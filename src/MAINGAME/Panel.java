package MAINGAME;

import Entities.Player;
import Tiles.TileManager;
import javax.swing.*;
import java.awt.*;
import static GameStates.StateConstants.*;

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

    /// GAME STATES  ////
    MenuPanel menuPanel;
    public static int currentState = MENU ;

    Panel (Game game)
    {
        tileManager = new TileManager() ;
        player = new Player();
        menuPanel = new MenuPanel() ;
        setBackground(Color.BLACK);
        addKeyListener(player.getKeyboard());
        addMouseListener(menuPanel.mouse);
        addMouseMotionListener(menuPanel.mouse);
        setAllSize();
    }

    private void setAllSize() {
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);
    }


    public void update ()
    {
        if (currentState == PLAYING)
        {
            player.update();
        }
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        if (currentState == MENU)
        {
            menuPanel.draw(g);
        }
        if (currentState == PLAYING)
        {
            tileManager.draw(g);
            player.draw(g);
        }

    }
}
