package Entities;

import Animations.PlayerAnimations;
import Audios.Audio;
import Audios.AudioConstants;
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
    public static double HP = 200;
    public static double maxHP = 200;
    String HP_STATUS = String.format("HP : %.2f",HP);
    public static boolean inAir = false;
    public Rectangle hearingRadius;

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
    int attack_delay = 0;
    public static int jump_delay = 15;

    //////// CONSTRUCTOR /////////
    public Player (TileManager tileManager , Audio audio)
    {
        this.tileManager = tileManager;
        this.audio = audio;
        playerAnimations = new PlayerAnimations();
        keyboard = new Keyboard(this) ;
        hitBox = new Rectangle(x , y + 5,width - 180 ,height - 154);
        hearingRadius = new Rectangle(x - 100 , y - 50 , width + 100 , height + 50);
        this.hitRadius = new Rectangle(x  , y + 5 ,width - 180 + 30,height - 154 );
        this.tileManager = tileManager ;

    }

    public void setDefaults()
    {
         x = 64;
         y = 64;
         width = TILE_SIZE + 144;
         height = TILE_SIZE + 144;
         maxSpeed = 4 ;
          xSpeed  = 0;
          ySpeed = 0;
          HP = 200;
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
            if (keyboard.Left && !keyboard.Right || !keyboard.Left && keyboard.Right) {
                updateAudio(RUNNING);
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
            attack_delay++;
            if(attack_delay >= 2) {
                updateAudio(ATTACK);
                attack_delay = 0;
            }
        }
        if (direction == LEFT && keyboard.Attack1 && !keyboard.Attack2){
            if (currentAnimation == ATTACK_LEFT_1 ) aniIndex = 0;
            currentAnimation = ATTACK_LEFT_1 ;
            attack_delay++;
        if(attack_delay >= 2) {
                updateAudio(ATTACK);
                attack_delay = 0;
            }

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
        updateAudio(JUMPING);
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

    if (ySpeed >= 0 && ySpeed<=1 )
    {
        inAir = false;

    }
    else inAir = true;

    x += (int) (xSpeed );
    y += (int) (ySpeed );
    hitBox.x = x ;
    hitBox.y = y ;
    hearingRadius.x = x - 100;
    hearingRadius.y = y - 50;
    hearingRadius.width = x + 100;
    hearingRadius.height = y + 50 ;

    if (y >= 768) HP = 0;
    if (y <= 0)
    {
        y = 1 ;
    }

    HP -= 0.001 ;

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
}

    private void updateAudio(int action) {
       audio.playAction(action);
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
        aniSpeed = 6 ;
        g.drawImage(playerAnimations.runningRightAnimations[aniIndex], x - 80 - xOffset, y - 70,null);
    }
    else if (currentAnimation == RUNNING_LEFT) {
        aniSpeed = 6 ;
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
    g.fillRect(1080 - 20,10,200,20);

    g.setColor(Color.GREEN);
    g.fillRect(1080 -20,10,(int)HP,20);

    g.setColor(Color.BLACK);
    HP_STATUS = String.format("HP : %.2f",HP);
    g.drawString(HP_STATUS, 1080 - 20 , 22 );
}

//// GETTERS AND SETTERS ////////
    public Keyboard getKeyboard() {
    return this.keyboard;
    }
    private boolean attacking(int xOffset)
    {
        if (keyboard.Attack1) {
            if (direction == RIGHT) {
                hitRadius.x = x ;
                hitRadius.y = y;
                hitRadius.width = width - 180 + 30;
                hitRadius.height = height - 154 ;
            }
            if (direction == LEFT) {
                hitRadius.x = x - 64;
                hitRadius.y = y;
                hitRadius.width = 64 + 40;
                hitRadius.height = height - 154;
            }
                return true ;
        }
        return false ;

    }
}

