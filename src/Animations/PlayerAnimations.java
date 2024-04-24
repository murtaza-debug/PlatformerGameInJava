package Animations;

import Entities.Player;
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

    public BufferedImage attackLeft2Image ;
    public BufferedImage attackRight2Image ;


    ////// FOR SUB IMAGES TO ANIMATE //////

    public BufferedImage [] idleRightAnimations;
    public BufferedImage [] idleLeftAnimations;
    public BufferedImage [] runningRightAnimations;
    public BufferedImage [] runningLeftAnimations;
    public BufferedImage [] attackLeft1Animations ;
    public BufferedImage [] attackRight1Animations ;
    public BufferedImage [] attackLeft2Animations ;
    public BufferedImage [] attackRight2Animations ;

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

        attackLeft1Animations = new BufferedImage[GetTotalImages(ATTACK_LEFT_1)];
        attackRight1Animations = new BufferedImage[GetTotalImages(ATTACK_RIGHT_1)];

        attackRight2Animations = new BufferedImage[GetTotalImages(ATTACK_RIGHT_2)];
        attackLeft2Animations = new BufferedImage[GetTotalImages(ATTACK_LEFT_2)];

        loadAnimations();
    }
    private void loadImages() {
        idleRightImage = Load.Image("IdleRight.png");
        idleLeftImage = Load.Image("IdleLeft.png");

        runningRightImage = Load.Image("RunRight.png");
        runningLeftImage = Load.Image("RunLeft.png");

        attackLeft1Image = Load.Image("AttackLeft1.png");
        attackRight1Image = Load.Image("AttackRight1.png");

        attackLeft2Image = Load.Image("AttackLeft2.png");
        attackRight2Image = Load.Image("AttackRight2.png");


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

            attackLeft2Animations[i] = attackLeft2Image.getSubimage(i * width, 0, 200, 200);
            attackRight2Animations[j] = attackRight2Image.getSubimage(j * width, 0, 200, 200);
        }


    }

}
