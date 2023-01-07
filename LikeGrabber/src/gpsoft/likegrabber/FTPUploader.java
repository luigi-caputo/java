package gpsoft.likegrabber;

import java.io.FileInputStream;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;

public class FTPUploader {

	public static boolean uploadFile(String host, String username, String password, String path, String name) {
		FTPClient client = new FTPClient(); 
		try {
			client.connect(host);
			client.login(username, password);
			FileInputStream in = new FileInputStream(path);
			client.storeFile(name, in);
			client.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: "+"\n"+e.getMessage());
			return false;
		}
	}

}
