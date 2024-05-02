package Entities;

import Tiles.TileManager;

import java.awt.*;

public  abstract  class Trap {

    int x ;
    int y ;
    int width ;
    int height ;
    Player player;
    Rectangle hitBox ;
    TileManager tileManager ;
    Trap(int x, int y, int width, int height, Player player , TileManager tileManager) {
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height ;
        this.player = player ;
        this.tileManager = tileManager ;
        hitBox = new Rectangle(x,y,width,height);
    }

    public abstract void update (int xOffset);

    public abstract void draw (Graphics2D g, int xOffset);

}
