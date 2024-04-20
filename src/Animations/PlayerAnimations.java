package Animations;

import Entities.Player;
import Loader.Load;

import java.awt.image.BufferedImage;

import static Animations.Constants.*;

public class PlayerAnimations {

    ///// FOR ENTIRE IMAGE ///////
    public BufferedImage idleRightImage;
    public BufferedImage idleLeftImage;
    public BufferedImage runningRightImage;
    public BufferedImage runningLeftImage;


    ////// FOR SUB IMAGES TO ANIMATE //////

    public BufferedImage [] idleRightAnimations;
    public BufferedImage [] idleLeftAnimations;
    public BufferedImage [] runningRightAnimations;
    public BufferedImage [] runningLeftAnimations;


    Player player;
    int width = 200 ;

    public PlayerAnimations(Player player)
    {
        this.player = player;

        loadImages();

        idleRightAnimations = new BufferedImage[GetTotalImages(IDLE_RIGHT)];
        idleLeftAnimations = new BufferedImage[GetTotalImages(IDLE_LEFT)];
        runningRightAnimations = new BufferedImage[GetTotalImages(RUNNING_RIGHT)];
        runningLeftAnimations = new BufferedImage[GetTotalImages(RUNNING_LEFT)];

        loadAnimations();
    }
    private void loadImages() {
        idleRightImage = Load.Image("IdleRight.png");
        idleLeftImage = Load.Image("IdleLeft.png");
        runningRightImage = Load.Image("RunRight.png");
        runningLeftImage = Load.Image("RunLeft.png");
    }

    private void loadAnimations() {
        for (int i = 0 ; i < GetTotalImages(IDLE_RIGHT) ; i++ ) {
            idleRightAnimations[i] = idleRightImage.getSubimage(i * width, 0, 200, 200);
            runningRightAnimations[i] = runningRightImage.getSubimage(i * width, 0, 200, 200);
        }
        for (int i = GetTotalImages(IDLE_LEFT) - 1 ; i >= 0 ; i-- )
        {
            runningLeftAnimations[i] = runningLeftImage.getSubimage(i * width, 0, 200, 200);
            idleLeftAnimations[i] = idleLeftImage.getSubimage(i * width, 0, 200, 200);
        }

    }

}
