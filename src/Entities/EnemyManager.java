package Entities;

import AI.PathFinder;
import MAINGAME.Panel;
import Tiles.TileManager;

import java.awt.*;
import java.util.ArrayList;

import static MAINGAME.Panel.TILE_SIZE;
import static MAINGAME.Panel.xOffset;

public class EnemyManager {

    Panel panel ;
    TileManager tileManager ;
    Ball ball;
    ArrayList<Trap> traps = new ArrayList<>();
    Player player;
    public EnemyManager(Panel panel , TileManager tileManager, Player player)
    {
        this.panel = panel;
        this.tileManager = tileManager;
        ball = new Ball(10,64,20 , player , tileManager);
        this.player = player;
        addTraps();

    }

    private void addTraps() {
        traps.add(new VerticalBall(2*TILE_SIZE , 10 * TILE_SIZE , 20 , 20,player , tileManager));
        traps.add(new VerticalBall(8*TILE_SIZE , 10 * TILE_SIZE , 20 , 20,player , tileManager));
        traps.add(new VerticalBall(9*TILE_SIZE , 10 * TILE_SIZE , 20 , 20,player , tileManager));
        traps.add(new VerticalBall(10*TILE_SIZE , 10 * TILE_SIZE , 20 , 20,player , tileManager));
        traps.add(new VerticalBall(45*TILE_SIZE , 10 * TILE_SIZE , 20 , 20,player , tileManager));
        traps.add(new VerticalBall(40*TILE_SIZE , 10 * TILE_SIZE , 20 , 20,player , tileManager));
    }


    public void update (int xOffset)
    {
        ball.update(xOffset);
        traps.getFirst().update(xOffset);
    }

    public void draw (Graphics2D g , int xOffset)
    {
        ball.draw(g , xOffset);
        traps.getFirst().draw(g , xOffset);
    }
}
