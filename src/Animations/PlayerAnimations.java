package Animations;

import Loader.Load;

import java.awt.image.BufferedImage;

import static Animations.PlayerConstants.*;

public class PlayerAnimations {

    ///// FOR ENTIRE IMAGE ///////
    public BufferedImage idleRightImage;
    public BufferedImage idleLeftImage;

    public BufferedImage runningRightImage;
    public BufferedImage runningLeftImage;

    public BufferedImage attackLeft1Image ;
    public BufferedImage attackRight1Image ;


    public BufferedImage jumpLeftImage;
    public BufferedImage jumpRightImage;

    ////// FOR SUB IMAGES TO ANIMATE //////

    public BufferedImage [] idleRightAnimations;
    public BufferedImage [] idleLeftAnimations;
    public BufferedImage [] runningRightAnimations;
    public BufferedImage [] runningLeftAnimations;
    public BufferedImage [] attackLeft1Animations ;
    public BufferedImage [] attackRight1Animations ;
    public BufferedImage [] jumpLeftAnimations ;
    public BufferedImage [] jumpRightAnimations ;

    int width = 200 ;

    public PlayerAnimations()
    {

        loadImages();

        idleRightAnimations = new BufferedImage[GetTotalImages(IDLE_RIGHT)];
        idleLeftAnimations = new BufferedImage[GetTotalImages(IDLE_LEFT)];

        runningRightAnimations = new BufferedImage[GetTotalImages(RUNNING_RIGHT)];
        runningLeftAnimations = new BufferedImage[GetTotalImages(RUNNING_LEFT)];

        attackLeft1Animations = new BufferedImage[GetTotalImages(ATTACK_LEFT_1)];
        attackRight1Animations = new BufferedImage[GetTotalImages(ATTACK_RIGHT_1)];


        jumpLeftAnimations = new BufferedImage[GetTotalImages(JUMP_LEFT)];
        jumpRightAnimations = new BufferedImage[GetTotalImages(JUMP_RIGHT)];

        loadAnimations();
    }
    private void loadImages() {
        idleRightImage = Load.Image("IdleRight.png");
        idleLeftImage = Load.Image("IdleLeft.png");

        runningRightImage = Load.Image("RunRight.png");
        runningLeftImage = Load.Image("RunLeft.png");

        attackLeft1Image = Load.Image("AttackLeft1.png");
        attackRight1Image = Load.Image("AttackRight1.png");

        jumpLeftImage = Load.Image("JumpLeft.png");
        jumpRightImage = Load.Image("JumpRight.png");

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

        for (int i = 0 , j = GetTotalImages(ATTACK_LEFT_1) - 1 ; i < GetTotalImages(ATTACK_LEFT_1) &&
                    j >= 0 ; i++ , j-- )
        {
            attackLeft1Animations[i] = attackLeft1Image.getSubimage(i * width, 0, 200, 200);
            attackRight1Animations[j] = attackRight1Image.getSubimage(j * width, 0, 200, 200);

        }

        for (int i = 0 , j = GetTotalImages(JUMP_LEFT) - 1 ; i < GetTotalImages(JUMP_LEFT) && j >= 0 ; i++ , j-- )
        {
            jumpLeftAnimations[i] = jumpLeftImage.getSubimage(i * width, 0, 200, 200);
            jumpRightAnimations[j] = jumpRightImage.getSubimage(i * width, 0, 200, 200);
        }

    }

}
