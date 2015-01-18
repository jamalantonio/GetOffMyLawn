package co.uk.jamalantonio.GetOffMyLawn.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import co.uk.jamalantonio.GetOffMyLawn.Game;

public class Living_Entity extends Entity {
	
	private int health;
	private int power;
	private int speed;
	private int xa = 0;
	private int ya = 0;
	private int tickTime = 0;
	private int maxHealth;
	
	public Living_Entity(int width, int height, Image image, Game game, int health, int power, int speed) {
		
		super(width, height, image, game);
		
		this.maxHealth = health;
		this.health = health;
		this.power = power;
		this.speed =  speed;
		
	}
	
	public int getHealth() { return health; }
	public int getPower() { return power; }
	public int getSpeed() { return speed; }
	public int getXa() { return xa; }
	public int getYa() { return ya; }
	public int getTickTime() { return tickTime; }
	public int getMaxHealth() { return maxHealth; }
	
	public void setXa(int xa) { this.xa = xa; }
	public void setYa(int ya) { this.ya = ya; }
	public void setHealth(int health) { this.health = health; }
	public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
	public void setPower(int power) { this.power = power; }
	
	public void tick() { tickTime++; }
	
	@Override
	public void paint(Graphics2D g) {
		
		String healthBar = "";
		
		int per = this.health * 10 / maxHealth;
		int hx = getX() - (((per * 3) - getWidth()) / 2);
		
		for (int i = 1; i <= per; i++) {
			
			healthBar = healthBar + "|";
			
		}
		
		g.setColor(Color.RED);
		g.drawString(healthBar, hx, getY() - 5);
		
	}
	
	@Override 
	public ArrayList<Byte> collision() {
		
		ArrayList<Byte> cols = new ArrayList<Byte>();
		
		for (int i = 0; i < getGame().entities.size(); i++) {
			
			Entity ent = getGame().entities.get(i);
			Rectangle eBounds = ent.getBounds();
			Rectangle bounds = this.getBounds();
			
			if (bounds.intersects(eBounds) && ent != this){
				
				int xe = ent.getX();
				int ye = ent.getY();
				
				if (ye < getY()) cols.add((byte) 1);
				if (xe > getX()) cols.add((byte) 3);
				if (ye > getY()) cols.add((byte) 5);
				if (xe < getX()) cols.add((byte) 7);
				
			}
			
			if (getY() + ya < 0) cols.add((byte) 1);
			if (getX() + getWidth() + xa > getGame().getWidth()) cols.add((byte) 3);
			if (getY() + getHeight() + ya > getGame().getHeight()) cols.add((byte) 5);
			if (getX() + xa < 0) cols.add((byte) 7);
			
		}	

		return cols;	
	}
	
	public Entity getInteraction() {
		
		Entity partner = null;
		byte face = this.getFace();
		
		for (int i = 0; i < getGame().entities.size(); i++) {
			
			Entity ent = getGame().entities.get(i);
			Rectangle eBounds = ent.getBounds();
			Rectangle bounds = this.getBounds();
			byte dir = 0;
			
			if (bounds.intersects(eBounds) && ent != this){
				
				int xe = ent.getX();
				int ye = ent.getY();
				
				if (ye < getY()) dir = 1; //Entity is N
				if (xe > getX()) dir = 3; //Entity is E
				if (ye > getY()) dir = 5; //Entity is S
				if (xe < getX()) dir = 7; //Entity is W
				if (ye < getY() && xe > getX()) dir = 2; //Entity is NE
				if (xe > getX() && ye > getY()) dir = 4; //Entity is SE
				if (ye > getY() && xe < getX()) dir = 6; //Entity is SW
				if (xe < getX() && ye < getY()) dir = 8; //Entity is NW
				
			}
			
			if (face == dir) partner = ent;
			
		}	
		
		return partner;
		
	}
	
	public void interact(Entity e) {}
	
	void attack(Living_Entity target, int power) { target.hurt(power); }
	
	void die() { System.out.println("Dead"); }
	
	void hurt(int damage) {
		
		this.health -= damage;
		
		if (this.health <= 0)
			this.die();
		
	}
}
