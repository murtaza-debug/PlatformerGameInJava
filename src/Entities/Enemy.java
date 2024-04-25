package Entities;

import java.awt.*;

public class Enemy {

    double x , y , width , height;
    Rectangle hitBox;
    public Enemy (double x , double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox((int)x,(int)y,width,height);
    }

    private void initHitBox(int x, int y, int width, int height) {
        hitBox = new Rectangle(x,y,width,height);
    }


}
