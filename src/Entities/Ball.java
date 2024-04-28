package Entities;

import AI.PathFinder;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

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

        // Check if player is in line of sight
        boolean inLineOfSight = checkLineOfSight();

        // If player is in line of sight, update player path
        if (inLineOfSight) {
            playerPath.add(new Point((player.hitBox.x + player.hitBox.width)/2,
                                    (player.hitBox.y + player.hitBox.height )/2));
        }

        // Adjust movement based on line of sight
        if (inLineOfSight || playerPath.size() > 1) {
            Point target;
            if (inLineOfSight) {
                target = new Point((player.hitBox.x + player.hitBox.width)/2,
                                (player.hitBox.y + player.hitBox.height )/2);
            } else {
                target = playerPath.get(playerPath.size() - 1);
            }

            // Move towards target position
            xSpeed = (target.x - x > 0) ? speed : (target.x - x < 0) ? -speed : 0;
            ySpeed = (target.y - y > 0) ? speed : (target.y - y < 0) ? -speed : 0;

            // Adjust movement based on tile collisions
            for (Tile tile : tileManager.tiles) {
                if (tile.hitBox.intersects(hitBox)) {
                    // Adjust x and y speed to continue chasing player after collision
                    if (xSpeed > 0) {
                        xSpeed = speed;
                    } else if (xSpeed < 0) {
                        xSpeed = -speed;
                    }

                    if (ySpeed > 0) {
                        ySpeed = speed;
                    } else if (ySpeed < 0) {
                        ySpeed = -speed;
                    }
                }
            }

            // Update position
            x += xSpeed;
            y += ySpeed;
            hitBox.x = (int) x;
            hitBox.y = (int) y;
        }

        // Update line of sight
        updateLineOfSight(x - xOffset, y, player.hitBox.x - xOffset, player.hitBox.y + 15);
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
            if (lineOfSight.intersects(tile.hitBox)) return false ;
        // Return true if the player is in line of sight, false otherwise
        return true; // Placeholder, replace with actual implementation
    }

    private void updateLineOfSight(int x1, int y1, int x2, int y2) {
        lineOfSight.setLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);
    }
}
