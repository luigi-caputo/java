package gp.test;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SoundManager {

	private static synchronized void playSound(final String name){
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Clip sound = AudioSystem.getClip();
					AudioInputStream astr = AudioSystem.getAudioInputStream(SoundManager.class.getResource("/sounds/" + name + ".wav"));
					sound.open(astr);
					sound.stop();
					sound.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	public static void shoot(){
		playSound("pistol");
	}
	
}
