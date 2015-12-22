package cam;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public final int WIDTH = 600;
	public final int HEIGHT = 400;

	private JFrame frame;
	private Thread thread;
	private Paddle player1;
	private Paddle player2;
	private Keys keys = new Keys();

	public boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		addKeyListener(keys);
		
		player1 = new Paddle(this, 1, true, keys);
		player2 = new Paddle(this, 2, false, keys);
		frame = new JFrame();
		createFrame();
	}

	public synchronized void start() {
		thread = new Thread(this, "Game");
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		running = false;
	}

	public void run() {
		long prev = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double limit = 1000000000.0 / 60.0;
		int updates = 0;
		int frames = 0;
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - prev) / limit;
			prev = now;
			if (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if ((System.currentTimeMillis() - timer) > 1) {
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.fillRect(0, player1.y, player1.paddleWidth, player1.paddleHeight);
		g.fillRect(WIDTH-30, player2.y, player2.paddleWidth, player2.paddleHeight);
		g.dispose();
		bs.show();
	}
	
	
	public void update() {
		keys.update();
		player1.update();
		player2.update();
	}
	
	public void createFrame() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Pong 2.0");
		frame.add(this);
		frame.addKeyListener(keys);
		frame.setVisible(true);

		start();
	}

}
