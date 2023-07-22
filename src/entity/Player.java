package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		setDefaultValues();
		getPlayerImage();
	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_up_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_left_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player/Walking sprites/boy_right_2.png"));
		} catch (IOException e) {
			System.out.println("CAN NOT READ PLAYER IMAGE");
		}
	}

	public void setDefaultValues() {
		worldX = gp.tileSize*23;
		worldY = gp.tileSize*21;
		speed = 4;
		direction = "down";
	}

	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed) {
				worldY -= speed;
				direction = "up";
			} else if (keyH.downPressed) {
				worldY += speed;
				direction = "down";
			} else if (keyH.leftPressed) {
				worldX -= speed;
				direction = "left";
			} else if (keyH.rightPressed) {
				worldX += speed;
				direction = "right";
			}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				spriteNum = spriteNum % 2 + 1;
				spriteCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		switch (direction) {
		case "up": {
			if (spriteNum == 1) {
				image = up1;
			}
			else if (spriteNum == 2) {
				image = up2;
			}
			break;
		}
		case "down": {
			if (spriteNum == 1) {
				image = down1;
			}
			else if (spriteNum == 2) {
				image = down2;
			}
			break;
		}
		case "left": {
			if (spriteNum == 1) {
				image = left1;
			}
			else if (spriteNum == 2) {
				image = left2;
			}
			break;
		}
		case "right": {
			if (spriteNum == 1) {
				image = right1;
			}
			else if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
