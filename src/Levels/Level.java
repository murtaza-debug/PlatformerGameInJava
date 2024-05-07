package Levels;

import Collectables.CollectableManager;
import Door.Door;
import Entities.EnemyManager;
import Entities.Player;
import Tiles.TileManager;

import static MAINGAME.Panel.*;

import java.awt.*;

public class Level {

    Player player;
    TileManager tileManager;
    CollectableManager collectableManager;
    EnemyManager enemyManager ;
    Door door;
    public Level (Player player , TileManager tileManager , CollectableManager collectableManager
            , EnemyManager enemyManager , Door door)
    {
        this.player = player;
        this.collectableManager = collectableManager;
        this.enemyManager = enemyManager ;
        this.tileManager = tileManager ;
        this.door = door;
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

    public void update()
    {
        checkCloseToBorder ();
        tileManager.update(xOffset);
        player.update(xOffset);
        enemyManager.update(xOffset);
        collectableManager.update();
        door.update();
    }

    public void draw (Graphics2D g , int xOffset)
    {
        tileManager.draw(g , xOffset);
        enemyManager.draw(g , xOffset);
        collectableManager.draw(g , xOffset);
        door.draw(g,xOffset);
        player.draw(g , xOffset);
    }

}
