package Animations;

public class PlayerConstants {

    /////// ANIMATIONS//////////
    public static final int IDLE_LEFT = 0 ;
    public static final int IDLE_RIGHT = 1 ;
    public static final int RUNNING_LEFT = 2 ;
    public static final int RUNNING_RIGHT = 3 ;
    public static final int ATTACK_LEFT_1 = 4 ;
    public static final int ATTACK_RIGHT_1 = 5 ;
    public static final int JUMP_RIGHT = 6 ;
    public static final int JUMP_LEFT = 7 ;

    ////// DIRECTION ///////////
    public static final int LEFT = 3 ;
    public static final int RIGHT = 4 ;

    public static int GetTotalImages (int currentAnimation)
    {
        switch (currentAnimation)
        {
            case IDLE_LEFT:
                case IDLE_RIGHT:
            case RUNNING_LEFT:
                case RUNNING_RIGHT:

                return 8 ;

            case ATTACK_LEFT_1:
            case ATTACK_RIGHT_1:

                return 6 ;

            case JUMP_RIGHT:
            case JUMP_LEFT:
                return 2 ;

            default:
                return 1 ;
        }

    }
}
