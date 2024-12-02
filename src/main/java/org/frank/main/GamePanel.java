package org.frank.main;

import org.frank.entity.Player;
import org.frank.object.SuperObject;
import org.frank.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    public final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 33;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol;//3200
    public final int screenHeight = tileSize * maxScreenRow;//1600

    //WORLD SETTINGS
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;

    //System
    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public TileManager tileManager = new TileManager(this);
    public Thread gameThread;

    //ENTITIES AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public SuperObject objects[] = new SuperObject[10];

    int FPS = 60;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame(){
        assetSetter.setObject();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);

        //paint objects
        for(int i = 0; i < objects.length; i++){
            if(objects[i] != null){
                objects[i].draw(g2d, this);
            }
        }
        player.draw(g2d);

        g2d.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }
    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }
}
