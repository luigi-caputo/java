package gp.test.entity;

import gp.test.Game;
import gp.test.graphics.Render3D;
import gp.test.graphics.Texture;

import java.util.Random;

public class Monster extends Entity{

	Random random = new Random();
	
	public Monster(double x, double z, Render3D render, Game game) {
		super(x, z, render, game);
		speed = 0.05;
	}

	public void render(){
		render.drawBitmap3D(64, 64, 64, 64, x, z, y, Texture.monster1, 0);
	}
	
	public void update(){
		
		boolean xb = random.nextBoolean();
		boolean zb = random.nextBoolean();
		
		if(xb && x > 0 && x < 100){
			x += speed;
		}else{
			x -= speed;
		}
		
		if(zb && z > 0 && z < 100){
			z += speed;
		}else{
			z -= speed;
		}
		
		y = Math.sin(game.time % 100.0/1000.0 * Math.PI);
	}
	
	
}
