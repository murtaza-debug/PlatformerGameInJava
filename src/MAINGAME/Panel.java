package MAINGAME;

import Audios.Audio;
import Collectables.CollectableManager;
import Entities.EnemyManager;
import Entities.Player;
import Levels.Level;
import Levels.LevelManager;
import Tiles.TileManager;
import javax.swing.*;
import java.awt.*;
import Door.Door;

import static Entities.Player.HP;
import static GameStates.StateConstants.*;
import static Levels.LevelManager.LEVEL2;
import static Levels.LevelManager.currentLevel;

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
    public static int leftBorder = (int) ( 0.5 * GAME_WIDTH) ;
    public static int rightBorder = (int) ( 0.6 * GAME_WIDTH) ;
    public static final int maxGameWidth = (200 )* TILE_SIZE ;



    Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT);

    //// ENTITIES AND MAPS //////
    Player player1;
    public TileManager tileManager ;
    Door door;
    ////// ENEMIES ///////
    EnemyManager enemyManager ;

    ///// COLLECTABLES /////////
    CollectableManager collectableManager;

    /// GAME STATES  ////
    MenuPanel menuPanel;
    GameOver gameOver;
    public static int currentState = MENU ;
    ///// SOUNDS ///////
    Audio audio;
    //// LEVELS /////////
    LevelManager levelManager ;
    Level level;

    /// constructor ////
    Panel (Game game)
    {
        audio = new Audio();

        levelManager = new LevelManager();

        tileManager = new TileManager("Map");
        player1 = new Player(tileManager , audio);
        enemyManager = new EnemyManager(this , tileManager , player1 , "EnemyMap");
        collectableManager = new CollectableManager(player1, "CollectableMap");
        ///// MAKE LEVEL 1 ////////
        makeLevel("Map","EnemyMap","CollectableMap",
                    64 , 64 , 191*TILE_SIZE,8*TILE_SIZE);


        menuPanel = new MenuPanel(player1.getKeyboard() , this) ;
        gameOver = new GameOver(player1.getKeyboard() , this) ;


        setBackground(Color.BLACK);
        addKeyListener(player1.getKeyboard());
        addMouseListener(menuPanel.mouse);
        addMouseMotionListener(menuPanel.mouse);
        setAllSize();
    }

    private void makeLevel(String tileMap , String enemyMap , String collectableMap
            , int playerX , int playerY , int doorX , int doorY )
    {
        tileManager.makeTiles(tileMap);
        enemyManager.addTraps(enemyMap);
        collectableManager.addHealth(collectableMap);
        door = new Door(player1 , doorX , doorY);
        player1.x = playerX;
        player1.y = playerY;
        level = new Level(player1,tileManager,collectableManager,enemyManager,door);
        levelManager.addLevel(level);
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
            levelManager.update();
        }
        if (door.levelUp)
        {
            currentLevel = LEVEL2;
            //// MAKE LEVEL 2 ///////
            makeLevel("Map2","EnemyMap2","CollectableMap2",
                    64 , 64 , 198*TILE_SIZE,10*TILE_SIZE);
            enemyManager.ball.setDefaults();
            door.setDefaults();
            door.levelUp = false;
        }
        if (HP <= 0 || door.over)
        {
            currentState = GAME_OVER ;
            if (HP <= 0) gameOver.won = false ;
            else gameOver.won = true ;

            player1.setDefaults();
            enemyManager.ball.setDefaults();
            door.setDefaults();
        }
        audio.playMusic();
    }



    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if (currentState == MENU)
        {
            menuPanel.draw(g2d);
        }
        if (currentState == GAME_OVER)
        {
            gameOver.draw(g2d);
        }
        if (currentState == PLAYING)
        {
            levelManager.draw(g2d,xOffset);
        }

    }

}
