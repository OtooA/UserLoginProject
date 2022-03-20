package userLogin;

import java.io.Console;


public class maskPassword {
	
	public String getMaskedPassword () throws Exception {
		ConsoleUtil hidePassword = new ConsoleUtil ();
	    Console cons = System.console();
	    String message = "Please enter a password: ";
	    String consoleResult = hidePassword.getPasswordMasked(cons, message);
	    return consoleResult;
	}

}
