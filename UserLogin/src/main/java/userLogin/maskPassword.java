package userLogin;

import java.io.Console;


public class maskPassword {
	//Returns masked version of user entered password
	public String getMaskedPassword () throws Exception {
		//Create instance of ConsoleUtil class to utilize console
		ConsoleUtil hidePassword = new ConsoleUtil ();
	    Console cons = System.console();
	    String message = "Please enter a password: ";
	    //Displays message on console and reads user entered password from console. Will return masked user password
	    String consoleResult = hidePassword.getPasswordMasked(cons, message);
	    return consoleResult;
	}

}
