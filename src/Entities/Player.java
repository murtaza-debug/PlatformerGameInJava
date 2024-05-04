package Entities;

import Animations.PlayerAnimations;
import Audios.Audio;
import Tiles.Tile;
import Tiles.TileManager;
import Tiles.TileMap;
import UserInput.Keyboard;

import java.awt.*;

import static Animations.PlayerConstants.*;
import static Audios.AudioConstants.*;
import static MAINGAME.Panel.*;

public class Player {

    ////// Movement /////////
    public int x = 64;
    public int y = 64;
    int width = TILE_SIZE + 144;
    int height = TILE_SIZE + 144;
    double maxSpeed = 4 ;
    public double xSpeed  = 0;
    public double ySpeed = 0;
    Rectangle hitRadius;
    public int direction;
    static double HP = 200;
    String str = String.format("HP : %.2f",HP);

    ///////// animations //////////
    PlayerAnimations playerAnimations;

    private int aniTick , aniIndex , aniSpeed = 15;

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

    ///// SOUNDS //////////
    Audio audio;

    //////// CONSTRUCTOR /////////
    public Player (TileManager tileManager , Audio audio)
    {
        this.tileManager = tileManager;
        this.audio = audio;
        playerAnimations = new PlayerAnimations();
        keyboard = new Keyboard(this) ;
        hitBox = new Rectangle(x , y + 5,width - 180 ,height - 154);
        this.hitRadius = new Rectangle(x + 30 , y + 5 ,width - 180 ,height - 154 );
        this.tileManager = tileManager ;
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
            if (currentAnimation == ATTACK_RIGHT_1 ) aniIndex = 0;
            currentAnimation = ATTACK_RIGHT_1 ;
        }
        if (direction == LEFT && keyboard.Attack1 && !keyboard.Attack2){
            if (currentAnimation == ATTACK_LEFT_1 ) aniIndex = 0;
            currentAnimation = ATTACK_LEFT_1 ;
        }
    }


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


/////// INPUTS //////

private void updatePosition (int xOffset)
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
    if (xSpeed <= -maxSpeed) xSpeed = -maxSpeed;
    /////// JUMPING ////////
    if (keyboard.Space)
    {
        hitBox.y ++ ;
        for(Tile tile : tileManager.tiles) {
            if (tile.hitBox.intersects(hitBox)) ySpeed = -7;
        }
        hitBox.y --;
    }

    ySpeed += 0.15;

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
    if (attacking(xOffset)) xSpeed = 0;

    x += (int) (xSpeed );
    y += (int) (ySpeed );
    hitBox.x = x ;
    hitBox.y = y ;

}


public void drawHitBox (Graphics g , int xOffset)
{
    g.setColor(Color.RED);
    g.drawRect(hitBox.x - xOffset,hitBox.y,hitBox.width,hitBox.height);
}


////// UPDATE AND DRAW /////


public void update(int xOffset) {
    updateAnimation();
    updateAnimationTick();
    updatePosition(xOffset);
    updateAudio();
}

    private void updateAudio() {

        if (keyboard.Attack1 && (aniIndex >= 2 && aniIndex <= GetTotalImages(currentAnimation) - 1)) {
            audio.playAction(ATTACK);
        }
        if (isMoving )
        {
            audio.playAction(RUNNING);
        }
        else if (!isMoving && !keyboard.Attack1 )
        {
            audio.playAction(STOP_ALL);
        }

    }

    public void draw(Graphics2D g , int xOffset) {
    
    if (currentAnimation == IDLE_RIGHT) {
        g.drawImage(playerAnimations.idleRightAnimations[aniIndex], x - 80 - xOffset , y - 70, null);
        aniSpeed = 15 ;
    }
    else if (currentAnimation == IDLE_LEFT) {
        g.drawImage(playerAnimations.idleLeftAnimations[aniIndex], x -90 - xOffset , y - 70, null);
        aniSpeed = 15;
    }
    if (currentAnimation == RUNNING_RIGHT) {
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.runningRightAnimations[aniIndex], x - 80 - xOffset, y - 70,null);
    }
    else if (currentAnimation == RUNNING_LEFT) {
        aniSpeed = 10 ;
        g.drawImage(playerAnimations.runningLeftAnimations[aniIndex], x - 90 - xOffset , y - 70, null);
    }
    if (currentAnimation == ATTACK_LEFT_1){
        aniSpeed = 6 ;
        g.drawImage(playerAnimations.attackLeft1Animations[aniIndex], x - 80 - xOffset, y - 70, null);
    }
    else if (currentAnimation == ATTACK_RIGHT_1){
        aniSpeed = 6 ;
        g.drawImage(playerAnimations.attackRight1Animations[aniIndex], x - 90 - xOffset , y - 70, null);
    }
    g.setColor(Color.RED);
    str = String.format("HP : %.2f",HP);
    g.drawString(str, 30 , 10 );
}

//// GETTERS AND SETTERS ////////
    public Keyboard getKeyboard() {
    return this.keyboard;
    }
    private boolean attacking(int xOffset)
    {
        if (keyboard.Attack1) {
            if (direction == RIGHT) {
                hitRadius.x = x + 30;
                hitRadius.y = y;
                hitRadius.width = width - 180;
                hitRadius.height = height - 154 ;
            }
            if (direction == LEFT) {
                hitRadius.x = x - 30;
                hitRadius.y = y;
                hitRadius.width = width - 180;
                hitRadius.height = height - 154;
            }
                return true ;
        }
        return false ;

    }
}

