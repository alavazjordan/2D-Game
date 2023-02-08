package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.MyPanel;

public class Player extends Entity{

	
	MyPanel mp;
	KeyHandler keyH;
	
	
	public Player(MyPanel mp, KeyHandler keyH) {
		this.mp = mp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	
	public void getPlayerImage() {
		try {
			idle = ImageIO.read(getClass().getResourceAsStream("/player/RusselIdle.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/RusselDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/RusselDown2.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true){
				direction = "up";
	            y -= speed;
	        }
	        if(keyH.downPressed == true){
	        	direction = "down";
	            y += speed;
	        }
	        if(keyH.leftPressed == true){
	        	direction = "left";
	            x -= speed;
	        }
	        if(keyH.rightPressed == true){
	        	direction = "right";
	            x += speed;
	        }
	        
	        spriteCounter++;
	        if(spriteCounter > 12) {
	        	if(spriteNum ==1) {
	        		spriteNum = 2;
	        	}
	        	else if(spriteNum ==2) {
	        		spriteNum = 1;
	        	}
	        	spriteCounter = 0;
	        }
		}
		else {
			direction = "idle";
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		//case "up":
		//	image = up1;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
		case "idle":
			image = idle;
		//case "left":
		//	image = left1;
		//case "right":
		//  image = right1;
		}
		
		g2.drawImage(image, x, y, mp.tileSize, mp.tileSize, null);
	}
}
