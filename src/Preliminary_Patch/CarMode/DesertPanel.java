package Preliminary_Patch.CarMode;

import Preliminary_Patch.GameConst;
import Preliminary_Patch.MasterPanel;
import Preliminary_Patch.Player;
import javax.swing.*;

public class DesertPanel extends MasterPanel {

    /**
     * This Class contains all the required information to draw the game
     * it contains the terrine Type sea,desert,grass.
     * number of vehicles to be spawned
     * number of the images needed to draw the side part
     * vehicles imageIcon  arraylist
     */


    private static final int terrineType= GameConst.DESERTMODE;

    private static final int numberOfBadVehicles = 2;
    private static final ImageIcon[] loadedbadVehicleImageIcons = new ImageIcon[numberOfBadVehicles];

    private static final int numberOfVehicle= 8;
    private static final int backgroundImages = 0;
    private static final int playingBackgroundSpeed = 100;
    private static final String backgroundImagesDirectory = ".\\src\\CarMode\\Background\\";
    private static final String sideRoadDirectory = ".\\src\\CarMode\\UpperSideRoad\\";

    private static final int sideBackgroundImages = 19;
    private static final ImageIcon[] loadedVehicleImageIcons = new ImageIcon[numberOfVehicle];

    public DesertPanel(Player player,int modeType) {
        super(player, modeType, terrineType,loadedbadVehicleImageIcons,loadedVehicleImageIcons, backgroundImages, playingBackgroundSpeed, backgroundImagesDirectory, sideRoadDirectory, sideBackgroundImages);

        if (GameConst.carBackgroundMusic != null)
            GameConst.carBackgroundMusic.play();

        //Normal Vehicles
        for (int i = 0; i < loadedVehicleImageIcons.length ; i++){
            loadedVehicleImageIcons[i] = new ImageIcon(".\\src\\CarMode\\Cars\\"+i+".png");
        }
        //Bad Vehicles
        for (int i = 0; i < loadedbadVehicleImageIcons.length ; i++) {
            loadedbadVehicleImageIcons[i] = new ImageIcon(".\\src\\CarMode\\BadCars\\"+i+".png");
        }
    }
    public void setFrame(JFrame frame) {
        super.setFrame(frame);
    }

}
