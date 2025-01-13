package org.frank.main;

import java.io.*;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));

            //Full screen
            if(gp.fullScreenOn){
                writer.write("On");
            }
            if(!gp.fullScreenOn){
                writer.write("Off");
            }
            writer.newLine();

            //Music Volume
            writer.write(String.valueOf(gp.music.volumeScale));
            writer.newLine();

            //Sound Effect Volume
            writer.write(String.valueOf(gp.soundEffect.volumeScale));
            writer.newLine();

            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));

            //Full screen
            String line = reader.readLine();
            if (line.equals("On")){
                gp.fullScreenOn = true;
            }
            if (line.equals("Off")){
                gp.fullScreenOn = false;
            }

            //Music Volume
            line = reader.readLine();
            gp.music.volumeScale = Integer.parseInt(line);


            //Sound effect Volume
            line = reader.readLine();
            gp.soundEffect.volumeScale = Integer.parseInt(line);

            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
