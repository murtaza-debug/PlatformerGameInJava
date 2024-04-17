package Entities;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;

public abstract class Entity {

    int x ;
    int y ;
    int width = TILE_SIZE;
    int height = TILE_SIZE;
    int speed = 10;

    public abstract void update ();

    public abstract void draw (Graphics g);

}
