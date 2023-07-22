package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	public Player player = new Player(this, keyHandler);
	TileManager tileM = new TileManager(this);

	// World setting
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxScreenCol;
	public final int worldHeight = tileSize * maxScreenRow;
	
	// FPS
	int FPS = 60;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		startGameThread();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		int milions = 1000000000;
		double drawInterval = milions / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime = lastTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				// 1. UPDATE: update information of character or something, ...
				update();
				// 2. DRAW: draw the screen with updated information
				repaint();

				delta--;
			}

		}
	}

	public void update() {

		// Player Moving
		player.update();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);

		player.draw(g2);
		
		g2.dispose();

	}
}