package co.uk.jamalantonio.GetOffMyLawn.Entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import co.uk.jamalantonio.GetOffMyLawn.Game;

public abstract class Entity {
	
	private final int WIDTH;
	private final int HEIGHT;
	private final Image IMAGE;
	private final Game GAME;
	
	private int x, y;
	private byte face = 1;
	
	public Entity(int width, int height, Image image, Game game) {
		
		this.WIDTH = width;
		this.HEIGHT = height;
		this.IMAGE = image;
		this.GAME = game;
		
		game.entities.add(this);
		
	}
	
	public int getWidth() { return WIDTH; }
	public int getHeight() { return HEIGHT; }
	public Game getGame() { return GAME; }
	public int getX() { return x; }
	public int getY() { return y; }
	public byte getFace() { return face; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setFace(int face) { this.face = (byte) face; }
	
	public Rectangle getBounds() { return new Rectangle(x, y, WIDTH, HEIGHT); }
	
	public ArrayList<Byte> collision() {
		
		ArrayList<Byte> cols = new ArrayList<Byte>();
		return cols;
		
	}
	
	public void paint(Graphics2D g) { g.fillRect(x, y, WIDTH, HEIGHT); }
	
	public abstract void tick();
}
