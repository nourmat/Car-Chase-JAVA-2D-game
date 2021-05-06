package Preliminary_Patch;
import Preliminary_Patch.BoatMode.BoatPanel;
import Preliminary_Patch.CarMode.DesertPanel;
import Preliminary_Patch.CarMode.DesertPanel;
import Preliminary_Patch.CarMode.GrassPanel;
import Preliminary_Patch.GameConst;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

class Backpnl extends JPanel{
    @Override
    public void paint(Graphics g){
        
        Image img = new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Background\\BackGround (3).jpg").getImage();
        g.drawImage(img, 0, 0, GameConst.getSCREENWIDTH(), GameConst.getSCREENHEIGHT(), null);
        super.paint(g);
    }
}

class Menu extends JFrame{
      
    private Backpnl panel = new Backpnl(); //Contains all panels using cardlayout
    CardLayout layout = new CardLayout(); //it is used as panel's layout and makes it able to change panels freely
    CardLayout maps = new CardLayout();
    private Container c = this.getContentPane();
    private Sound click = new Sound(System.getProperty("user.dir")+"\\src\\Sounds\\Button.wav");
    //TODO
    private Sound BGM = new Sound(System.getProperty("user.dir")+"\\src\\Sounds\\bgm.wav");
    private boolean soundsCheck = true;
    private boolean musicCheck = true;
    //Main menu components
    private Backpnl Buttons = new Backpnl();
    private JLabel play = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\Startlbl.png"));
    private JLabel Welcome = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Title (1).png"));
    private JLabel Settings = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\Settingslbl.png"));
    private JLabel fame = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\Hall of Fame lbl.png"));
    private JLabel Exit = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\Exitlbl.png"));
    private JLabel Credits = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\Creditslbl.png"));
    //GameMode Componenets
    private Backpnl GameMode = new Backpnl();
    private JPanel ModeTop = new JPanel();
    private JLabel ModeBack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\arrow-left-icon.png"));
    private JLabel ChooseModelbl = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Mode (1).png"));
    private JPanel chooseMode = new JPanel();
    private JLabel Attack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\ModeGIFs\\Attack.png"));
    private JLabel Defend = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\ModeGIFs\\Defend.png"));
    //GameType Components
    private JPanel Type = new JPanel(); //Labels for Modes added in it
    private Backpnl GameType = new Backpnl(); 
    private JLabel ChooseTypelbl = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Mode (2).png"));
    private JPanel GameTypeTop = new JPanel();
    private JLabel GameTypeBack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\arrow-left-icon.png")); //Each back button needed an objct on it's on as weirdly enough the same object can't be set to more than a panel

