package Preliminary_Patch;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * this class expresses the score
 * @score the integer value of the score
 * @name the name of the player
 */
class Score implements Serializable, Comparable{
    private int score;
    private String name;

    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        Score that = (Score)o;
        return Integer.compare(that.score, this.score);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}

/**
 * this class holds the data for high scores
 */
public class HighScores {


    final static private String fileName = ".//src//score.nym";


    static private FileOutputStream fos = null;
    static private ObjectOutputStream oos = null;

    static private FileInputStream fis = null;
    static private ObjectInputStream ois = null;

    static private File file = null;

    static private ArrayList<Score> scoreList = null;

    static private boolean loaded = false;


    /**
     * private function that makes sure that the file has been initiated
     */
    private static void init(){
        while(!loaded){
            file = new File(fileName);

            if(!file.exists()){
                try{
                    file.createNewFile();
                    scoreList = new ArrayList<>(5);
                    fos = new FileOutputStream(file);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(scoreList);
                    fos.close();
                    oos.close();
                    loaded = true;
                }
                catch (Exception e1){
                    e1.printStackTrace();
                    break;
                }
            }
            else{
                try{
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    scoreList = (ArrayList<Score>) ois.readObject();
                    loaded = true;
                }
                catch (Exception e2){
                    try{
                        scoreList = new ArrayList<>(5);
                        fos = new FileOutputStream(file);
                        oos = new ObjectOutputStream(fos);
                        oos.writeObject(scoreList);
                        fos.close();
                        oos.close();
                        loaded = true;
                    }
                    catch (Exception e3){break;}
                }
            }

        }

    }

    /**
     * this functions adds a new score to the score file
     * @param score
     * @param name
     */
    public static void addScore(int score, String name){

        init();

        Score tmpScore = new Score(score,name);
        int index = Collections.binarySearch((List)scoreList, tmpScore);
        if(index < 0){
            scoreList.add(~index,tmpScore);
        }
        else{
            scoreList.add(index,tmpScore);
        }


    }

    /**
     *
     * @return the array list of the high scores sorted in non decreasing order
     */
    public static Score[] getScoreList(){
        init();
        Score[] score = new Score[5];
        if(loaded){
            int index = 0;
            for(; index < scoreList.size() && index < score.length; index++){
                score[index] = scoreList.get(index);
            }
            for(;index < score.length; index++){
                score[index] = new Score(-1,"");
            }
            return score;
        }
        else{
            for(int i = 0;i < score.length; i++){
                score[i] = new Score(-1,"");
            }
            return score;
        }
    }

    /**
     * saves the files
     */
    public static void saveScore(){

        if(loaded){

            try {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(scoreList);
            }
            catch (IOException e1){
                System.err.println("failed to save file");
                e1.printStackTrace();
            }
            finally {
                try{
                    fos.close();
                    oos.close();
                }
                catch (Exception e2){
                    fos = null;
                    oos = null;
                }
            }
        }
    }

    /**
     * this function deletes all scores so far
     * it is supposed to be used only for testinng purposes
     */
    public static void deleteScores(){
        init();
        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Score>(0));

        }
        catch (IOException e1){
            e1.printStackTrace();
        }
        finally {
            try {

                fos = null;
                oos = null;
            }
            catch (Exception e2){
                fos = null;
                oos = null;
            }
        }
    }
    private HighScores(){}

}