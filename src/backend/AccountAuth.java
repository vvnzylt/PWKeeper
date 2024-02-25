package backend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AccountAuth {
	private static boolean isAccountLoggedIn;
	private Font headingFont = new Font("Helvetica", Font.BOLD, 18);
	private static Font bodyFont = new Font("Helvetica", Font.PLAIN, 16); 
	private static Image appIcon = Toolkit.getDefaultToolkit().getImage("src/assets/logo_without_text.png");
	
	public static void loginAccount(String usernameValue, String passwordValue, JButton loginBtn) {
		for (int i = 0; i < AccountDB.getAccount().size(); i++) {
			if (usernameValue.isEmpty() || usernameValue.trim().isEmpty() || passwordValue.trim().isEmpty() || passwordValue.isEmpty()) {
				System.out.println("AccountAuth.java: Can't login. One of the text fields cannot be empty.");
				showDialog("EMPTY_FIELD");
				break;
			} else if (AccountDB.getAccount().get(i).getUsername().equals(usernameValue) && AccountDB.getAccount().get(i).getPassword().equals(passwordValue)) {
//				showDialog("SUCCESSFUL_LOGIN");
				AccountDB.setLoggedInAccountIndex(i);
				isAccountLoggedIn = true;
	            System.out.println("LaunchPage.java: Account logged-in with username \"" + usernameValue + "\"");
	            JOptionPane.showMessageDialog(loginBtn, "You are now logged in!", "Success", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			if (i == (AccountDB.getAccount().size()-1)) {
				System.out.println("AccountAuth.java: Cannot login. Invalid credentials.");
				showDialog("WRONG_CREDENTIALS");
				break;
			}
		}
		
		// This will trigger the code whenever user attempts to login whenever there are no
		// registered accounts in the database
		if (AccountDB.getAccount().size() == 0) {
			if (usernameValue.isEmpty() || usernameValue.trim().isEmpty() || passwordValue.trim().isEmpty() || passwordValue.isEmpty()) {
				showDialog("EMPTY_FIELD");
			} else {
				System.out.println("AccountAuth.java: Cannot login. Invalid credentials.");
				showDialog("WRONG_CREDENTIALS");
			}
		}
	}
	
	public static boolean isAccountLoggedIn() {
		return isAccountLoggedIn;
	}
	
	public static void setAccountLoggedInStatus(boolean status) {
		isAccountLoggedIn = status;
	}
	
	public static void registerAccount(String usernameValue, String passwordValue, JButton registerBtn) {
		boolean isExistingAccountFound = true; // Temporary initialization; value does not mean anything
		
		// This loop checks if the entered username already exist on the temporary database.
		for (int i = 0; i < AccountDB.getAccount().size(); i++) {
			if (AccountDB.getAccount().get(i).getUsername().equals(usernameValue)) {
				isExistingAccountFound = true;
				showDialog("USERNAME_ALREADY_EXIST");
				break;
			}
			
			if (i == AccountDB.getAccount().size() - 1) {
				isExistingAccountFound = false;
			}
		}
		
		// This if-statement acts as an alternative to loop above since the if-statement
		// inside the loop above won't be triggered if there is no account registered in
		// temporary database
		if (AccountDB.getAccount().size() == 0) {
			isExistingAccountFound = false;
		}
		
		if (isExistingAccountFound == false) {
			if (usernameValue.isEmpty() || usernameValue.trim().isEmpty() || passwordValue.trim().isEmpty() || passwordValue.isEmpty()) {
				System.out.println("AccountAuth.java: Can't login. One of the text fields cannot be empty.");
				showDialog("EMPTY_FIELD");
			} else {
				AccountDB.getAccount().add(new AccountDB(usernameValue, passwordValue));
	            System.out.println("AccountAuth.java: Account registered with username \"" + usernameValue + "\"");
	            showDialog("SUCCESSFULLY_REGISTERED");
			}
		}
	}
	
	public static void showRegisteredAccounts() {
		System.out.println("Total registered accounts: " + AccountDB.getAccount().size());
		
		if (AccountDB.getAccount().size() == 0) {
			System.out.println("There are no registered accounts.");
		} else {
			System.out.println("Here are the list of registered accounts");
			
			for (int i = 0; i < AccountDB.getAccount().size(); i++) {
				System.out.println("Account #" + (i+1));
				System.out.println("Username: " + AccountDB.getAccount().get(i).getUsername());
				System.out.println("Password: " + AccountDB.getAccount().get(i).getPassword());
			}
		}
		System.out.println();
	}
	
	public static void getListOfAccountDetails() {
		AccountDB.getAccount().get(AccountDB.getLoggedInAccountIndex()).getListOfAccountDetails();
	}
	
	private static void showDialog(String value) {
		JFrame frame = new JFrame();
		String imagePath = null;
		String infoMessage = null;
		
		if (value.contains("EMPTY_FIELD")) {
			imagePath = "src/assets/warning.png";
			infoMessage = "<html>Can't login. One of the text fields  cannot be empty.</html>";
			frame.setTitle("Error");
		} else if (value.contains("WRONG_CREDENTIALS")) {
			imagePath = "src/assets/warning.png";
			infoMessage = "<html>Wrong credentials. Please try again.</html>";
			frame.setTitle("Error");
		}
		else if (value.contains("USERNAME_ALREADY_EXIST")) {
			imagePath = "src/assets/warning.png";
			infoMessage = "<html>Username already exist. Please use another username.</html>";
			frame.setTitle("Error");
		} else if (value.contains("SUCCESSFUL_LOGIN")) {
			imagePath = "src/assets/check.png";
			infoMessage = "<html>You are now logged in!</html>";
			frame.setTitle("Success");
		} else if (value.contains("SUCCESSFULLY_REGISTERED")) {
			imagePath = "src/assets/check.png";
			infoMessage = "<html>Account registered.</html>";
			frame.setTitle("Success");
		} 
				
		ImageIcon frameIcon = new ImageIcon(imagePath);
		
		Image img = frameIcon.getImage();
		Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		frameIcon = new ImageIcon(scaledImg);
		
		frame.setIconImage(appIcon);
		frame.setLocation(700, 330);
		frame.setSize(330, 170);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(26, 26, 26));
		frame.setLayout(null);
		frame.setResizable(false);
		
		JLabel iconLbl = new JLabel();
		iconLbl.setIcon(frameIcon);
		iconLbl.setBounds(25, 30, 30, 30);
		
		JLabel infoLbl = new JLabel(infoMessage);
		
		infoLbl.setFont(bodyFont);
		infoLbl.setBounds(65, 20, 250, 50);
		infoLbl.setForeground(new Color(228, 228, 228));
		
		JButton okBtn = new JButton("OK");
		okBtn.setFont(bodyFont);
		okBtn.setBounds(125, 85, 60, 30);
		okBtn.setBackground(new Color (40, 40, 40));
		okBtn.setForeground(new Color(192, 192, 192));
		okBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		okBtn.setFocusPainted(false);
		
		frame.add(iconLbl);
		frame.add(infoLbl);
		frame.add(okBtn);
		
		okBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				okBtn.setBackground(new Color (60, 60, 60));
				okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				okBtn.setBackground(new Color (40, 40, 40));
			}
		});
	}
}
