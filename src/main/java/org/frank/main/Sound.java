package org.frank.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundUrl[] = new URL[10];
    FloatControl fc;
    int volumeScale = 3;//makes 6 levels of volume
    float volume;

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
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void checkVolume(){
        switch (volumeScale){
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
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
