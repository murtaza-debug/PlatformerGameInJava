package Animations;

import Loader.Load;

import java.awt.image.BufferedImage;
import static Animations.DarkKnightConstants.* ;

public class DarkKnightAnimations {



    BufferedImage idleImageRight ;
    BufferedImage idleImageLeft ;
    BufferedImage runImageRight ;
    BufferedImage runImageLeft ;
    BufferedImage attackImageRight ;
    BufferedImage attackImageLeft;
    BufferedImage takeHitImageLeft;
    BufferedImage takeHitImageRight;
    BufferedImage deathImageRight ;
    BufferedImage deathImageLeft;

    public BufferedImage[] idleRight;
    public BufferedImage[] idleLeft;
    public BufferedImage[] runRight;
    public BufferedImage[] runLeft;
    public BufferedImage[] attackRight;
    public BufferedImage[] attackLeft;

    public DarkKnightAnimations() {
        loadImages();

        idleRight = new BufferedImage[GetTotalImages(IDLE_RIGHT)];
        idleLeft = new BufferedImage[GetTotalImages(IDLE_LEFT)];
        runRight = new BufferedImage[GetTotalImages(RUNNING_RIGHT)] ;
        runLeft = new BufferedImage[GetTotalImages(RUNNING_LEFT)] ;
        attackRight = new BufferedImage[GetTotalImages(ATTACK_RIGHT)];
        attackLeft = new BufferedImage[GetTotalImages(ATTACK_LEFT)];

        loadAnimations();
    }

    private void loadImages() {

        idleImageRight = Load.Image("NightBorne/IdleRight.png");
        idleImageLeft = Load.Image("NightBorne/IdleLeft.png");

        runImageRight = Load.Image("NightBorne/RunRight.png");
        runImageLeft = Load.Image("NightBorne/RunLeft.png");

        attackImageLeft = Load.Image("NightBorne/AttackLeft.png");
        attackImageRight = Load.Image("NightBorne/AttackRight.png");

        takeHitImageLeft = Load.Image("NightBorne/TakeHitLeft.png");
        takeHitImageRight = Load.Image("NightBorne/TakeHitRight.png");

        deathImageRight = Load.Image("NightBorne/DeathRight.png");
        deathImageLeft = Load.Image("NightBorne/DeathLeft.png");

    }

    private void loadAnimations() {

        int index = 0 ;
        for (int i = 0; i < GetTotalImages(IDLE_RIGHT); i++) {
            idleRight[i] = idleImageRight.getSubimage(i * 80 , 0 , 80 , 64 );
        }
        for (int i = GetTotalImages(IDLE_LEFT) - 1; i >= 0 ; i--) {
            idleLeft[index] = idleImageLeft.getSubimage(i * 80, 0  , 80 , 64 );
            index ++ ;
        }
        for (int i = 0; i < GetTotalImages(RUNNING_RIGHT); i++) {
            runRight[i] = runImageRight.getSubimage(i * 80 , 0 , 80 , 64 );
        }
        index = 0 ;
        for (int i = GetTotalImages(RUNNING_LEFT) - 1; i >= 0 ; i--) {
            idleLeft[index] = idleImageLeft.getSubimage(i * 80, 0  , 80 , 64 );
            index ++ ;
        }
    }



}
