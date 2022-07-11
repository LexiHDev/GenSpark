package com.genspark.utils;

import com.genspark.Main;
import com.genspark.entities.Humanoid;
import com.genspark.tiles.Tile;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class GUI extends JFrame implements KeyListener
{
	public int tileWidth = 800;
	public int tileHeight = 800;
	int size = 30;
	public Directions direction;
	public boolean playing;
	public GamePanel gPanel;
	
	@Override
	public void keyTyped(KeyEvent e){
	}
	
	@Override
	public void keyPressed(KeyEvent e){
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Main.direction = Directions.NORTH;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			Main.direction = Directions.WEST;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Main.direction = Directions.EAST;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			Main.direction = Directions.SOUTH;
		}
	}
	
	private class GamePanel extends JPanel {
		GameGrids gameGrids;
		
		public GamePanel(GameGrids gameGrids)
		{
			this.gameGrids = gameGrids;
			super.setDoubleBuffered(true);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setBackground(new Color(174, 119, 255));
			g2d.clearRect(0, 0, 900, 900);
			g2d.setPaint(new Color(11, 1, 21, 255));
			g2d.fillRect(5, 5, tileWidth - 25, tileHeight - 50);  // - 50 height, - 25 width. (Why idk!)
			g2d.drawRect(10, 10, tileWidth - 35, tileHeight - 60);
			
			g2d.fillRect(tileWidth,  10, tileWidth, tileHeight - 60);
			try
			{
				OutputStream.nullOutputStream().write(Main.messages.toString().getBytes(StandardCharsets.UTF_8));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			Optional<String> str = Main.messages
				.subList(Math.max(Main.messages.size() - 10, 0), Main.messages.size()).stream()
				.reduce((acc, com) -> acc + com + "\n");
			
			g2d.setPaint(new Color(175, 175, 175));
			AtomicInteger y = new AtomicInteger(15);
			str.ifPresent(s -> {
				for (String line : s.split("\n"))
					g2d.drawString(line, tileWidth + 5, y.addAndGet(g.getFontMetrics().getHeight()));
			});
			
			y.set(11);
			
			for (int horizontalSlice = 0; horizontalSlice < gameGrids.tileGrid[0].length; horizontalSlice++)
			{
				int x = 11;
				// new line
				// add left border.
				for (int verticalSlice = 0; verticalSlice < gameGrids.tileGrid.length; verticalSlice++)
				{
					if (gameGrids.characterGrid[verticalSlice][horizontalSlice] instanceof Humanoid) // Check for a humanoid
					{ // with
						g2d.drawImage(((Humanoid) gameGrids.characterGrid[verticalSlice][horizontalSlice]).toPixelArt(), x, y.get(), null); // character if exists
					}
					else {
						g2d.drawImage(((Tile) gameGrids.tileGrid[verticalSlice][horizontalSlice]).toPixelArt(), x, y.get(), null); // tile if character null.
					}
					x += size + 1;
				}
				y.addAndGet(size + 1);
				// add right border.
			}
			
			g2d.fillRect(905, 0, 900, 900);
		}
	}
	
	public GUI(GameGrids gameGrids, Directions direction, boolean playing)
	{
		addKeyListener(this);
		this.direction = direction;
		this.playing = playing;
		super.setTitle("Humans VS. Goblins.");
		super.setSize(tileWidth, tileHeight);
		super.setResizable(true);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gPanel = new GamePanel(gameGrids);
		tileWidth = (gameGrids.tileGrid.length + 1) * size + 15;
		tileHeight = (gameGrids.tileGrid[0].length + 2) * size + 15;
		super.setSize(tileWidth + 400, tileHeight);
		
		super.add(gPanel);
	}

}
