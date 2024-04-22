package Entities;

import Animations.PlayerAnimations;
import Tiles.Tile;
import Tiles.TileManager;
import Tiles.TileMap;
import UserInput.Keyboard;

import java.awt.*;

import static Animations.Constants.*;
import static MAINGAME.Panel.*;

public class Player {

    ////// Movement /////////
    public int x = 64;
    public int y = 64;
    int width = TILE_SIZE + 144;
    int height = TILE_SIZE + 144;
    double maxSpeed = 5 ;
    public double xSpeed  = 0;
    public double ySpeed = 0;

    public int direction;

    ///////// animations //////////
    PlayerAnimations playerAnimations;

    int aniTick , aniIndex , aniSpeed = 15;

    public int currentAnimation = IDLE_RIGHT ;

    ////// MAP ///////////
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
        playerAnimations = new PlayerAnimations(this);
        keyboard = new Keyboard(this) ;
        hitBox = new Rectangle(x , y,width - 170 ,height - 154);
        tileManager = new TileManager(this);
    }

    /////// ANIMATIONS ////////
    protected void updateAnimation ()
    {
        if (isMoving)
        {
            if (direction == LEFT && keyboard.Left && !keyboard.Right) {
                if (currentAnimation != RUNNING_LEFT ) {
                    currentAnimation = RUNNING_LEFT;
                    aniIndex = 0;
                }
            }
            if (direction == RIGHT && keyboard.Right && !keyboard.Left) {
                if (currentAnimation != RUNNING_RIGHT ) {
                    currentAnimation = RUNNING_RIGHT;
                    aniIndex = 0;
                }
            }
        }
        else {
            if (direction == LEFT) {
                currentAnimation = IDLE_LEFT;
            }
            if (direction == RIGHT)
                currentAnimation = IDLE_RIGHT ;
        }
        if (direction == RIGHT && keyboard.Attack1 && !keyboard.Attack2) {
            currentAnimation = ATTACK_RIGHT_1;
        }
        if (direction == LEFT && keyboard.Attack1 && !keyboard.Attack2){
            currentAnimation = ATTACK_LEFT_1 ;
        }
        /*if (direction == RIGHT && keyboard.Attack2 && !keyboard.Attack1)
        {
            currentAnimation = ATTACK_RIGHT_2 ;
        }
        if (direction == LEFT && keyboard.Attack2 && !keyboard.Attack1)
        {
            currentAnimation = ATTACK_LEFT_2 ;
        }*/
    }


protected void updateAnimationTick()
{
    aniTick++;
    if(aniTick >= aniSpeed)
    {
        aniTick = 0;
        aniIndex++;
        if (aniIndex >= GetTotalImages(currentAnimation) - 1 )
        {
            aniIndex = 0;
            currentAnimation = IDLE_RIGHT ;
        }
    }
}


/////// INPUTS //////

private void updatePosition ()
{
    /// moving left and right //////
    isMoving = false ;
    if (keyboard.Left && keyboard.Right || !keyboard.Left && !keyboard.Right
            || keyboard.Attack1 || keyboard.Attack2) xSpeed *= 0.7 ;

    else if (keyboard.Left && !keyboard.Right) {
        xSpeed --;
        isMoving = true ;
        direction = LEFT;
    }
    else if (keyboard.Right && !keyboard.Left)
    {
        xSpeed ++;
        isMoving = true ;
        direction = RIGHT;
    }

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
    updateAnimation();
    updateAnimationTick();
    updatePosition();
}



public void draw(Graphics2D g) {
    g.setColor(Color.white);


    if (currentAnimation == IDLE_RIGHT) {
        g.drawImage(playerAnimations.idleRightAnimations[aniIndex], x - 80, y - 70, null);
    }
    if (currentAnimation == IDLE_LEFT) {
        g.drawImage(playerAnimations.idleLeftAnimations[aniIndex], x-90, y - 70, null);
    }
    if (currentAnimation == RUNNING_RIGHT) {
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.runningRightAnimations[aniIndex], x - 80, y - 70,null);
    }
    if (currentAnimation == RUNNING_LEFT) {
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.runningLeftAnimations[aniIndex], x - 90, y - 70, null);
    }
    if (currentAnimation == ATTACK_LEFT_1){
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.attackLeft1Animations[aniIndex], x - 80, y - 70, null);
    }
    if (currentAnimation == ATTACK_RIGHT_1){
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.attackRight1Animations[aniIndex], x - 90, y - 70, null);
    }
    if (currentAnimation == ATTACK_LEFT_2){
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.attackLeft2Animations[aniIndex], x - 80, y - 70 , null);
    }
    if (currentAnimation == ATTACK_RIGHT_2){
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.attackRight2Animations[aniIndex], x - 90, y - 70, null);
    }
}

//// GETTERS AND SETTERS ////////
    public Keyboard getKeyboard() {
    return this.keyboard;
    }
}

