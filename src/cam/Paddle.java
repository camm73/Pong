package cam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle{

	int y = 0;
	int player;
	boolean left;
	Game game;
	
	private Keys keys;
	
	public int paddleWidth = 25;
	public int paddleHeight = 50;
	public int speed = 5;
	private int bumper = 80;
	
	public Paddle(Game game, int player, boolean left, Keys keys){
		this.left = left;
		this.game = game;
		this.player = player;
		this.keys = keys;
	}
	
	public void update(){
		
		if(player == 1){
			if(keys.w){
				if(y>=0){
					y-= speed;
				}
			}
			
			if(keys.s){
				System.out.println("this is a test");
				if(y <= game.HEIGHT-bumper){
					y+= speed;
				}
			}
		}else if(player == 2){
			if(keys.up){
				if(y>=0){
					y-= speed;
				}
			}
			
			if(keys.down){
				if(y< game.HEIGHT-bumper){
					y+=speed;
				}
			}
		}
	}
}
