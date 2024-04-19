package Entities;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;

public abstract class Entity {

    public int x = 100;
    public int y = 100;
    int width = TILE_SIZE;
    int height = TILE_SIZE;
    int speed = 10;

    public abstract void update ();

    public abstract void draw (Graphics g);

}
