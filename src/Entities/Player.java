package Entities;

import UserInput.Keyboard;

import java.awt.*;

public class Player extends Entity{


    /// defaults ////

    private Keyboard keyboard ;



    public Player ()
    {
        keyboard = new Keyboard() ;
    }

    @Override
    public void update() {
        updatePosition();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.x,this.y , 64, 64);
    }


    /////// INPUTS //////

    private void updatePosition ()
    {
        if (keyboard.Up) this.y -= speed;
        if (keyboard.Down) this.y += speed;
        if (keyboard.Left) this.x -= speed;
        if (keyboard.Right) this.x += speed;
    }

    public Keyboard getKeyboard() {
        return this.keyboard;
    }
}
