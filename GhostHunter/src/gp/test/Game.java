package gp.test;

import gp.test.controls.Controls;

import java.awt.event.KeyEvent;

public class Game {
public int time = 0;

public Controls controller;
public boolean fire = false;

public Game(){
	controller = new Controls();
}

public void tick(boolean[] key){
	time++;
	boolean forward = key[KeyEvent.VK_W];
	boolean back = key[KeyEvent.VK_S];
	boolean left = key[KeyEvent.VK_A];
	boolean right = key[KeyEvent.VK_D];
	boolean jump = key[KeyEvent.VK_SPACE];
	boolean turnLeft = key[KeyEvent.VK_LEFT];
	boolean turnRight = key[KeyEvent.VK_RIGHT];
	
	fire = key[KeyEvent.VK_UP];
	
	controller.tick(forward, back, right, left, jump, turnRight, turnLeft);
	
	if(key[KeyEvent.VK_ESCAPE]){
		System.exit(0);
	}
}
}
