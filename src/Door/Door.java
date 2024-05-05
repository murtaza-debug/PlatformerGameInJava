package Door;

import Collectables.Health;
import Entities.Player;
import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GameStates.StateConstants.MENU;
import static MAINGAME.Panel.TILE_SIZE;
import static MAINGAME.Panel.currentState;

public class Door {

    int x , y ;

    public int aniIndex = 0 , aniSpeed = 8, aniTick;

    BufferedImage doorImage ;
    BufferedImage[] doorAnimation ;
    BufferedImage doorLocation ;

    Rectangle doorHitBox ;
    Player player ;

    public boolean over = false ;

    public Door(Player player) {
        doorLocation = Load.Image("DoorLocation.png");
        doorImage = Load.Image("Door.png");
        doorAnimation = new BufferedImage[16];

        doorAnimation[0] = doorImage.getSubimage(0,0,32,32);
        doorAnimation[1] = doorImage.getSubimage(32,0,32,32);
        doorAnimation[2] = doorImage.getSubimage(64,0,32,32);
        doorAnimation[3] = doorImage.getSubimage(96,0,32,32);
        doorAnimation[4] = doorImage.getSubimage(0,32,32,32);
        doorAnimation[5] = doorImage.getSubimage(32,32,32,32);
        doorAnimation[6] = doorImage.getSubimage(64,32,32,32);
        doorAnimation[7] = doorImage.getSubimage(96,32,32,32);
        doorAnimation[8] = doorImage.getSubimage(0,64,32,32);
        doorAnimation[9] = doorImage.getSubimage(32,64,32,32);
        doorAnimation[10] = doorImage.getSubimage(64,64,32,32);
        doorAnimation[11] = doorImage.getSubimage(96,64,32,32);
        doorAnimation[12] = doorImage.getSubimage(0,96,32,32);
        doorAnimation[13] = doorImage.getSubimage(32,96,32,32);
        doorAnimation[14] = doorImage.getSubimage(64,96,32,32);
        doorAnimation[15] = doorImage.getSubimage(96,96,32,32);

        x = TILE_SIZE * 191;
        y = TILE_SIZE * 8;

        doorHitBox = new Rectangle(x,y,64,64);
        this.player = player;
    }

    private void checkHit()
    {
        if (player.hitBox.intersects(doorHitBox))
            over = true ;

    }

    public void setDefaults ()
    {
        over = false ;
    }

    protected void updateAnimationTick()
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 16)
            {
                aniIndex = 0;
            }
        }
    }

    public void update ()
    {
        updateAnimationTick();
        checkHit();
    }
    public void draw (Graphics2D g , int xOffset)
    {
        g.drawImage(doorAnimation[aniIndex],x - xOffset,y,64,64,null);
    }
}
