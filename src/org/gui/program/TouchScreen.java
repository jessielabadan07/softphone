package org.gui.program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class TouchScreen extends JFrame 
	implements IValidate {
	
	/**
	 * Program Version: V1.0
	 * 
	 */	
	private static final long serialVersionUID = 6071329097652985065L;

	
	// Component container
	private JPanel topPanel;	
	private JPanel fourButtonPanel;
	private JPanel threeButtonPanel;
	private JPanel buttonPanel;	
	private JPanel mainPanel;
	private Box box,box2,box3,box4;
	
	// Components 
	private JTextArea windowTextArea;
	private JButton[] fourButtons;
	private JButton[] threeButtons;
	private JButton[] numberButtons;	
	private JButton flashBtn, clearBtn, btnOnCall, btnHangUp;
	
	// boolea variable	
	private boolean isLogin = false;
	
	// String variables 
	private String inputText = "";
	private String dialNumber = "";	
	private String[] topFourString = { "1", "2", "3", "4" };
	private String[] topThreeString = { "FRWD", "CONF", "LOG IN" };
	private String[] buttonString = { "1", "2", "3",
									  "4", "5", "6",
									  "7", "8", "9",
									  "*", "0", "#" }; 
	
	
	// counter variable
	private static int counterCalling = 0;
	
	private JList<User> users;
	
	public TouchScreen(String title) {
		super(title);		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);		
		mainPanel.setLayout(new FlowLayout());
		
		box = Box.createHorizontalBox();
		box2 = Box.createHorizontalBox();
		box3 = Box.createHorizontalBox();
		box4 = Box.createHorizontalBox();
		
		windowTextArea = new JTextArea(7,35);		
		windowTextArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		windowTextArea.setForeground(new Color(0,0,128));
		windowTextArea.setLineWrap(true);
		windowTextArea.setEditable(false);
		topPanel = new JPanel();		
		topPanel.setBackground(Color.black);
		topPanel.add(new JScrollPane(windowTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		topButtonFour();
		topButtonThree();
		numberButtons();		
		box.add(topPanel, BorderLayout.NORTH);
		box2.add(fourButtonPanel, BorderLayout.WEST);
		box3.add(threeButtonPanel, BorderLayout.WEST);
		box4.add(buttonPanel, BorderLayout.CENTER);				
		mainPanel.add(box);
		mainPanel.add(box2);
		mainPanel.add(box3);
		mainPanel.add(box4);
		add(mainPanel);		
		settings();
	}
	
	private void topButtonFour() {
		fourButtonPanel = new JPanel();
		fourButtonPanel.setBackground(Color.black);
		fourButtonPanel.setLayout(new FlowLayout());
		fourButtonPanel.add(new JLabel());
		fourButtonPanel.add(new JLabel());
		fourButtons = new JButton[topFourString.length];
		for(int i = 0; i < fourButtons.length; i++) {
			fourButtons[i] = new JButton(topFourString[i]);			
			fourButtons[i].setFont(new Font("Tahoma", Font.BOLD, 16));
			fourButtons[i].setBackground(new Color(240,255,255));
			// action listener
			fourButtons[i].addActionListener(
					new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(isLogin) {
								inputText += e.getActionCommand();
								windowTextArea.setText(inputText);
							}
						}
					});
			fourButtonPanel.add(fourButtons[i]);
		}
	}
	
	private void topButtonThree() {
		threeButtonPanel = new JPanel();
		threeButtonPanel.setBackground(Color.black);
		threeButtonPanel.setLayout(new FlowLayout());
		threeButtons = new JButton[topThreeString.length];
		for(int i = 0; i < threeButtons.length; i++) {
			threeButtons[i] = new JButton(topThreeString[i]);
			threeButtons[i].setFont(new Font("Tahoma", Font.BOLD, 16));
			
			threeButtons[i].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equalsIgnoreCase("LOG IN")) {						
						final LoginForm login = new LoginForm(null, "Log-In Client");
						
						login.btnLogin.addActionListener(new ActionListener() {							
							@Override
							public void actionPerformed(ActionEvent e) {
								if(validateNumber(login.userNameField.getText())) {
									if(validateAlphaNumericChars(login.passwordField.getText())) {
										inputText += "Your User Number: <" + login.userNameField.getText() + ">";
										windowTextArea.setText(inputText);
										login.setVisible(false);
										isLogin = true;										
									} else {
										displayError("Error: Invalid Input", "Please input alphanumeric only for password!");
										login.passwordField.setBackground(new Color(255,228,225));
										login.passwordField.setFocusable(true);
									}
								} else {
									displayError("Error: Invalid Input", "Please input number only!");
									login.userNameField.setBackground(new Color(255,228,225));
									login.userNameField.setFocusable(true);
								}
							}
						});
						
						
						login.getContentPane().setBackground(new Color(245,255,250));
						login.setResizable(false);
						login.setSize(400,180);
						login.setVisible(true);
						login.setDefaultCloseOperation(HIDE_ON_CLOSE);
					}									
				}
			});
			
			threeButtonPanel.add(threeButtons[i]);
		}
	}
	
	private void numberButtons() {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.black);
		
		JPanel leftBtnPanel = new JPanel();
		leftBtnPanel.setBackground(Color.black);
		Image myImageCall = getToolkit().createImage("btnCall.png");
		ImageIcon callIcon = new ImageIcon(myImageCall);
		btnOnCall = new JButton(callIcon);		
		btnOnCall.setBackground(Color.green);	
		btnOnCall.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(isLogin) {
					inputText += '\n' + "Line " + counterCalling + ": Calling " + dialNumber + "...";
					windowTextArea.setText(inputText);
				}
				dialNumber = "";
			}
		});
		leftBtnPanel.add(btnOnCall);
		
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.black);
		btnPanel.setLayout(new GridLayout(5, 3, 5, 5));
		
		threeButtonPanel.setBackground(Color.black);		
		numberButtons = new JButton[buttonString.length];
		
		// flash button
		flashBtn = new JButton("FLASH");
		flashBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// clear button
		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				inputText = "";
				windowTextArea.setText(null);
			}
		});				
		
		btnPanel.add(flashBtn);
		btnPanel.add(new JLabel());
		btnPanel.add(clearBtn);		
		for(int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton(buttonString[i]);
			numberButtons[i].setFont(new Font("Tahoma", Font.BOLD, 16));		
			numberButtons[i].setBackground(new Color(255,248,220));
			numberButtons[i].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(isLogin) {
						dialNumber += e.getActionCommand();						
						//inputText += e.getActionCommand();
						//windowTextArea.setText(inputText);
					}
				}
			});
			btnPanel.add(numberButtons[i]);
		}
		
		JPanel rightBtnPanel = new JPanel();
		Image myImageHangCall = getToolkit().createImage("btnHangUp.png");
		ImageIcon hangUpIcon = new ImageIcon(myImageHangCall);
		btnHangUp = new JButton(hangUpIcon);
		btnHangUp.setBackground(new Color(220,20,60));
		
		//btnHangUp.
		
		rightBtnPanel.add(btnHangUp);
		rightBtnPanel.setBackground(Color.black);
		
		// add to button panel
		buttonPanel.add(leftBtnPanel, BorderLayout.WEST);
		buttonPanel.add(btnPanel, BorderLayout.CENTER);
		buttonPanel.add(rightBtnPanel, BorderLayout.EAST);		
		counterCalling++;
	}
	
	private void settings() {
		centerScreen();
		getContentPane().setBackground(Color.black);				
		setSize(470, 470);
		setResizable(false);
		setVisible(true);				
		try {
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);			
		} catch (UnsupportedLookAndFeelException e) { }	    
	    catch (ClassNotFoundException e) { }	    
	    catch (InstantiationException e) { }
	    catch (IllegalAccessException e) { }
	    catch(Exception e) { e.printStackTrace(); }
		
		centerScreen();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void centerScreen() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
	
		
	@Override
	public Boolean validateNumber(String input) {
		// TODO Auto-generated method stub
		return input.matches("[0-9]+");
	}

	@Override
	public Boolean validateAlphaNumericChars(String input) {
		// TODO Auto-generated method stub
		return input.matches("[a-zA-Z]+[0-9]+");
	}

	@Override
	public void displayError(String errorTitle, String errorMessage) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}

}
