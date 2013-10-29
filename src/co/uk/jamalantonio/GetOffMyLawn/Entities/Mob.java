package co.uk.jamalantonio.GetOffMyLawn.Entities;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import co.uk.jamalantonio.GetOffMyLawn.Game;

public class Mob extends Living_Entity {
	
	int xd;
	int yd;

	public Mob(int width, int height, Image image, Game game, int health,
			int power, int speed) {
		
		super(width, height, image, game, health, power, speed);
		
		this.x = (int) (Math.random() * 501);
		this.y = (int) (Math.random() * 501);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (tickTime > 300) {
			xa = 0;
			ya = 0;
			xd = GAME.player.getX();
			yd = GAME.player.getY();
			
	
			if (y > yd) this.face = 1; 
			if (x < xd) this.face = 3; 
			if (y < yd) this.face = 5;
			if (x > xd) this.face = 7;  
			if (y > yd && x < xd) this.face = 2;
			if (x < xd && y < yd) this.face = 4;
			if (y < yd && x > xd) this.face = 6;
			if (x > xd && y > yd) this.face = 8;
			  
			if (!collision().contains((byte) 1) && y > yd) ya -= this.speed;
			if (!collision().contains((byte) 3) && x < xd) xa += this.speed; 
			if (!collision().contains((byte) 5) && y < yd) ya += this.speed; 
			if (!collision().contains((byte) 7) && x > xd) xa -= this.speed;
			
			this.x += xa;
			this.y += ya;
			
			interact(getInteraction());
		}
	}

	@Override
	public void paint(Graphics2D g) {
		Image image = new ImageIcon("resources/sprites/zombie1.png").getImage();
		
		if (face == 1) image = new ImageIcon("resources/sprites/zombie1.png").getImage();
		if (face == 2) image = new ImageIcon("resources/sprites/zombie2.png").getImage();
		if (face == 3) image = new ImageIcon("resources/sprites/zombie3.png").getImage();
		if (face == 4) image = new ImageIcon("resources/sprites/zombie4.png").getImage();
		if (face == 5) image = new ImageIcon("resources/sprites/zombie5.png").getImage();
		if (face == 6) image = new ImageIcon("resources/sprites/zombie6.png").getImage();
		if (face == 7) image = new ImageIcon("resources/sprites/zombie7.png").getImage();
		if (face == 8) image = new ImageIcon("resources/sprites/zombie8.png").getImage();
		
		g.drawImage(image, x, y, GAME);
	}
	
	@Override
	public void interact(Entity e) {
		Living_Entity ent = (Living_Entity) e;
		if (ent != null && tickTime % 100 == 0 && ent == GAME.player) {
			ent.hurt(this.power);
		}
	}
	
	@Override
	void die() {
		GAME.player.score++;
		GAME.entities.remove(this);
	}
}
