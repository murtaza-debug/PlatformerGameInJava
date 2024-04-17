package Entities;

import UserInput.Keyboard;

import java.awt.*;
import static MAINGAME.Panel.*;

public class Player extends Entity{


    /// defaults ////
    private Keyboard keyboard ;


    //// HIT_BOX //////
    Rectangle hitBox;
    Dimension dimensionHitBox ;

    //////// CONSTRUCTOR /////////
    public Player ()
    {
        dimensionHitBox = new Dimension(TILE_SIZE,TILE_SIZE);
        keyboard = new Keyboard() ;
        hitBox = new Rectangle(dimensionHitBox);
    }


    /////// INPUTS //////

    private void updatePosition ()
    {
        if (keyboard.Up) this.y -= speed;
        if (keyboard.Down) this.y += speed;
        if (keyboard.Left) this.x -= speed;
        if (keyboard.Right) this.x += speed;
    }

    ///// UPDATE AND DRAW HIT_BOX  ///////

    public void updateHitBox ()
    {
        hitBox.x = x;
        hitBox.y = y;
    }
    public void drawHitBox (Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.drawRect(hitBox.x,hitBox.y,hitBox.width,hitBox.height);
    }

    ////// UPDATE AND DRAW /////

    @Override
    public void update() {
        updatePosition();
        updateHitBox();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.x,this.y , 64, 64);
        g.setColor(Color.RED);
        g.drawString("x :" + x + "  y : " + y , x , y - 10);
        drawHitBox(g);
    }

    //// GETTERS AND SETTERS ////////
    public Keyboard getKeyboard() {
        return this.keyboard;
    }
}
