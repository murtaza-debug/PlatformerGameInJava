package Entities;

import Animations.FireSkullAnimations;
import Audios.Audio;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;
import java.awt.geom.Line2D;

import static Audios.AudioConstants.SKULL;

public class FireSkull {

    int x;
    int y;
    int radius;
    int speed = 2;
    //Line2D lineOfSight;
    Rectangle hitBox;
    Player player;
    TileManager tileManager;
    private boolean isHit = false;
    private long coolDown ;
    FireSkullAnimations fireSkullAnimations;
    float coolDownTime = 1.5f;
    Audio audio ;

    public FireSkull(int x, int y, int radius, Player player, TileManager tileManager) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.player = player;
        this.tileManager = tileManager;
        hitBox = new Rectangle(x, y, radius + 10, radius + 10);
        fireSkullAnimations = new FireSkullAnimations();
        audio = new Audio();

    }

    public void setDefaults()
    {
        x = - 100 ;
        y = 64 ;
        hitBox.x = x ;
        hitBox.y = y ;
        hitBox.width = radius+ 10 ;
        hitBox.height = radius + 10 ;
    }


    public void update(int xOffset) {
        fireSkullAnimations.update();
        checkIfHit();
        if (!isHit) {
            // Calculate movement direction towards the player
            double xDistance = player.hitBox.x - x;
            double yDistance = player.hitBox.y - y;

            // Move towards the player
            double tempXSpeed = (xDistance > 0) ? speed : (xDistance < 0) ? -speed : 0;
            double tempYSpeed = (yDistance > 0) ? speed : (yDistance < 0) ? -speed : 0;

            // Update ball's position
            x += (int) tempXSpeed;
            y += (int) tempYSpeed;

            // Update hitbox position
            hitBox.x = (int) x;
            hitBox.y = (int) y;
        }

        else if (System.currentTimeMillis() - coolDown  > coolDownTime * 1000)
        {
            isHit = false;
        }

        checkIfAttacking();
    }

    
    public void draw(Graphics2D g, int xOffset) {

        fireSkullAnimations.draw(g , x - xOffset, y);
    }
    private void checkIfAttacking ()
    {
        if (player.hitBox.intersects(hitBox) && !isHit) {
            player.HP -= 0.8;
        }
    }
    private void checkIfHit ()
    {
        if (player.hitRadius.intersects(hitBox) && player.getKeyboard().Attack1)
        {
            coolDown = System.currentTimeMillis();
            isHit = true;
        }
    }

}
