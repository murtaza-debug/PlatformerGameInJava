package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Animations.EnemyConstants.Dark_Knight;

public class DarkKnight extends Enemy {

    BufferedImage idleImageRight ;

    public DarkKnight(int x, int y , int width, int height) {
        super(x,y,width,height);
        enemyType = Dark_Knight;
        loadImage();
    }

    public void loadImage ()
    {

    }

    public void update ()
    {

    }
    public void draw (Graphics2D g)
    {
        g.fillRect(x,y,width,height);
    }
}
