package co.uk.jamalantonio.GetOffMyLawn.Entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import co.uk.jamalantonio.GetOffMyLawn.Game;

public class Entity {
	
	final int WIDTH;
	final int HEIGHT;
	final Image IMAGE;
	final Game GAME;
	
	int x, y;
	byte face = 1;
	
	public Entity(int width, int height, Image image, Game game) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.IMAGE = image;
		this.GAME = game;
		
		game.entities.add(this);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public byte getFace() {
		return face;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public ArrayList<Byte> collision() {
		ArrayList<Byte> cols = new ArrayList<Byte>();
		return cols;
	}
	
	public void paint(Graphics2D g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public void tick(){
		
	}
}
