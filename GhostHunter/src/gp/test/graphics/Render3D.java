package gp.test.graphics;

import gp.test.Display;
import gp.test.Game;

public class Render3D extends Render{

	private double[] zBuffer, zBufferWall;
	private double limiter = 5000.0;
	int c = 0;
	double rotation, sine, cosine, forward, right, up, Correct = 0.0625;
	public double pX, pY;
	
	public Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width*height];
		zBufferWall = new double[width];
	}
	
	public void floor(Game game){
		
		for (int x = 0; x < width; x++) {
			zBufferWall[x] = 0;
		}
		
		double ceilingpos = 8.0;
		double floorpos = 8.0;
		
		rotation = game.controller.rotation;
		sine = Math.sin(rotation);
		cosine = Math.cos(rotation);
		forward = game.controller.x;
		right = game.controller.z;
		up = game.controller.y;
		
		pX = (right * Correct);
		pY = (-forward * Correct);
		
		for(int y = 0; y<height; y++){
			double yDepth = (y - height / 2.0) / height;
			double z = (floorpos+up) / yDepth;
			c=0;
			if(yDepth < 0){
				z = (ceilingpos-up)/-yDepth;
				c = 1;
			}
			for(int x = 0; x<width; x++){
				double xDepth= (x - width / 2.0) / height;
				xDepth*=z;
				
				double xx = xDepth*sine - z*cosine;
				double yy = z*sine + xDepth*cosine;
				
				int xPix = (int)(xx + forward);
				int yPix = (int)(yy + right);
				
				zBuffer[x+y*width] = z;
				
				if(c==0){
					pixels[x+y*width] = Texture.floor.pixels[(xPix&31) + (yPix&31) * 96];
				}else{
					pixels[x+y*width] = Texture.floor.pixels[((xPix&31) + 32) + (yPix&31) * 96];
				}
				
				if(z > 300){
					pixels[x+y*width] = 0;
				}
			}
		}
		
	}
	
	public void drawBitmap3D(int widthL, int widthR, int heightL, int heightR,
			double x, double z, double y, Render texture, int imgPos){
		double xc = ((x/2) - (right * Correct));
		double yc = ((y/2) + (up * 0.1));
		double zc = ((z/2) + (forward * Correct));
		
		double rotX = xc * cosine - zc * sine;
		double rotY = yc;
		double rotZ = zc * cosine + xc * sine;
		
		double xPixel = (rotX / rotZ * height + Display.WIDTH/2);
		double yPixel = (rotY / rotZ * height + Display.HEIGHT/2);
		
		double xPixelL = (xPixel - widthL / rotZ);
		double xPixelR = (xPixel + widthR / rotZ);
		double yPixelL = (yPixel - heightL / rotZ);
		double yPixelR = (yPixel + heightR / rotZ);
		
		int xpl = (int)(xPixelL);
		int xpr = (int)(xPixelR);
		int ypl = (int)(yPixelL);
		int ypr = (int)(yPixelR);
		
		if(xpl < 0){
			xpl = 0;
		}
		if(xpr > width){
			xpr = width;
		}
		if(ypl < 0){
			ypl = 0;
		}
		if(ypr > height){
			ypr = height;
		}
		
		rotZ *= 30.0;
		
		for(int xa = xpl; xa<xpr; xa++){
			double pixelRotation = (xa - xPixelL)/(xPixelR - xPixelL);
			int xTexture = (int)(widthL * pixelRotation);
			
			for(int ya = ypl; ya<ypr; ya++){
				double pixelRotationY = (ya - yPixelL)/(yPixelR - yPixelL);
				int yTexture = (int)(heightL * pixelRotationY);
				if(zBuffer[xa + ya*width] > rotZ){
					int alphaColor = 0;
					int alpha = texture.pixels[(xTexture&(widthL-1) +imgPos) + (yTexture&(heightL-1)) * widthL];
					
					if(alpha < alphaColor){
							pixels[xa+ya*width] = alpha;
							zBuffer[xa+ya*width] = rotZ;
					}
				}
			}
		}
	}
	
	public void drawWalls(double xLeft, double xRight, double zDistanceLeft, double zDistanceRight, double yHeight, int texPos){
		
		double xcLeft = ((xLeft) - (right * Correct)) * 2;
		double zcLeft = ((zDistanceLeft) + (forward * Correct)) * 2;

		double rotLeftSideX = xcLeft * cosine - zcLeft * sine;
		double yCornerTL = ((-yHeight) - (-up * Correct)) * 2;
		double yCornerBL = ((+0.5 - yHeight) - (-up * Correct)) * 2;
		double rotLeftSideZ = zcLeft * cosine + xcLeft * sine;

		double xcRight = ((xRight) - (right * Correct)) * 2;
		double zcRight = ((zDistanceRight) +(forward * Correct)) * 2;

		double rotRightSideX = xcRight * cosine - zcRight * sine;
		double yCornerTR = ((-yHeight) - (-up * Correct)) * 2;
		double yCornerBR = ((+0.5 - yHeight) - (-up * Correct)) * 2;
		double rotRightSideZ = zcRight * cosine + xcRight * sine;

		double tex30 = 0;
		double tex40 = 32;
		double clip = 0.5;
		
		if (rotLeftSideZ < clip && rotRightSideZ < clip) {
			return;
		}

		if (rotLeftSideZ < clip) {
			double clip0 = (clip - rotLeftSideZ) / (rotRightSideZ - rotLeftSideZ);
			rotLeftSideZ = rotLeftSideZ + (rotRightSideZ - rotLeftSideZ) * clip0;
			rotLeftSideX = rotLeftSideX + (rotRightSideX - rotLeftSideX) * clip0;
			tex30 = tex30 + (tex40 - tex30) * clip0;
		}

		if (rotRightSideZ < clip) {
			double clip0 = (clip - rotLeftSideZ) / (rotRightSideZ - rotLeftSideZ);
			rotRightSideZ = rotLeftSideZ + (rotRightSideZ - rotLeftSideZ) * clip0;
			rotRightSideX = rotLeftSideX + (rotRightSideX - rotLeftSideX) * clip0;
			tex40 = tex30 + (tex40 - tex30) * clip0;
		}
		
		double xPixelLeft = (rotLeftSideX / rotLeftSideZ * height + width / 2.0);
		double xPixelRight = (rotRightSideX / rotRightSideZ * height + width / 2.0);

		if (xPixelLeft >= xPixelRight) {
			return;
		}

		int xPixelLeftInt = (int) (xPixelLeft);
		int xPixelRightInt = (int) (xPixelRight);

		if (xPixelLeftInt < 0) {
			xPixelLeftInt = 0;
		}

		if (xPixelRightInt > width) {
			xPixelRightInt = width;
		}

		double yPixelLeftTop = (yCornerTL / rotLeftSideZ * height + height / 2.0);
		double yPixelLeftBottom = (yCornerBL / rotLeftSideZ * height + height / 2.0);
		double yPixelRightTop = (yCornerTR / rotRightSideZ * height + height / 2.0);
		double yPixelRightBottom = (yCornerBR / rotRightSideZ * height + height / 2.0);

		double tex1 = 1 / rotLeftSideZ;
		double tex2 = 1 / rotRightSideZ;
		double tex3 = tex30 / rotLeftSideZ;
		double tex4 = tex40 / rotRightSideZ - tex3;

		for (int x = xPixelLeftInt; x < xPixelRightInt; x++) {
			double pixelRotation = (x - xPixelLeft) / (xPixelRight - xPixelLeft);
			double zWall = tex1 + (tex2 - tex1) * pixelRotation;
			
			if(zBufferWall[x] > zWall){
				continue;
			}
			zBufferWall[x] = zWall;
			
			int xTexture = (int) ((tex3 + tex4 * pixelRotation) / zWall);

			double yPixelTop = yPixelLeftTop + (yPixelRightTop - yPixelLeftTop) * pixelRotation;
			double yPixelBottom = yPixelLeftBottom + (yPixelRightBottom - yPixelLeftBottom) * pixelRotation;

			int yPixelTopInt = (int) (yPixelTop);
			int yPixelBottomInt = (int) (yPixelBottom);

			if (yPixelTopInt < 0) {
				yPixelTopInt = 0;
			}

			if (yPixelBottomInt > height) {
				yPixelBottomInt = height;
			}

			for (int y = yPixelTopInt; y < yPixelBottomInt; y++) {
				double pixelRotationY = (y - yPixelTop) / (yPixelBottom - yPixelTop);
				int yTexture = (int) (32 * pixelRotationY);
				pixels[x + y * width] = Texture.floor.pixels[((xTexture & 31) + texPos) + (yTexture & 31) * 96];
				zBuffer[x + y * width] = 1 / (tex1 + (tex2 - tex1) * pixelRotation) * 8;
			}

		}

	}
	
	public void fog(){
		for(int i = 0;i<width*height;i++){
			int colour = pixels[i];
			int brightness = (int) (limiter/zBuffer[i]);
			
			if(brightness < 0){
				brightness = 0;
			}
			
			if(brightness > 255){
				brightness = 255;
			}
			
			int r = (colour>>16)&0xff;
			int g = (colour>>8)&0xff;
			int b = (colour)&0xff;
			
			r = r * brightness / 255;
			g = g * brightness / 255;
			b = b * brightness / 255;
			
			pixels[i] = r << 16 | g << 8 | b;
		}
	}

}
