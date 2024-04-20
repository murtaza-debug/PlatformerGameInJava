package Tiles;

import java.awt.*;

import static Tiles.TileConstants.*;

public class Tile {
    int x ;
    int y ;
    int width ;
    int height ;
    int type;

    public Rectangle hitBox ;

    public Tile(int x, int y, int width, int height) {
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height ;
        hitBox = new Rectangle(x,y,width,height) ;
    }

    public void draw(Graphics g) {


        if (type == WALL) g.setColor(Color.magenta);
        if (type == GRASS) g.setColor(Color.red);

        g.fillRect(x,y,width,height) ;
        g.setColor(Color.RED);
        g.drawRect(x,y,width,height) ;
    }
}
