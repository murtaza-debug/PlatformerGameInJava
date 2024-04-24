package Entities;

import java.awt.*;

public class Enemy {

    int x , y , width , height;
    Rectangle hitBox;
    public int enemyType ;
    private int aniIndex , enemyState;
    public int aniTick , aniSpeed = 20 ;

    public Enemy (int x , int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox(x,y,width,height);
    }

    private void initHitBox(int x, int y, int width, int height) {
        hitBox = new Rectangle(x,y,width,height);
    }

    private void updateAnimation ()
    {
        aniTick++;
        if (aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 9)
            {
                aniIndex = 0;
            }
        }
    }


    public void update()
    {
        updateAnimation();
    }
    public void draw (Graphics g)
    {

    }
}
