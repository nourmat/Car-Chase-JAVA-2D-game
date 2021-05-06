package Preliminary_Patch;

import Preliminary_Patch.*;
import Preliminary_Patch.BoatMode.Boats;
import Preliminary_Patch.CarMode.Cars;
import Preliminary_Patch.Collectables.CannonBall;
import Preliminary_Patch.Collectables.Coin;
import Preliminary_Patch.Collectables.Heart;
import Preliminary_Patch.Collectables.Rocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class AttGamePanel extends JPanel {

    /**
     * This is the Attack GamePanel
     * it will be in the middle of the screen and contain the main game in the attack mode
     * it controlles all the logic
     *  masterPanel
     */


    private MasterPanel masterPanel; //used to change score speed and lives
    private int terrineType; //this is used to know what kind of vehicle to create

    private JFrame frame;

    private String explosionSoundDirectroy = ".\\src\\Collectables\\explosion\\explosion.wav";
    private String explosionImageDirectory = ".\\src\\Collectables\\explosion\\";

    private int score = 0;
    private int lives = 3;
    private int rockets = 5; // number of rockets left
    private int level = 1;

    private int YVelocityInPixels = 10;// speed in y axis for the player

    private Random random = new Random();
    private ArrayList<Vehicle> vehiclesList  = new ArrayList<>(); //Vehicles that were created in the game.
    private ArrayList<Vehicle> rocketList = new ArrayList<>(); //this contains all rockets in the screen.
    private ImageIcon[] loadedVehicleImageIcons; //Icons from the modegame panel depends on the mode.
    private ImageIcon[] loadedbadVehicleImageIcons; //Icons from game mode panel represents bad vehicles

    private Timer timerUpDown;
    private Timer increasingMinSpeed;
    private Timer timer;
    private Timer timer2;
    private Timer vehicleTimerInc = new Timer(1,null);
    private Timer vehicleTimerDec = new Timer(1,null);

    private int scoreAccelerator = 50; //this is increased every level

    private Player player;

//    private int backgroundImageNumber = 0;
//    private int backgroundImages;// in mode panel
//    private Image currentBackground;
//    private int playingBackgroundSpeed; //in mode panel
//    private String backgroundImagesDirectory; // in mode panel

    private int creatingDelay; //in player
    private int movingDelay; // in player

    public AttGamePanel (Player player ,int terrineType ,ImageIcon[] loadedbadVehicleImageIcons,ImageIcon[] loadedVehicleImageIcons,
                         int backgroundImages ,int playingBackgroundSpeed, String backgroundImagesDirectory,MasterPanel masterPanel){
        this.player = player;
        this.loadedVehicleImageIcons = loadedVehicleImageIcons;
        this.loadedbadVehicleImageIcons = loadedbadVehicleImageIcons;
//        this.backgroundImagesDirectory = backgroundImagesDirectory;
//        this.backgroundImages = backgroundImages;
//        this.playingBackgroundSpeed = playingBackgroundSpeed;
        this.masterPanel = masterPanel;
        this.terrineType = terrineType;
        masterPanel.setRockets(rockets);
        masterPanel.setLevel(level);
        Vehicle.loadData(explosionImageDirectory,explosionSoundDirectroy);
        updateMovingSpeed();
        inti();
    }

    public void inti(){

        timer = new Timer(creatingDelay, new ActionListener() {

            Vehicle vehicle = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * this part is to keep hold of the obstacle until you find a specific space and draw it
                 * with out it it will change and find another object that fits
                 * which makes the chances of drawing different sizes
                 */

                if (vehicle == null) {
                    //TODO
                    /**determin what kind of object to draw (Heart, Extra Score ,Boats)*/
                    int vehicleType = random.nextInt(4);
                    if (vehicleType == 0)
                        vehicle = getAVehicle();
                    else if (vehicleType == 1){
                        vehicle = getBadVehicle();
                    }
                    else if (vehicleType == 2)
                        vehicle = getAHeart();
                    else if (vehicleType == 3)
                        vehicle = getACoin();
                }
                /**To not let the array empty when checking for (size -1 )*/
                if (vehiclesList.size() == 0){
                    vehiclesList.add(vehicle);
                    vehicle = null;
                }
                /** to make sure that no images are on each other and prevent out of boundaries exception*/
                else  if (vehiclesList.size() == 1 && (vehiclesList.get(vehiclesList.size() - 1).getX()) > (vehicle.getWidth() + player.getWidth() + player.getWidth() * 0.2)) {
                    vehiclesList.add(vehicle);
                    vehicle = null;
                }

                /**handeling out of boundries*/
                else if (vehiclesList.size() >= 2) {
                    /**two consecutive obstacles have the distance a player can go through then you can add an obstacle behind the last one and checks that the obstacle is not an island
                     * and that the obstacles won't appear on each other*/

                    if (vehiclesList.get(vehiclesList.size() - 2).getX() - (vehiclesList.get(vehiclesList.size() - 1).getX() + vehiclesList.get(vehiclesList.size() - 1).getWidth())
                            >= (player.getWidth() + (player.getWidth() * 0.3))
                            && vehiclesList.get(vehiclesList.size() - 1).getX() > vehicle.getWidth() + vehicle.getWidth() * 0.3
                            &&!vehicle.isIsland()){

                        vehiclesList.add(vehicle);
                        vehicle = null;
                    }
                    /**on the same y and not on each other and the obstacle is not an island*/
                    else if (vehiclesList.get(vehiclesList.size() - 1).getYLane() == vehicle.getYLane()
                            && vehiclesList.get(vehiclesList.size() - 1).getX() > vehicle.getWidth() + vehicle.getWidth() * 0.3
                            && !vehicle.isIsland()){

                        vehiclesList.add(vehicle);
                        vehicle = null;
                    }

                    /**if there is a space for adding an obstacle then add one*/
                    else if (vehiclesList.get(vehiclesList.size() - 1).getX() > player.getWidth() + player.getWidth() * 0.4 + vehicle.getWidth() ) {
                        vehiclesList.add(vehicle);
                        vehicle = null;
                    }
                }
            }
        }); //draw objects
        timer.start();

        timer2 = new Timer(movingDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outer:
                for (int i = 0 ;i < vehiclesList.size() ; i++){
                    /**this is to check if a rocket hit a vehicle or not*/
                    for(int j = 0;j < rocketList.size();j++){
                        if (checkCollisionGeneral(vehiclesList.get(i),rocketList.get(j))){
                            rocketList.remove(j);
                            vehiclesList.get(i).destroy();
                            rockets--;
                            continue outer;
                        }
                    }
                    vehiclesList.get(i).setX(vehiclesList.get(i).getX() + 10);
                    if (checkCollision(vehiclesList.get(i)) && !vehiclesList.get(i).isBeingDestroyed()){
                        if (!(vehiclesList.get(i) instanceof Coin) && !(vehiclesList.get(i) instanceof Heart))
                            vehiclesList.get(i).destroy();
                        else
                            vehiclesList.remove(i);
                        continue;
                    }
                    if (vehiclesList.get(i).getX() >= GameConst.getSCREENWIDTH()-20 || vehiclesList.get(i).isDestroyed())
                        vehiclesList.remove(i);
                }
                for(int j = 0 ; j < rocketList.size() ; j++){
                    rocketList.get(j).setX(rocketList.get(j).getX() - 30);
                    if (rocketList.get(j).getX() + rocketList.get(j).getWidth() < 0 )
                        rocketList.remove(j);
                }
                masterPanel.setScore(score);
                repaint();
            }
        }); //move and remove objects
        timer2.start();

        /**Decreasing the minimum speed of the player*/
        increasingMinSpeed = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level < 40) {
                    player.setMinVelocity(player.getMinVelocity() - player.getVehicleChangingVelocity());
                    {
                        if (!vehicleTimerInc.isRunning() && !vehicleTimerDec.isRunning())
                            updateMovingSpeed();
                    }
                    timer.setDelay(creatingDelay);
                    timer2.setDelay(movingDelay);
                    level++;
                    scoreAccelerator += 10;
                    masterPanel.setLevel(level);
                }
            }
        });
        increasingMinSpeed.start();
