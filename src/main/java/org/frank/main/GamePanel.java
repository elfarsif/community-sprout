package org.frank.main;

import org.frank.entity.Entity;
import org.frank.entity.Player;
import org.frank.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    public final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;
    //System
    public KeyHandler keyHandler = new KeyHandler(this);
    public MouseHandler mouseHandler = new MouseHandler(this);
    public Sound sound = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public TileManager tileManager = new TileManager(this);
    public UI ui = new UI(this);
    public Thread gameThread;
    public EventHandler eventHandler = new EventHandler(this);


    //ENTITIES AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public Entity[] objects = new Entity[10];
    public Entity[] npc = new Entity[10];
    ArrayList<Entity> entities = new ArrayList<Entity>();

    int FPS = 60;

    //GAME STATE
    public int gameState;
    public final int playState =1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 4;

    public Graphics2D g2d;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseMotionListener(mouseHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame(){
        gameState = titleState;
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        stopMusic();
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

        if(gameState == playState){
            player.update();
            //update all npc
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == playState){
            //TODO
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        this.g2d = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyHandler.tPressed){
            drawStart = System.nanoTime();
        }

        //Title SCREEN
        if(gameState == titleState){
            g2d.setColor(Color.WHITE);
            ui.draw(g2d);
        }else {

            tileManager.draw(g2d);

            //ADD ENTITIES TO LIST
            entities.add(player);
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entities.add(npc[i]);
                }
            }

            for(int i = 0; i < objects.length; i++){
                if(objects[i] != null){
                    entities.add(objects[i]);
                }
            }
            //SORT by world Y so lower entities are drawn first so that higher entities dont overlap on top
            Collections.sort(entities, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(Entity e : entities){
                e.draw(g2d);
            }
            //EMPTY LIST
            entities.clear();

            //UI
            ui.draw(g2d);


        }

        //DEBUG
        if(keyHandler.tPressed){
            long drawEnd = System.nanoTime();
            long drawTime = drawEnd - drawStart;
            g2d.drawString("Draw Time: " + drawTime, 10, 30);
            System.out.println("Draw Time: " + drawTime);
        }


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
