package Collectables;

import Entities.Player;
import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Entities.Player.HP;
import static Entities.Player.maxHP;

public class Health {

    int x;
    int y;
    int health = 30;
    Rectangle hitBox;
    Player player ;
    boolean dead;
    BufferedImage health_Image;

    Health(int x, int y, Player player) {
        this.x = x + 20;
        this.y = y + 20;
        this.player = player;
        this.hitBox = new Rectangle(x + 30, y + 30, 30, 30);
        health_Image = Load.Image("Health.png");
    }

    public void isHit()
    {
        if (player.hitBox.intersects(hitBox)) {
            dead = true;
        }
        if (dead)
        {
            if (HP + health <= maxHP) {
                HP += health;
            }
            else HP = maxHP ;
        }
    }


    public void draw (Graphics2D g , int xOffset)
    {
        g.drawImage(health_Image, x - xOffset, y, 50, 50, null);


    }
}
