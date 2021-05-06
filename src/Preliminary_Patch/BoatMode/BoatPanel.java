package Preliminary_Patch.BoatMode;

/**
 * This class extends Master Panel
 * It contains information about the required Driving Mode
 *
 * as player ,Obsticals and background
 */

import Preliminary_Patch.GameConst;
import Preliminary_Patch.MasterPanel;
import Preliminary_Patch.Player;
import Preliminary_Patch.*;
import jdk.internal.dynalink.beans.StaticClass;
import sun.plugin2.message.GetAppletMessage;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class BoatPanel extends MasterPanel {

    /**
     * This Class contains all the required information to draw the game
     * it contains the terrine Type sea,desert,grass.
     * number of vehicles to be spawned
     * number of the images needed to draw the side part
     * vehicles imageIcon  arraylist
     */

    private static final int terrineType = GameConst.BOATMODE;

    private static final int numberOfBadVehicles = 2;
    private static final ImageIcon[] loadedbadVehicleImageIcons = new ImageIcon[numberOfBadVehicles];
    private static final int numberOfVehicle= 15;
    private static final int backgroundImages = 31;
    private static final int playingBackgroundSpeed = 100;
    private static final String backgroundImagesDirectory = ".\\src\\BoatMode\\waterBackground\\";
    private static final String sideRoadDirectory = ".\\src\\BoatMode\\Bottom Side road\\";
    private static final int sideBackgroundImages = 21;
    private static final ImageIcon[] loadedVehicleImageIcons = new ImageIcon[numberOfVehicle];

    public BoatPanel(Player player,int modeType) {

        super(player ,modeType,terrineType,loadedbadVehicleImageIcons,loadedVehicleImageIcons ,backgroundImages,playingBackgroundSpeed,backgroundImagesDirectory , sideRoadDirectory,sideBackgroundImages);

        if (GameConst.boatBackgroundMusic != null)
            GameConst.boatBackgroundMusic.play();

        //Good Vehicles
        for (int i = 0; i < loadedVehicleImageIcons .length ; i++){
            loadedVehicleImageIcons [i] = new ImageIcon(".\\src\\BoatMode\\Boats\\"+i+".png");
        }
        //Bad Vehicles
        for (int i = 0; i < loadedbadVehicleImageIcons.length ; i++) {
            loadedbadVehicleImageIcons[i] = new ImageIcon(".\\src\\BoatMode\\BadBoat\\" + i + ".png");
        }
    }
    public void setFrame(JFrame frame) {
        super.setFrame(frame);
    }

}
