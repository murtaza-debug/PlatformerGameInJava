package Entities;

import AI.PathFinder;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static MAINGAME.Panel.TILE_SIZE;

public class Ball {

    int x;
    int y;
    int radius;
    int speed = 2;
    Line2D lineOfSight;
    Rectangle hitBox;
    Player player;
    TileManager tileManager;
    private boolean isHit = false;
    private long coolDown ;

    public Ball(int x, int y, int radius, Player player, TileManager tileManager) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.player = player;
        this.tileManager = tileManager;
        hitBox = new Rectangle(x, y, radius + 10, radius + 10);
        lineOfSight = new Line2D.Double(hitBox.x, hitBox.y, player.hitBox.x, player.hitBox.y);
    }

    public void update(int xOffset) {
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

        else if (System.currentTimeMillis() - coolDown  > 1000)
        {
            isHit = false;
        }
    }

    
    public void draw(Graphics2D g, int xOffset) {
        g.setColor(Color.black);
        g.fillOval(x - xOffset, y, 2 * radius, 2 * radius);
        g.setColor(Color.white);
        g.drawRect(hitBox.x - xOffset, hitBox.y, hitBox.width, hitBox.height);

        for (Tile tile : tileManager.tiles)
            if (lineOfSight.intersects(tile.hitBox)) g.setColor(Color.RED);

        g.drawLine((int) lineOfSight.getX1(), (int) lineOfSight.getY1(),
                (int) lineOfSight.getX2(), (int) lineOfSight.getY2());
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
