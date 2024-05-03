package Entities;

import Animations.FireTrapAnimations;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;

import static MAINGAME.Panel.maxGameWidth;

public class HorizontalBall extends Trap{

    int speed  = 2;
    FireTrapAnimations fireAnimations;
    int currentAnimation = 0;
    HorizontalBall(int x, int y, int width, int height, Player player, TileManager tileManager) {
        super(x, y, width - 32, height - 32, player, tileManager);
        fireAnimations = new FireTrapAnimations ();
    }

    @Override
    public void update(int xOffset) {
        System.out.println(isCollidingWall());
        if (isCollidingWall()) speed = -speed ;

        if (x<0 || x >= maxGameWidth )
        {
            speed = -speed;
        }

        if (speed > 0 ) currentAnimation = 1;
        else currentAnimation = 0;

        x += speed;
        updateHitBox(xOffset);
        fireAnimations.update();
    }
    private void updateHitBox(int xOffset)
    {
        if (currentAnimation == 0) {
            hitBox.x = x + 10;
            hitBox.y = y + 10;
        }
        if (currentAnimation == 1) {
            hitBox.x = x + 40;
            hitBox.y = y + 5;
        }
    }

    @Override
    public void draw(Graphics2D g , int xOffset) {
        //g.setColor(Color.RED);
        if (currentAnimation == 0)
            g.drawImage(fireAnimations.moveLeft[fireAnimations.aniIndex] , x -xOffset, y , null );
        if (currentAnimation == 1)
            g.drawImage(fireAnimations.moveRight[fireAnimations.aniIndex] , x - xOffset, y , null );
        //g.setColor(Color.BLACK);
       // g.drawRect(hitBox.x - xOffset, hitBox.y, hitBox.width, hitBox.height);

    }

    private boolean isCollidingWall ()
    {
        for (Tile tile : tileManager.tiles)
            if (tile.hitBox.intersects(hitBox)) return true;

        return false;
    }
}
