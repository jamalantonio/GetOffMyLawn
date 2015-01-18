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
		
		setX((int) (Math.random() * 501));
		setY((int) (Math.random() * 501));
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (getTickTime() > 300) {
			setXa(0);
			setYa(0);
			xd = getGame().player.getX();
			yd = getGame().player.getY();
			
	
			if (getY() > yd) setFace(1); 
			if (getX() < xd) setFace(3); 
			if (getY() < yd) setFace(5);
			if (getX() > xd) setFace(7);  
			if (getY() > yd && getX() < xd) setFace(2);
			if (getX() < xd && getY() < yd) setFace(4);
			if (getY() < yd && getX() > xd) setFace(6);
			if (getX() > xd && getY() > yd) setFace(8);
			  
			if (!collision().contains((byte) 1) && getY() > yd) setYa(getYa() - getSpeed());
			if (!collision().contains((byte) 3) && getX() < xd) setXa(getXa() + getSpeed()); 
			if (!collision().contains((byte) 5) && getY() < yd) setYa(getYa() + getSpeed()); 
			if (!collision().contains((byte) 7) && getX() > xd) setXa(getXa() - getSpeed());
			
			setX(getX() + getXa());
			setY(getY() + getYa());
			
			interact(getInteraction());
		}
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		
		Image image = new ImageIcon("resources/sprites/zombie1.png").getImage();
		
		if (getFace() == 1) image = new ImageIcon("resources/sprites/zombie1.png").getImage();
		if (getFace() == 2) image = new ImageIcon("resources/sprites/zombie2.png").getImage();
		if (getFace() == 3) image = new ImageIcon("resources/sprites/zombie3.png").getImage();
		if (getFace() == 4) image = new ImageIcon("resources/sprites/zombie4.png").getImage();
		if (getFace() == 5) image = new ImageIcon("resources/sprites/zombie5.png").getImage();
		if (getFace() == 6) image = new ImageIcon("resources/sprites/zombie6.png").getImage();
		if (getFace() == 7) image = new ImageIcon("resources/sprites/zombie7.png").getImage();
		if (getFace() == 8) image = new ImageIcon("resources/sprites/zombie8.png").getImage();
		
		g.drawImage(image, getX(), getY(), getGame());
	}
	
	@Override
	public void interact(Entity e) {
		Living_Entity ent = (Living_Entity) e;
		if (ent != null && getTickTime() % 100 == 0 && ent == getGame().player) {
			ent.hurt(getPower());
		}
	}
	
	@Override
	void die() {
		getGame().player.score++;
		getGame().entities.remove(this);
	}
}
