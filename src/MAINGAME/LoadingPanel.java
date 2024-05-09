package MAINGAME;

import Collectables.CollectableManager;
import Door.Door;
import Entities.EnemyManager;
import Entities.Player;
import Levels.Level;
import Levels.LevelManager;
import Tiles.TileManager;

import java.awt.*;

import static GameStates.StateConstants.MENU;
import static MAINGAME.Panel.*;

public class LoadingPanel {

    TileManager tileManager;
    Player player1;
    EnemyManager enemyManager;
    CollectableManager collectableManager;
    LevelManager levelManager;
    public LoadingPanel(TileManager tileManager, Player player1, EnemyManager enemyManager, CollectableManager collectableManager , LevelManager levelManager) {
        this.tileManager = tileManager;
        this.player1 = player1;
        this.enemyManager = enemyManager;
        this.collectableManager = collectableManager;
        this.levelManager = levelManager;
    }

    public void update()
    {

    }
    public void loadLevel(String tileMap, String enemyMap, String collectableMap, int playerX, int playerY, int doorX , int doorY , Level level) {

        tileManager.makeTiles(tileMap);
        enemyManager.addTraps(enemyMap);
        collectableManager.addHealth(collectableMap);
        door = new Door(player1 , doorX , doorY);
        player1.x = playerX;
        player1.y = playerY;
        level = new Level(player1,tileManager,collectableManager,enemyManager,door);
        levelManager.addLevel(level);
    }

    public void draw(Graphics2D g) {

    }


}
