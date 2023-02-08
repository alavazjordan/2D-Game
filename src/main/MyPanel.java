package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyPanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16
    final double scale = 7.5;// 16x7.5 = 120 pixels

    //making it public to allow other packages to access it
    public final int tileSize = (int)((double)originalTileSize * scale);//120x120 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 9;
    final int screenWidth = tileSize * maxScreenCol;// 1920
    final int screenHeight = tileSize * maxScreenRow;// 1080

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    Thread gameThread;// allows for the concept of time to allow for core looping functions such as update and repaint


    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public MyPanel(){

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);// enabling this can improve game's rendering performance

        addKeyListener(keyH);
        setFocusable(true);//With this the panel can be "focused" to recieve key input


        
    }


    public void startGameThread(){
        gameThread = new Thread(this);// this is how you instantiate a thread
        gameThread.start();//automatically calls the run method
    }

    @Override // This will contain the game loop which will be the core of the game
    public void run() {
        //Here we are using the sleep method to achieve 60 FPS
        double drawInterval = 1000000000/FPS;// This is the total time between each frame 
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            //1,000,000,000 nanosecond is equal to 1 second
            long currentTime = System.nanoTime();//returns the current value of the running Java virtual Machines high-resolution time source, in nanoseconds.

            update();// Updates information such as character positions
            repaint();// Draws the screen with the updated information

            try{
                double remainingTime = nextDrawTime - System.nanoTime();

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime/1000000);
                nextDrawTime += drawInterval;
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
    }


    public void update(){
        player.update();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();//This disposes of this graphics context and release any system resources that it is using.
    }
}
