package Tiles;

import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {
    int x ;
    int y ;
    int width ;
    int height ;
    int type;

    BufferedImage[] tileImage;

    public Rectangle hitBox ;

    public Tile(int x, int y, int width, int height) {
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height ;
        hitBox = new Rectangle(x,y,width,height) ;
        tileImage = new BufferedImage[7] ;
    }


    public void updateTileHitBox(int xOffset)
    {
        hitBox.x = x ;
    }

    public void draw(Graphics2D g , int xOffset) {

        g.setColor(Color.black);
        g.drawRect(hitBox.x - xOffset ,hitBox.y,width,height) ;
    }
}
