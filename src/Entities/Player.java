package Entities;

import Tiles.Tile;
import Tiles.TileManager;
import Tiles.TileMap;
import UserInput.Keyboard;

import java.awt.*;
import static MAINGAME.Panel.*;

public class Player {

    public int x = 64;
    public int y = 64;

    int width = TILE_SIZE;
    int height = TILE_SIZE;
    double maxSpeed = 10 ;
    public double xSpeed  = 0;
    public double ySpeed = 0;

    TileManager tileManager;
    int [] [] map1 = TileMap.GetMap();

    /// defaults ////
    private Keyboard keyboard ;


    //// HIT_BOX //////
    public Rectangle hitBox;


    /////// Gravity //////////
    public boolean isMoving = false ;

    //////// CONSTRUCTOR /////////
    public Player ()
    {
        keyboard = new Keyboard(this) ;
        hitBox = new Rectangle(x, y,width,height);
        tileManager = new TileManager(this);
    }


    /////// INPUTS //////

    private void updatePosition ()
    {
        /// moving left and right //////

        if (keyboard.Left && keyboard.Right || !keyboard.Left && !keyboard.Right) xSpeed *= 0.7 ;

        else if (keyboard.Left && !keyboard.Right) xSpeed --;
        else if (keyboard.Right && !keyboard.Left) xSpeed ++;

        if (xSpeed > 0 && xSpeed < 0.75) xSpeed = 0;
        if (xSpeed < 0 && xSpeed > -0.75) xSpeed = 0;

        if (xSpeed > maxSpeed) xSpeed = maxSpeed;
        if (xSpeed < -maxSpeed) xSpeed = -maxSpeed;

        /////// JUMPING ////////
        if (keyboard.Space)
        {
            hitBox.y ++ ;
            for(Tile tile : tileManager.tiles) {
                if (tile.hitBox.intersects(hitBox)) ySpeed = -7.4;
            }
            hitBox.y --;
        }

        ySpeed += 0.2;

        ///// Horizontal Collisions /////////////
        hitBox.x += xSpeed;
        for (Tile tile : tileManager.tiles) {
            if (tile.hitBox.intersects(hitBox))
            {
                hitBox.x -= xSpeed;
                while (!tile.hitBox.intersects(hitBox)) {
                    hitBox.x += (int) Math.signum(xSpeed);
                }
                hitBox.x -= (int) Math.signum(xSpeed);
                xSpeed = 0;
                x = hitBox.x;

            }
        }

        ///// Vertical Collisions //////////////

        hitBox.y += ySpeed;
        for (Tile tile : tileManager.tiles) {
            if (tile.hitBox.intersects(hitBox))
            {
                hitBox.y -= ySpeed;
                while (!tile.hitBox.intersects(hitBox)) {
                    hitBox.y += (int) Math.signum(ySpeed);
                }
                hitBox.y -= (int) Math.signum(ySpeed);
                ySpeed = 0;
                y = hitBox.y;

            }
        }

        x += xSpeed ;
        y += ySpeed ;
        hitBox.x = x;
        hitBox.y = y;
    }



    public void drawHitBox (Graphics g)
    {
        g.setColor(Color.RED);
        g.drawRect(hitBox.x,hitBox.y,hitBox.width,hitBox.height);
    }

    ////// UPDATE AND DRAW /////


    public void update() {
        updatePosition();
    }



    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 64, 64);
        g.setColor(Color.RED);
        g.drawString("x :" + x + "  y : " + y, x + 70 , y);
        drawHitBox(g);
    }

    //// GETTERS AND SETTERS ////////
    public Keyboard getKeyboard() {
        return this.keyboard;
    }
}
