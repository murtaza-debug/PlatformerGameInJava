package Animations;

public class DarkKnightConstants {


    /////// ANIMATIONS//////////
    public static final int IDLE_LEFT = 0 ;
    public static final int IDLE_RIGHT = 1 ;
    public static final int RUNNING_LEFT = 2 ;
    public static final int RUNNING_RIGHT = 3 ;
    public static final int ATTACK_LEFT = 4 ;
    public static final int ATTACK_RIGHT = 5 ;
    public static final int TAKE_HIT_LEFT = 6 ;
    public static final int TAKE_HIT_RIGHT = 7 ;
    public static final int DEATH_LEFT = 8 ;
    public static final int DEATH_RIGHT = 9 ;

    ////// DIRECTION ///////////
    public static final int LEFT = 3 ;
    public static final int RIGHT = 4 ;

    public static int GetTotalImages (int currentAnimation)
    {
        switch (currentAnimation)
        {
            case IDLE_LEFT:
            case IDLE_RIGHT:
                return 9 ;

            case RUNNING_LEFT:
            case RUNNING_RIGHT:
                return 6 ;

            case ATTACK_LEFT:
            case ATTACK_RIGHT:
                return 12;

            case TAKE_HIT_LEFT:
            case TAKE_HIT_RIGHT:
                return 5 ;

            case DEATH_LEFT:
            case DEATH_RIGHT:
                return 22 ;

            default:
                return 1 ;
        }

    }
}
