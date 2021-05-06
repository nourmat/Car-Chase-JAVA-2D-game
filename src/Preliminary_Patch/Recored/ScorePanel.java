package Preliminary_Patch.Recored;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ScorePanel extends JPanel {


    JLabel scoreLabel, scoreWordLabel;
    JPanel scorePanel, scoreWordPanel;
    ImageIcon imageIcon;

    /**
     * constructs the score panel with a gif and a number underneath representing the score
     * if the icon isnot found in memory it will print error message
     * @param fileName name of file that has the score word gif
     */
    public ScorePanel(String fileName){
        scoreLabel = new JLabel("0",SwingConstants.CENTER);
        scoreLabel.setForeground(Color.CYAN);
        scoreLabel.setFont(new Font("Viner Hand ITC",Font.BOLD,25));
        scorePanel = new JPanel();
        scorePanel.add(scoreLabel);
        scorePanel.setOpaque(false);

        scoreWordLabel = new JLabel();

        try {
            imageIcon = new ImageIcon(fileName);
            scoreWordLabel.setIcon(imageIcon);
            scoreWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null,"failed to load important data");
            //TODO add System.exit()
            System.err.println("failed to load scorepanel data");
        }

        scoreWordPanel = new JPanel();
        scoreWordPanel.setOpaque(false);
        scoreWordPanel.add(scoreWordLabel);

        this.setLayout(new GridLayout(2,1));
        add(scoreWordLabel);
        add(scoreLabel);
        setBackground(Color.BLUE);
        setOpaque(false);

    }


    /**
     *this function changes color of the score number
     * @param c color of the score number
     */
    public void setScoreLabelColor(Color c){
        scoreLabel.setForeground(c);
    }

    /**
     * this function sets the font of the score value
     * @param f the font that is taken
     */
    public void setScoreLabelFont(Font f){
        scoreLabel.setFont(f);
    }

    /**
     *
     * @param sizePx the size point of the font
     */
    public void setScoreLabelFontSize(Float sizePx){
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(sizePx));
    }

    /**
     * this functions takes a gif of the word score
     * @param icon the icon of the word score
     */
    public void setScoreWordLabelIcon(Icon icon){
        scoreWordLabel.setIcon(icon);
    }


    /**
     * this functions changes the score made by the user so far
     * @param score
     */
    public void setScore(int score){
        scoreLabel.setText(score+"");
    }

}
