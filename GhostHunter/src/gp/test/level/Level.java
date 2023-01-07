package gp.test.level;

import gp.test.Display;
import gp.test.Game;
import gp.test.SoundManager;
import gp.test.entity.Bullet;
import gp.test.entity.Entity;
import gp.test.entity.Monster;
import gp.test.graphics.Render3D;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Level {
	
	private Pilon[] pilon;
	private ArrayList<Entity> b;
	private Random random;
	protected Render3D render;
	protected Game game;
	
	public Level(Point area, int maxPilon, int maxGhost, Render3D render, Game game){
		this.render = render;
		this.game = game;
		
		b = new ArrayList<Entity>();
		
		pilon = new Pilon[maxPilon];
		random = new Random();
		
		for(int i = 0;i<pilon.length; i++){
			pilon[i] = new Pilon(random.nextInt(area.x), random.nextInt(area.y));
		}
		
		for(int i = 0; i<maxGhost; i++){
			b.add(new Monster(random.nextInt(area.x), random.nextInt(area.y), render, game));
		}
	}
	
	public void draw(Render3D render3d, Game game){
		for(int i = 0;i<pilon.length; i++){
			pilon[i].drawPilon(render3d, game);
		}
		
		for(int i = 0; i<b.size(); i++){
			b.get(i).render();
		}
	}
	
	public void update(){
		for(int i = 0;i<pilon.length; i++){
			pilon[i].collisionMethod(render.pX, render.pY, game);
		}
		
		if(game.fire){
			SoundManager.shoot();
			b.add(new Bullet(render.pX*2, render.pY*2, Math.sin(game.controller.rotation), 
					Math.cos(game.controller.rotation), render, game));
		}
		
		for(int i = 0; i<b.size(); i++){
			b.get(i).update();
			if(b.get(i) instanceof Monster){
				if(b.get(i).collision(render.pX*2, 0, render.pY*2)){
					Display.life--;
				}
			}
			
			if(b.get(i) instanceof Bullet){
				
				Bullet bul = (Bullet)b.get(i);
				
				if(Math.abs(bul.z) > Math.abs(bul.z0) + 3 || Math.abs(bul.x) > Math.abs(bul.x0) + 3){
					b.remove(bul);
				}
				
					for(int k = 0; k<b.size(); k++){
						if(b.get(k) instanceof Monster){
							Monster m = (Monster)b.get(k);
							if(bul.collision(m.x, m.y, m.z)){
								b.remove(m);
								Display.EnemyKilled++;
							}
						}
					}
			}
			
		}
	}
	
	
}
