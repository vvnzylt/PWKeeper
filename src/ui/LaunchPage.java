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
		Font headingFont = new Font("Helvetica", Font.BOLD, 20);
		Font bodyFont = new Font("Helvetica", Font.PLAIN, 16);
		Image icon = Toolkit.getDefaultToolkit().getImage("src/resources/logo_without_text.png");
		ImageIcon visibilityOnIcon = new ImageIcon(new ImageIcon("src/resources/visibility_on.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon visibilityOffIcon = new ImageIcon(new ImageIcon("src/resources/visibility_off.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon appNameLogo = new ImageIcon("src/resources/logo_landscape_252x90.png");
		JLabel appNameLogoJL = new JLabel();
		appNameLogoJL.setIcon(appNameLogo);
		
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
//		UIManager.put("Button.darcula.hoverColor", new Color(50, 50, 50));
//		UIManager.put("Button.mouseHoverColor", new Color(100, 50, 50));
		
		// Customization of JFrame components
		JPanel appName = new JPanel();
		appName.setBounds(420, 50, 250, 100);
		appName.add(appNameLogoJL);
		
		JPanel loginHeading = new JPanel();
		JLabel loginLbl = new JLabel("<html><body style='text-align: center'>Login or register <br>your account</html>");
		loginLbl.setFont(headingFont);
		loginLbl.setForeground(Color.white);
		loginHeading.setBounds(420, 160, 250, 65);
		loginHeading.setBackground(new Color(26, 26, 26));
		loginHeading.add(loginLbl);
		
		JPanel userCredentials = new JPanel();
		JTextField username = new JTextField("Enter your username");
		JPasswordField password = new JPasswordField("Enter your password");
		JLabel showOrHideBtn = new JLabel(visibilityOffIcon);
		JButton loginBtn = new JButton("Login");
		JButton registerBtn = new JButton("Register");
		JButton registeredAccBtn = new JButton("Show registered accounts");		
		userCredentials.setBounds(420, 270, 250, 300);
		userCredentials.setLayout(null);
		userCredentials.setBackground(new Color(26, 26, 26));
		username.setFont(bodyFont);
		username.setBounds(0, 0, 250, 40);
		username.setBorder(new EmptyBorder(10, 10, 10, 10));
		password.setFont(bodyFont);
		password.setBounds(0, 50, 200, 40);
		password.setBorder(new EmptyBorder(10, 10, 10, 10));
		password.setEchoChar((char)0);
		showOrHideBtn.setOpaque(true);
		showOrHideBtn.setBounds(200, 50, 50, 40);
		showOrHideBtn.setBackground(new Color(56, 56, 56));
//		showOrHideBtn.setHorizontalAlignment(SwingConstants.CENTER);
//		showOrHideBtn.setVerticalAlignment(SwingConstants.CENTER);
		loginBtn.setFont(bodyFont);
		loginBtn.setBounds(0, 100, 120, 40);
		loginBtn.setBackground(new Color(56, 58, 61));
		loginBtn.setForeground(new Color(226, 226, 226));
		loginBtn.setBorder(BorderFactory.createLineBorder(Color.red, 0));
		loginBtn.setFocusPainted(false);
		registerBtn.setFont(bodyFont);
		registerBtn.setBounds(130, 100, 120, 40);
		registerBtn.setBackground(new Color(56, 58, 61));
		registerBtn.setForeground(new Color(226, 226, 226));
		registerBtn.setBorder(BorderFactory.createLineBorder(Color.red, 0));
		registerBtn.setFocusPainted(false);
		registeredAccBtn.setFont(bodyFont);
		registeredAccBtn.setBounds(0, 150, 250, 40);
		registeredAccBtn.setBackground(new Color(56, 58, 61));
		registeredAccBtn.setForeground(new Color(226, 226, 226));
		registeredAccBtn.setBorder(BorderFactory.createLineBorder(Color.red, 0));
		registeredAccBtn.setFocusPainted(false);
		
		userCredentials.add(username);
		userCredentials.add(password);
		userCredentials.add(showOrHideBtn);
		userCredentials.add(loginBtn);
		userCredentials.add(registerBtn);
		userCredentials.add(registeredAccBtn);
		
		add(appName);
		add(loginHeading);
		add(userCredentials);
		
		// MouseListener for clearing text fields when user clicks on those fields
		username.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (username.getText().equals("Enter your username")) {
					username.setText(null);
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
		
		password.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (password.getText().equals("Enter your password")) {
					password.setText(null);
					password.setEchoChar('•');
					showOrHideBtn.setIcon(visibilityOnIcon);
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
					password.setEchoChar((char)0);
				} else {
					showOrHideBtn.setIcon(visibilityOnIcon);
					password.setEchoChar('•');
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
                String usernameValue = username.getText();
                String passwordValue = password.getText();
                
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
                String usernameValue = username.getText();
                String passwordValue = password.getText();
                
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
		
		registeredAccBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	AccountAuth.showRegisteredAccounts();
            }
        });
		
		registeredAccBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registeredAccBtn.setBackground(new Color (50, 50, 50));
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
				registeredAccBtn.setBackground(new Color (50, 50, 50));
				registeredAccBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				registeredAccBtn.setBackground(new Color (56, 58, 61));
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