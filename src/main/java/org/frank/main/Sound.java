package org.frank.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundUrl[] = new URL[3];

    public Sound(){
        soundUrl[0] = getClass().getResource("/sounds/lofi.wav");
        soundUrl[1] = getClass().getResource("/sounds/chopping-wood.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            System.out.println("Sound loaded");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void play(){
        clip.start();
        System.out.println("Sound played");
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
