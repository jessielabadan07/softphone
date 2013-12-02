package org.gui.program;

public interface IValidate {
	
	public Boolean validateNumber(String input);
	
	public Boolean validateAlphaNumericChars(String input);
	
	public void displayError(String errorTitle, String errorMessage);
	
}
