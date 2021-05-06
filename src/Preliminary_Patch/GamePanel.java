//package Preliminary_Patch;
//
///**
// * This is the game panel
// * it will be in the middle of the screen and contain the main game
// * it controlles all the logic
// */
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class GamePanel extends JPanel {
//
//    private MasterPanel masterPanel; //used to change score speed and lives
//
//    private int modeType; //this is used to know what kind of vehicle to create
//
//    private int score = 0;
//    private int lives = 3;
//
//    private int YVelocityInPixels = 10;// speed in y axis for the player
//
//    private Random random = new Random();
//    private ArrayList<Vehicle> vehiclesList  = new ArrayList<>(); //Vehicles that were created in the game.
//    private ImageIcon[] loadedVehicleImageIcons; //Icons from the modegame panel depends on the mode.
//    private Player player;
//    private GamePanel gamePanel = this;
//
////    private int backgroundImageNumber = 0;
////    private int backgroundImages;// in mode panel
////    private Image currentBackground;
//
//    private int creatingDelay; //in player
//    private int movingDelay; // in player
////    private int playingBackgroundSpeed; //in mode panel
////    private String backgroundImagesDirectory; // in mode panel
//
////    private Timer playerTimer = new Timer(1,null); //used to smoothly move the player
//    private Timer vehicleTimerInc = new Timer(1,null);
//    private Timer vehicleTimerDec = new Timer(1,null);
//
//    public GamePanel(Player player ,int modeType ,ImageIcon[] loadedVehicleImageIcons,
//                     int backgroundImages ,int playingBackgroundSpeed, String backgroundImagesDirectory,MasterPanel masterPanel){
//        this.player = player;
//        this.loadedVehicleImageIcons = loadedVehicleImageIcons;
////        this.backgroundImagesDirectory = backgroundImagesDirectory;
////        this.backgroundImages = backgroundImages;
////        this.playingBackgroundSpeed = playingBackgroundSpeed;
//        this.masterPanel = masterPanel;
//        this.modeType = modeType;
//        movingDelay = player.getMinVelocity(); //Ex. 100 Max is 10 delay analogy
//        creatingDelay = (player.getMinVelocity() * 20);
//        inti();
//    }
//
//    public void inti(){
//
//        Timer timer = new Timer(creatingDelay, new ActionListener() {
//
//            int islandCounter = 0; //to make sure not to much islands appear randomlly
//            Vehicle vehicle = null;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                /**
//                 * this part is to keep hold of the obstacle until you find a specific space and draw it
//                 * with out it it will change and find another object that fits
//                 * which makes the chances of drawing different sizes
//                 */
//                if (vehicle == null){
//                    if (modeType == GameConst.BOATMODE) {
//                        vehicle = new Boats(0, random.nextInt(3), loadedVehicleImageIcons[random.nextInt(loadedVehicleImageIcons.length)]);
//                        vehicle.setX(0);
//                        vehicle.setY(random.nextInt(GameConst.getGamePanelHEIGHT() - vehicle.getHeight()));
//                    }
//                    else if (modeType == GameConst.GRASSMODE){
//
//                    }
//                    else if (modeType == GameConst.DESERTMODE){
//
//                    }
//                    else if (modeType == GameConst.CHIECKENMODE){
//
//                    }
//                }
//                /**
//                    this part is to check and not to draw too much islands unless some boats are drawn first
//                 so it checks of islandCounter = 0
//                 if that is true it draw the island and adds 1
//                 else it adds one and keep looping until the obstacle is a boat
//                 it doesn't draw another boat unless at least five boats are drawn in the middle
//                 */
////                System.out.println(islandCounter);
//                if(vehicle.isIsland()){
//
////                    if (islandCounter == 0) {
//                    vehicle.setY(random.nextInt(2) * GameConst.getLaneHalfHeightDistance() * 2);
////                        islandCounter++;
////                    }
////                    else {
////                        do {
////                            obstacles = new Obstacles(0, random.nextInt(3), loadedObsticaleImageIcons[random.nextInt(loadedObsticaleImageIcons.length)]);
////                            islandCounter++;
////                        }while (obstacles.isIsland());
////
//                }
////                else if (islandCounter > 0){
////                    islandCounter++;
////                    islandCounter = islandCounter > 1 ? 0 : islandCounter;
////                }
//
//                //To not let the array empty when checking for (size -1 )
//                if (vehiclesList.size() == 0){
//                    vehiclesList.add(vehicle);
//                    vehicle = null;
//                }
//                /** to make sure that no images are on each other and prevent out of boundaries exception*/
//                else  if (vehiclesList.size() == 1 && (vehiclesList.get(vehiclesList.size() - 1).getX()) > (vehicle.getWidth() + player.getWidth() + player.getWidth() * 0.2)) {
//                    vehiclesList.add(vehicle);
//                    vehicle = null;
//                }
//
//                /**handeling out of boundries*/
//                else if (vehiclesList.size() >= 2) {
//                    /**two consecutive obstacles have the distance a player can go through then you can add an obstacle behind the last one and checks that the obstacle is not an island
//                     * and that the obstacles won't appear on each other*/
//
//                    if (vehiclesList.get(vehiclesList.size() - 2).getX() - (vehiclesList.get(vehiclesList.size() - 1).getX() + vehiclesList.get(vehiclesList.size() - 1).getWidth())
//                            >= (player.getWidth() + (player.getWidth() * 0.3))
//                            && vehiclesList.get(vehiclesList.size() - 1).getX() > vehicle.getWidth() + vehicle.getWidth() * 0.3
//                            &&!vehicle.isIsland()){
//
//                        vehiclesList.add(vehicle);
//                        vehicle = null;
//                            System.out.println("first");
//                    }
//                    /**on the same y and not on each other and the obstacle is not an island*/
//                    else if (vehiclesList.get(vehiclesList.size() - 1).getYLane() == vehicle.getYLane()
//                            && vehiclesList.get(vehiclesList.size() - 1).getX() > vehicle.getWidth() + vehicle.getWidth() * 0.3
//                            && !vehicle.isIsland()){
//
//                        vehiclesList.add(vehicle);
//                        vehicle = null;
//                        System.out.println("Second");
//                    }
//
//                    /**if there is a space for adding an obstacle then add one*/
//                    else if (vehiclesList.get(vehiclesList.size() - 1).getX() > player.getWidth() + player.getWidth() * 0.4 + vehicle.getWidth() ) {
//                        vehiclesList.add(vehicle);
//                        vehicle = null;
//                        System.out.println("third");
//                    }
//                }
//            }
//        }); //draw objects
//        timer.start();
//
//        Timer timer2 = new Timer(movingDelay, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                for (int i = 0 ;i < vehiclesList.size() ; i++){
//                    vehiclesList.get(i).setX(vehiclesList.get(i).getX() + 10);
//                    if (checkCollision(vehiclesList.get(i))){
//                        vehiclesList.remove(i);
//                        continue;
//                    }
//                    if (vehiclesList.get(i).getX() >= GameConst.getSCREENWIDTH()-20){
//                        vehiclesList.remove(i);
//                    }
//                }
//                score++;
//                masterPanel.setScoreLabel(score);
//                gamePanel.repaint();
//            }
//        }); //move and remove objects
//        timer2.start();
//
////        drawBackground();
//
//        this.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    if (vehicleTimerInc.isRunning())
//                        vehicleTimerInc.stop();
//                    MovePlayerSmoothlyRight(timer,timer2);
//                }
//            }
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    MovePlayerSmoothlyUp();
//                }
//                else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//                    MovePlayerSmoothlyDown();
//                }
//                else if (e.getKeyCode() == KeyEvent.VK_LEFT){
//                    if (vehicleTimerDec.isRunning())
//                        vehicleTimerDec.stop();
//                    MovePlayerSmoothlyLeft(timer,timer2);
//                }
//                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
//                    System.exit(0);
//                }
//            }
//        });
//        this.setFocusable(true);
//    }
//
//    @Override
//    public void paint(Graphics graphics) {
//        super.paint(graphics);
//
//        graphics.drawImage(new ImageIcon(".\\src\\BoatMode\\water background.png").getImage(),
//                0,0,GameConst.getSCREENWIDTH(),GameConst.getSCREENHEIGHT(),null);
////        graphics.drawImage(currentBackground, 0, 0, GameConst.getSCREENWIDTH(), GameConst.getGamePanelHEIGHT(), null);
////        if (backgroundImageNumber > backgroundImages)
////            backgroundImageNumber = 0;
//
//        for (Vehicle s : vehiclesList){
//            graphics.fillRect(s.getX(),s.getY(),20,20);
//            s.draw(graphics);
//        }
//        graphics.fillRect(player.getX(),player.getY(),20,20);
//        player.draw(graphics);
//    }
//
////    public void drawBackground(){
////        Timer timer = new Timer(playingBackgroundSpeed, new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                currentBackground = new ImageIcon(backgroundImagesDirectory + backgroundImageNumber + ".png").getImage();
////                gamePanel.repaint();
////                backgroundImageNumber++;
////            }
////        });
////        timer.start();
////    }
//
//    public boolean checkCollision(Vehicle vehicle) {
//        int playerX = player.getX();
//        int playerY = player.getY();
//        int vehicleX = vehicle.getX();
//        int vehicleY = vehicle.getY();
//        //if x or x + width of player is between the vehicle x and x+vehicle + width
//        //and y or y + height of player is between the vehicle y and y + vehicle + height
//        if ((vehicleX < playerX && playerX < vehicleX + vehicle.getWidth())
//                && ((vehicleY < playerY && playerY < vehicleY + vehicle.getHeight())
//                || (vehicleY < playerY + player.getHeight() && playerY + player.getHeight() < vehicleY + vehicle.getHeight())
//                || (vehicleY < playerY + player.getHeight() / 2 && playerY + player.getHeight() / 2 < vehicleY + vehicle.getHeight() ))){
//            lives--;
//            masterPanel.setLives(lives);
//            JOptionPane.showMessageDialog(null,"you Hit Your live is " + lives);
//            return true;
//        }
//        return false;
//    }
//
//    public void MovePlayerSmoothlyDown() {
//        if (player.getY() + player.getHeight() * 2 <= GameConst.getGamePanelHEIGHT()) {//at the bottom
//            player.setY(player.getY() + YVelocityInPixels);
////            System.out.println(player.getHeight());
////            System.out.println(player.getY());
////            System.out.println(player.getY() + player.getHeight() + YVelocityInPixels <= GameConst.getGamePanelHEIGHT());
//        }
//    }
//
//    public void MovePlayerSmoothlyUp() {
//        if (player.getY() -  YVelocityInPixels >= 0) {//at the bottom
//            player.setY(player.getY() - YVelocityInPixels);
//        }
//    }
//
//    public void MovePlayerSmoothlyLeft(Timer timer , Timer timer2) {
//        if (!vehicleTimerInc.isRunning()) {
//            vehicleTimerInc = new Timer(player.getMinVelocity(), new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (movingDelay - player.getVehicleChangingVelocity() >= player.getMaxVelocity()) { //Ex 100-2 > 50 Delay analogy
//                        movingDelay -= player.getVehicleChangingVelocity(); //decrease the delay 100 - 2
//                        creatingDelay -= 35;//2000 - (35 * (100-Max(50) / Changingvel(2)) = 1125 MS
//                        timer.setDelay(creatingDelay);
//                        timer2.setDelay(movingDelay);
//                        System.out.println("creatingDelay = " + creatingDelay);
//                        System.out.println("movingDelay = " + movingDelay);
//                    }
//                }
//            });
//            vehicleTimerInc.start();
//        }
//    }
//
//    public void MovePlayerSmoothlyRight(Timer timer, Timer timer2) {
//        if (!vehicleTimerDec.isRunning()) {
//            vehicleTimerDec = new Timer(player.getMinVelocity(), new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (movingDelay < player.getMinVelocity()){ //Ex 50 <= 100 delay analogy
//                        movingDelay += player.getVehicleChangingVelocity(); //decrease the delay 100 - 2
//                        creatingDelay += 17*player.getVehicleChangingVelocity();
//                        timer.setDelay(creatingDelay);
//                        timer2.setDelay(movingDelay);
//                        System.out.println("creatingDelay = " + creatingDelay);
//                        System.out.println("movingDelay = " + movingDelay);
//                    }
//                }
//            });
//            vehicleTimerDec.start();
//        }
//    }
//
////    public boolean checkCollision(Vehicle obstacles) ,{
////        if (obstacles.isIsland()) {
////            if ((player.getX() + 20 <= obstacles.getX() + obstacles.getWidth()) &&
////                    ((obstacles.getYLane() == player.getYLane()) || (obstacles.getYLane() + 1 == player.getYLane()))) {
////                lives--;
////                JOptionPane.showMessageDialog(null,"You crashed Lives Left "+lives);
////                masterPanel.setLives(lives);
////                return true;
////            }
////        }
////        else {
////                if (player.getX() + 20 <= obstacles.getX() + obstacles.getWidth() && player.getYLane() == obstacles.getYLane()){
////                    lives--;
////                    JOptionPane.showMessageDialog(null,"You crashed Lives Left "+lives);
////                    masterPanel.setLives(lives);
////                    return true;
////            }
////        }
////        return false;
////    }
//
////    public void MovePlayerSmoothlyDown() {
////        if (player.getY() + GameConst.getLaneHalfHeightDistance() * 2 < GameConst.getGamePanelHEIGHT()) {//at the bottom
////           if (!playerTimer.isRunning()) {   // this checking is because sometimes when pressing down button twice fast the vehicle
////                                    // gets out of bounds because error in calculating Y Lane as bu default the value is grounded
////                                    //and to not control the car before it gets to its lane
////               playerTimer = new Timer(1, new ActionListener() {
////                    int index = 0;
////                    int y = player.getYLane();
////                    @Override
////                    public void actionPerformed(ActionEvent e) {
////                        int yp = player.getY();
////                        player.setY(yp + YVelocityInPixels);
////                        gamePanel.repaint();
////                        index++;
////                        if (index >= Math.abs(player.calcYLane(y + 1) - player.calcYLane(y))/YVelocityInPixels)
////                            ((Timer) e.getSource()).stop();
////                    }
////                });
////               playerTimer.start();
////            }
////        }
////    }
////
////    public void MovePlayerSmoothlyUp() {
////        if (player.getYLane() != 0) { //at the Top
////            if (!playerTimer.isRunning()) {
////                playerTimer = new Timer(1, new ActionListener() {
////                    int index = 0;
////                    int y = player.getYLane();
////                    @Override
////                    public void actionPerformed(ActionEvent e) {
////                        int yp = player.getY();
////                        player.setY(yp - YVelocityInPixels);
////                        gamePanel.repaint();
////                        index++;
////                        if (index >= Math.abs(player.calcYLane(y - 1) - player.calcYLane(y))/YVelocityInPixels)
////                            ((Timer) e.getSource()).stop();
////                    }
////                });
////                playerTimer.start();
////            }
////        }
////    }
//
//}
