package Animations;

public class Constants {

    public static final int IDLE_LEFT = 0 ;
    public static final int IDLE_RIGHT = 1 ;
    public static final int RUNNING_LEFT = 2 ;
    public static final int RUNNING_RIGHT = 3 ;
    public static final int ATTACK1 = 4 ;

    public static final int LEFT = 3 ;
    public static final int RIGHT = 4 ;

    public static int GetTotalImages (int animation)
    {
        switch (animation)
        {
            case IDLE_LEFT:
                case IDLE_RIGHT:

            case RUNNING_LEFT:
                case RUNNING_RIGHT:
                return 8 ;
            case ATTACK1:
                return 6 ;

            default:
                return 1 ;
        }

    }
}