//        drawBackground();

        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (vehicleTimerInc.isRunning())
                        vehicleTimerInc.stop();
                    MovePlayerSmoothlyRight(timer,timer2);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
                    timerUpDown.stop();
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    throughRocket();

            }
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (timerUpDown != null)
                        timerUpDown.stop();
                    MovePlayerSmoothlyUp();
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    if (timerUpDown != null)
                        timerUpDown.stop();
                    MovePlayerSmoothlyDown();
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    if (!vehicleTimerInc.isRunning()) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (terrineType == GameConst.GRASSMODE || terrineType == GameConst.DESERTMODE){
                                    if (GameConst.motorSound != null)
                                        GameConst.motorSound.play();
                                }
                                else if (terrineType == GameConst.BOATMODE) {
                                    if (GameConst.piretSound != null) {
                                        GameConst.piretSound.play();
                                    }
                                }
                            }
                        });
                        thread.start();
                    }
                    if (vehicleTimerDec.isRunning())
                        vehicleTimerDec.stop();
                    MovePlayerSmoothlyLeft(timer,timer2);
                }
                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
        });
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (terrineType == GameConst.DESERTMODE)
        graphics.drawImage(new ImageIcon(".\\src\\CarMode\\Background\\road.png").getImage(),
                0,0,GameConst.getSCREENWIDTH(),GameConst.getGamePanelHEIGHT(),null);
        else if (terrineType == GameConst.BOATMODE)
        graphics.drawImage(new ImageIcon(".\\src\\BoatMode\\water background.png").getImage(),
                0,0,GameConst.getSCREENWIDTH(),GameConst.getGamePanelHEIGHT(),null);
        else if (terrineType == GameConst.GRASSMODE)graphics.drawImage(new ImageIcon(".\\src\\CarMode\\Background\\road2.png").getImage(),
                0,0,GameConst.getSCREENWIDTH(),GameConst.getGamePanelHEIGHT(),null);
