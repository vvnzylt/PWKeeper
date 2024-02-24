package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import backend.ItemDetails;

public class EditItemPage extends JFrame {
	public EditItemPage(JFrame loggedInPageJFrame, ItemDetails item) {
		initializeUI(loggedInPageJFrame, item);
	}
	
	private void initializeUI(JFrame loggedInPageJFrame, ItemDetails item) {
		loggedInPageJFrame.setEnabled(false);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("src/resources/logo_without_text.png");
		Font headingFont = new Font("Helvetica", Font.BOLD, 18);
		Font bodyFont = new Font("Helvetica", Font.PLAIN, 16);
		
		setSize(720, 600);
		setIconImage(icon);
		setTitle("Edit item — PWKeeper");
		setLayout(null);
		setResizable(false);
		setLocation(420, 120);
		setVisible(true);
		
		JPanel mainPanel = new JPanel();
		
		JLabel editItemHeading = new JLabel("EDIT ITEM");
		editItemHeading.setFont(headingFont);
		editItemHeading.setForeground(new Color(192, 192, 192));
		editItemHeading.setBounds(110, 30, 100, 50);
		
		JPanel panel1 = new JPanel();
		
		JLabel itemNameLbl = new JLabel("Item name");
		itemNameLbl.setFont(bodyFont);
		itemNameLbl.setBounds(15, 10, 90, 30);
		itemNameLbl.setForeground(new Color(192, 192, 192));
		JTextField itemNameTxtField = new JTextField();
		itemNameTxtField.setFont(bodyFont);
		itemNameTxtField.setBounds(16, 40, 465, 40);
		itemNameTxtField.setForeground(new Color(192, 192, 192));
		itemNameTxtField.setBackground(new Color(96, 96, 96));
		itemNameTxtField.setBorder(new EmptyBorder(10, 10, 10, 10));
		itemNameTxtField.setCaretColor(Color.white);
		itemNameTxtField.setText(item.getItemName());
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setFont(bodyFont);
		usernameLbl.setBounds(15, 90, 90, 30);
		usernameLbl.setForeground(new Color(192, 192, 192));
		JTextField usernameTxtField = new JTextField();
		usernameTxtField.setFont(bodyFont);
		usernameTxtField.setBounds(16, 120, 465, 40);
		usernameTxtField.setForeground(new Color(192, 192, 192));
		usernameTxtField.setBackground(new Color(96, 96, 96));
		usernameTxtField.setBorder(new EmptyBorder(10, 10, 10, 10));
		usernameTxtField.setCaretColor(Color.white);
		usernameTxtField.setText(item.getUsername());
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(bodyFont);
		passwordLbl.setBounds(15, 170, 90, 30);
		passwordLbl.setForeground(new Color(192, 192, 192));
		JPasswordField passwordTxtField = new JPasswordField();
		passwordTxtField.setFont(bodyFont);
		passwordTxtField.setBounds(16, 200, 465, 40);
		passwordTxtField.setForeground(new Color(192, 192, 192));
		passwordTxtField.setBackground(new Color(96, 96, 96));
		passwordTxtField.setBorder(new EmptyBorder(10, 10, 10, 10));
		passwordTxtField.setCaretColor(Color.white);
		passwordTxtField.setText(item.getPassword());
		
		JLabel urlLbl = new JLabel("URL");
		urlLbl.setFont(bodyFont);
		urlLbl.setBounds(15, 250, 90, 30);
		urlLbl.setForeground(new Color(192, 192, 192));
		JTextField urlTxtField = new JTextField();
		urlTxtField.setFont(bodyFont);
		urlTxtField.setBounds(16, 280, 465, 40);
		urlTxtField.setForeground(new Color(192, 192, 192));
		urlTxtField.setBackground(new Color(96, 96, 96));
		urlTxtField.setBorder(new EmptyBorder(10, 10, 10, 10));
		urlTxtField.setCaretColor(Color.white);
		urlTxtField.setText(item.getURL());
		
		
		panel1.setLayout(null);
		panel1.setBackground(new Color(40, 40, 40));
		panel1.setBounds(100, 80, 500, 350);
		panel1.add(itemNameLbl);
		panel1.add(itemNameTxtField);
		panel1.add(usernameLbl);
		panel1.add(usernameTxtField);
		panel1.add(passwordLbl);
		panel1.add(passwordTxtField);
		panel1.add(urlLbl);
		panel1.add(urlTxtField);
		
		JLabel notesHeading = new JLabel("NOTES");
		notesHeading.setFont(headingFont);
		notesHeading.setForeground(new Color(192, 192, 192));
		notesHeading.setBounds(110, 445, 100, 50);
		
		JTextArea notesTxtArea = new JTextArea();
		notesTxtArea.setFont(bodyFont);
		notesTxtArea.setBackground(new Color(40, 40, 40));
		notesTxtArea.setForeground(Color.white);
		notesTxtArea.setCaretColor(Color.white);
		notesTxtArea.setLineWrap(true);
		notesTxtArea.setWrapStyleWord(true);
		notesTxtArea.setBorder(new EmptyBorder(15, 15, 15, 15));
		notesTxtArea.setCaretColor(Color.white);
		notesTxtArea.setText(item.getNotes());
		
		JScrollPane notesTxtAreaPane = new JScrollPane(notesTxtArea);
//		notesTxtAreaPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		notesTxtAreaPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notesTxtAreaPane.setPreferredSize(new Dimension(500, 100));
		notesTxtAreaPane.setBorder(new LineBorder(Color.RED, 0));
		notesTxtAreaPane.setBounds(100, 495, 500, 300);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.setBounds(100, 845, 100, 40);
		saveBtn.setFont(bodyFont);
		saveBtn.setBackground(null);
		saveBtn.setForeground(new Color(192, 192, 192));
		saveBtn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
		saveBtn.setFocusPainted(false);
		
		mainPanel.add(editItemHeading);
		mainPanel.add(panel1);
		mainPanel.add(notesHeading);
		mainPanel.add(notesTxtAreaPane);
		mainPanel.add(saveBtn);
		mainPanel.setBounds(0, 0, 720, 600);
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(26, 26, 26));
		mainPanel.setPreferredSize(new Dimension(720, 950));
		
		JScrollPane panel1Scroll = new JScrollPane(mainPanel);
		panel1Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel1Scroll.setBounds(-1, 0, 720, 600);
		panel1Scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		panel1Scroll.getVerticalScrollBar().setUnitIncrement(16);
		getContentPane().add(panel1Scroll);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	loggedInPageJFrame.setEnabled(true);
            }
        });
		
		saveBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveBtn.setBackground(new Color (50, 50, 50));
				
				String itemNameValue = itemNameTxtField.getText();
            	String usernameValue = usernameTxtField.getText();
            	char[] passwordChar = passwordTxtField.getPassword();
                String passwordValue = new String(passwordChar);
                String urlValue = urlTxtField.getText();
                String notesValue = notesTxtArea.getText();
                
                item.setItemName(itemNameValue);
                item.setUsername(usernameValue);
                item.setPassword(passwordValue);
                item.setURL(urlValue);
                item.setNotes(notesValue);
                
                ((LoggedInPage) loggedInPageJFrame).updateRightPanelGUI(item);
                loggedInPageJFrame.setEnabled(true);
                ((LoggedInPage) loggedInPageJFrame).updateLeftPanelGUI();
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
				saveBtn.setBackground(new Color (60, 60, 60));
				saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				saveBtn.setBackground(null);
			}
		});
	}
}
