package Preliminary_Patch.Collectables;

import Preliminary_Patch.Vehicle;

import javax.swing.*;
import java.awt.*;

public class Heart extends Vehicle {

    /**
     * this is a Heart object extend Vehicle
     * @param x position in the x axis px
     * @param y position in the y axis px
     */

    public Heart(int x, int y) {
        super(x, y, new ImageIcon("C:\\Users\\Nour PC\\IdeaProjects\\Programming 2 Project\\src\\Collectables\\Heart.png"));
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(),getX(),getY(),getHeight(),getWidth(),null);
    }

}
