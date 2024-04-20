package Tiles;

import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        tileImage[1] = Load.Image("Dirt1.png");
        tileImage[2] = Load.Image("Dirt2.png");
        tileImage[3] = Load.Image("Dirt3.png");
        tileImage[4] = Load.Image("Stone1.png");
        tileImage[5] = Load.Image("Stone2.png");
        tileImage[6] = Load.Image("Stone3.png");
    }

    public void draw(Graphics g) {

        if (type == Dirt1)
            g.drawImage(tileImage[Dirt1],x,y,width,height,null) ;
        if (type == Dirt2)
            g.drawImage(tileImage[Dirt2],x,y,width,height,null) ;
        if (type == Dirt3)
            g.drawImage(tileImage[Dirt3],x,y,width,height,null) ;
        if (type == Stone1)
            g.drawImage(tileImage[Stone1],x,y,width,height,null) ;
        if (type == Stone2)
            g.drawImage(tileImage[Stone2],x,y,width,height,null) ;
        if (type == Stone3)
            g.drawImage(tileImage[Stone3],x,y,width,height,null) ;


    }
}
