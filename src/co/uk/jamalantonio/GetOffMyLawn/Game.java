package co.uk.jamalantonio.GetOffMyLawn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import co.uk.jamalantonio.GetOffMyLawn.Entities.Entity;
import co.uk.jamalantonio.GetOffMyLawn.Entities.Mob;
import co.uk.jamalantonio.GetOffMyLawn.Entities.Player;

@SuppressWarnings("serial")
public class Game extends JPanel {
	
	public static boolean playing = true;
	
	public List<Entity> entities = new ArrayList<Entity>();
	InputHandler input = new InputHandler();
	
	public Player player = new Player(24, 24, null, this, input);
	public Mob mob = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob2 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob3 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob4 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob5 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob6 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob7 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob8 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob9 = new Mob(24, 24, null, this, 1, 1, 1);
	public Mob mob10 = new Mob(24, 24, null, this, 1, 1, 1);
	
	public Game() {
		addKeyListener(input);
		setFocusable(true);
        setBackground(new Color(0, 128, 0)); 
	}
	 
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Get Off My Lawn");
		Game game = new Game();
		
		frame.add(game);
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		while(playing) {
			game.tick();
			game.repaint();
			Thread.sleep(10);
		}
	}
	
	private void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).paint(g2d);
		}
		
		
		//Temporary "GUI"
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Veranda", Font.BOLD, 30));
		g2d.drawString("Score: " + String.valueOf(player.getScore()), 10, 30);
		g2d.setColor(Color.RED);
		g2d.drawString("Health: " + String.valueOf(player.getHealth()), 10, 70);
		
	}
	
	public void gameOver() {
		playing = false;
	}
}
