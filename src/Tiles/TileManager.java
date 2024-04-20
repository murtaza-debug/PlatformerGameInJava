package Tiles;

import Entities.Player;
import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static MAINGAME.Panel.*;
import static Tiles.TileConstants.*;

public class TileManager extends Defaults{

    private int[][] map1 = TileMap.GetMap() ;

    public ArrayList<Tile> tiles = new ArrayList<>();

    Player player;

    BufferedImage[] background1;
    BufferedImage[] background2;

    public TileManager (Player player)
    {
        this.player = player;
        background1 = new BufferedImage[4];
        background2 = new BufferedImage[4];

        background1[0] = Load.Image("background1.png");
        background1[1] = Load.Image("background2.png");
        background1[2] = Load.Image("background3.png");
        background1[3] = Load.Image("background4.png");

        background2[0] = Load.Image("Skye1.png");
        background2[1] = Load.Image("Skye2.png");
        background2[2] = Load.Image("Skye3.png");
        background2[3] = Load.Image("Skye4.png");

        makeWalls();
    }
    @Override
    public void update() {

    }


    public void makeWalls (){
        for (int x = 0 ; x < map1.length ; x++) {
            for (int y = 0; y < map1[x].length; y++)
            {
                if (map1[x][y] != SKYE)
                {
                    tiles.add(new Tile(y*TILE_SIZE , x*TILE_SIZE, TILE_SIZE, TILE_SIZE));
                    tiles.getLast().type = map1[x][y] ;
                }
            }
        }

    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < background1.length ; i++) {
            g.drawImage(background1[i] , 0 , 0 , null);
        }
        for (int i = 0; i < background2.length ; i++) {
            g.drawImage(background2[i] , 0 , 0 , null);
        }


        for (int x = 0 ; x < map1.length ; x++) {
            for (int y = 0 ;  y < map1[0].length ; y++) {
                if (map1[x][y] != SKYE)
                    for (Tile tile : tiles) tile.draw(g) ;
            }
        }

    }



}
