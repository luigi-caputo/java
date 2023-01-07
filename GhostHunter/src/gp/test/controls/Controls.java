package gp.test.controls;

public class Controls {

	public double x,y,z,rotation,xa,za,rotationa;
	//public boolean turnRight, turnLeft;
	
	public void tick(boolean forward, boolean back, boolean right, boolean left, boolean jump, boolean turnRight, boolean turnLeft){
		double xMove = 0;
		double zMove = 0;
		double walkSpeed = 1.0;
		double rotationSpeed = 0.08;
		double crouchHeight = -0.5;
		
		if(forward){zMove++;}
		
		if(back){zMove--;}
		
		if(right){xMove++;}
		
		if(left){xMove--;}
		
		if(turnLeft){
			rotation -= rotationSpeed;
		}
		if(turnRight){
			rotation += rotationSpeed;
		}
		
		if(jump){
			y+=crouchHeight;
		}
		
		xa = (xMove * Math.sin(rotation) - zMove * Math.cos(rotation))*walkSpeed;
		za = (zMove * Math.sin(rotation) + xMove * Math.cos(rotation))*walkSpeed;
		
		x += xa;
		z += za;
		y *= 0.8;
		rotation += rotationa;
	}
	
}
