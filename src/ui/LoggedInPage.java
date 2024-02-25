package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import backend.AccountAuth;
import backend.AccountDB;
import backend.ItemDetails;

public class LoggedInPage extends JFrame {
    private JTextField searchField;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JScrollPane rightPanelScrollPane;
	private ArrayList<ItemDetails> itemList = AccountDB.getAccount().get(AccountDB.getLoggedInAccountIndex()).getAccountListObj();
	private Font headingFont = new Font("Helvetica", Font.BOLD, 18);
	private Font bodyFont = new Font("Helvetica", Font.PLAIN, 16); 
	private Image appIcon = Toolkit.getDefaultToolkit().getImage("src/resources/logo_without_text.png");
	
	public LoggedInPage() {
		initializeUI();
	}
	
	private void initializeUI() {		
		setSize(1080, 720);
		setIconImage(appIcon);
		setTitle("PWKeeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocation(250, 50);
		
		JPanel topPanel = new JPanel();
		leftPanel = new JPanel();
		JPanel smallerLeftPanel = new JPanel();
		rightPanel = new JPanel();
		
		topPanel.setBackground(new Color(40, 40, 40));
		topPanel.setBounds(0, 0, 1080, 65);
		topPanel.setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(300, 16, 450, 35);
		searchField.setBackground(new Color(96, 96, 96));
		searchField.setForeground(new Color(255, 255, 255));
		searchField.setCaretColor(new Color(255, 255, 255));
		searchField.setBorder(new EmptyBorder(8, 8, 8, 8));
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(930, 16, 100, 35);
		logoutBtn.setFont(bodyFont);
		logoutBtn.setBackground(new Color (40, 40, 40));
		logoutBtn.setForeground(new Color(192, 192, 192));
		logoutBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		logoutBtn.setFocusPainted(false);
		
		topPanel.add(searchField);
		topPanel.add(logoutBtn);
		
		leftPanel.setBackground(new Color(32, 32, 32));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JScrollPane leftPanelScrollPane = new JScrollPane(leftPanel);
		leftPanelScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		leftPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		leftPanelScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hide vertical scrollbar
		leftPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftPanelScrollPane.setBounds(0, 65, 324, 545);
		leftPanelScrollPane.setBorder(null);
		
		rightPanel.setBackground(new Color(26, 26, 26));
		rightPanel.setPreferredSize(new Dimension(756, 1000));
		rightPanel.setLayout(null);
		
		rightPanelScrollPane = new JScrollPane(rightPanel);
		rightPanelScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		rightPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rightPanelScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		rightPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanelScrollPane.setBounds(324, 65, 756, 620);
		rightPanelScrollPane.setBorder(null);
		
		smallerLeftPanel.setBackground(new Color(40, 40, 40));
		smallerLeftPanel.setBounds(0, 605, 324, 80);
		smallerLeftPanel.setLayout(null);
		JButton addBtn = new JButton("Add item");
//		UIManager.put("Button.select", new Color(50, 50, 50));
//      UIManager.put("Button.darcula.hoverColor", new Color(50, 50, 50));
		addBtn.setBounds(45, 20, 240, 40);
		addBtn.setFont(bodyFont);
		addBtn.setBackground(new Color (40, 40, 40));
		addBtn.setForeground(new Color(192, 192, 192));
		addBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		addBtn.setFocusPainted(false);
//		addBtn.setRolloverEnabled(false);
		smallerLeftPanel.add(addBtn);
		
		add(topPanel);
		add(leftPanelScrollPane);
		add(smallerLeftPanel);
		getContentPane().add(rightPanelScrollPane);
		
		searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	searchAndSortLeftPanel();
            }
            
            public void removeUpdate(DocumentEvent e) {
            	searchAndSortLeftPanel();
            }
            
            public void changedUpdate(DocumentEvent e) {
            	searchAndSortLeftPanel();
            }
        });
		
		addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame loggedinPageJFrame = getjFrame();
                AddItemPage addItemPage = new AddItemPage(loggedinPageJFrame);
                addItemPage.setVisible(true);
            }
        });
		
		addBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
				addBtn.setBackground(new Color (60, 60, 60));
				addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				addBtn.setBackground(new Color (40, 40, 40));
			}
		});
		
		logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	showLogoutDialog();
            }
        });
		
		logoutBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logoutBtn.setBackground(new Color (60, 60, 60));
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
				logoutBtn.setBackground(new Color (60, 60, 60));
				logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logoutBtn.setBackground(new Color (40, 40, 40));
			}
		});
		
		searchAndSortLeftPanel();
	}
	
	private void showDeletedItemSuccessDialog() {
		JFrame frame = new JFrame();
		
		ImageIcon successIcon = new ImageIcon(new ImageIcon("src/resources/check.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		
		frame.setIconImage(appIcon);
		frame.setTitle("Success");
		frame.setLocation(692, 630);
		frame.setSize(250, 80);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(53, 122, 56));
		frame.setLayout(null);
		
		JLabel iconLbl = new JLabel();
		iconLbl.setIcon(successIcon);
		iconLbl.setBounds(25, 25, 30, 30);
		
		JLabel infoMessageLbl = new JLabel("Item has been deleted.");
		infoMessageLbl.setFont(bodyFont);
		infoMessageLbl.setBounds(65, 23, 200, 30);
		infoMessageLbl.setForeground(new Color(228, 228, 228));
		
		frame.add(iconLbl);
		frame.add(infoMessageLbl);
		
		Timer timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		timer.setRepeats(false);
		timer.start();
	}
	
	private void showLogoutDialog() {
		LoggedInPage.this.setEnabled(false);
		
		JFrame logoutFrame = new JFrame();
		ImageIcon questionMarkIcon = new ImageIcon("src/resources/question_mark.png");
		
		Image img = questionMarkIcon.getImage();
		Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		questionMarkIcon = new ImageIcon(scaledImg);
		
		logoutFrame.setIconImage(appIcon);
		logoutFrame.setTitle("Logout");
		logoutFrame.setLocation(700, 330);
		logoutFrame.setSize(270, 160);
		logoutFrame.setVisible(true);
		logoutFrame.getContentPane().setBackground(new Color(26, 26, 26));
		logoutFrame.setLayout(null);
		
		JLabel iconLbl = new JLabel();
		iconLbl.setIcon(questionMarkIcon);
		iconLbl.setBounds(25, 25, 30, 30);
		
		JLabel askUserLbl = new JLabel("Do you want to logout?");
		askUserLbl.setFont(bodyFont);
		askUserLbl.setBounds(60, 23, 200, 30);
		askUserLbl.setForeground(new Color(228, 228, 228));
		
		JButton yesBtn = new JButton("Yes");
		yesBtn.setFont(bodyFont);
		yesBtn.setBounds(65, 65, 60, 30);
		yesBtn.setBackground(new Color (40, 40, 40));
		yesBtn.setForeground(new Color(192, 192, 192));
		yesBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		yesBtn.setFocusPainted(false);
		
		JButton noBtn = new JButton("No");
		noBtn.setFont(bodyFont);
		noBtn.setBounds(135, 65, 60, 30);
		noBtn.setBackground(new Color (40, 40, 40));
		noBtn.setForeground(new Color(192, 192, 192));
		noBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		noBtn.setFocusPainted(false);		
		
		logoutFrame.add(iconLbl);
		logoutFrame.add(askUserLbl);
		logoutFrame.add(yesBtn);
		logoutFrame.add(noBtn);
		
		logoutFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LoggedInPage.this.setEnabled(true);
				}
			});
		
		yesBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				yesBtn.setBackground(new Color (60, 60, 60));
				AccountAuth.setAccountLoggedInStatus(false);
	        	LaunchPage launchPage = new LaunchPage();
	        	launchPage.setVisible(true);
	        	logoutFrame.dispose();
	        	dispose();
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
				yesBtn.setBackground(new Color (60, 60, 60));
				yesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				yesBtn.setBackground(new Color (40, 40, 40));
			}
		});
		
		noBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoggedInPage.this.setEnabled(true);
				logoutFrame.dispose();
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
				noBtn.setBackground(new Color (60, 60, 60));
				noBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				noBtn.setBackground(new Color (40, 40, 40));
			}
		});
	}
	
	private void updateLeftPanel(ArrayList<ItemDetails> items) {
		leftPanel.removeAll(); // Clear existing components
        for (ItemDetails item : items) {
        	JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            itemPanel.setPreferredSize(new Dimension(324, 70));
            itemPanel.setMaximumSize(new Dimension(324, 70));
            itemPanel.setBackground(new Color(226, 226, 226));
            
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/logo_without_text.png"));
            Image scaledIcon = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledIcon);
            
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setBackground(null);
            iconLabel.setPreferredSize(new Dimension(70, 70));
            itemPanel.add(iconLabel, BorderLayout.WEST);
            
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            textPanel.setBackground(null);
            
            JLabel nameLabel = new JLabel(item.getItemName());
            nameLabel.setFont(headingFont);
            textPanel.add(nameLabel);

            JLabel usernameLabel = new JLabel(item.getUsername());
            usernameLabel.setFont(bodyFont);
            textPanel.add(usernameLabel);
            
            itemPanel.add(textPanel, BorderLayout.CENTER);
            
            leftPanel.add(itemPanel);
            
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Display details of the clicked item on rightPanel
                	updateRightPanel(item);
                	resetView();
                }
            });
        }
        
        leftPanel.revalidate();
        leftPanel.repaint();
	}
	
	private void searchAndSortLeftPanel() {
		String query = searchField.getText().toLowerCase();
		ArrayList<ItemDetails> filteredItems = new ArrayList<>();
		
		if (query.isEmpty()) {
	        updateLeftPanel(itemList);
	        return;
	    }
		
		for (ItemDetails item : itemList) {
			if (item.getUsername().toLowerCase().contains(query) || item.getItemName().toLowerCase().contains(query) || item.getNotes().toLowerCase().contains(query)) {
				filteredItems.add(item);
			}
		}
		
		updateLeftPanel(filteredItems);
	}
	
	private void updateRightPanel(ItemDetails item) {
		rightPanel.removeAll();
		
		JLabel addItemHeading = new JLabel("ITEM INFORMATION");
		
		JPanel primaryInfoPanel = new JPanel();
		JLabel itemNameLbl = new JLabel("Item name");
		JLabel itemNameValue = new JLabel(item.getItemName());
		ImageIcon copyIcon = new ImageIcon(new ImageIcon("src/resources/copy.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon greenCheckIcon = new ImageIcon(new ImageIcon("src/resources/green_check.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel copyItemNameIconBtn = new JLabel(copyIcon);
		JLabel usernameLbl = new JLabel("Username");
		JLabel usernameValue = new JLabel(item.getUsername());
		JLabel copyUsernameIconBtn = new JLabel(copyIcon);
		JLabel passwordLbl = new JLabel("Password");
		JPasswordField passwordValue = new JPasswordField(item.getPassword());
		ImageIcon visibilityOnIcon = new ImageIcon(new ImageIcon("src/resources/visibility_on.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon visibilityOffIcon = new ImageIcon(new ImageIcon("src/resources/visibility_off.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JLabel togglePasswordVisibilityIconBtn = new JLabel(visibilityOnIcon);
		JLabel copyPasswordIconBtn = new JLabel(copyIcon);
		
		JPanel websitePanel = new JPanel();
		JLabel websiteLbl = new JLabel("Website");
		JLabel websiteValue = new JLabel(item.getURL());
		JLabel copyWebsiteIconBtn = new JLabel(copyIcon);
		JLabel notesLbl = new JLabel("NOTES");
		JTextArea notesValue = new JTextArea();
		JScrollPane notesValuePane = new JScrollPane(notesValue);
		JButton editItemBtn = new JButton("Edit");
		JButton deleteItemBtn = new JButton("Delete");
		
		MouseListener buttonMouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String copiedValue;
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				
				if (e.getSource() == togglePasswordVisibilityIconBtn) {
					if (passwordValue.getEchoChar() == 0) {
						passwordValue.setEchoChar('•');
						togglePasswordVisibilityIconBtn.setIcon(visibilityOnIcon);
					} else {
						passwordValue.setEchoChar((char)0);
						togglePasswordVisibilityIconBtn.setIcon(visibilityOffIcon);
					}
				}
				
				if (e.getSource() == copyItemNameIconBtn) {
					copiedValue = item.getItemName();
					StringSelection stringSelection = new StringSelection(copiedValue);
					clipboard.setContents(stringSelection, null);
					copyItemNameIconBtn.setIcon(greenCheckIcon);
					
					Timer timer = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							copyItemNameIconBtn.setIcon(copyIcon);
						}
					});
					
					timer.setRepeats(false);
					timer.start();
				}
				else if (e.getSource() == copyUsernameIconBtn) {
					copiedValue = item.getUsername();
					StringSelection stringSelection = new StringSelection(copiedValue);
					clipboard.setContents(stringSelection, null);
					
					copyUsernameIconBtn.setIcon(greenCheckIcon);
					
					Timer timer = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							copyUsernameIconBtn.setIcon(copyIcon);
						}
					});
					
					timer.setRepeats(false);
					timer.start();
				} 
				else if (e.getSource() == copyPasswordIconBtn) {
					copiedValue = item.getPassword();
					StringSelection stringSelection = new StringSelection(copiedValue);
					clipboard.setContents(stringSelection, null);
					
					copyPasswordIconBtn.setIcon(greenCheckIcon);
					
					Timer timer = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							copyPasswordIconBtn.setIcon(copyIcon);
						}
					});
					
					timer.setRepeats(false);
					timer.start();
				} 
				else if (e.getSource() == copyWebsiteIconBtn) {
					copiedValue = item.getURL();
					StringSelection stringSelection = new StringSelection(copiedValue);
					clipboard.setContents(stringSelection, null);
					
					copyWebsiteIconBtn.setIcon(greenCheckIcon);
					
					Timer timer = new Timer(2000, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							copyWebsiteIconBtn.setIcon(copyIcon);
						}
					});
					
					timer.setRepeats(false);
					timer.start();
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
				((Component) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
				((Component) e.getSource()).setBackground(new Color(56, 56, 56));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				((Component) e.getSource()).setBackground(new Color(40, 40, 40));
			}
		};
		
		addItemHeading.setFont(headingFont);
		addItemHeading.setForeground(new Color(192, 192, 192));
		addItemHeading.setBounds(110, 30, 178, 50);
		
		primaryInfoPanel.setBounds(100, 80, 550, 225);
		primaryInfoPanel.setLayout(null);
		primaryInfoPanel.setBackground(new Color(40, 40, 40));

		itemNameLbl.setFont(bodyFont);
		itemNameLbl.setBounds(15, 10, 90, 30);
		itemNameLbl.setForeground(new Color(192, 192, 192));

		itemNameValue.setFont(bodyFont);
		itemNameValue.setBounds(15, 40, 465, 20);
		itemNameValue.setForeground(new Color(255, 255, 255));
		copyItemNameIconBtn.setOpaque(true);
		copyItemNameIconBtn.setBounds(485, 20, 50, 50);
		copyItemNameIconBtn.setBackground(new Color(40, 40, 40));
		copyItemNameIconBtn.addMouseListener(buttonMouseListener);
		usernameLbl.setFont(bodyFont);
		usernameLbl.setBounds(15, 80, 90, 30);
		usernameLbl.setForeground(new Color(192, 192, 192));
		usernameValue.setFont(bodyFont);
		usernameValue.setBounds(15, 110, 465, 20);
		usernameValue.setForeground(new Color(255, 255, 255));
		copyUsernameIconBtn.setOpaque(true);
		copyUsernameIconBtn.setBounds(485, 90, 50, 50);
		copyUsernameIconBtn.setBackground(new Color(40, 40, 40));
		copyUsernameIconBtn.addMouseListener(buttonMouseListener);
		passwordLbl.setFont(bodyFont);
		passwordLbl.setBounds(15, 150, 90, 30);
		passwordLbl.setForeground(new Color(192, 192, 192));
		passwordValue.setBackground(null);
		passwordValue.setBorder(null);
		passwordValue.setFont(bodyFont);
		passwordValue.setBounds(15, 180, 400, 20);
		passwordValue.setForeground(new Color(255, 255, 255));
		togglePasswordVisibilityIconBtn.setOpaque(true);
		togglePasswordVisibilityIconBtn.setBounds(430, 160, 50, 50);
		togglePasswordVisibilityIconBtn.setBackground(new Color(40, 40, 40));
		togglePasswordVisibilityIconBtn.addMouseListener(buttonMouseListener);
		copyPasswordIconBtn.setOpaque(true);
		copyPasswordIconBtn.setBounds(485, 160, 50, 50);
		copyPasswordIconBtn.setBackground(new Color(40, 40, 40));
		copyPasswordIconBtn.addMouseListener(buttonMouseListener);
		websitePanel.setBounds(100, 350, 550, 80);
		websitePanel.setLayout(null);
		websitePanel.setBackground(new Color(40, 40, 40));
		
		websiteLbl.setFont(bodyFont);
		websiteLbl.setBounds(15, 10, 90, 30);
		websiteLbl.setForeground(new Color(192, 192, 192));
		websiteValue.setFont(bodyFont);
		websiteValue.setBounds(15, 40, 465, 20);
		websiteValue.setForeground(new Color(255, 255, 255));
		copyWebsiteIconBtn.setOpaque(true);
		copyWebsiteIconBtn.setBounds(485, 15, 50, 50);
		copyWebsiteIconBtn.setBackground(new Color(40, 40, 40));
		copyWebsiteIconBtn.addMouseListener(buttonMouseListener);

		notesLbl.setFont(headingFont);
		notesLbl.setBounds(110, 475, 64, 30);
		notesLbl.setForeground(new Color(192, 192, 192));

		notesValue.setText(item.getNotes());
		notesValue.setFont(bodyFont);
		notesValue.setBackground(new Color(40, 40, 40));
		notesValue.setForeground(new Color(255, 255, 255));
		notesValue.setCaretColor(Color.white);
		notesValue.setLineWrap(true);
		notesValue.setWrapStyleWord(true);
		notesValue.setEditable(false);
		notesValue.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		notesValuePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notesValuePane.setBounds(100, 515, 550, 300);
		notesValuePane.setPreferredSize(new Dimension(500, 300));
		notesValuePane.setBorder(new LineBorder(Color.RED, 0));
		
		editItemBtn.setBounds(100, 865, 100, 40);
		editItemBtn.setFont(bodyFont);
		editItemBtn.setBackground(null);
		editItemBtn.setForeground(new Color(192, 192, 192));
		editItemBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		editItemBtn.setFocusPainted(false);
		
		deleteItemBtn.setBounds(220, 865, 100, 40);
		deleteItemBtn.setFont(bodyFont);
		deleteItemBtn.setBackground(null);
		deleteItemBtn.setForeground(new Color(192, 192, 192));
		deleteItemBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		deleteItemBtn.setFocusPainted(false);
		
		primaryInfoPanel.add(itemNameLbl);
		primaryInfoPanel.add(itemNameValue);
		primaryInfoPanel.add(copyItemNameIconBtn);
		primaryInfoPanel.add(usernameLbl);
		primaryInfoPanel.add(usernameValue);
		primaryInfoPanel.add(copyUsernameIconBtn);
		primaryInfoPanel.add(passwordLbl);
		primaryInfoPanel.add(passwordValue);
		primaryInfoPanel.add(togglePasswordVisibilityIconBtn);
		primaryInfoPanel.add(copyPasswordIconBtn);
		websitePanel.add(websiteLbl);
		websitePanel.add(websiteValue);
		websitePanel.add(copyWebsiteIconBtn);
		
		rightPanel.add(addItemHeading);
		rightPanel.add(primaryInfoPanel);
		rightPanel.add(websitePanel);
		rightPanel.add(notesLbl);
		rightPanel.add(notesValuePane);
		rightPanel.add(editItemBtn);
		rightPanel.add(deleteItemBtn);
		
		rightPanel.revalidate();
		rightPanel.repaint();
		
		editItemBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame loggedinPageJFrame = getjFrame();
				editItemBtn.setBackground(new Color (50, 50, 50));
                EditItemPage editItemPage = new EditItemPage(loggedinPageJFrame, item);
                editItemPage.setVisible(true);
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
				editItemBtn.setBackground(new Color (60, 60, 60));
				editItemBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				editItemBtn.setBackground(null);
			}
		});
		
		deleteItemBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AccountDB.getAccount().get(AccountDB.getLoggedInAccountIndex()).removeItem(item);
				showDeletedItemSuccessDialog();
				updateLeftPanelGUI();
				resetRightPanel();
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
				deleteItemBtn.setBackground(new Color (60, 60, 60));
				deleteItemBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deleteItemBtn.setBackground(null);
			}
		});
	}
	
	public void updateLeftPanelGUI() {
		updateLeftPanel(itemList);
	}
	
	private void resetRightPanel() {
		rightPanel.removeAll();
		rightPanel.revalidate();
		rightPanel.repaint();
	}
	
	public void updateRightPanelGUI(ItemDetails item) {
		updateRightPanel(item);
	}
	
	
	public void resetView() {
		rightPanelScrollPane.getViewport().setViewPosition(new Point(0,0));
	}
	
	public JFrame getjFrame() {
		return this;
	}
}
