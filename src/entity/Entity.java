package entity;

import java.awt.image.BufferedImage;

//Parent class for the player
public class Entity {

	
	public int x, y;
	public int speed;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, left1, left2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
