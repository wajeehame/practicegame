package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize=16; //16x16 tile
	final int scale=3;
	public final int tileSize=originalTileSize*scale;//48x48 tile
	final int maxScreenCol=16;
	final int maxScreenRow=12;
	final int screenWidth=tileSize*maxScreenCol;//768 pixels
	final int screenHeight=tileSize*maxScreenRow;//576 pixels
	
	//fps
	int FPS=60;
	
	KeyHandler keyH= new KeyHandler();
	Thread gameThread;
	Player player=new Player(this,keyH);
	
	int playerx=100;
	int playery=100;
	int playerSpeed=4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread= new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		
		//sleep method
		double drawInterval=1000000000/FPS; //0.016666 sec
		double nextDrawTime=System.nanoTime()+drawInterval;
		
		
		while(gameThread!=null) {
			// update info on character position;
			update();
			
			// draw screen w updated info
			repaint();
			

				
				try {
					double remainingTime=nextDrawTime-System.nanoTime();
					remainingTime=remainingTime/1000000;
					if(remainingTime <0) {
						remainingTime=0;
					}
					Thread.sleep((long) remainingTime);
					
					nextDrawTime+=drawInterval;
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	} 

	
	// delta method
	 /*
	 public void run(){
	 
	  double drawInterval=1000000000/FPS; //0.016666 sec
	  double delta=0;
	  long lastTime =System.nanoTime();
	  long currentTime;
	  long timer =0;
	  int drawCount=0;
	  
	  while(gameThread != null){
	  
		  currentTime=System.nanoTime();
	  
		  delta+=(currentTime-lastTime)/drawInterval;
		  timer +=(currentTime-lastTime);
		  lastTime=currentTime;
	 
		  if(delta>=1){
	  
			  update();
			  repaint();
			  delta--;
			  drawCount++;
			  }
		   if(timer>=1000000000){
		      System.out...
		  }
	  } */ //doesn't work and not continued
	  
	public void update() {
		
		player.update();
		
		}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2= (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}
}










