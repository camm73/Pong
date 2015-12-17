package cam;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	public final int WIDTH = 600;
	public final int HEIGHT = 400;
	
	private JFrame frame;
	private Thread thread;
	
	public boolean running = false;
	
	public Game(){
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		frame = new JFrame();
		createFrame();
	}
	
	public synchronized void start(){
		thread = new Thread(this, "Game");
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		running = false;
	}
	
	public void run(){
		long prev = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double limit = 1000000000.0 / 60.0;
		int updates = 0;
		int frames = 0;
		
		
		while(running){
			
		}
	}
	
	
	public void createFrame(){
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Pong 2.0");
		frame.add(this);
		frame.setVisible(true);
		
		start();
	}

}
