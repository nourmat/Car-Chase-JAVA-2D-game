package Preliminary_Patch;

import Preliminary_Patch.Recored.LivePanel;
import Preliminary_Patch.Recored.ScorePanel;
import Preliminary_Patch.Recored.SpeedPanel;

import javax.swing.*;
import java.awt.*;

/**
 * This is MainPanel
 * it containes
 * the game panel and the score
 * it also controlles the drawing of the Boarder and there moving
 */

public abstract class MasterPanel extends JPanel{

    /**
     * This class hold the main game panel
     * and the moving side roads panel
     * draws the score lives rockets speed and level
     *
     * depending on the Game mode
     */

    private sideRoadPanel upperStreetBoarderPanel;
    private sideRoadPanel lowerStreetBoarderPanel;

    private LivePanel lives = new LivePanel(".\\src\\ScoreLiveIcons\\like.png",".\\src\\ScoreLiveIcons\\heart.png");
    private ScorePanel score = new ScorePanel(".\\src\\ScoreLiveIcons\\score.gif");
    private ScorePanel level = new ScorePanel(".\\src\\ScoreLiveIcons\\level.gif");
    private ScorePanel rockets = new ScorePanel(".\\src\\ScoreLiveIcons\\rockets.gif");
    private SpeedPanel speed = new SpeedPanel();

    private JFrame frame;

    private int terrineType;
    private int modeType;

    private AttGamePanel attGamePanel; //Attacker
    private DefGamePanel defGamePanel;//Deffender

    public MasterPanel(Player player ,int modeType,int terrineType ,ImageIcon[] loadedbadVehicleImageIcons,ImageIcon[] loadedVehicleImageIcons, int backgroundImages,int playingBackgroundSpeed,String backgroundImagesDirectory , String sideRoadDirectory,int sideBackgroundimages) {

        this.terrineType =  terrineType;
        this.modeType = modeType;

        upperStreetBoarderPanel = new sideRoadPanel(sideRoadDirectory,sideBackgroundimages);
        lowerStreetBoarderPanel = new sideRoadPanel(sideRoadDirectory,sideBackgroundimages);

        if (modeType == GameConst.ATTACKMODE)
            attGamePanel =  new AttGamePanel(player , terrineType,loadedbadVehicleImageIcons,loadedVehicleImageIcons , backgroundImages,playingBackgroundSpeed,backgroundImagesDirectory,this);
        else if (modeType == GameConst.DEFENCEMOED)
            defGamePanel =  new DefGamePanel(player , terrineType,loadedVehicleImageIcons , backgroundImages,playingBackgroundSpeed,backgroundImagesDirectory,this);
        inti();
    }

    public void inti()
    {
        this.setLayout(new BorderLayout());
        //Setting sizes
        upperStreetBoarderPanel.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH(),GameConst.getBorderSize()));
        lowerStreetBoarderPanel.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH(),GameConst.getBorderSize()));
        if (modeType == GameConst.ATTACKMODE){
            attGamePanel.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH(),GameConst.getGamePanelHEIGHT()));
        }
        else if (modeType == GameConst.DEFENCEMOED) {
            defGamePanel.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH(), GameConst.getGamePanelHEIGHT()));
        }

        upperStreetBoarderPanel.setLayout(new BorderLayout());
        lowerStreetBoarderPanel.setLayout(new BorderLayout());
        upperStreetBoarderPanel.add(score,BorderLayout.EAST);
        upperStreetBoarderPanel.add(rockets,BorderLayout.CENTER);
        upperStreetBoarderPanel.add(lives,BorderLayout.WEST);
        lowerStreetBoarderPanel.add(level,BorderLayout.WEST);
        lowerStreetBoarderPanel.add(speed,BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(upperStreetBoarderPanel,BorderLayout.NORTH);
        if (modeType == GameConst.ATTACKMODE)
            this.add(attGamePanel,BorderLayout.CENTER);
        else if (modeType == GameConst.DEFENCEMOED)
            this.add(defGamePanel,BorderLayout.CENTER);
        this.add(lowerStreetBoarderPanel,BorderLayout.SOUTH);
    }

    public void setFrame(JFrame frame) {
        if (modeType == GameConst.ATTACKMODE){
            attGamePanel.setFrame(frame);
        }
        else if (modeType == GameConst.DEFENCEMOED) {
            defGamePanel.setFrame(frame);
        }
        this.frame = frame;
    }

    public void setRockets(int rockets){
        this.rockets.setScore(rockets);
    }

    public void setLevel(int level){
        this.level.setScore(level);
    }

    public void setScore(int score) {
        this.score.setScore(score);
    }

    public  void decrementLives() {
        lives.decrementLives();
    }

    public  void incrementLives() {
        lives.incrementLives();
    }

    public void setSpeed(int rowSpeed){
        speed.setSpeed(rowSpeed);
    }

}