//        graphics.drawImage(currentBackground, 0, 0, GameConst.getSCREENWIDTH(), GameConst.getGamePanelHEIGHT(), null);
//        if (backgroundImageNumber > backgroundImages)
//            backgroundImageNumber = 0;

        /** drawing backward to not make the waves go above the boat*/
        for (int i = vehiclesList.size()-1 ; i >= 0; i--){
            Vehicle s = vehiclesList.get(i);
            s.draw(graphics);
        }
        for (int i = 0 ; i < rocketList.size() ; i++) {
            rocketList.get(i).draw(graphics);
        }

        player.draw(graphics);
    }

    public boolean checkCollision(Vehicle vehicle) {
        int playerX = player.getX();
        int playerY = player.getY();
        int vehicleX = vehicle.getX();
        int vehicleY = vehicle.getY();
        /**if x or x + width of player is between the vehicle x and x+vehicle + width
         and y or y + height of player is between the vehicle y and y + vehicle + height*/
        if (((vehicleX < playerX && playerX < vehicleX + vehicle.getWidth())
                ||(vehicleX < playerX + player.getWidth() && playerX + player.getWidth() < vehicleX + vehicle.getWidth())
                ||(vehicleX < playerX + player.getWidth() / 2 && playerX + player.getWidth() / 2 < vehicleX + vehicle.getWidth() ))
                && ((vehicleY < playerY && playerY < vehicleY + vehicle.getHeight())
                || (vehicleY < playerY + player.getHeight() && playerY + player.getHeight() < vehicleY + vehicle.getHeight())
                || (vehicleY < playerY + player.getHeight() / 2 && playerY + player.getHeight() / 2 < vehicleY + vehicle.getHeight() ))){
            if ((vehicle instanceof Cars || vehicle instanceof Boats) && !vehicle.isBeingDestroyed() && vehicle.isBad()) {
                lives--;
                masterPanel.decrementLives();
                timerUpDown.stop(); //stops moving car up and down
                if (lives == 0) {
                    if (GameConst.destroySound != null)
                        GameConst.destroySound.play();
//                    JOptionPane.showMessageDialog(null, "you lost");
                    JLabel scoreLabel = new JLabel("You Lose \n Your Score is "+ score + "  Please Enter Your Name");
                    String name = JOptionPane.showInputDialog(scoreLabel);
                    if (name == null)
                        closeEverything();
                    else{
                        HighScores.addScore(score,name);
                        HighScores.saveScore();
                    }
                    closeEverything();
                }
                if (GameConst.crashSound != null)
                    GameConst.crashSound.play();
            }
            else if (vehicle instanceof Heart){
                if (lives < 3){
                    lives = lives + 1;
                    masterPanel.incrementLives();
                }
                if (GameConst.coidSound != null)
                    GameConst.coidSound.play();
            }
            else if (vehicle instanceof Coin){
                score += 100;
                masterPanel.setScore(score);
                if (GameConst.coidSound != null)
                    GameConst.coidSound.play();
            }
            else  if ((vehicle instanceof Cars || vehicle instanceof Boats) && !vehicle.isBeingDestroyed()){
                score+=scoreAccelerator;
                timerUpDown.stop(); //stops moving car up and down
                if (GameConst.crashSound != null)
                    GameConst.crashSound.play();
            }
            return true;
        }
        return false;
    }
    /** this function takes to Vehcel and checks if a collision happended between them*/
    public boolean checkCollisionGeneral(Vehicle vehicle , Vehicle rocket) {
        int playerX = rocket.getX();
        int playerY = rocket.getY();
        int vehicleX = vehicle.getX();
        int vehicleY = vehicle.getY();
        /**if x or x + width of player is between the vehicle x and x+vehicle + width
         and y or y + height of player is between the vehicle y and y + vehicle + height*/
        if ((vehicleX < playerX && playerX < vehicleX + vehicle.getWidth()
                ||(vehicleX < playerX + rocket.getWidth() && playerX + rocket.getWidth() < vehicleX + vehicle.getWidth())
                ||(vehicleX < playerX + rocket.getWidth() / 2 && playerX + rocket.getWidth() / 2 < vehicleX + vehicle.getWidth() ))
                && ((vehicleY < playerY && playerY < vehicleY + vehicle.getHeight())
                || (vehicleY < playerY + rocket.getHeight() && playerY + rocket.getHeight() < vehicleY + vehicle.getHeight())
                || (vehicleY < playerY + rocket.getHeight() / 2 && playerY + rocket.getHeight() / 2 < vehicleY + vehicle.getHeight() ))){
            return true;
        }
        return false;
    }

    public void MovePlayerSmoothlyDown() {
        timerUpDown = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getY() + player.getHeight() + player.getHeight() * 0.2 <= GameConst.getGamePanelHEIGHT()) {//at the bottom
                    player.setY(player.getY() + YVelocityInPixels);
                    repaint();
                }
            }
        });
        timerUpDown.start();
    }

    public void MovePlayerSmoothlyUp() {
        //at the bottom
        timerUpDown= new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getY() -  YVelocityInPixels >= 0) {
                    player.setY(player.getY() - YVelocityInPixels);
                    repaint();
                }
            }
        });
        timerUpDown.start();
    }

    public void MovePlayerSmoothlyLeft(Timer timer , Timer timer2) {
        if (!vehicleTimerInc.isRunning()) {
            vehicleTimerInc = new Timer(player.getMinVelocity(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (movingDelay - player.getVehicleChangingVelocity() >= player.getMaxVelocity()) { //Ex 100-2 > 50 Delay analogy
                        movingDelay -= player.getVehicleChangingVelocity(); //decrease the delay 100 - 2
                        creatingDelay -= 35;//2000 - (35 * (100-Max(50) / Changingvel(2)) = 1125 MS
                        timer.setDelay(creatingDelay);
                        timer2.setDelay(movingDelay);
                        //TODO speed
                        player.increaseSpeed();
                        masterPanel.setSpeed(player.getSpeed());
                    }
                }
            });
            vehicleTimerInc.start();
        }
    }

    public void MovePlayerSmoothlyRight(Timer timer, Timer timer2) {
        if (!vehicleTimerDec.isRunning()) {
            vehicleTimerDec = new Timer(player.getMinVelocity(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (movingDelay < player.getMinVelocity()){ //Ex 50 <= 100 delay analogy
                        movingDelay += player.getVehicleChangingVelocity(); //decrease the delay 100 - 2
                        creatingDelay += 17*player.getVehicleChangingVelocity();
                        timer.setDelay(creatingDelay);
                        timer2.setDelay(movingDelay);
                        //TODO speed
                        player.decrementSpeed();
                        masterPanel.setSpeed(player.getSpeed());
                    }
                }
            });
            vehicleTimerDec.start();
        }
    }

    int islandCounter = 0; //to make sure not to much islands appear randomlly

    public Vehicle getAVehicle() {

        Vehicle vehicle = null;
        if (terrineType == GameConst.BOATMODE) {
            vehicle = new Boats(0, 0, loadedVehicleImageIcons[random.nextInt(loadedVehicleImageIcons.length)]);
        }
        else if (terrineType == GameConst.DESERTMODE || terrineType == GameConst.GRASSMODE)
            vehicle = new Cars(0, 0, loadedVehicleImageIcons[random.nextInt(loadedVehicleImageIcons.length)]);

        vehicle.setY(random.nextInt(GameConst.getGamePanelHEIGHT() - vehicle.getHeight()));
        /**
         this part is to check and not to draw too much islands unless some boats are drawn first
         so it checks of islandCounter = 0
         if that is true it draw the island and adds 1
         else it adds one and keep looping until the obstacle is a boat
         it doesn't draw another boat unless at least five boats are drawn in the middle
         */
        if (vehicle.isIsland()) {
            if (islandCounter == 0) {
                vehicle.setY(random.nextInt(2) * GameConst.getLaneHalfHeightDistance() * 2);
                vehicle.setBad(true);
                islandCounter++;
            } else {
                do {
                    if (terrineType == GameConst.BOATMODE)
                        vehicle = new Boats(0, random.nextInt(3), loadedVehicleImageIcons[random.nextInt(loadedVehicleImageIcons.length)]);
                    else if (terrineType == GameConst.DESERTMODE || terrineType == GameConst.GRASSMODE)
                        vehicle = new Cars(0, random.nextInt(3), loadedVehicleImageIcons[random.nextInt(loadedVehicleImageIcons.length)]);
                    islandCounter++;
                } while (vehicle.isIsland());
            }
        } else if (islandCounter > 0) {
            islandCounter++;
            islandCounter = islandCounter > 5 ? 0 : islandCounter;
        }
        return vehicle;
    }

    int HeartCounter = 0; //to make sure not to much Hearts appear randomlly

    public Vehicle getAHeart(){
        Vehicle vehicle;
        if (HeartCounter == 0) {
            vehicle = new Heart(0, 0);
            vehicle.setY(random.nextInt(GameConst.getGamePanelHEIGHT()) - vehicle.getHeight());
            HeartCounter ++;
        }
        else {
            vehicle = getAVehicle();
            HeartCounter++;
            HeartCounter = HeartCounter > 15 ? 0:HeartCounter;
        }
        return vehicle;
    }

    public Vehicle getBadVehicle(){
        Vehicle vehicle ;
        if (terrineType == GameConst.BOATMODE)
            vehicle = new Boats(0, 0, loadedbadVehicleImageIcons[random.nextInt(loadedbadVehicleImageIcons.length)]);
        else
            vehicle = new Cars(0,0,loadedbadVehicleImageIcons[random.nextInt(loadedbadVehicleImageIcons.length)]);
        vehicle.setBad(true);
        vehicle.setY(random.nextInt(GameConst.getGamePanelHEIGHT() - vehicle.getHeight()));
        return vehicle;
    }

    public void throughRocket() {
        if (rockets > 0) {
            if (terrineType == GameConst.DESERTMODE || terrineType == GameConst.GRASSMODE) {
                Vehicle rocket = new Rocket(player.getX(), player.getY() + (player.getHeight() / 2));
                rocketList.add(rocket);
                rockets--;
                masterPanel.setRockets(rockets);
                if (GameConst.rocketSound != null)
                    GameConst.rocketSound.play();
            }
            else if (terrineType == GameConst.BOATMODE){
                Vehicle cannon = new CannonBall(player.getX(), player.getY() + (player.getHeight() / 2));
                rocketList.add(cannon);
                rockets--;
                masterPanel.setRockets(rockets);
                if (GameConst.cannonSound != null)
                    GameConst.cannonSound.play();
            }
        }
    }

    public void updateMovingSpeed(){
        movingDelay = player.getMinVelocity(); //Ex. 100 Max is 10 delay analogy
        creatingDelay = (player.getMinVelocity() * 15);
    }

    int CoinCounter = 0;

    public Vehicle getACoin(){
        Vehicle vehicle;
        if (CoinCounter == 0) {
            vehicle = new Coin(0, 0);
            vehicle.setY(random.nextInt(GameConst.getGamePanelHEIGHT()) - vehicle.getHeight());
            CoinCounter ++;
        }
        else {
            vehicle = getAVehicle();
            CoinCounter++;
            CoinCounter = CoinCounter > 10 ? 0:CoinCounter;
        }
        return vehicle;
    }

     public void setFrame(JFrame frame){
        this.frame = frame;
     }

     public void closeEverything() {
        if (GameConst.carBackgroundMusic != null && GameConst.boatBackgroundMusic != null && GameConst.bmg != null) {
            GameConst.carBackgroundMusic.stop();
            GameConst.boatBackgroundMusic.stop();
            GameConst.bmg.play();
        }
         timerUpDown.stop();
         vehicleTimerDec.stop();
         vehicleTimerInc.stop();
         timer.stop();
         timer2.stop();
         increasingMinSpeed.stop();
         frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
     }
