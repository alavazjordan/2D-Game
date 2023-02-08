package entity;

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
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Down2.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Up2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right2.png"));
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
	}
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			else if(spriteNum == 2) {
				image = up2;
			}
		case "down":
			if(spriteNum == 1) {
				image = down1;
				System.out.println("not bruh");
			}
			else if(spriteNum == 2) {
				image = down2;
			}
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			else if(spriteNum == 2) {
				image = left2;
			}
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			else if(spriteNum == 2) {
				image = right2;
				System.out.println("Bruh");
			}
		}
		System.out.println(direction);
		g2.drawImage(image, x, y, mp.tileSize, mp.tileSize, null);
	}
}
