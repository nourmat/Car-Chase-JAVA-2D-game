package Preliminary_Patch.Recored;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;


public class LivePanel extends JPanel implements Serializable {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double ratio = 1.0*screenSize.width/1920 ;

    public int iconWidth = (int)(100 * ratio);
    public int iconHeight = (int)(100* ratio);

    public final int MAXLIVES = 3;
    ImageIcon liveIcon, deadIcon;


    int lives = MAXLIVES ;
    JLabel[] liveLables = new JLabel[MAXLIVES];

    /**
     * this functions draws the red hearts and black hearts according to the lives count
     */
    private void updateLiveLabels(){
        int i;
        for(i = 0; i < lives; i++){
            liveLables[i].setIcon(liveIcon);
        }

        for(;i < MAXLIVES; i++){
            liveLables[i].setIcon(deadIcon);
        }
        repaint();
    }

    /**
     *construcs a livepanel object, if the icons arenot found in memory it prints error message
     * @param liveHeartIcon the path to the red heart image
     * @param deadHeartIcon the path to the dead heart image
     */
    public LivePanel(String liveHeartIcon, String deadHeartIcon) {

        BufferedImage liveImage = null;
        BufferedImage deadImage = null;
        try {
            liveImage = ImageIO.read(new File(liveHeartIcon));
            deadImage = ImageIO.read(new File(deadHeartIcon));
        }
        catch (Exception e){
            JOptionPane.showConfirmDialog(null,"failed to load important data");
            //TODO add System.exit()
            System.err.println("failed to load the live panel images");
            e.printStackTrace();
        }

        liveIcon = new ImageIcon(liveImage.getScaledInstance(iconWidth,iconHeight,Image.SCALE_SMOOTH));

        deadIcon = new ImageIcon(deadImage.getScaledInstance(iconWidth,iconHeight,Image.SCALE_SMOOTH));

        setLayout(new GridLayout(1,3));
        for(int i  = 0; i < MAXLIVES; i++){
            liveLables[i] = new JLabel();
            liveLables[i].setOpaque(false);
            liveLables[i].setIcon(liveIcon);
            add(liveLables[i]);
        }

        setOpaque(false);
    }

    /**
     * this functions decrease the lives by 1
     */
    public void decrementLives(){
        lives = (lives-1>0)? lives - 1: 0;
        updateLiveLabels();
    }

    /**
     * this functions increase the lives by 1
     */
    public void incrementLives(){
        lives = (lives+1>3)? 3: lives+1;
        updateLiveLabels();
    }
}
