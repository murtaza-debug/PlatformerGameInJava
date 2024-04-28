package Entities;

import AI.PathFinder;
import MAINGAME.Panel;
import Tiles.TileManager;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;
import static MAINGAME.Panel.xOffset;

public class EnemyManager {

    Panel panel ;
    TileManager tileManager ;
    Ball ball;
    public EnemyManager(Panel panel , TileManager tileManager, Player player , PathFinder pathFinder)
    {
        this.panel = panel;
        this.tileManager = tileManager;
        ball = new Ball(10,64,20 , player , tileManager , pathFinder);
    }


    public void update (int xOffset)
    {
        ball.update(xOffset);
    }

    public void draw (Graphics2D g , int xOffset)
    {
        ball.draw(g , xOffset);
    }
}
