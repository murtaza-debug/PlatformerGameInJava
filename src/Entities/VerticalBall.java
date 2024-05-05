package Entities;

import Animations.FireTrapAnimations;
import Tiles.Tile;
import Tiles.TileManager;

import java.awt.*;

import static Audios.AudioConstants.FIRE;

public class VerticalBall extends Trap{

    int speed  = 2;

    FireTrapAnimations fireAnimations;
    int currentAnimation = 0;

    VerticalBall(int x, int y, int width, int height , Player player , TileManager tileManager) {
        super(x , y, width - 32, height -32 , player , tileManager);
        fireAnimations = new FireTrapAnimations ();
    }

    @Override
    public void update(int xOffset) {

        System.out.println(isCollidingWall());
        if (isCollidingWall()) speed = -speed ;

        if (y >= 768 || y < 0)
        {
            speed = -speed ;
        }

        if (speed > 0 ) currentAnimation = 1;
        else currentAnimation = 0;

        y += speed;
        updateHitBox(xOffset);
        fireAnimations.update();
        checkIfAttacking();
    }
    private void updateHitBox(int xOffset)
    {
        if (currentAnimation == 0) {
            hitBox.x = x + 10;
            hitBox.y = y + 10;
        }
        if (currentAnimation == 1) {
            hitBox.x = x + 5;
            hitBox.y = y + 40;
        }
    }
    private void checkIfAttacking ()
    {
        if (player.hitBox.intersects(hitBox)) {
            player.HP -= 5;
        }
    }

    @Override
    public void draw(Graphics2D g , int xOffset) {
        if (currentAnimation == 0)
            g.drawImage(fireAnimations.moveUp[fireAnimations.aniIndex] , x - xOffset , y , null );
        if (currentAnimation == 1)
            g.drawImage(fireAnimations.moveDown[fireAnimations.aniIndex] , x - xOffset , y , null );
    }


    private boolean isCollidingWall ()
    {
        for (Tile tile : tileManager.tiles)
            if (tile.hitBox.intersects(hitBox)) return true;

        return false;
    }
}
