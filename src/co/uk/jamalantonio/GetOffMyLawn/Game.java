package co.uk.jamalantonio.GetOffMyLawn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import co.uk.jamalantonio.GetOffMyLawn.Entities.Entity;
//import co.uk.jamalantonio.GetOffMyLawn.Entities.Mob;
import co.uk.jamalantonio.GetOffMyLawn.Entities.Player;

@SuppressWarnings("serial")
public class Game extends JPanel {
	
	public static boolean playing = true;
	
	public List<Entity> entities = new ArrayList<Entity>();
	InputHandler input = new InputHandler();
	
	public Player player = new Player(24, 24, null, this, input);
	public WaveManager wm = new WaveManager(this);
	/*public Mob mob = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob2 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob3 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob4 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob5 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob6 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob7 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob8 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob9 = new Mob(24, 24, null, this, 5, 1, 1);
	public Mob mob10 = new Mob(24, 24, null, this, 5, 1, 1);*/
	
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
		wm.tick();
		
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
		
		BufferedImage image = null;
		
		try {
			image = (BufferedImage)ImageIO.read(new File("resources/backgrounds/grass1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image.getGraphics().drawImage(image, 0, 0, null);
		g.drawImage(image, 0 , 0, null);
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).paint(g2d);
		}
		
		
		//Temporary "GUI"
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Veranda", Font.BOLD, 15));
		g2d.drawString("Score: " + String.valueOf(player.getScore()), 10, 15);
		g2d.drawString("Wave: " + String.valueOf(wm.getWave()), 10, 30);
		g2d.drawString("Level: " + String.valueOf(player.getLevel()), 10, 45);
		
	}
	
	public void gameOver() {
		playing = false;
	}
}
