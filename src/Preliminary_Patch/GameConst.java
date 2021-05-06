package Preliminary_Patch;

import java.awt.*;

/**
 * This Object Contains all the Constans Here
 *
 * It asummes that the width is 100 grid and the height is 3
 *
 * @SCREENWIDTH : It has the FullScreen Width
 * @SCREENHEIGHT : It has the FullScreen HEIGHT
 *
 * @SCOREHEIGHT : it contains the HEIGHT of The score Panel
 * that came by a percentage of the Screen Height
 *
 * @noOFGRIDS : Contain the number of Grids in the x - axis
 *
 * @GRIDSIZE : The Grid width Size in pixels
 *
 *
 * NOTE That every Object and car Has its own size.
 */

public abstract class GameConst {

    private static boolean musicON = true;
    private static boolean soundON = true;

    public static Sound piretSound = new Sound(".\\src\\Sounds\\Pirate.wav");
    public static Sound coidSound = new Sound(".\\src\\Sounds\\smw_coin.wav");
    public static Sound motorSound = new Sound(".\\src\\Sounds\\Motor.wav");
    public static Sound cannonSound = new Sound(".\\src\\Sounds\\Cannon.wav");
    public static Sound laserSound = new Sound(".\\src\\Sounds\\Laser.wav");
    public static Sound rocketSound = new Sound(".\\src\\Sounds\\Rocket.wav");
    public static Sound buttonSound = new Sound(".\\src\\Sounds\\Button.wav");
    public static Sound crashSound = new Sound(".\\src\\Sounds\\Crash.wav");
    public static Sound destroySound = new Sound (".\\src\\Sounds\\Destroy1.wav");
    public static Sound shipBell = new Sound(".\\src\\Sounds\\ShipBell.wav");
    public static Sound carBackgroundMusic = new Sound(".\\src\\Sounds\\Car background.wav");
    public static Sound boatBackgroundMusic = new Sound(".\\src\\Sounds\\pirate background music.wav");
    public static Sound bmg = new Sound(".\\src\\Sounds\\bgm.wav");

    public static final int ATTACKMODE = 0;
    public static final int DEFENCEMOED = 1;

    public static final int BOATMODE = 0;
    public static final int GRASSMODE = 1;
    public static final int DESERTMODE = 2;
    public static final int CHIECKENMODE = 3;

    private static int SCREENWIDTH;
    private static int SCREENHEIGHT;

    private static int GamePanelHEIGHT;

    private static int NoOFGRIDS;
    private static int GRIDSIZE;
    private static int BorderSize;

    private static int LaneHalfHeightDistance;

    /**
     * This function is called when the program starts
     * it calculates the screen height and width
     * the game panel height
     * the lane height
     */
    public static void settingItUp() {
        SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
        SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
        NoOFGRIDS = 100;
        /**
         * This is how the screen will be divided
         * The sum is 0.9
         * thats because The Boarder Size will be accutlly multiplied by 2 as there are 2 Borders
         */
        GamePanelHEIGHT = (int)(SCREENHEIGHT * 0.76);
        BorderSize = (int) (SCREENHEIGHT * 0.12); /** we have top boarders*/
        LaneHalfHeightDistance = GamePanelHEIGHT / 3 / 2 ; /**getting the distance from the max point of the Game panel to the middle of the first lane*/
        GRIDSIZE = SCREENWIDTH / NoOFGRIDS; // Ex: 1600 / 100 = 16 pixels each time the screen repaints .. to be smooth :)
    }

    public static int getBorderSize() {
        return BorderSize;
    }

    public static int getSCREENWIDTH() {
        return SCREENWIDTH;
    }

    public static int getSCREENHEIGHT() {
        return SCREENHEIGHT;
    }

    public static int getNoOFGRIDS() {
        return NoOFGRIDS;
    }

    public static int getGRIDSIZE() {
        return GRIDSIZE;
    }

    public static int getGamePanelHEIGHT() {
        return GamePanelHEIGHT;
    }


    public static int getLaneHalfHeightDistance() {
        return LaneHalfHeightDistance;
    }


    /**
     * reverse the music option
     * if closed opens and vise versa
     */

    public static void reverseMusic(){
        musicON = !musicON;
        if (musicON) {
            carBackgroundMusic = new Sound(".\\src\\Sounds\\Car background.wav");
            boatBackgroundMusic = new Sound(".\\src\\Sounds\\pirate background music.wav");
            bmg = new Sound(".\\src\\Sounds\\bgm.wav");
        }
        else{
            carBackgroundMusic = null;
            boatBackgroundMusic = null;
            bmg = null;
        }
        System.out.println("MUSIC ON" + musicON);
    }

    /**
     * reverse the sound option
     * if closed opens and vise versa
     */
    public static void reveerseSound() {
        soundON = !soundON;
        if (soundON) {
            piretSound = new Sound(".\\src\\Sounds\\Pirate.wav");
            coidSound = new Sound(".\\src\\Sounds\\smw_coin.wav");
            motorSound = new Sound(".\\src\\Sounds\\Motor.wav");
            cannonSound = new Sound(".\\src\\Sounds\\Cannon.wav");
            laserSound = new Sound(".\\src\\Sounds\\Laser.wav");
            rocketSound = new Sound(".\\src\\Sounds\\Rocket.wav");
            buttonSound = new Sound(".\\src\\Sounds\\Button.wav");
            crashSound = new Sound(".\\src\\Sounds\\Crash.wav");
            destroySound = new Sound(".\\src\\Sounds\\Destroy1.wav");
            shipBell = new Sound(".\\src\\Sounds\\ShipBell.wav");
        } else {
            piretSound = null;
            coidSound = null;
            motorSound = null;
            cannonSound = null;
            laserSound = null;
            rocketSound = null;
            buttonSound = null;
            crashSound = null;
            destroySound = null;
            shipBell = null;
        }
    }
}
