package Tiles;

import java.awt.*;
import static MAINGAME.Panel.*;
public abstract class Defaults {

    int Width = TILE_SIZE ;
    int height = TILE_SIZE ;

    public abstract void update ();
    public abstract void draw (Graphics g) ;

}
