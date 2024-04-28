package Entities;

import AI.PathFinder;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;
import java.awt.geom.Line2D;

import static MAINGAME.Panel.TILE_SIZE;

public class Ball {

    int x;
    int y;
    int radius;
    int speed = 4;
    double xSpeed ;
    double ySpeed ;
    Line2D lineOfSight;
    Rectangle hitBox;
    Player player;
    TileManager tileManager;
    public boolean isOnPath = false ;
    public PathFinder pathFinder ;
    public Ball(int x, int y, int radius , Player player, TileManager tileManager, PathFinder pathFinder) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.player = player;
        this.tileManager = tileManager;
        this.pathFinder = pathFinder;
        hitBox = new Rectangle(x, y, radius + 10, radius + 10);
        lineOfSight = new Line2D.Double(hitBox.x, hitBox.y, player.hitBox.x, player.hitBox.y);
    }

    public void OnPath()
    {
        if (isOnPath)
        {
            int goalCol = 0  ;
            int goalRow  = 0;

            searchPath(goalCol , goalRow);
        }
    }

    private void searchPath(int goalCol, int goalRow) {
        int startCol = x / TILE_SIZE ;
        int startRow = y / TILE_SIZE ;

        pathFinder.setNode(startCol,startRow,goalCol,goalRow);

        if (pathFinder.search() == true)
        {


        }
    }


    public void update (int xOffset)
    {
        xSpeed = 0 ;
        ySpeed = 0 ;

        if (player.hitBox.x - x  >= 0)
            xSpeed = speed;
        else if (player.hitBox.x - x<= 0)
            xSpeed = -speed ;

        if ( player.hitBox.y -y  >= 0)
            ySpeed = speed ;
        else if (player.hitBox.y -y  <= 0)
            ySpeed = -speed ;


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
        hitBox.x = x ;
        hitBox.y = y ;
        updateLineOfSight(x - xOffset ,y,player.hitBox.x - xOffset, player.hitBox.y + 15);
    }
    public void draw(Graphics2D g , int xOffset) {

        g.setColor(Color.black);
        g.fillOval(x - xOffset, y, 2 * radius, 2 * radius);
        g.setColor(Color.white);
        g.drawRect(hitBox.x - xOffset, hitBox.y, hitBox.width, hitBox.height);

        for (Tile tile : tileManager.tiles)
            if (lineOfSight.intersects(tile.hitBox) ) g.setColor(Color.RED);


        g.drawLine((int) lineOfSight.getX1() , (int) lineOfSight.getY1()
                    ,(int) lineOfSight.getX2() , (int) lineOfSight.getY2());
    }

    private void updateLineOfSight (int x1 , int y1, int x2, int y2) {
        lineOfSight.setLine(x1, y1, x2, y2);
    }
}
