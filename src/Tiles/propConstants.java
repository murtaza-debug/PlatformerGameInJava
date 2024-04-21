package Tiles;

public class propConstants {

    public static final int Grass2 = 11 ;



    public static int GetAmount(int type)
    {
        switch (type)
        {
            case Grass2:
                return 30;

            default:
                return 1;
        }
    }
}
