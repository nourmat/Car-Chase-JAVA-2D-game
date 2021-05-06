package Preliminary_Patch;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/** This class is used to control any sounds in the game*/

public class Sound {
    private Clip clip;
    public Sound(String SoundPath){
        try{
            File file = new File(SoundPath);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(IOException e)
        {

        }
        catch(UnsupportedAudioFileException e)
        {

        }
        catch(LineUnavailableException e)
        {

        }
    }
        public void play(){
            clip.setFramePosition(0);
            clip.start();
        }
        public void stop(){
            clip.stop();
        }
        public void loop(){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        public void rewind(){
            clip.setFramePosition(0);
        }
}
