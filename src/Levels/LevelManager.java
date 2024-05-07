package Levels;

import java.awt.*;
import java.util.ArrayList;

public class LevelManager {

    public static int LEVEL1 = 0;
    public static int LEVEL2 = 1;
    public static int currentLevel = LEVEL1;
    public static int maxLevels = 2;

    ArrayList<Level> levels ;
    public LevelManager ()
    {
        levels = new ArrayList<>();
    }

    public void addLevel (Level level)
    {
        levels.add(level);
    }

    public void update()
    {
        if (currentLevel == LEVEL1)
            levels.get(LEVEL1).update();

        if (currentLevel == LEVEL2)
            levels.get(LEVEL2).update();
    }

    public void draw(Graphics2D g , int xOffset)
    {
        if (currentLevel == LEVEL1)
            levels.get(LEVEL1).draw(g,xOffset);

        if(currentLevel == LEVEL2)
            levels.get(LEVEL2).draw(g,xOffset);
    }

}
