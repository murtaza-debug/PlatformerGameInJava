package Animations;

import Loader.Load;

import java.awt.image.BufferedImage;

public class FireTrapAnimations {

    BufferedImage upImage;
    BufferedImage downImage;
    BufferedImage leftImage;
    BufferedImage rightImage;

    public BufferedImage[] moveUp ;
    public BufferedImage[] moveDown ;
    public BufferedImage[] moveLeft ;
    public BufferedImage[] moveRight ;

    public int aniIndex = 0 , aniSpeed = 8, aniTick;

    public FireTrapAnimations ()
    {
        upImage = Load.Image("Fire/FireBallUp.png");
        downImage = Load.Image("Fire/FireBallDown.png");
        leftImage = Load.Image("Fire/FireBallLeft.png");
        rightImage = Load.Image("Fire/FireBallRight.png");

        moveUp = new BufferedImage[6];
        moveDown = new BufferedImage[6];
        moveLeft = new BufferedImage[6];
        moveRight = new BufferedImage[6];
        loadAnimations();
    }

    private void loadAnimations() {
        for (int i = 0; i < 6; i++) {
            moveLeft[i] = leftImage.getSubimage(i* 80,0, 80, 45);
        }
        for (int i = 0; i < 6; i++) {
            moveRight[i] = rightImage.getSubimage(i* 80,0, 80, 45);
        }
        for (int i = 0; i < 6; i++) {
            moveUp[i] = upImage.getSubimage( 0,i* 80, 45, 80);
        }
        for (int i = 0; i < 6; i++) {
            moveDown[i] = downImage.getSubimage(0,i* 80, 45, 80);
        }
    }



    public void update ()
    {
        updateAnimationTick();
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
}
