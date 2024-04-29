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
    double xSpeed;
    double ySpeed;
    Line2D lineOfSight;
    Rectangle hitBox;
    Player player;
    TileManager tileManager;
    public boolean isOnPath = false;
    public PathFinder pathFinder;
    private List<Point> playerPath;

    public Ball(int x, int y, int radius, Player player, TileManager tileManager, PathFinder pathFinder) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.player = player;
        this.tileManager = tileManager;
        this.pathFinder = pathFinder;
        hitBox = new Rectangle(x, y, radius + 10, radius + 10);
        lineOfSight = new Line2D.Double(hitBox.x, hitBox.y, player.hitBox.x, player.hitBox.y);
        playerPath = new ArrayList<>();
        playerPath.add(new Point(player.x, player.y));
    }

    public void update(int xOffset) {
        // Calculate movement direction towards the player
        double xDistance = player.hitBox.x - x;
        double yDistance = player.hitBox.y - y;

        // Move towards the player
        double tempXSpeed = (xDistance > 0) ? speed : (xDistance < 0) ? -speed : 0;
        double tempYSpeed = (yDistance > 0) ? speed : (yDistance < 0) ? -speed : 0;

        // Update ball's position
        x += tempXSpeed;
        y += tempYSpeed;

        // Update hitbox position
        hitBox.x = (int) x;
        hitBox.y = (int) y;
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

    private boolean checkLineOfSight() {
        // Implementation of line of sight check
        for (Tile tile : tileManager.tiles)
            if (lineOfSight.intersects(tile.hitBox)) return false;

        // Check if line of sight intersects with player's hitbox
        if (lineOfSight.intersects(player.hitBox))
            return true; // Player is in line of sight

        return false; // Player is not in line of sight
    }

    private void updateLineOfSight(int x1, int y1, int x2, int y2) {
        lineOfSight.setLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);
    }
}
