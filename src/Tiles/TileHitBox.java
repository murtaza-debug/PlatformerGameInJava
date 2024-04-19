package Tiles;

import Entities.Player;

import java.awt.*;

import static MAINGAME.Panel.*;
import static MAINGAME.Panel.TILE_SIZE;
import static Collision.Collision.*;
import static Tiles.TileConstants.*;

public class TileHitBox {
    public Rectangle[][] hitBox ;
    Dimension box = new Dimension(TILE_SIZE,TILE_SIZE);
    Player player;
    int[][] map1 ;
    public TileHitBox (Player player , int [][] map1)
    {
        this.map1 = map1;
        this.player = player;
        hitBox = new Rectangle[map1.length][map1[0].length ];
        setHitBox();
    }
    public void setHitBox() {

        for (int x = 0 ; x < 12 ; x++)
            for (int y = 0 ; y < 20 ; y++)
            {
                hitBox[x][y] = new Rectangle(box);
                hitBox[x][y].y = x *TILE_SIZE ;
                hitBox[x][y].x = y *TILE_SIZE ;
            }
    }

    public void updateTileHitBox ()
    {
        for (int x = 0 ; x < 12 ; x++)
            for (int y = 0 ; y < 20 ; y++) {
                player.isMoving = true ;
                if (!CanMoveHere(map1, player, x, y))
                    if (IsColliding(player, hitBox[x][y]))
                    {

                        /// ON GROUND ////
                        if (player.y + TILE_SIZE >= hitBox[x][y].y)
                        {
                            if (x != 1)
                                if (map1[x-1][y] == 0) {
                                    player.y = hitBox[x][y].y - TILE_SIZE - 1;
                                }
                        }
                        //// IT FROM ABOVE
                        if (hitBox[x][y].y + TILE_SIZE >= player.y)
                        {
                            if (x!= 11)
                                if (map1[x+1][y] == 0) {
                                    player.y = hitBox[x][y].y + TILE_SIZE + 1;
                                }
                        }
                        ///// LEFT HIT ///////
                         if ((hitBox[x][y].x + TILE_SIZE) >= player.x) {
                             if (y != 19)
                                if (map1[x][y+1] == 0)
                                {
                                    player.x = hitBox[x][y].x + TILE_SIZE + 1;
                                }

                        }
                         ////// RIGHT HIT ////////
                         if (player.x + TILE_SIZE >= hitBox[x][y].x)
                        {
                            if (y != 0)
                                if (map1[x][y-1] == 0) {
                                    player.x = hitBox[x][y].x - 1 - TILE_SIZE;
                                }
                        }

                    }
            }
    }
    public void draw (Graphics g) {

        for (int x = 0 ; x < 12 ; x++)
            for (int y = 0 ; y < 20 ; y++) {

                //System.out.println((y));
                g.drawRect(hitBox[x][y].x
                        , hitBox[x][y].y, TILE_SIZE , TILE_SIZE );
                if (!CanMoveHere(map1, player, x, y))
                    if (IsColliding(player, hitBox[x][y])) {
                        g.setColor(Color.pink);
                        g.drawString("X : " + hitBox[x][y].x + " Y : " +
                                hitBox[x][y].y, hitBox[x][y].x, hitBox[x][y].y - 70);
                    }
            }
    }

}
