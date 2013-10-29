package co.uk.jamalantonio.GetOffMyLawn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
	
	public class Key {
		public boolean down = false;
		
		public Key() {
			keys.add(this);
		}
			
		public void toggle(boolean pressed) {
			if (down != pressed) {
				down = pressed;
			}
		}
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void toggle(KeyEvent e, boolean pressed) {
		if (e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
	}
}
