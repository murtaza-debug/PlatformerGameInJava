package MAINGAME;

import Entities.DarkKnight;
import Entities.EnemyManager;
import Entities.Player;
import Tiles.Tile;
import Tiles.TileManager;
import javax.swing.*;
import java.awt.*;
import static GameStates.StateConstants.*;
import static Tiles.TileMap.GetMap;

public class Panel extends JPanel {

    //// Display Size Width . ////////
    public static final int DEFAULT_TILE_SIZE = 32 ;
    public final static float SCALE = 2f ;
    public final static int TILES_IN_WIDTH = 20 ;
    public final static int TILES_IN_HEIGHT = 12 ;
    public final static int TILE_SIZE = (int)(DEFAULT_TILE_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;
    //// CAMERA //////////
    public static int xOffset ;
    public static int yOffset ;
    public static int leftBorder = (int) ( 0.5 * GAME_WIDTH) ;
    public static int rightBorder = (int) ( 0.6 * GAME_WIDTH) ;
    public static int maxGameWidth = (GetMap()[0].length - TILES_IN_WIDTH)* TILE_SIZE ;
    public static int maxGameHeight = (GetMap().length - TILES_IN_HEIGHT) * TILE_SIZE;



    Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT);

    //// ENTITIES AND MAPS //////
    Player player ;
    TileManager tileManager ;

    ////// ENEMIES ///////
    EnemyManager enemyManager ;

    /// GAME STATES  ////
    MenuPanel menuPanel;
    public static int currentState = MENU ;
    /// constructor /////
    Panel (Game game)
    {
        player = new Player();
        tileManager = new TileManager(player) ;
        menuPanel = new MenuPanel(player.getKeyboard()) ;
        enemyManager = new EnemyManager(this , tileManager , xOffset);
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
            tileManager.update();
            player.update();
            enemyManager.update(xOffset);
            checkCloseToBorder ();
        }
    }

    private void checkCloseToBorder() {
        int playerX = player.hitBox.x;
        int diff = playerX - xOffset;

        if (diff > rightBorder)
        {
            xOffset += diff - rightBorder;
        }
        else if (diff < leftBorder)
        {
            xOffset += diff - leftBorder;
        }

        if (xOffset > maxGameWidth)
        {
            xOffset = maxGameWidth;
        }
        else if (xOffset < 0)
        {
            xOffset = 0;
        }
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        if (currentState == MENU)
        {
            menuPanel.draw(g2d);
        }
        if (currentState == PLAYING)
        {
            tileManager.draw(g2d , xOffset);

            player.draw(g2d , xOffset);
            enemyManager.draw(g2d , xOffset);
        }

    }

}
