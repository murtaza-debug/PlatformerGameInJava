package Entities;

import Animations.DarkKnightAnimations;
import Loader.Load;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;

import static Animations.DarkKnightConstants.*;

public class DarkKnight extends Enemy {

    /////// ANIMATION /////////
    private int aniTick  , aniIndex , aniSpeed = 15 ;
    DarkKnightAnimations animations ;
    TileManager tileManager ;
    int currentAnimation = RUNNING_RIGHT;

    //// MOVEMENT AND HIT_BOX //////
    double xSpeed, ySpeed , maxSpeed = 6;
    int direction = RIGHT;
    boolean firstUpdate = true;
    int enemyState = RUNNING_RIGHT ;
    boolean jumping ;

    public DarkKnight(int x, int y , int width, int height , TileManager tileManager , int xOffset) {
        super(x,y,width,height);
        updateHitBox(xOffset);
        this.tileManager = tileManager;
        animations = new DarkKnightAnimations();
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
        /*if (firstUpdate) {
            hitBox.y += ySpeed;
            for (Tile tile : tileManager.tiles) {
                if (tile.hitBox.intersects(hitBox)) {
                    hitBox.y -= ySpeed;
                    while (!tile.hitBox.intersects(hitBox)) {
                        hitBox.y += (int) Math.signum(ySpeed);
                    }
                    hitBox.y -= (int) Math.signum(ySpeed);
                    ySpeed = 0;
                    y = hitBox.y;
                    firstUpdate = false;
                }
            }
            if (firstUpdate) ;
            //y += 0.2 ;
        }*/

        switch (enemyState)
        {
            case IDLE_RIGHT:
                enemyState = RUNNING_RIGHT ;
                break;
            case RUNNING_RIGHT:
                float runSpeed = 0 ;
                if (direction == RIGHT)
                    xSpeed = runSpeed ;
                else
                    xSpeed = -runSpeed ;

                break;

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
        hitBox.height = (int) (height - 30);
    }

    ////// UPDATE AND DRAW /////////
    public void update (int xOffset)
    {
        updateAnimationTick();
        updateAnimations();
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

        if (direction == LEFT)
        {
            if (currentAnimation == IDLE_LEFT)
                g.drawImage(animations.idleLeft[aniIndex] , (int)(x - xOffset),(int) y, null);
        }


        g.setColor(Color.RED);
        g.drawRect(hitBox.x,hitBox.y,hitBox.width,hitBox.height);
    }
}
