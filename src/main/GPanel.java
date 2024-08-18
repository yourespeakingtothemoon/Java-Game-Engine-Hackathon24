package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.IOException;

import Logic.CollisionManager;
import entity.*;
import Renderer.*;
import entity.props.Prop_Bedroll;
import entity.props.Prop_BootPlant;
import entity.props.Prop_Campfire;

public class GPanel extends JPanel implements Runnable {

    //Rendering Settings
    final int assetTileSize = 22;  //Size of the tiles in the sprite sheet
    public final int scale = 3;

    public final int tileSize = assetTileSize * scale;  //Size of the tiles in the game


    final int[] aspectRatio = {4, 3};  //Aspect ratio of the game
    final int tileResolution = 4; //"Resolution" of the tiles in the game (not the same as pixel resolution

    final int tileWidth = aspectRatio[0] * tileResolution;  //Width of the game in tiles
    final int tileHeight = aspectRatio[1] * tileResolution;  //Height of the game in tiles

    public final int width = tileWidth * tileSize;  //Width of the game in pixels
    public final int height = tileHeight * tileSize;  //Height of the game in pixels
    final Color clearColor = new Color(0x000000);  //Color to clear the screen with


    int FramesPerSecond = 60;  //Frames per second to run the game at
    //clock
    Thread m_thread;
    public int m_tickCount = 0;

    //logic
    public CollisionManager m_collision = new CollisionManager(this);

    //Input
    InputManager m_input = new InputManager();

    //Entities
    Player player = new Player(this, m_input);

    //map
    public TileRenderer t_renderer = new TileRenderer(this, tileWidth, tileHeight, "maps/homebase.map","DesolationGround.png",assetTileSize, tileSize, "maps/homebasecollision.map");
    public final int maxWorldColumns = 100;
    public final int maxWorldRows = 100;
    public final int maxWorldWidth = maxWorldColumns * tileSize;
    public final int maxWorldHeight = maxWorldRows * tileSize;

    //props
    public Prop[] props = new Prop[3];


    public GPanel() throws IOException {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(this.clearColor);
        this.setDoubleBuffered(true);
        this.addKeyListener(m_input);
        this.setFocusable(true);
        props[0] = new Prop_Bedroll(800, 320, this);
        props[1] = new Prop_Campfire(800, 360, this);
        props[2] = new Prop_BootPlant(850, 330, this);
    }

    public void initalizeThread() {
        m_thread = new Thread(this);
        player.init(tileSize, tileSize);
        m_thread.start();
    }

    @Override
    public void run() {
        //loop
        while(m_thread != null){
            update();

            repaint();

            try {
                Thread.sleep(1000 / FramesPerSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void update() {
        //update game logic
       player.update();
        m_tickCount++;
        if(m_tickCount % 60 == 0){
            System.out.println("FPS: " + m_tickCount);
            m_tickCount = 0;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        t_renderer.draw(g2d);
        drawProps(g2d);
        player.draw(g2d);
        g2d.dispose();
    }

    public void drawProps(Graphics2D g2d){
        for(Prop p : props){
            p.draw(g2d);
        }
    }
}
