package co.uk.jamalantonio.GetOffMyLawn.Entities;

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
	
	public Living_Entity(int width, int height, Image image, Game game, int health, int power, int speed) {
		super(width, height, image, game);
		
		this.health = health;
		this.power = power;
		this.speed =  speed;
		this.GAME = game;
	}
	
	public void tick() {
		tickTime++;
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
				
				if (ye < this.y) dir = 1;
				if (xe > this.x) dir = 3;
				if (ye > this.y) dir = 5;
				if (xe < this.x) dir = 7;
				if (y > ye && x < xe) dir = 2;
				if (x < xe && y < ye) dir = 4;
				if (y < ye && x > xe) dir = 6;
				if (x > xe && y > ye) dir = 8;
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
