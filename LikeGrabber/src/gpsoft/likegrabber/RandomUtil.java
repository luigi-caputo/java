package gpsoft.likegrabber;

import java.util.Random;

public class RandomUtil {

	static Random random = new Random();
	static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	public static String generateRandomString(int complex) {
		StringBuilder builder = new StringBuilder();
		int limit = random.nextInt(complex);
		for(int i = 1; i<limit; i++){
			builder.append(random.nextInt(i)+Character.toString(alphabet.charAt(getCloseInterval(0,alphabet.length()-1))));
		}
		return builder.toString();
	}
	
	public static int getCloseInterval(int start, int end){
		int ran = random.nextInt(Math.abs(end));
		if(ran < start){
			ran = start;
		}
		return ran;
	}

}
