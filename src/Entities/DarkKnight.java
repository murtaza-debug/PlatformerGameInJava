package Entities;

import Animations.DarkKnightAnimations;
import Loader.Load;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;
import java.util.Random;

import static Animations.DarkKnightConstants.*;
import static MAINGAME.Panel.maxGameWidth;

public class DarkKnight extends Enemy {

    //// MOVEMENT AND HIT_BOX //////
    double xSpeed, ySpeed = 0 , maxSpeed = 6;
    float gravity = 0.2F;
    int direction = RIGHT;
    boolean firstUpdate = true;
    int enemyState = RUNNING_RIGHT ;
    boolean jumping ;

    /////// ANIMATION /////////
    private int aniTick  , aniIndex , aniSpeed = 15 ;
    DarkKnightAnimations animations ;
    TileManager tileManager ;
    int currentAnimation = enemyState;
    int actionCounter = 0 ;



    public DarkKnight(int x, int y , int width, int height , TileManager tileManager , int xOffset) {
        super(x,y,width,height);
        updateHitBox(xOffset);
        this.tileManager = tileManager;
        animations = new DarkKnightAnimations();
    }

    //// MOVEMENT /////////
    private void setAction ()
    {
        actionCounter++;
        if (actionCounter >= 100 ) {
            Random random = new Random();

            int i = random.nextInt(1000) + 1;
            System.out.println(i);
            if (i <= 250) {
                direction = RIGHT;
                currentAnimation = RUNNING_RIGHT;
            }
            if (i <= 500 && i > 250 ) {
                direction = LEFT;
                currentAnimation = RUNNING_LEFT;
            }
            if (i <= 1000 && i > 500) {
                if (direction == LEFT) {
                    currentAnimation = IDLE_RIGHT;
                }
                if (direction == RIGHT) {
                    currentAnimation = IDLE_LEFT;
                }
            }
            aniIndex = 0 ;
            System.out.println(currentAnimation);
            actionCounter = 0;
        }
    }

     //////// ANIMATION //////////
    protected void updateAnimationTick()
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetTotalImages(currentAnimation) - 1)
            {
                aniIndex = 0;
            }
        }
    }

    private void updateAnimations ()
    {



    }

    private void updatePosition (int xOffset) {

        xSpeed = 0 ;

        if (direction == RIGHT && currentAnimation == RUNNING_RIGHT) {
            xSpeed = 3 ;
        }
        if (direction == LEFT && currentAnimation == RUNNING_LEFT) {
            xSpeed = -3 ;
        }
        if (x + xSpeed < 0) {
            xSpeed = 0;
            direction = RIGHT;
        }
        if (x - xSpeed > 1280) {
            xSpeed = 0;
            direction = LEFT;
        }

        x += xSpeed ;
        y += ySpeed ;
        updateHitBox(xOffset);
    }
    private void updateHitBox (int xOffset)
    {
        hitBox.x = (int) (x + 30 - xOffset);
        hitBox.y = (int) (y + 30);
        hitBox.width = (int) (width - 30);
        hitBox.height = (int) (height - 33);
    }

    ////// UPDATE AND DRAW /////////
    public void update (int xOffset)
    {
        updateAnimationTick();
        updateAnimations();
        setAction();
        updatePosition(xOffset);
    }

    public void draw (Graphics2D g , int xOffset)
    {
        if (direction == RIGHT)
        {
            if (currentAnimation == IDLE_RIGHT)
                g.drawImage( animations.idleRight[aniIndex], (int)(x - xOffset),(int) y, null);

            if (currentAnimation == RUNNING_RIGHT)
                g.drawImage( animations.runRight[aniIndex], (int)(x - xOffset),(int) y, null);
        }
        else
        {
            if (currentAnimation == IDLE_LEFT)
                g.drawImage(animations.idleLeft[aniIndex] , (int)(x - xOffset),(int) y, null);

            if (currentAnimation == RUNNING_LEFT)
                g.drawImage(animations.runLeft[aniIndex], (int)(x - xOffset),(int) y, null);
        }


        g.setColor(Color.RED);
        g.drawRect(hitBox.x,hitBox.y,hitBox.width,hitBox.height);
    }
}