    ////////////////////////////Resizing\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private JLabel grass = new JLabel(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\ModeGIFs\\Grass Mode.gif").getImage().getScaledInstance((int)(GameConst.getSCREENWIDTH()*0.25),(int)(GameConst.getSCREENHEIGHT()*0.09),Image.SCALE_DEFAULT)));
    private JLabel desert = new JLabel(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\ModeGIFs\\Desert Mode.gif").getImage().getScaledInstance((int)(GameConst.getSCREENWIDTH()*0.25),(int)(GameConst.getSCREENHEIGHT()*0.09),Image.SCALE_DEFAULT)));
    private JLabel boat = new JLabel(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\ModeGIFs\\Boat Mode.gif").getImage().getScaledInstance((int)(GameConst.getSCREENWIDTH()*0.25),(int)(GameConst.getSCREENHEIGHT()*0.09),Image.SCALE_DEFAULT)));
    private JLabel chicken = new JLabel(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\ModeGIFs\\Chickens2.gif").getImage().getScaledInstance((int)(GameConst.getSCREENWIDTH()*0.25),(int)(GameConst.getSCREENHEIGHT()*0.09),Image.SCALE_DEFAULT)));
    ////////////////////////////Resizing\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private JPanel Map = new JPanel();
    private JLabel desertMap = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Maps\\Dessert.PNG"));
    private JLabel grassMap = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Maps\\Grass.PNG"));
    private JLabel seaMap = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Maps\\Sea.PNG"));
    private JLabel EmptyMap = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Maps\\Map.PNG"));
    private int modeType; /** 0 Attack 1 Defence*/
    private int TerrienType /**chosen from Game Const*/;

    //Vehicles Selection Components
    private JLabel SelectionBack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\arrow-left-icon.png"));
    private JLabel Car1 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\0.png"));
    private JLabel Car2 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\1.png"));
    private JLabel Car3 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\2.png"));
    private JLabel Car4 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\3.png"));
    private JLabel Car5 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\4.png"));
    private JLabel Car6 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\5.png"));
    private JLabel Car7 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\6.png"));
    private JLabel Car8 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Cars\\7.png"));
    private JLabel Boat1 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\2.png"));
    private JLabel Boat2 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\4.png"));
    private JLabel Boat3 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\5.png"));
    private JLabel Boat4 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\6.png"));
    private JLabel Boat5 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\7.png"));
    private JLabel Boat6 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\8.png"));
    private JLabel Boat7 = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Boats\\9.png"));
    private Backpnl Vehicles = new Backpnl();
    private JPanel TypeTop = new JPanel(); // To add label and back button on top
    private JPanel chooseCar = new JPanel();
    private JPanel chooseBoat = new JPanel();
    private JLabel Choice = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Vehicle.png"));
    //Settings Components
    private JLabel Music = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Music.png"));
    private JLabel Sound = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Sound.png"));
    private JCheckBox MusicBox = new JCheckBox();
    private JCheckBox SoundBox = new JCheckBox();
    private JLabel SettingsBack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\arrow-left-icon.png"));
    private JLabel Settingslbl = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Sound Options.png"));
    private Backpnl Settingspnl = new Backpnl();
    private JPanel optionslbl = new JPanel();
    private JPanel options = new JPanel();
    private JPanel options1 = new JPanel();
    //Hall of fame Components
    private Score[] scoring;
    private Backpnl hof = new Backpnl();
    private JPanel hofTop = new JPanel();
    private JLabel hofBack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\arrow-left-icon.png"));
    private JLabel hofTitle = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\High Scores.png"));
    private JPanel scores = new JPanel();
    private JLabel Score = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Score.png"));
    private JLabel Player = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Logos\\Player.png"));
    private JLabel [] people = new JLabel[10];
    /**Setting up credits*/
    private JPanel Creditspnl = new JPanel(new BorderLayout()); 
    private JLabel Credit = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Background\\Credits.png"));
    private JLabel CreditsBack = new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\src\\Panel Things\\Buttons\\arrow_back_white_144x144.png"));

    private Player player;
    private BoatPanel boatPanel;
    private DesertPanel desertPanel;
    private GrassPanel grassPanel;
    private JFrame frame;

    private Graphics g;
    public Menu()
    {
        /**Setting up all panels layouts*/
        GameConst.settingItUp();
        panel.setLayout(layout);
        Map.setLayout(maps);
        Map.add(desertMap, "Desert");
        Map.add(grassMap, "Grass");
        Map.add(seaMap, "Sea");
        Map.add(EmptyMap,"Empty");
        Buttons.setLayout(new GridLayout(6,1));
        Type.setLayout(new GridLayout(2,2));
        GameType.setLayout(new BorderLayout());
        Vehicles.setLayout(new BorderLayout());
        TypeTop.setLayout(new FlowLayout());
        chooseCar.setLayout(new GridLayout(1,8));
        chooseBoat.setLayout(new GridLayout(1,7));
        Settingspnl.setLayout(new BorderLayout());
        optionslbl.setLayout(new FlowLayout());
        options.setLayout(new GridLayout(2,1));
        options1.setLayout(new GridLayout(2,1));
        chooseMode.setLayout(new GridLayout(2,1));
        GameMode.setLayout(new BorderLayout());
        hof.setLayout(new BorderLayout());
        scores.setLayout(new GridLayout(6,2));
        /** Setting up the main menu*/
        this.setUndecorated(true);
        this.setSize(GameConst.getSCREENWIDTH(),GameConst.getSCREENHEIGHT());
        Welcome.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH(),GameConst.getSCREENHEIGHT()/4));
        Welcome.setHorizontalAlignment(SwingConstants.CENTER);
        Welcome.setVerticalAlignment(SwingConstants.CENTER);
        play.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Mode");
                    }
                });
        Credits.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Credits");
                    }
                });
        fame.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Hall of Fame");
                        scores.removeAll();
                        settingUPHallOFFame();
                    }
                });
        Settings.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel,"Settings");
                    }
                });
        Exit.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        System.exit(0);
                    }
                });
        Buttons.add(Welcome);
        Buttons.add(play);
        Buttons.add(Settings);
        Buttons.add(fame);
        Buttons.add(Credits);
        Buttons.add(Exit);
        /**Set up Game mode selection*/
        ModeBack.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Menu");
                    }
                });
        Attack.setHorizontalAlignment(SwingConstants.CENTER);
        Attack.setVerticalAlignment(SwingConstants.CENTER);
        Attack.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            modeType = GameConst.ATTACKMODE;
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Play");
                    }
                });
        Defend.setHorizontalAlignment(SwingConstants.CENTER);
        Defend.setVerticalAlignment(SwingConstants.CENTER);
        Defend.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            modeType= GameConst.DEFENCEMOED;
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Play");
                    }
                });
        ModeTop.add(ModeBack);
        ModeTop.add(ChooseModelbl);
        GameMode.add(ModeTop,BorderLayout.NORTH);
        chooseMode.add(Attack);
        chooseMode.add(Defend);
        GameMode.add(chooseMode);
        /** Setting up GameType Panel*/
        grass.setPreferredSize(new Dimension(250,200));
        desert.setPreferredSize(new Dimension(250,200));
        chicken.setPreferredSize(new Dimension(250,200));
        boat.setPreferredSize(new Dimension(250,200));
        Type.add(grass);
        Type.add(desert);
        Type.add(boat);
        Type.add(chicken);
        GameType.add(Type);
        GameTypeTop.add(GameTypeBack);
        GameTypeTop.add(ChooseTypelbl);
        GameType.add(GameTypeTop,BorderLayout.NORTH);
        GameType.add(Map,BorderLayout.EAST);
        maps.show(Map, "Empty");

        GameTypeBack.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Mode");
                    }
                });

        //TODO
        grass.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        TerrienType = GameConst.GRASSMODE;
                        /** if attacker mode then enter with a pirate*/
                        if (modeType == GameConst.ATTACKMODE) {
                            player = new Player(new ImageIcon(".\\src\\CarMode\\Cars\\Attacker Mode player.png"));
                            grassPanel = new GrassPanel(player, modeType);
                            frame = new StartFrame(grassPanel);
                            frame.setVisible(true);
                            grassPanel.setFrame(frame);
                            BGM.stop();
                            if (GameConst.bmg != null)
                                GameConst.bmg.stop();
                        }
                        else {
                            Vehicles.add(chooseCar);
                            layout.show(panel, "Select");
                        }
                    }
                    public void mouseEntered(MouseEvent e) {
                        maps.show(Map, "Grass");
                    }
                    public void mouseExited(MouseEvent e) {
                        maps.show(Map, "Empty");
                    }
                });
        desert.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        TerrienType = GameConst.DESERTMODE;
                        /** if attacker mode then enter with a pirate*/
                        if (modeType == GameConst.ATTACKMODE) {
                            player = new Player(new ImageIcon(".\\src\\CarMode\\Cars\\Attacker Mode player.png"));
                            desertPanel = new DesertPanel(player, modeType);
                            frame = new StartFrame(desertPanel);
                            frame.setVisible(true);
                            desertPanel.setFrame(frame);
                            BGM.stop();
                            if (GameConst.bmg != null)
                                GameConst.bmg.stop();
                        }
                        else {
                            Vehicles.add(chooseCar);
                            layout.show(panel, "Select");
                        }
                    }
                    public void mouseEntered(MouseEvent e){
                        maps.show(Map, "Desert");
                    }
                    public void mouseExited(MouseEvent e){
                        maps.show(Map, "Empty");
                    }
                });
        boat.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        TerrienType = GameConst.BOATMODE;
                        /** if attacker mode then enter with a pirate*/
                        if (modeType == GameConst.ATTACKMODE) {
                            player = new Player(new ImageIcon(".\\src\\BoatMode\\Boats\\Attacker Mode Player.png"));
                            boatPanel = new BoatPanel(player, modeType);
                            frame = new StartFrame(boatPanel);
                            frame.setVisible(true);
                            boatPanel.setFrame(frame);
                            BGM.stop();
                            if (GameConst.bmg != null)
                                GameConst.bmg.stop();
                        }
                        else {
                            Vehicles.add(chooseBoat);
                            layout.show(panel, "Select");
                        }
                    }
                    public void mouseEntered(MouseEvent e) {
                        maps.show(Map, "Sea");
                    }
                    public void mouseExited(MouseEvent e) {
                        maps.show(Map, "Empty");
                    }
                });
        /**Setting up Selection panel*/
        SelectionBack.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH()/10,128));
        SelectionBack.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Play");
                        Vehicles.remove(chooseCar);
                        Vehicles.remove(chooseBoat);
                    }
                });
        MouseAdapter playCar = new MouseAdapter(){ //playcar mouseAdapter is used to decrease redundancy
            public void mouseClicked(MouseEvent e){
                if(TerrienType == GameConst.DESERTMODE) {
                    /** getting image number to then match it and set the player*/
                    String carno = ((JLabel)e.getSource()).getIcon().toString();
                    carno = carno.substring(carno.length()-5,carno.length()-4);
                    player = new Player(new ImageIcon(".\\src\\CarMode\\Cars\\"+carno+".png"));
                    desertPanel = new DesertPanel(player, modeType);
                    frame = new StartFrame(desertPanel);
                    frame.setVisible(true);
                    desertPanel.setFrame(frame);
                    BGM.stop();
                    if (GameConst.bmg != null)
                        GameConst.bmg.stop();
                }
                else if (TerrienType == GameConst.GRASSMODE){
                    /** getting image number to then match it and set the player*/
                    String carno = ((JLabel)e.getSource()).getIcon().toString();
                    carno = carno.substring(carno.length()-5,carno.length()-4);
                    player = new Player(new ImageIcon(".\\src\\CarMode\\Cars\\"+carno+".png"));
                    grassPanel = new GrassPanel(player, modeType);
                    frame = new StartFrame(grassPanel);
                    frame.setVisible(true);
                    grassPanel.setFrame(frame);
                    BGM.stop();
                    if (GameConst.bmg != null)
                        GameConst.bmg.stop();
                }
            }
        };
        MouseAdapter playBoat = new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(TerrienType == GameConst.BOATMODE) {
                    /** getting image number to then match it and set the player*/
                    String carno = ((JLabel)e.getSource()).getIcon().toString();
                    carno = carno.substring(carno.length()-5,carno.length()-4);
                    player = new Player(new ImageIcon(".\\src\\BoatMode\\Boats\\"+ carno +".png"));
                    boatPanel = new BoatPanel(player, modeType);
                    frame = new StartFrame(boatPanel);
                    frame.setVisible(true);
                    boatPanel.setFrame(frame);
                    BGM.stop();
                    if (GameConst.bmg != null)
                        GameConst.bmg.stop();
                }
            }
        };
        TypeTop.add(SelectionBack);
        TypeTop.add(Choice);
        Vehicles.add(TypeTop,BorderLayout.NORTH);
        chooseCar.add(Car1);
        chooseCar.add(Car2);
        chooseCar.add(Car3);
        chooseCar.add(Car4);
        chooseCar.add(Car5);
        chooseCar.add(Car6);
        chooseCar.add(Car7);
        chooseCar.add(Car8);
        chooseBoat.add(Boat1);
        chooseBoat.add(Boat2);
        chooseBoat.add(Boat3);
        chooseBoat.add(Boat4);
        chooseBoat.add(Boat5);
        chooseBoat.add(Boat6);
        chooseBoat.add(Boat7);
        Car1.addMouseListener(playCar);
        Car2.addMouseListener(playCar);
        Car3.addMouseListener(playCar);
        Car4.addMouseListener(playCar);
        Car5.addMouseListener(playCar);
        Car6.addMouseListener(playCar);
        Car7.addMouseListener(playCar);
        Car8.addMouseListener(playCar);
        Boat1.addMouseListener(playBoat);
        Boat2.addMouseListener(playBoat);
        Boat3.addMouseListener(playBoat);
        Boat4.addMouseListener(playBoat);
        Boat5.addMouseListener(playBoat);
        Boat6.addMouseListener(playBoat);
        Boat7.addMouseListener(playBoat);
        /** Setting up Settings panel*/
        Settingslbl.setFont(new Font("arial",Font.BOLD,30));
        SettingsBack.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Menu");
                    }
                });
        optionslbl.add(SettingsBack);
        optionslbl.add(Settingslbl);
        Settingspnl.add(optionslbl,BorderLayout.NORTH);
