package Preliminary_Patch;

import Preliminary_Patch.BoatMode.BoatPanel;
import Preliminary_Patch.CarMode.DesertPanel;
import Preliminary_Patch.CarMode.GrassPanel;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {


    /**
     * this class extend JFrame it used to draw the game on it
     * @param gamepanel which will contain the game.
     */

    public StartFrame(JPanel gamepanel) {
        GameConst.settingItUp();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setPreferredSize(new Dimension(GameConst.getSCREENWIDTH(), GameConst.getSCREENHEIGHT()));
        setUndecorated(true); /* To Make it FullScreen */

        Container container = this.getContentPane();

        container.add(gamepanel);
        this.pack();
    }

}
