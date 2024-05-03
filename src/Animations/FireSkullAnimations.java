package Animations;

import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Animations.PlayerConstants.GetTotalImages;

public class FireSkullAnimations {

    private BufferedImage image;
    public BufferedImage[] chase ;

    private int aniIndex = 0 , aniSpeed = 12, aniTick;

    public FireSkullAnimations ()
    {
        chase = new BufferedImage[3];
        image = Load.Image("Fire/FireSkull.png");
        chase[0] = image.getSubimage(0,0, 64, 64);
        chase[1] = image.getSubimage(64,0, 64, 64);
        chase[2] = image.getSubimage(128,0, 64, 64);
    }

    protected void updateAnimationTick()
    {
        aniTick++;
        if(aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 3)
            {
                aniIndex = 0;
            }
        }
    }

    public void update ()
    {
        updateAnimationTick();
    }
    public void draw (Graphics2D g , int x , int y)
    {
        g.drawImage(chase[aniIndex], x, y, null);
    }

}
