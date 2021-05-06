package Preliminary_Patch.Recored;

import javax.swing.*;
import java.awt.*;

public class SpeedPanel extends JPanel{

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static double ratio = 1.0 * screenSize.width / 1920;
    final static int boxWidth = (int) (30*ratio);
    final static int boxHeight= (int) (30*ratio);
    final static int numberOfSquares = 10;
    final static int minRowPeed = 30;
    int speed = 1;
    Color []colors = new Color[numberOfSquares];

    @Override
    public void paint(Graphics g){
        super.paint(g);
        int i;
        for(i = 0; i < speed && i <numberOfSquares;i++){
            g.setColor(colors[i]);
            g.fill3DRect(1 + boxWidth*i, 1, boxWidth - 2, boxHeight - 2,true);
        }
        for(;i< numberOfSquares; i++){
            g.setColor(Color.lightGray);
            g.fill3DRect(1 + boxWidth*i, 1, boxWidth - 2, boxHeight - 2,false);
        }
    }

    /**
     * constructs a speedpanel object
     */
    public SpeedPanel(){
        int i = 0;
        for(; i < numberOfSquares / 4; i++)
            colors[i] = Color.green;
        for(; i < numberOfSquares / 2; i++)
            colors[i] = Color.yellow;
        for(; i < 3*numberOfSquares / 4; i++)
            colors[i] = Color.orange;
        for(; i < numberOfSquares; i++)
            colors[i] = Color.red;
        setPreferredSize(new Dimension(numberOfSquares * boxWidth, boxHeight));
        setLayout(new GridLayout(1,numberOfSquares));

        setOpaque(false);
    }

    public void setSpeed(int rowSpeed){
        this.speed = 1 + (rowSpeed - minRowPeed) / 22;
        repaint();
    }
}
