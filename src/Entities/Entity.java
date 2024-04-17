package Entities;

import java.awt.*;

public abstract class Entity {

    int x ;
    int y ;
    int width ;
    int height ;
    int speed = 10;
    Rectangle hitBox ;

    public abstract void update ();

    public abstract void draw (Graphics g);

}
