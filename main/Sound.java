package main;

// the following imports are needed for music
import java.io.File;
import java.net.URL;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL [30];

    public Sound() {
        soundURL[0] = getClass().getResource("audio/bkgMusic.wav");

    }

}
