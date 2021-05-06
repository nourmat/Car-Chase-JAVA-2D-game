package Preliminary_Patch;

import javax.swing.*;
import java.awt.*;

public class Player extends Vehicle {

    /**
     * Player object is  placed in the middle lane by default and at the right area of the screen
     * @param imageIcon the image of the player
     */

    public Player(ImageIcon imageIcon) {

        super(0, GameConst.getGamePanelHEIGHT() / 2,imageIcon);
        this.setX((int)(GameConst.getSCREENWIDTH()-getWidth()- GameConst.getSCREENWIDTH() * 0.02));
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(this.getImage(),getX(),getY() ,getWidth(),getHeight(),null);
        /**
         * The width came from 1568/1600 to leave around 40 px so factor was 1-(0.98) :)
         */


        /** Example if scree is 1600 * 900 then
        width = the no of grids will be 100/10 * 16 so it will be 160
        Height = laneHalfSize is 120 * (2/3) = 80
        */
    }
}