//    public void drawBackground(){
//        Timer timer = new Timer(playingBackgroundSpeed, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentBackground = new ImageIcon(backgroundImagesDirectory + backgroundImageNumber + ".png").getImage();
//                gamePanel.repaint();
//                backgroundImageNumber++;
//            }
//        });
//        timer.start();
//    }
//
//    public boolean checkCollision(Vehicle obstacles) ,{
//        if (obstacles.isIsland()) {
//            if ((player.getX() + 20 <= obstacles.getX() + obstacles.getWidth()) &&
//                    ((obstacles.getYLane() == player.getYLane()) || (obstacles.getYLane() + 1 == player.getYLane()))) {
//                lives--;
//                JOptionPane.showMessageDialog(null,"You crashed Lives Left "+lives);
//                masterPanel.setLives(lives);
//                return true;
//            }
//        }
//        else {
//                if (player.getX() + 20 <= obstacles.getX() + obstacles.getWidth() && player.getYLane() == obstacles.getYLane()){
//                    lives--;
//                    JOptionPane.showMessageDialog(null,"You crashed Lives Left "+lives);
//                    masterPanel.setLives(lives);
//                    return true;
//            }
//        }
//        return false;
//    }

//    public void MovePlayerSmoothlyDown() {
//        if (player.getY() + GameConst.getLaneHalfHeightDistance() * 2 < GameConst.getGamePanelHEIGHT()) {//at the bottom
//           if (!playerTimer.isRunning()) {   // this checking is because sometimes when pressing down button twice fast the vehicle
//                                    // gets out of bounds because error in calculating Y Lane as bu default the value is grounded
//                                    //and to not control the car before it gets to its lane
//               playerTimer = new Timer(1, new ActionListener() {
//                    int index = 0;
//                    int y = player.getYLane();
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        int yp = player.getY();
//                        player.setY(yp + YVelocityInPixels);
//                        gamePanel.repaint();
//                        index++;
//                        if (index >= Math.abs(player.calcYLane(y + 1) - player.calcYLane(y))/YVelocityInPixels)
//                            ((Timer) e.getSource()).stop();
//                    }
//                });
//               playerTimer.start();
//            }
//        }
//    }
//
//    public void MovePlayerSmoothlyUp() {
//        if (player.getYLane() != 0) { //at the Top
//            if (!playerTimer.isRunning()) {
//                playerTimer = new Timer(1, new ActionListener() {
//                    int index = 0;
//                    int y = player.getYLane();
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        int yp = player.getY();
//                        player.setY(yp - YVelocityInPixels);
//                        gamePanel.repaint();
//                        index++;
//                        if (index >= Math.abs(player.calcYLane(y - 1) - player.calcYLane(y))/YVelocityInPixels)
//                            ((Timer) e.getSource()).stop();
//                    }
//                });
//                playerTimer.start();
//            }
//        }
//    }
}
