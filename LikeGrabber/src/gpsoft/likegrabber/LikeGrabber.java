package gpsoft.likegrabber;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class LikeGrabber {
	
	/*
	* Copyright (c) 2013, GicoPiro (GPSoft)
	* All rights reserved.
	*/
	
	private JFrame frmLikegrabberGpsoft;
	private JTextField txtUsername;
	private JTextField textPassword;
	private JTextField textUrl;
	private JTextField textPage;
	private JTextField textTitle;
	private JTextField textText;
	private JTextField textImage;
    private StringBuilder HTMLCode;
    private JTextField txtGenerated;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LikeGrabber window = new LikeGrabber();
					window.frmLikegrabberGpsoft.setVisible(true);
					window.frmLikegrabberGpsoft.setLocationRelativeTo(null);
					window.frmLikegrabberGpsoft.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LikeGrabber() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		final File conf = new File("./conf.ini");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmLikegrabberGpsoft = new JFrame();
		frmLikegrabberGpsoft.setIconImage(Toolkit.getDefaultToolkit().getImage(LikeGrabber.class.getResource("/images/icon.png")));
		frmLikegrabberGpsoft.setTitle("LikeGrabber - GPSoft");
		frmLikegrabberGpsoft.setResizable(false);
		frmLikegrabberGpsoft.setSize(502, 615);
		frmLikegrabberGpsoft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLikegrabberGpsoft.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LikeGrabber.class.getResource("/images/title.png")));
		label.setBounds(10, 24, 476, 85);
		frmLikegrabberGpsoft.getContentPane().add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 297, 476, 249);
		frmLikegrabberGpsoft.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textPage = new JTextField();
		textPage.setColumns(10);
		textPage.setBounds(10, 40, 456, 20);
		panel_1.add(textPage);
		
		JLabel lblFacebookPagewithout = new JLabel("Facebook page (with http:// etc..):");
		lblFacebookPagewithout.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFacebookPagewithout.setBounds(10, 15, 253, 14);
		panel_1.add(lblFacebookPagewithout);
		
		JLabel lblFakeNewsTitle = new JLabel("Fake news title:");
		lblFakeNewsTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFakeNewsTitle.setBounds(10, 71, 125, 14);
		panel_1.add(lblFakeNewsTitle);
		
		textTitle = new JTextField();
		textTitle.setColumns(10);
		textTitle.setBounds(10, 96, 456, 20);
		panel_1.add(textTitle);
		
		JLabel lblShortTexteg = new JLabel("Short text (e.g. : Click here to see the article):");
		lblShortTexteg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblShortTexteg.setBounds(10, 127, 456, 20);
		panel_1.add(lblShortTexteg);
		
		textText = new JTextField();
		textText.setColumns(10);
		textText.setBounds(10, 158, 456, 20);
		panel_1.add(textText);
		
		JLabel lblImageUrlwithout = new JLabel("Image url (with http:// etc..):");
		lblImageUrlwithout.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImageUrlwithout.setBounds(10, 189, 456, 20);
		panel_1.add(lblImageUrlwithout);
		
		textImage = new JTextField();
		textImage.setColumns(10);
		textImage.setBounds(10, 218, 456, 20);
		panel_1.add(textImage);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 134, 476, 153);
		frmLikegrabberGpsoft.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setEnabled(false);
		txtUsername.setBounds(153, 42, 313, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(18, 44, 125, 14);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textPassword = new JTextField();
		textPassword.setEnabled(false);
		textPassword.setBounds(153, 67, 313, 20);
		panel.add(textPassword);
		textPassword.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(18, 69, 125, 14);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textUrl = new JTextField();
		textUrl.setEnabled(false);
		textUrl.setBounds(197, 91, 269, 20);
		panel.add(textUrl);
		textUrl.setColumns(10);
		
		final JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPassword.setText("");
				txtUsername.setText("");
				textUrl.setText("");
			}
		});
		btnClear.setEnabled(false);
		btnClear.setBounds(377, 119, 89, 23);
		panel.add(btnClear);
		btnClear.setBackground(Color.LIGHT_GRAY);
		
		if(conf.exists()){
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(conf));
				String line = reader.readLine();
				String[] data = line.split("<>");
				txtUsername.setText(data[0]);
				textPassword.setText(data[1]);
				textUrl.setText(data[2]);
			} catch (Exception e) {
				e.printStackTrace();
				btnClear.doClick();
			}
		}
		
		JLabel lblFtpUrlwithout = new JLabel("Ftp url (without ftp:// or http://):");
		lblFtpUrlwithout.setBounds(18, 94, 177, 14);
		panel.add(lblFtpUrlwithout);
		lblFtpUrlwithout.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		final JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
				if(conf.exists()){
					conf.delete();
				}
					conf.createNewFile();
				
				OutputStreamWriter creator = new OutputStreamWriter(new FileOutputStream(conf));
				creator.write(txtUsername.getText()+"<>"+textPassword.getText()+"<>"+textUrl.getText());
				creator.close();
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frmLikegrabberGpsoft, "Error: "+"\n"+e.getMessage());
				}
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(18, 119, 89, 23);
		panel.add(btnSave);
		btnSave.setBackground(Color.LIGHT_GRAY);
		
		final JCheckBox chckbxCustomFtpServer = new JCheckBox("Custom FTP Server");
		chckbxCustomFtpServer.setBounds(18, 12, 135, 23);
		panel.add(chckbxCustomFtpServer);
		chckbxCustomFtpServer.setBackground(Color.LIGHT_GRAY);
		
		chckbxCustomFtpServer.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent arg0) {
				if(chckbxCustomFtpServer.isSelected()){
					txtUsername.setEnabled(true);
					textPassword.setEnabled(true);
					textUrl.setEnabled(true);
					btnSave.setEnabled(true);
					btnClear.setEnabled(true);
				}else{
					txtUsername.setEnabled(false);
					textPassword.setEnabled(false);
					textUrl.setEnabled(false);
					btnSave.setEnabled(false);
					btnClear.setEnabled(false);
				}
			}
			
		});
		
		JButton btnGenerateUrl = new JButton("Generate URL");
		btnGenerateUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HTMLCode = new StringBuilder();
				String line = "";
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(LikeGrabber.class.getResourceAsStream("/code/code")));
					while((line = reader.readLine())!=null){
						if(line.startsWith("#MOD")){
							line = line.replace("#MOD", "");
							if(line.startsWith("<TITLE>")){
								line = "<TITLE>"+textTitle.getText()+"</TITLE>";
							}
							if(line.startsWith("<div align=")){
								line = "<div align=\"center\"><img src=" + "\"" + textImage.getText() + "\"" + "width=\"100\" height=\"60\" />";
							}
							if(line.startsWith("<p>")){
								line = "<p>"+textText.getText()+"</p>";
							}
							if(line.startsWith("<div class=")){
								line = "<div class=\"fb-like\" data-href="+"\""+textPage.getText()+"\""+"data-width=\"450\" data-show-faces=\"true\" data-send=\"true\"></div>";
							}
						}
						HTMLCode.append(line).append("\n");
					}
					reader.close();
					
					boolean done = false;
					File file = new File("./tempH.html");
					
					if(file.exists()){
						file.delete();
					}
					
					file.createNewFile();
					OutputStreamWriter creator = new OutputStreamWriter(new FileOutputStream(file));
					creator.write(HTMLCode.toString());
					creator.close();
					
					String site = "";
					
					if(chckbxCustomFtpServer.isSelected()){
						site = textUrl.getText();
					}else{
						site = "newsglobal.altervista.org";
					}
					
					String name = RandomUtil.generateRandomString(40)+".html";
					
					String url = site + "/" + name;
					
					if(chckbxCustomFtpServer.isSelected()){
						done = FTPUploader.uploadFile(site, txtUsername.getText(), textPassword.getText(), "./tempH.html", name);
					}else{
						done = FTPUploader.uploadFile(site, "newsglobal", "penmonodfi91", "./tempH.html", name);
					}
					
					if(!done)return;
					txtGenerated.setText(url);
					
					JOptionPane.showMessageDialog(frmLikegrabberGpsoft, "URL generated !");
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(frmLikegrabberGpsoft, "Error: "+"\n"+e.getMessage());
				}
				
			}
		});
		btnGenerateUrl.setBounds(10, 547, 116, 34);
		frmLikegrabberGpsoft.getContentPane().add(btnGenerateUrl);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setIcon(new ImageIcon(LikeGrabber.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		btnExit.setBounds(375, 547, 111, 34);
		frmLikegrabberGpsoft.getContentPane().add(btnExit);
		
		txtGenerated = new JTextField();
		txtGenerated.setToolTipText("CTRL + C to copy");
		txtGenerated.setBackground(SystemColor.activeCaption);
		txtGenerated.setForeground(Color.BLACK);
		txtGenerated.setBounds(136, 554, 229, 20);
		frmLikegrabberGpsoft.getContentPane().add(txtGenerated);
		txtGenerated.setColumns(10);
	}
}
