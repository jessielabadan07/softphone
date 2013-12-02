package org.gui.program;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JDialog {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel userNameLabel, passwordLabel;
	public JTextField userNameField;
	public JPasswordField passwordField;
	public JButton btnLogin, btnCancel;
	
	public LoginForm(JFrame frame, String title) {
		super(frame, title, true);
		
		userNameLabel = new JLabel("User No. ");
		userNameField = new JTextField(20);
		userNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel = new JLabel("Password ");
		passwordField = new JPasswordField(20);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin = new JButton("Log-In");
		btnCancel = new JButton("Cancel");
				
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(userNameLabel);
		panel1.add(new JLabel("  "));
		panel1.add(userNameField);
		panel1.setBackground(new Color(245,255,250));
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(passwordLabel);
		panel2.add(passwordField);
		panel2.setBackground(new Color(245,255,250));
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());						
		panel3.add(btnLogin);		
		panel3.add(btnCancel);
		panel3.setBackground(new Color(245,255,250));
		
		this.getContentPane().setLayout(new GridLayout(3, 1, 2, 2));
		
		add(panel1);
		add(panel2);
		add(panel3);
		centerScreen();
	}
		
	private void centerScreen() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 3);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 3);
	    this.setLocation(x, y);
	}

}
