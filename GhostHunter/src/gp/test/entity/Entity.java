package gp.test.entity;

import gp.test.Game;
import gp.test.graphics.Render3D;

public class Entity {

	protected Render3D render;
	protected Game game;
	public double x, y, z, speed;
	
	public Entity(double x, double z, Render3D render, Game game){
		this.render = render;
		this.game = game;
		this.x = x;
		this.z = z;
	}
	
	
	public void render(){
		
	}
	
	public void update(){
		
	}
	
	public boolean collision(double xx, double yy, double zz){
		if((Math.round(xx) == Math.round(x) && Math.round(zz) == Math.round(z)) ||
				(Math.round(xx) == Math.round(x) && Math.round(zz) == Math.round(z) && yy == Math.round(y))){
			return true;
		}
		return false;
	}
}
