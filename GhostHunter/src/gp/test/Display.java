package gp.test;

import gp.test.controls.InputHandler;
import gp.test.graphics.Screen;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Display extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = 480;
	public static int EnemyKilled = 0, life = 1;
	private Thread thread;
	private static final String title = "Ghost Hunter | GicoPiro 2013";
	private boolean running = false;
	private Screen screen;
	private int[] pixels;
	private BufferedImage img;
	private Game game;
	private InputHandler input;
	String fps = "";
	//private int oldx, newx;
	//public static double mouseSpeed;
	
	public Display(){
		Dimension d = new Dimension(WIDTH, HEIGHT);
		setMaximumSize(d);
		setPreferredSize(d);
		setMinimumSize(d);
		
		game = new Game();
		screen = new Screen(WIDTH, HEIGHT, game);
		img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		
		input = new InputHandler();
		addKeyListener(input);
		addMouseListener(input);
		addFocusListener(input);
		addMouseMotionListener(input);
	}
	
	public static void main(String[] args){
		try {
			BufferedImage icon = ImageIO.read(Display.class.getResource("/textures/pistol.png"));
			BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "Cursor");
			Display game = new Display();
			JFrame frame = new JFrame();
			frame.add(game);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setTitle(title);
			frame.setResizable(false);
			frame.setIconImage(icon);
			frame.getContentPane().setCursor(cur);
			game.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start(){
		if(running)return;
		running = true;
		thread = new Thread(this, "MainThread");
		thread.start();
	}
	
	public void stop(){
		if(!running)return;
		running = false;
		try{
			thread.join();
		}catch(Exception e){
			System.exit(1);
		}
	}
	
	public void rend(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		for(int i = 0; i<WIDTH*HEIGHT; i++){
			pixels[i] = screen.pixels[i];
		}
		screen.rend();
		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		g.setFont(new Font("Verdana", 1, 20));
		g.setColor(Color.RED);
		g.drawString("Enemies Killed: " + EnemyKilled, 30, 50);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Verdana", 0, 10));
		g.drawString("Do not touch the enemies!", WIDTH-160, HEIGHT-30);
		g.dispose();
		bs.show();
	}
	
	public void update(){
		requestFocus();
		game.tick(input.key);
		screen.update();
		if(life <= 0 || EnemyKilled >= 250){
			System.exit(0);
		}
		/*
		newx = input.xMouse;
		if(newx > oldx){
			game.controller.turnRight = true;
			game.controller.turnLeft = false;
		}
		if(newx < oldx){
			game.controller.turnRight = false;
			game.controller.turnLeft = true;
		}
		if(newx == oldx){
			game.controller.turnRight = false;
			game.controller.turnLeft = false;
		}
		mouseSpeed = Math.abs(newx - oldx);
		oldx = newx;
		*/
	}
	
	public void run(){
		int tickCount = 0;
		double secondsPerTick = 1/60.0;
		long last = System.nanoTime();
		double unprocessedSeconds = 0;
		int frames = 0;
		
		while(running){
			long now = System.nanoTime();
			long passed = now - last;
			last = now;
			if(passed < 0) passed = 0;
			if(passed > 1000000000) passed = 1000000000;
			unprocessedSeconds += passed/1000000000.0;
			boolean ticked = false;
			
			while(unprocessedSeconds > secondsPerTick){
				update();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				
				if(tickCount % 60.0 == 0){
					fps = Integer.toString(frames);
					frames = 0;
					last += 1000;
				}
			}
			
			if(ticked){
				rend();
				frames++;
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {}
			}
		}
		
	}
	
}
