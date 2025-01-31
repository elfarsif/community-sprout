package org.frank.main;

import org.frank.ai.PathFinder;
import org.frank.entity.Entity;
import org.frank.entity.Player;
import org.frank.tile.Map;
import org.frank.tile.TileManager;
import org.frank.tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    public final int originalTileSize = 16;
    public final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 30;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //FULLSCREEN
    int screenWidth2= screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2d;
    public boolean fullScreenOn = false;

    //WORLD SETTINGS
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 48;
    public final int maxMap=10;
    public int currentMap = 0;
    public Map parentsHome = new Map(10,10);

    //System
    public KeyHandler keyHandler = new KeyHandler(this);
    public MouseHandler mouseHandler = new MouseHandler(this);
    public Sound music = new Sound();
    public Sound soundEffect = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public TileManager tileManager = new TileManager(this);
    public UI ui = new UI(this);
    public Thread gameThread;
    public EventHandler eventHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pathFinder = new PathFinder(this);
    public CutsceneManager cutsceneManager = new CutsceneManager(this);

    //ENTITIES AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public Entity[][] objects = new Entity[maxMap][10];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] monsters = new Entity[maxMap][10];
    public InteractiveTile[][] iTiles = new InteractiveTile[maxMap][10];
    ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Entity> projectiles = new ArrayList<Entity>();

    int FPS = 60;

    //GAME STATE
    public int gameState;
    public final int playState =1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 4;
    public final int optionsState = 5;
    public final int transitionMapState = 6;
    public final int tradeState = 7;
    public final int cutsceneState = 8;


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
        assetSetter.setMonster();
        assetSetter.setInteractiveTiles();
        playMusic(0);

        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn){
            setFullScreen();
        }
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
                drawToTempScreen();
                drawToScreen();
                delta--;
            }
        }
    }

    public void update(){


        if(gameState == playState){
            player.update();
            //update all npc
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            //update monsters
            for(int i = 0; i < monsters[1].length; i++){
                if(monsters[currentMap][i] != null){
                    if (monsters[currentMap][i].alive && !monsters[currentMap][i].dying){
                        monsters[currentMap][i].update();
                    }
                    if (!monsters[currentMap][i].alive){
                        monsters[currentMap][i].checkDrop();
                        monsters[currentMap][i] = null;
                    }
                }
            }
            //update projectiles
            for(int i = 0; i < projectiles.size(); i++){
                if(projectiles.get(i) != null){
                    if (projectiles.get(i).alive){
                        projectiles.get(i).update();
                    }
                    if (!projectiles.get(i).alive){
                        projectiles.remove(i);
                    }
                }
            }
            //update interactive tiles
            for (int i = 0; i < iTiles[1].length; i++){
                if(iTiles[currentMap][i] != null){
                    iTiles[currentMap][i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }
    }

    public void drawToTempScreen(){
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

            for (int i = 0 ; i < iTiles[1].length; i++){
                if(iTiles[currentMap][i] != null){
                    iTiles[currentMap][i].draw(g2d);
                }
            }

            //ADD ENTITIES TO LIST
            entities.add(player);
            //ADD INTERACTIVE TILES
            for (int i = 0; i < iTiles[1].length; i++){
                if(iTiles[currentMap][i] != null){
                    entities.add(iTiles[currentMap][i]);
                }
            }
            //paint npc
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    entities.add(npc[currentMap][i]);
                }
            }
            //paint objects
            for(int i = 0; i < objects[1].length; i++){
                if(objects[currentMap][i] != null){
                    entities.add(objects[currentMap][i]);
                }
            }
            //paint monsters
            for(int i = 0; i < monsters[1].length; i++){
                if(monsters[currentMap][i] != null){
                    entities.add(monsters[currentMap][i]);
                }
            }
            //paint projectiles
            for(int i = 0; i < projectiles.size(); i++){
                if(projectiles.get(i) != null){
                    entities.add(projectiles.get(i));
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
            //DRAW CUSTSCENE
            cutsceneManager.draw(g2d);
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

    }

    public void setFullScreen(){
        //Get local screen size device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();


    }

    public void drawToScreen(){
        Graphics g = this.getGraphics();
        g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }

}
