package Preliminary_Patch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sideRoadPanel extends JPanel{

    /**
     * this class is used to draw the sideroad
     */

    private Image currentSideBackground;
    private int sideBackgroundImages; // total number of images
    private int backgroundImageNumber = 0; // current drawn one
    private String sideRoadDirectory;
    private Timer timer;

    /**
     * draw the side road on a JPanel
     * @param sideRoadDirectory the directory of the images
     * @param sideBackgroundImages the number of images in the directory
     */

    public sideRoadPanel(String sideRoadDirectory , int sideBackgroundImages) {
        this.sideRoadDirectory = sideRoadDirectory;
        this.sideBackgroundImages = sideBackgroundImages;
        setSideRoadTimer();
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(currentSideBackground, 0, 0, GameConst.getSCREENWIDTH(), GameConst.getBorderSize(), null);
        if (backgroundImageNumber > sideBackgroundImages)
            backgroundImageNumber = 0;
        setOpaque(false);
        super.paint(graphics);
    }

    /**
     * this sets a timer to update the images every interval of time
     */

    public void setSideRoadTimer(){
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSideBackground = new ImageIcon( sideRoadDirectory + backgroundImageNumber + ".png").getImage();
                repaint();
                backgroundImageNumber++;
            }
        });
        timer.start();
    }
}
