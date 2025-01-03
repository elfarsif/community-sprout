package org.frank.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundUrl[] = new URL[10];

    public Sound(){
        soundUrl[0] = getClass().getResource("/sounds/acoustic-lofi.wav");
        soundUrl[1] = getClass().getResource("/sounds/chopping-wood.wav");
        soundUrl[2] = getClass().getResource("/sounds/inventory-cursor.wav");
        soundUrl[3] = getClass().getResource("/sounds/sword-sound.wav");
        soundUrl[4] = getClass().getResource("/sounds/21_orc_damage_1.wav");
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
