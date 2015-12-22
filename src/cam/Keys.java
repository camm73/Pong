package cam;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {

	public boolean[] keys = new boolean[68836];
	public boolean up, down, w, s;
	
	public void update(){
		w = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
