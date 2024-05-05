package Collectables;

import Entities.Player;

import java.awt.*;

import static Entities.Player.HP;
import static Entities.Player.maxHP;

public class Health {

    int x;
    int y;
    int health = 10;
    Rectangle hitBox;
    Player player ;
    boolean dead;

    Health(int x, int y, Player player) {
        this.x = x + 32;
        this.y = y + 32;
        this.player = player;
        this.hitBox = new Rectangle(x + 32, y + 32, 20, 20);
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
        g.setColor(Color.red);
        g.fillOval(x - xOffset, y, 20, 20);

        g.setColor(Color.black);
        g.drawRect(hitBox.x - xOffset, hitBox.y, hitBox.width, hitBox.height);

    }
}
