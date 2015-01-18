package co.uk.jamalantonio.GetOffMyLawn.Entities;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import co.uk.jamalantonio.GetOffMyLawn.Game;
import co.uk.jamalantonio.GetOffMyLawn.InputHandler;

public class Player extends Living_Entity {

	int score = 0;
	int level = 1;
	InputHandler input = new InputHandler();
	
	public Player(int width, int height, Image image, Game game, InputHandler input) {
		
		super(width, height, image, game, 10, 2, 2);
		
		this.input = input;
		setX(250 - getWidth());
		setY(250 - getHeight());
	}
	
	@Override
	public void tick() {
		super.tick();
		
		setYa(0);
		setXa(0);
		
		if (input.up.down) setFace(1);
		if (input.right.down) setFace(3);
		if (input.down.down) setFace(5);
		if (input.left.down) setFace(7);
		if (input.up.down && input.right.down) setFace(2);
		if (input.right.down && input.down.down) setFace(4);
		if (input.down.down && input.left.down) setFace(6);
		if (input.left.down && input.up.down) setFace(8);
		
		if (input.up.down && !collision().contains((byte) 1)) setYa(getYa() - getSpeed());
		if (input.right.down && !collision().contains((byte) 3)) setXa(getXa() + getSpeed());
		if (input.down.down && !collision().contains((byte) 5)) setYa(getYa() + getSpeed());
		if (input.left.down && !collision().contains((byte) 7)) setXa(getXa() - getSpeed());
		
		setX(getX() + getXa());
		setY(getY() + getYa());
		
		interact(getInteraction());
		updateStats();
	}
	
	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		
		Image image = new ImageIcon("resources/sprites/player1.png").getImage();
		
		if (getFace() == 1) image = new ImageIcon("resources/sprites/player1.png").getImage();
		if (getFace() == 2) image = new ImageIcon("resources/sprites/player2.png").getImage();
		if (getFace() == 3) image = new ImageIcon("resources/sprites/player3.png").getImage();
		if (getFace() == 4) image = new ImageIcon("resources/sprites/player4.png").getImage();
		if (getFace() == 5) image = new ImageIcon("resources/sprites/player5.png").getImage();
		if (getFace() == 6) image = new ImageIcon("resources/sprites/player6.png").getImage();
		if (getFace() == 7) image = new ImageIcon("resources/sprites/player7.png").getImage();
		if (getFace() == 8) image = new ImageIcon("resources/sprites/player8.png").getImage();
		
		g.drawImage(image, getX(), getY(), getGame());
	}
	
	@Override
	public void interact(Entity e) {
		Living_Entity ent = (Living_Entity) e;
		if (ent != null && input.attack.down && getTickTime() % 20 == 0) {
			ent.hurt(getPower());
		}
	}
	
	void updateStats() {
		int exp = score * 5;
		int expNeeded = 10 * this.level * (1 + this.level);
		
		if (exp >= expNeeded) {
			this.level++;
			setMaxHealth(9 + level);
			setHealth(getMaxHealth());
			setPower(getPower() + 1);
		}
	}
	
	@Override
	void die() {
		JOptionPane.showMessageDialog(getGame(), "You scored: " + score, "Game Over", JOptionPane.YES_NO_OPTION);
		getGame().gameOver();
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
}
