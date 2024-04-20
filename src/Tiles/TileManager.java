package Tiles;

import Entities.Player;

import java.awt.*;
import java.util.ArrayList;

import static MAINGAME.Panel.*;
import static Tiles.TileConstants.*;

public class TileManager extends Defaults{

    private int[][] map1 = TileMap.GetMap() ;

    public ArrayList<Tile> tiles = new ArrayList<>();
    Player player;
    public TileManager (Player player)
    {
        this.player = player;
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

        for (int x = 0 ; x < map1.length ; x++) {
            for (int y = 0 ;  y < map1[0].length ; y++) {
                if (map1[x][y] != SKYE)
                    for (Tile tile : tiles) tile.draw(g) ;
            }
        }

    }



}
