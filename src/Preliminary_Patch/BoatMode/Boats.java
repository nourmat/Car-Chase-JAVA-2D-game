package Preliminary_Patch.BoatMode;

import Preliminary_Patch.GameConst;
import Preliminary_Patch.Vehicle;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Boats extends Vehicle {

    /**
     * extends vehicle
     * but it draws a wave behind it.
     */

//    private int drawtime = 10; //this is used to make drawing the wve slower after drawing the boat number of times

    private boolean isIsland;

    //Uses this only in case of
    private int imageNumber = 0;
    private String waveDirectory = ".\\src\\BoatMode\\Water wave\\";
    ImageIcon waveImageIcon = new ImageIcon(waveDirectory + imageNumber + ".png");
    Image wave = waveImageIcon.getImage();
    Timer timer;


    public Boats (int x, int y, ImageIcon imageIcon) {
        super(x, y, imageIcon);
        isIsland = getHeight() > GameConst.getLaneHalfHeightDistance() * 2;
        setWaveTimer();
    }

    /**
     * It know if it is an island if its size is greater than one lane.
     * @return true if the object is and island
     */
    public boolean isIsland() {
        return isIsland;
    }

    @Override
    public void draw(Graphics graphics) {
        if (!isIsland) {

            int waveWidth = waveImageIcon.getIconWidth() * GameConst.getSCREENWIDTH() / 1600; //These numbers were the reference of getting the printing factor in draw function
            int waveHeight = waveImageIcon.getIconHeight() * GameConst.getSCREENHEIGHT() / 900; // and all of the objects sizes in px were made relative to these numbers
            graphics.drawImage(wave, getX() + getWidth() - 20, (getY() + getHeight()/ 2) - (waveImageIcon.getIconHeight()/2), waveWidth, waveHeight, null);
            graphics.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
        }
        else
            graphics.drawImage(getImage(),getX(),getY() ,getWidth(),getHeight(),null);
        /**
         * The width came from 1568/1600 to leave around 40 px so factor was 1-(0.98) :)
         */


        /** Example if scree is 1600 * 900 then
         width = the no of grids will be 100/10 * 16 so it will be 160
         Height = laneHalfSize is 120 * (2/3) = 80
         */
    }

    /** this function is used to draw the wave behind boats only and change its image relative to time*/
    public void setWaveTimer(){
        if (!isIsland) {
            timer = new Timer(180, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wave = new ImageIcon(waveDirectory + imageNumber+ ".png").getImage();
                    imageNumber++;
                    imageNumber = imageNumber > 13 ? 0 : imageNumber; /** 13 is the number of wave images */
                    waveImageIcon = new ImageIcon(waveDirectory + imageNumber + ".png");
                    wave = waveImageIcon.getImage();
                }
            });
            timer.start();
        }
    }
}