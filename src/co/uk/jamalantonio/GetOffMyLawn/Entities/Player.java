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
		this.x = 250 - WIDTH;
		this.y = 250 - HEIGHT;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		this.ya = 0;
		this.xa = 0;
		
		if (input.up.down) this.face = 1;
		if (input.right.down) this.face = 3;
		if (input.down.down) this.face = 5;
		if (input.left.down) this.face = 7;
		if (input.up.down && input.right.down) this.face = 2;
		if (input.right.down && input.down.down) this.face = 4;
		if (input.down.down && input.left.down) this.face = 6;
		if (input.left.down && input.up.down) this.face = 8;
		
		if (input.up.down && !collision().contains((byte) 1)) this.ya -= this.speed;
		if (input.right.down && !collision().contains((byte) 3)) this.xa += this.speed;
		if (input.down.down && !collision().contains((byte) 5)) this.ya += this.speed;
		if (input.left.down && !collision().contains((byte) 7)) this.xa -= this.speed;
		
		this.x += xa;
		this.y += ya;
		
		interact(getInteraction());
		updateStats();
	}
	
	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		
		Image image = new ImageIcon("resources/sprites/player1.png").getImage();
		
		if (face == 1) image = new ImageIcon("resources/sprites/player1.png").getImage();
		if (face == 2) image = new ImageIcon("resources/sprites/player2.png").getImage();
		if (face == 3) image = new ImageIcon("resources/sprites/player3.png").getImage();
		if (face == 4) image = new ImageIcon("resources/sprites/player4.png").getImage();
		if (face == 5) image = new ImageIcon("resources/sprites/player5.png").getImage();
		if (face == 6) image = new ImageIcon("resources/sprites/player6.png").getImage();
		if (face == 7) image = new ImageIcon("resources/sprites/player7.png").getImage();
		if (face == 8) image = new ImageIcon("resources/sprites/player8.png").getImage();
		
		g.drawImage(image, x, y, GAME);
	}
	
	@Override
	public void interact(Entity e) {
		Living_Entity ent = (Living_Entity) e;
		if (ent != null && input.attack.down && tickTime % 20 == 0) {
			ent.hurt(this.power);
		}
	}
	
	void updateStats() {
		
		if (score == Math.round((Math.pow(level, 2) / 2 + 5))) {
			this.level++;
			this.maxHealth = 9 + level;
			this.health = maxHealth;
			this.power++;
		}
	}
	
	@Override
	void die() {
		JOptionPane.showMessageDialog(GAME, "You scored: " + score, "Game Over", JOptionPane.YES_NO_OPTION);
		GAME.gameOver();
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
}