//        Music.setHorizontalAlignment(SwingConstants.CENTER);
//        Music.setVerticalAlignment(SwingConstants.CENTER);
//        Sound.setHorizontalAlignment(SwingConstants.CENTER);
//        Sound.setVerticalAlignment(SwingConstants.CENTER);
        options.add(Music);
        options.add(Sound);
        Music.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH()/4,100));
        Settingspnl.add(options,BorderLayout.WEST);
        options1.add(MusicBox);
        options1.add(SoundBox);
        Settingspnl.add(options1);
        MusicBox.setSelected(musicCheck);
        SoundBox.setSelected(soundsCheck);
        MusicBox.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
               GameConst.reverseMusic();
               try{
               if(MusicBox.isSelected())
               {
                   musicCheck = true;
                   BGM.loop();
               }
               else
               {
                   musicCheck = false;
                   BGM.stop();
               }
               }
               catch(Exception err){}
           }
        });
        SoundBox.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
               GameConst.reveerseSound();
               if(SoundBox.isSelected())
                   soundsCheck = true;
               else
                   soundsCheck = false;
           }
        });
        Creditspnl.add(CreditsBack,BorderLayout.NORTH);
        CreditsBack.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try{
                        if(soundsCheck)
                            click.play();
                        }
                        catch(Exception error){}
                        layout.show(panel, "Menu");
                    }
                });
        Creditspnl.add(Credit);
        Creditspnl.setBackground(Color.BLACK);
        panel.setBackground(Color.PINK);
        /** Setting up Hall of Fame panel*/
        hofTop.add(hofBack);
        hofTop.add(hofTitle);
        hof.add(hofTop,BorderLayout.NORTH);
        scores.add(Player);
        scores.add(Score);
        settingUPHallOFFame();
        hof.add(scores);
        hofBack.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                try{
                    if(soundsCheck)
                        click.play();
                }
                catch(Exception error){}
                layout.show(panel, "Menu");
            }
        });
        /** Opaque all components with backgrounds to allow th presence of a background*/
        Map.setOpaque(false);
        Buttons.setOpaque(false);
        scores.setOpaque(false);
        ModeTop.setOpaque(false);
        GameTypeTop.setOpaque(false);
        GameType.setOpaque(false);
        Type.setOpaque(false);
        Vehicles.setOpaque(false);
        optionslbl.setOpaque(false);
        options.setOpaque(false);
        options1.setOpaque(false);
        MusicBox.setOpaque(false);
        SoundBox.setOpaque(false);
        Settingspnl.setOpaque(false);
        chooseCar.setOpaque(false);
        chooseBoat.setOpaque(false);
        chooseMode.setOpaque(false);
        GameMode.setOpaque(false);
        TypeTop.setOpaque(false);
        hof.setOpaque(false);
        hofTop.setOpaque(false);
        /**Add all panels to JPanel "panel" so it can flip them easily*/
        panel.add(Buttons,"Menu");
        panel.add(GameMode,"Mode");
        panel.add(GameType,"Play");
        panel.add(Vehicles,"Select");
        panel.add(Settingspnl,"Settings");
        panel.add(hof,"Hall of Fame");
        panel.add(Creditspnl,"Credits");
        c.add(panel);
        layout.show(panel,"Menu");
        try{
        if(musicCheck)
            BGM.loop();
        }
        catch(Exception e){}
    }
    public void settingUPHallOFFame(){
        scoring = HighScores.getScoreList();
        for(int i = 0 ; i<10 ; i++)
        {
            if(i%2==0)
                if(scoring[i/2].getName().equals(""))
                    people[i] = new JLabel("---");
                else
                    people[i] = new JLabel(scoring[i/2].getName());
            else
            if(scoring[i/2].getScore()==-1)
                people[i] = new JLabel(""+0);
            else
                people[i] = new JLabel(""+scoring[i/2].getScore());
            people[i].setVerticalAlignment(SwingConstants.CENTER);
            people[i].setHorizontalAlignment(SwingConstants.CENTER);
            people[i].setFont(new Font("SansSerif",Font.BOLD,40));
            if(i%2==0)
                people[i].setForeground(Color.red);
            else
                people[i].setForeground(Color.blue);
            scores.add(people[i]);
        }
    }
}

public class MainMenu2 {

    public static void main(String[] args) {
            GameConst.settingItUp();
            Menu m = new Menu();
            m.setVisible(true);
    }
    
}
