package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import backend.AccountAuth; // Enables this class to access the classes from back-end package of project's src

public class LaunchPage extends JFrame {
	public LaunchPage() {
		initializeUI();
	}
	
	private void initializeUI() {
		System.out.println("LaunchPage.java: Homepage UI initialized.");
		
		// Setting necessary UI-related components
		Font headingFont = new Font("Helvetica", Font.BOLD, 18);
		Font bodyFont = new Font("Helvetica", Font.PLAIN, 16);
		Image icon = Toolkit.getDefaultToolkit().getImage("src/resources/logo_without_text.png");
		ImageIcon visibilityOnIcon = new ImageIcon(new ImageIcon("src/resources/visibility_on.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon visibilityOffIcon = new ImageIcon(new ImageIcon("src/resources/visibility_off.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon appLogo = new ImageIcon(new ImageIcon("src/resources/logo_landscape_white_text.png").getImage().getScaledInstance(215, 50, Image.SCALE_SMOOTH));
		ImageIcon randomDesignImg = new ImageIcon(new ImageIcon("src/resources/poly_grid-haikei.png").getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH));
		JLabel appLogoLbl = new JLabel();
		appLogoLbl.setIcon(appLogo);
		
		// Setting JFrame properties
		setSize(1080, 720);
		setLayout(null);
		setIconImage(icon);
		setTitle("PWKeeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(250, 50);
		setResizable(false);
		getContentPane().setBackground(new Color(26, 26, 26));
		
		UIManager.put("Button.select", new Color(50, 50, 50));
		
		// Customization of JFrame components
		JPanel leftPanel = new JPanel();
		JLabel randomDesignImgLbl = new JLabel();
		randomDesignImgLbl.setIcon(randomDesignImg);
		randomDesignImgLbl.setBackground(null);
		leftPanel.setOpaque(false);
		leftPanel.setBounds(200, 135, 260, 405);
		leftPanel.add(randomDesignImgLbl);
		
		JPanel appNamePanel = new JPanel();
		appNamePanel.setBounds(420, 50, 250, 100);
		appNamePanel.add(appLogoLbl);
		
		JPanel rightPanel = new JPanel();
		appLogoLbl.setBounds(95, 60, 215, 50);
		JLabel loginRegisterLbl = new JLabel("Login/Register");
		loginRegisterLbl.setFont(headingFont);
		loginRegisterLbl.setForeground(new Color(226, 226, 226));
		loginRegisterLbl.setBounds(145, 125, 180, 30);

		rightPanel.setLayout(null);
		rightPanel.setBounds(460, 140, 400, 400);
		rightPanel.setBackground(new Color(36, 36, 36));
		
		JTextField usernameField = new JTextField("Enter your username");
		JPasswordField passwordField = new JPasswordField("Enter your password");
		JLabel showOrHideBtn = new JLabel(visibilityOffIcon);
		JButton loginBtn = new JButton("Login");
		JButton registerBtn = new JButton("Register");
		usernameField.setFont(bodyFont);
		usernameField.setBounds(80, 200, 250, 40);
		usernameField.setBorder(new EmptyBorder(10, 10, 10, 10));
		usernameField.setForeground(Color.gray);
		passwordField.setFont(bodyFont);
		passwordField.setBounds(80, 250, 200, 40);
		passwordField.setBorder(new EmptyBorder(10, 10, 10, 10));
		passwordField.setEchoChar((char)0);
		passwordField.setForeground(Color.gray);
		showOrHideBtn.setOpaque(true);
		showOrHideBtn.setBounds(280, 250, 50, 40);
		showOrHideBtn.setBackground(new Color(56, 56, 56));
		loginBtn.setFont(bodyFont);
		loginBtn.setBounds(80, 300, 120, 40);
		loginBtn.setBackground(new Color(56, 58, 61));
		loginBtn.setForeground(new Color(226, 226, 226));
		loginBtn.setBorder(BorderFactory.createLineBorder(Color.red, 0));
		loginBtn.setFocusPainted(false);
		registerBtn.setFont(bodyFont);
		registerBtn.setBounds(210, 300, 120, 40);
		registerBtn.setBackground(new Color(56, 58, 61));
		registerBtn.setForeground(new Color(226, 226, 226));
		registerBtn.setBorder(BorderFactory.createLineBorder(Color.red, 0));
		registerBtn.setFocusPainted(false);
		
		rightPanel.add(loginRegisterLbl);
		rightPanel.add(appLogoLbl);
		rightPanel.add(usernameField);
		rightPanel.add(passwordField);
		rightPanel.add(showOrHideBtn);
		rightPanel.add(loginBtn);
		rightPanel.add(registerBtn);
		
		add(leftPanel);
		add(rightPanel);
		
		// MouseListener for clearing text fields when user clicks on those fields
		usernameField.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usernameField.getText().equals("Enter your username")) {
					usernameField.setText(null);
					usernameField.setForeground(Color.black);
				}
				
				if (passwordField.getText().isEmpty()) {
					passwordField.setForeground(Color.GRAY);
					passwordField.setEchoChar((char)0);
					showOrHideBtn.setIcon(visibilityOffIcon);
					passwordField.setText("Enter your password");
				}
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		passwordField.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (passwordField.getText().equals("Enter your password")) {
					passwordField.setText(null);
					passwordField.setEchoChar('•');
					showOrHideBtn.setIcon(visibilityOnIcon);
					passwordField.setForeground(Color.BLACK);
				}
				
				if (usernameField.getText().isEmpty()) {
					usernameField.setForeground(Color.GRAY);
					usernameField.setText("Enter your username");
				}
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		showOrHideBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (showOrHideBtn.getIcon() == visibilityOnIcon) {
					showOrHideBtn.setIcon(visibilityOffIcon);
					passwordField.setEchoChar((char)0);
				} else {
					showOrHideBtn.setIcon(visibilityOnIcon);
					passwordField.setEchoChar('•');
				}
				
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
				// TODO Auto-generated method stub
				showOrHideBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				showOrHideBtn.setBackground(new Color(68, 68, 68));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				showOrHideBtn.setBackground(new Color(56, 56, 56));
			}
		});
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameValue = usernameField.getText();
				String passwordValue = passwordField.getText();
				
				AccountAuth.loginAccount(usernameValue, passwordValue, loginBtn);
                
				if (AccountAuth.isAccountLoggedIn() == true) {
					Timer timer = new Timer(1000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							LoggedInPage loggedInPage = new LoggedInPage();
							loggedInPage.setVisible(true);
							dispose(); // Closes the LaunchPage window permanently
						}
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		});
		
		loginBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginBtn.setBackground(new Color (50, 50, 50));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				loginBtn.setBackground(new Color (50, 50, 50));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				loginBtn.setBackground(new Color (50, 50, 50));
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBackground(new Color (50, 50, 50));
				loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setBackground(new Color (56, 58, 61));
			}
		});
		
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameValue = usernameField.getText();
				String passwordValue = passwordField.getText();
				
				AccountAuth.registerAccount(usernameValue, passwordValue, registerBtn);
			}
		});
		
		registerBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerBtn.setBackground(new Color (50, 50, 50));
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
				registerBtn.setBackground(new Color (50, 50, 50));
				registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				registerBtn.setBackground(new Color (56, 58, 61));
			}
		});
	}
	
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	LaunchPage lp = new LaunchPage();
        	lp.setVisible(true);
        });
    }
}