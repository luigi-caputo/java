package gp.test.graphics;

import gp.test.Display;
import gp.test.Game;
import gp.test.level.Level;
import java.awt.Point;

public class Screen extends Render{
	
	private Render3D render3d;
	private Level level;
	final Game game;
	
	public Screen(int width, int height, Game game) {
		super(width, height);
		this.game = game;
		render3d = new Render3D(Display.WIDTH, Display.HEIGHT);
		level = new Level(new Point(60, 60), 1000, 250, render3d, game);
	}

	public void clearScreen(){
		for(int i = 0; i<width*height; i++){
			pixels[i] = 0;
		}
	}
	
	public void rend(){
		clearScreen();
		render3d.floor(game);
		level.draw(render3d, game);
		render3d.fog();
		draw(render3d, 0, 0);
	}
	
	public void update(){
		level.update();
	}
	
}
