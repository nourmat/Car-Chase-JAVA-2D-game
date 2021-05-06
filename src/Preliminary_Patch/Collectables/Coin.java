package Preliminary_Patch.Collectables;

import Preliminary_Patch.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coin extends Vehicle {

    private int imageNumber = 0;
    private String coindDirectory = ".\\src\\Collectables\\Coin\\";
    ImageIcon coindImageIcon = new ImageIcon(coindDirectory  + imageNumber + ".png");
    Image coinImage = coindImageIcon.getImage();
    Timer timer;

    /**
     * Coin Object constructor
     * @param x position in the x axis px
     * @param y position in the y axis px
     */

    public Coin(int x, int y) {
        super(x, y, new ImageIcon( ".\\src\\Collectables\\Coin\\0.png"));
        setCoinTimer();
    }
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(coinImage ,getX(),getY() ,getWidth(),getHeight(),null);
    }

    /**
     * method keeps updating the coin image and makes it rotate
      */
    public void setCoinTimer(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coinImage  = new ImageIcon(coindDirectory + imageNumber+ ".png").getImage();
                imageNumber++;
                imageNumber = imageNumber > 9 ? 0 : imageNumber; /** 3 is the number of rocket images */
                coindImageIcon = new ImageIcon(coindDirectory  + imageNumber + ".png");
                coinImage = coindImageIcon.getImage();
            }
        });
        timer.start();
    }


}
