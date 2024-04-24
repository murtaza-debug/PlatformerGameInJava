package Tiles;

import Animations.PropAnimations;
import Entities.Player;
import Loader.Load;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static MAINGAME.Panel.*;
import static Tiles.TileConstants.*;
import static Tiles.propConstants.Grass2;

public class TileManager extends Defaults {

    private int[][] map1 = TileMap.GetMap();

    public ArrayList<Tile> tiles = new ArrayList<>();
    public ArrayList<PropAnimations> propAnimations = new ArrayList<>();



    Player player;

    BufferedImage[] background1;
    BufferedImage[] background2;

    public TileManager(Player player) {
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

        makeTiles();
    }

    @Override
    public void update() {
        for (int i = 0; i < propAnimations.size(); i++)
            propAnimations.get(i).update();

    }

    public void makeTiles() {
        for (int x = 0; x < map1.length; x++) {
            for (int y = 0; y < map1[x].length; y++) {
                if (map1[x][y] != SKYE && map1[x][y] != Grass2) {
                    tiles.add(new Tile(y * TILE_SIZE, x * TILE_SIZE, TILE_SIZE, TILE_SIZE));
                    tiles.getLast().type = map1[x][y];
                }
                if (map1[x][y] == Grass2) {
                    propAnimations.add(new PropAnimations(y * TILE_SIZE + y - x, x * TILE_SIZE
                            , TILE_SIZE, TILE_SIZE, Grass2));
                    propAnimations.getLast().props.type = map1[x][y];
                }

            }
        }

    }

    @Override
    public void draw(Graphics2D g , int xOffSet) {

        for (int i = 0; i < background1.length; i++) {
                g.drawImage(background1[i],0, 0, null);
        }

        for (Tile tile : tiles)
        {
            tile.draw(g , xOffSet);
        }

        for (PropAnimations propAnimation : propAnimations) propAnimation.draw(g , xOffset);



    }


}

