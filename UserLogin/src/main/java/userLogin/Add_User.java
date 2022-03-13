package userLogin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Add_User {
	public static void addUser () throws IOException, Exception {
		//Create instance of HashSHA256 class to hash user entered password
		HashSHA256 sha256Object = new HashSHA256 ();
		//Create instance of UpdateSQLiteDatabase class to update database with user information
		UpdateSQLiteDatabase databaseObject = new UpdateSQLiteDatabase ();
		//Create instance of maskPassword class to mask user entered password
		maskPassword maskPWObject = new maskPassword ();
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
		int result = 0;
		String username; 
		//WIll store result of user choice when asked to add an additional user
		String addUserCheck;
		
		System.out.print("Would you like to add a user? " +
				"enter yes to add user and no to exit : ");
		
		addUserCheck = input.readLine();
		if (addUserCheck.equals("yes")) {
		
			do {
				System.out.print("Enter a Username: ");
				username = input.readLine();
				result = databaseObject.checkExistingUsername(username);
				if (result > 0)
				System.out.println ("Username has already been taken. Please enter a new username");
			} while (result != 0);
			//Read in, hash and mask user entered password and update database with new user information
			String password = maskPWObject.getMaskedPassword ();
			String sha256Result = sha256Object.getSHA256 (password);
			databaseObject.updateDatabase (username,sha256Result);
	
		}
		else if (addUserCheck.equals("no")) {
			return;
		}
		else {
			return;
		}

	}

}
