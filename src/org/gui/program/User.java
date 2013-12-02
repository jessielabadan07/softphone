package org.gui.program;

public class User {
	
	private int _userNumber;
	private String _userPassword;
		
	public void setUserNumber(int userNumber) {
		this._userNumber = userNumber;
	}
	
	public void setUserPassword(String userPassword) {
		this._userPassword = userPassword;
	}
	
	public int getUserNumber() {
		return this._userNumber;
	}
	
	public String getUserPassword() {
		return this._userPassword;
	}

}
