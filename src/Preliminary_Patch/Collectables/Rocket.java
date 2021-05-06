package Preliminary_Patch.Collectables;

import Preliminary_Patch.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rocket extends Vehicle {

    private int imageNumber = 0;
    private String rocketDirectory = ".\\src\\Collectables\\Fire\\";
    ImageIcon rocketImageIcon = new ImageIcon(rocketDirectory + imageNumber + ".png");
    Image rocketImage = rocketImageIcon.getImage();
    Timer timer;
    /**
     * this is a Rocket object
     * @param x position in the x axis px
     * @param y position in the y axis px
     */


    public Rocket(int x, int y) {
        super(x, y, new ImageIcon(".\\src\\Collectables\\Fire\\0.png"));
        setRocketTimer();
    }
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(rocketImage,getX(),getY() - getHeight() /2 ,getWidth(),getHeight(),null);
    }

    /**
     * this sets up a timer to draw the rocket animation
     */

    public void setRocketTimer(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rocketImage = new ImageIcon(rocketDirectory+ imageNumber+ ".png").getImage();
                imageNumber++;
                imageNumber = imageNumber > 3 ? 0 : imageNumber; /** 3 is the number of rocket images */
                rocketImageIcon = new ImageIcon(rocketDirectory + imageNumber + ".png");
                rocketImage= rocketImageIcon.getImage();
            }
        });
        timer.start();
    }
}
