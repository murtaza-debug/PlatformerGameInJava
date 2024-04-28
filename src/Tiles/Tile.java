package Tiles;

import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static MAINGAME.Panel.*;
import static Tiles.TileConstants.*;

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
        loadTileImages();
    }

    private void loadTileImages ()
    {
        tileImage[1] = Load.Image("Dirt1o.png");
        tileImage[2] = Load.Image("Dirt2o.png");
        tileImage[3] = Load.Image("Dirt3o.png");
        tileImage[4] = Load.Image("Stone1o.png");
        tileImage[5] = Load.Image("Stone2.png");
        tileImage[6] = Load.Image("Dirt4o.png");
    }
    public void updateTileHitBox(int xOffset)
    {
        hitBox.x = x - xOffset ;
    }

    public void draw(Graphics2D g , int xOffset) {
/*
        if (type == Dirt1)
            g.drawImage(tileImage[Dirt1],x - xOffset ,y,null) ;
        if (type == Dirt2)
            g.drawImage(tileImage[Dirt2],x - xOffset ,y,null) ;
        if (type == Dirt3)
            g.drawImage(tileImage[Dirt3],x - xOffset ,y,null) ;
        if (type == Stone1)
            g.drawImage(tileImage[Stone1],x - xOffset ,y,null) ;
        if (type == Stone2)
            g.drawImage(tileImage[Stone2],x - xOffset ,y,null) ;
        if (type == Dirt4)
            g.drawImage(tileImage[Dirt4],x - xOffset ,y,null) ;*/

        g.setColor(Color.black);
        g.drawRect(hitBox.x - xOffset,hitBox.y,width,height) ;

    }
}
