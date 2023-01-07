package gp.test.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	public static Render floor = loadBitmap("/textures/tile.png");
	public static Render monster1 = loadBitmap("/textures/sprite1.png");
	public static Render pistol = loadBitmap("/textures/pistol.png");
	
	private static Render loadBitmap(String path) {
		try {
			BufferedImage texture = ImageIO.read(Texture.class.getResource(path));
			int width = texture.getWidth();
			int height = texture.getHeight();
			Render box = new Render(width,height);
			texture.getRGB(0, 0, width, height, box.pixels, 0, width);
			return box;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
