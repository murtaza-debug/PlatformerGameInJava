package Collision;

import Entities.Player;

import java.awt.*;

import static MAINGAME.Panel.TILE_SIZE;

public class Collision {

    public static boolean CanMoveHere (int[][] tileMap , Player player,
                                       int x , int y)
    {
        if (tileMap[x][y] == 0)
            return true;

        else if (player.hitBox.x - (x*TILE_SIZE) >= 19  & player.hitBox.y - (y*TILE_SIZE) >= 11)
            return false;

        return false;

    }

    public static boolean IsColliding (Player player , Rectangle hitBox)
    {
        if (player.hitBox.intersects(hitBox))
            return true ;

        return false ;
    }

}