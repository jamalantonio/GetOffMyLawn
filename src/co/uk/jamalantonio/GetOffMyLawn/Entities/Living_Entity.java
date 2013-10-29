package co.uk.jamalantonio.GetOffMyLawn.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import co.uk.jamalantonio.GetOffMyLawn.Game;

public class Living_Entity extends Entity {

	final Game GAME;
	
	int health;
	int power;
	int speed;
	int xa = 0;
	int ya = 0;
	int tickTime = 0;
	int maxHealth;
	
	public Living_Entity(int width, int height, Image image, Game game, int health, int power, int speed) {
		super(width, height, image, game);
		
		this.maxHealth = health;
		this.health = health;
		this.power = power;
		this.speed =  speed;
		this.GAME = game;
	}
	
	public void tick() {
		tickTime++;
	}
	
	@Override
	public void paint(Graphics2D g) {
		String healthBar = "";
		int per = this.health * 10 / maxHealth;
		int hx = x - (((health * 3) - WIDTH) / 2);
		
		for (int i = 1; i <= per; i++) {
			healthBar = healthBar + "|";
		}
		
		g.setColor(Color.RED);
		g.drawString(healthBar, hx, y - 5);
	}
	
	@Override 
	public ArrayList<Byte> collision() {
		ArrayList<Byte> cols = new ArrayList<Byte>();
		
		for (int i = 0; i < GAME.entities.size(); i++) {
			Entity ent = GAME.entities.get(i);
			Rectangle eBounds = ent.getBounds();
			Rectangle bounds = this.getBounds();
			
			if (bounds.intersects(eBounds) && ent != this){
				int xe = ent.getX();
				int ye = ent.getY();
				
				if (ye < this.y) cols.add((byte) 1);
				if (xe > this.x) cols.add((byte) 3);
				if (ye > this.y) cols.add((byte) 5);
				if (xe < this.x) cols.add((byte) 7);
			}
			
			if (this.y + ya < 0) cols.add((byte) 1);
			if (this.x + WIDTH + xa > GAME.getWidth()) cols.add((byte) 3);
			if (this.y + HEIGHT + ya > GAME.getHeight()) cols.add((byte) 5);
			if (this.x + xa < 0) cols.add((byte) 7);
		}	

		return cols;	
	}
	
	public Entity getInteraction() {
		Entity partner = null;
		byte face = this.getFace();
		
		for (int i = 0; i < GAME.entities.size(); i++) {
			Entity ent = GAME.entities.get(i);
			Rectangle eBounds = ent.getBounds();
			Rectangle bounds = this.getBounds();
			byte dir = 0;
			
			if (bounds.intersects(eBounds) && ent != this){
				int xe = ent.getX();
				int ye = ent.getY();
				
				if (ye < this.y) dir = 1; //Entity is N
				if (xe > this.x) dir = 3; //Entity is E
				if (ye > this.y) dir = 5; //Entity is S
				if (xe < this.x) dir = 7; //Entity is W
				if (ye < this.y && xe > this.x) dir = 2; //Entity is NE
				if (xe > this.x && ye > this.y) dir = 4; //Entity is SE
				if (ye > this.y && xe < this.x) dir = 6; //Entity is SW
				if (xe < this.x && ye < this.y) dir = 8; //Entity is NW
			}
			
			if (face == dir) partner = ent;
		}	
		
		return partner;
	}
	
	public void interact(Entity e) {
		
	}
	
	void attack(Living_Entity target, int power) {
		target.hurt(power);
	}
	
	void die() {
		System.out.println("Dead");
	}
	
	void hurt(int damage) {
		this.health -= damage;
		
		if (this.health <= 0)
			this.die();
	}
	
	public int getHealth() {
		return health;
	}
}
