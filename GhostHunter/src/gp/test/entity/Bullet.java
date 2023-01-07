package gp.test.entity;

import gp.test.Game;
import gp.test.graphics.Render3D;
import gp.test.graphics.Texture;

public class Bullet extends Entity{
	final double vecx, vecy;
	public boolean noLonger = false;
	public double z0, x0;
	
	public Bullet(double x, double z, double vecx, double vecy, 
			Render3D render, Game game) {
		super(x, z, render, game);
		speed = 0.6;
		this.vecx = vecx;
		this.vecy = vecy;
		z0 = z;
		x0 = x;
	}

	public void render(){
		render.drawBitmap3D(32, 32, 32, 32, x, z, 0, Texture.pistol, 0);
	}
	
	public void update(){
		x += vecx*speed;
		z += vecy*speed;
	}
}
