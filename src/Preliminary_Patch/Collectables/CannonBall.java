package Preliminary_Patch.Collectables;

import Preliminary_Patch.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CannonBall extends Vehicle{

    /**
     * this is a cannon Ball object
     * @param x position in the x axis px
     * @param y position in the y axis px
     */

    public CannonBall(int x, int y) {
        super(x, y, new ImageIcon(".\\src\\Collectables\\cannon ball.png"));
    }
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(),getX(),getY() - getHeight() /2  ,getWidth(),getHeight(),null);
    }
}
