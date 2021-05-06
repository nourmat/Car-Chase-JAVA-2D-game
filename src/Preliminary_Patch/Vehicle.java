package Preliminary_Patch;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Vehicle {

    /**
     * all objects in the game extends this class
     * it contains the image ,x ,y ,speed,is destroyed or not
     */

    //TODO speed
    private int MaxVelocity = 5;
    private int MinVelocity = 100; // bigger as it will be put in delay
    private int vehicleChangingVelocity = 4;
    private int speed;

    private static int GIFSPEED = 100;
    private static int DESTRUCTOINDELAY = 750;

    private int width;
    private  boolean isDestroyed = false;
    private int height;
    private int x;
    private int y;
    private Image image;

    private Boolean isBad = false;

    //the following was added by mohamed it is responsible for the gif and audio played at destruction
    private static Clip clip = null;
    //TODO this may result in problems when trying to play same file more than one time
    private static AudioInputStream audioInputStream;

    private static ArrayList<Image> destructionImageParts = null;
    private static boolean dataLoaded = false;

    private int gifIndex = 0;
    private boolean isBeingDestroyed = false;

    public Vehicle(int x, int y,ImageIcon imageIcon) {
//        this.x = x * GameConst.getGRIDSIZE();               //it can take from 0 to 100--> out of the panel
//        this.y = y * (GameConst.getLaneHalfHeightDistance() * 2)
//                + GameConst.getLaneHalfHeightDistance(); //place in the middle of the lane (0 or 1 or 2)
        this.x = x;
        this.y = y;
        image = imageIcon.getImage();
        width = imageIcon.getIconWidth() * GameConst.getSCREENWIDTH() / 1600; //These numbers were the reference of getting the printing factor in draw function
        height = imageIcon.getIconHeight() * GameConst.getSCREENHEIGHT() / 900; // and all of the objects sizes in px were made relative to these numbers

        String imageNo = imageIcon.toString();
        imageNo = imageNo.substring(imageNo.length()-5,imageNo.length()-4);
        try {
            //big m is fast
            int m = Integer.parseInt(imageNo) + 1;
            MinVelocity = 100 - (m * 2);
            MinVelocity = MinVelocity > 100 ? 100 : MinVelocity;
            MaxVelocity = 20 - (m * 2);
            MaxVelocity = MaxVelocity<5 ? 5 : MaxVelocity;
            vehicleChangingVelocity = (m-3) >= 2 ? m : 2;
        }catch (Exception e){}

        speed = (MinVelocity - 30)/vehicleChangingVelocity;

    }

    /**
     *
     * this functions fills the array list of the gif parts
     * @param FolderName the name of the folder where the spreadsheet of the gif exist
     */
    static private void loadDestructionGif(String FolderName) {
        destructionImageParts = new ArrayList<>();
        File file;

        int index = 0;
        while(true){
            try{
                file = new File(FolderName + index++ + ".png");

                if(!file.exists())
                    break;

                destructionImageParts.add(ImageIO.read(file));
            }
            catch(IOException e){
                System.err.println("failed to load some images for destruction");
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * this functions updates the image of the car in case it is being destroyed
     */
    private void updateDestructionImage(){
        if (gifIndex >= destructionImageParts.size())
            gifIndex = 0;
        image = destructionImageParts.get(gifIndex++).getScaledInstance(width,height,Image.SCALE_FAST);
    }

    /**
     * loads important data for destruction of any vehicle into memory
     * @param destructionImageFolderName the name of the image file
     * @param destructionAudioFileName the name of the audio file
     */

    public static void loadData(String destructionImageFolderName, String destructionAudioFileName){
        try {

            loadDestructionGif(destructionImageFolderName);

            audioInputStream = AudioSystem.getAudioInputStream(new File(destructionAudioFileName));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch(Exception e){
            System.err.println("failed to load destruction data in vehicle class");
            e.printStackTrace();
            return;
        }
        if (!destructionImageParts.isEmpty())
            dataLoaded = true;
        else
            System.err.println("Can't find images");
    }


    public boolean isBeingDestroyed() {
        return isBeingDestroyed;
    }

    /**
     * destroys the vehicle and draw the destruction animation
     * and prepare it to be removed
     */
    public void destroy(){
        isBeingDestroyed = true;
        if(dataLoaded){
            clip.setFramePosition(0);
            clip.start();

            javax.swing.Timer imageChangerTimer = new javax.swing.Timer(GIFSPEED, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateDestructionImage();
                }
            });
            imageChangerTimer.start();
            Timer destructionTimer = new Timer();
            destructionTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isDestroyed = true;
                    destructionTimer.cancel();
                }
            }, DESTRUCTOINDELAY);
        }
        else
            isDestroyed = true;
    }


    public Boolean isBad() {
        return isBad;
    }

    public void setBad(Boolean bad) {
        isBad = bad;
    }

    /**
     *
     * @return true is the vehicle has already been destroyed and needs to be removed
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setYLane(int y) {
        this.y = y * (GameConst.getLaneHalfHeightDistance() * 2) + GameConst.getLaneHalfHeightDistance();
        //place in the middle of the lane (0 or 1 or 2)
    }

    /**
     * it calculates which lane the object is at consedring that the screen is spited into three horizontal lanes
     * @return a number representing the lane 1,2 or 3
     */

    public int getYLane(){
        if (0 <= y && y < GameConst.getLaneHalfHeightDistance() * 2)
            return 0;
        else if (GameConst.getLaneHalfHeightDistance() * 2 <= y && y < GameConst.getLaneHalfHeightDistance() * 4)
            return 1;
        else// if (GameConst.getLaneHalfHeightDistance() * 4 <= y || y < GameConst.getLaneHalfHeightDistance() * 6)
            return 2;
    }

    public int getXLane(){
        return x / GameConst.getGRIDSIZE();
    }

    public int calcYLane(int y){
        return y * (GameConst.getLaneHalfHeightDistance() * 2) + GameConst.getLaneHalfHeightDistance();
    }

    public void setXLane(int x) {
        this.x = x * GameConst.getGRIDSIZE();
    }

    public Image getImage() {
        return image;
    }

    public boolean isIsland(){
        return false;
    }

    public abstract void draw(Graphics graphics);

    public  int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMinVelocity(int minVelocity) {
        MinVelocity = minVelocity;
    }

    public int getMaxVelocity() {

        return MaxVelocity;
    }

    public int getMinVelocity() {
        return MinVelocity;
    }

    public int getVehicleChangingVelocity() {
        return vehicleChangingVelocity;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * increase the speed variable in vehicle to draw the speed bar
     */
    public void increaseSpeed(){
        speed += vehicleChangingVelocity *  3;

    }
    /**
    decrease the speed variable in vehicle to draw the speed bar
     */
    public void decrementSpeed(){
        speed -= vehicleChangingVelocity * 3;
    }
}
