package gp.test.level;

import gp.test.Game;
import gp.test.graphics.Render3D;

public class Pilon{

	private final double xpos, zpos;
	
	public Pilon(double xpos, double zpos){
		this.xpos = xpos;
		this.zpos = zpos;
	}
	
	public void drawPilon(Render3D render, Game game){
		//First block//
				render.drawWalls(0.5 + xpos, 0 + xpos, 1.5 + zpos, 1.5 + zpos, 0, 64);
				render.drawWalls(0 + xpos, 0 + xpos, 1.5 + zpos, 1.0 + zpos, 0, 64);
				render.drawWalls(0 + xpos, 0.5 + xpos, 1 + zpos, 1 + zpos, 0, 64);
				render.drawWalls(0.5 + xpos, 0.5 + xpos, 1 + zpos, 1.5 + zpos, 0, 64);
	    //Second block//
				render.drawWalls(0.5 + xpos, 0 + xpos, 1.5 + zpos, 1.5 + zpos, 0.5, 64);
				render.drawWalls(0 + xpos, 0 + xpos, 1.5 + zpos, 1.0 + zpos, 0.5, 64);
				render.drawWalls(0 + xpos, 0.5 + xpos, 1 + zpos, 1 + zpos, 0.5, 64);
				render.drawWalls(0.5 + xpos, 0.5 + xpos, 1 + zpos, 1.5 + zpos, 0.5, 64);
	}
	
	public void collisionMethod(double pX, double pY, Game game){

		double adjust = 0.5;
		
		if(pX >= 0 + xpos && pX <= 0.5 + xpos &&
				pY+adjust >= 1 + zpos && pY + adjust <= 1.5 + zpos){
			game.controller.x++;
		}
		
		if(pX >= 0 + xpos && pX <= 0.5 + xpos &&
				pY - adjust >= 1 + zpos && pY - adjust <= 1.5 + zpos){
			game.controller.x--;
		}
		
		if(pX + adjust >= 0 + xpos && pX + adjust <= 0.5 + xpos &&
				pY >= 1 + zpos && pY <= 1.5 + zpos){
			game.controller.z--;
		}
		
		if(pX - adjust >= 0 + xpos && pX - adjust <= 0.5 + xpos &&
				pY >= 1 + zpos && pY <= 1.5 + zpos){
			game.controller.z++;
		}
		
		double adjust2 = 0.399;
		
		if(pX - adjust2 >= 0 + xpos && pX - adjust2 <= 0.5 + xpos &&
				pY - adjust2 >= 1 + zpos && pY - adjust2 <= 1.5 + zpos){
			game.controller.z++;
		}
		
		if(pX + adjust2 >= 0 + xpos && pX + adjust2 <= 0.5 + xpos &&
				pY + adjust2 >= 1 + zpos && pY + adjust2 <= 1.5 + zpos){
			game.controller.z--;
		}
		
		if(pX - adjust2 >= 0 + xpos && pX - adjust2 <= 0.5 + xpos &&
				pY + 0.35 >= 1 + zpos && pY + 0.35 <= 1.5 + zpos){
			game.controller.z++;
		}
		
		if(pX + adjust2 >= 0 + xpos && pX + adjust2 <= 0.5 + xpos &&
				pY - adjust2 >= 1 + zpos && pY - adjust2 <= 1.5 + zpos){
			game.controller.z--;
		}
	}
}
