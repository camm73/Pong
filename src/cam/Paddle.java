package cam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle implements KeyListener{

	int player1y = 0;
	int player2y = 0;
	int player;
	boolean left;
	Game game;
	
	public boolean[] keys = new boolean[68836];
	public boolean w, s, up, down;
	
	public int paddleWidth = 25;
	public int paddleHeight = 50;
	
	public Paddle(Game game, int player, boolean left){
		this.left = left;
		this.game = game;
		this.player = player;
	}
	
	public void update(){
		
		w = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		
		
		if(player == 1){
			if(w){
				if(player1y>=0){
					player1y--;
				}
			}
			
			if(s){
				System.out.println("this is a test");
				if(player1y <= game.HEIGHT){
					player1y++;
				}
			}
		}else if(player == 2){
			if(up){
				if(player2y>=0){
					player2y--;
				}
			}
			
			if(down){
				if(player2y< game.HEIGHT){
					player2y++;
				}
			}
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.white);
		if(left){
			g.fillRect(0, player1y, paddleWidth, paddleHeight);
		}else{
			g.fillRect(game.WIDTH-30, player2y, paddleWidth, paddleHeight);
		}
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
