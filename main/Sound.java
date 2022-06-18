 /**
  * Sound
  * Desc: Sets up and runs audio functions
  * @author Cynthia L & Phoebe Y ICS4U
  * @version 1.2 Jun 2022
  */

package main;

// the following imports are needed for music
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL [10];

    public Sound() {
        soundURL[0] = getClass().getResource("/audio/bkgMusic.wav");
        soundURL[1] = getClass().getResource("/audio/buzz.wav");
        soundURL[2] = getClass().getResource("/audio/ouch.wav");
        soundURL[3] = getClass().getResource("/audio/popLevelUp.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]); // passes audio based on array index
            clip = AudioSystem.getClip();
            clip.open(audioStream);

        }catch(Exception ex){
        }
    }
    public void play(){
        clip.start(); //starts audio
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY); //loops audio

    }
    public void stop(){
        clip.stop(); //stops audio
    }

}
