package Entities;

import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;

public class VerticalBall extends Trap{

    int speed  = 2;

    VerticalBall(int x, int y, int width, int height , Player player , TileManager tileManager) {
        super((x + TILE_SIZE ) /2 + TILE_SIZE , y, width, height , player , tileManager);
    }

    @Override
    public void update(int xOffset) {


        if (isCollidingWall()) speed = -speed ;

        if (y >= 768 || y < 0)
        {
            speed = -speed ;
        }



        y += speed;
        x = x - 2*xOffset ;
        updateHitBox(xOffset);

    }
    private void updateHitBox(int xOffset)
    {
        hitBox.x = x -2*xOffset;
        hitBox.y = y;
    }

    @Override
    public void draw(Graphics2D g , int xOffset) {
        g.setColor(Color.RED);
        g.fillOval(x - 2* xOffset, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(hitBox.x , hitBox.y, hitBox.width, hitBox.height);
    }


    private boolean isCollidingWall ()
    {
        for (Tile tile : tileManager.tiles)
            if (tile.hitBox.intersects(hitBox)) return true;

        return false;
    }
}
