package userLogin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

public class userLogin {

	public static void main(String[] args) throws Exception, NoSuchAlgorithmException {
		//Create instance of HashSHA256 class to be used in hashing user entered password
		HashSHA256 sha256Object = new HashSHA256 ();
		//Create instance of maskPassword class to hide user password while typing
		maskPassword maskPWObject = new maskPassword ();
		//Create instance of UpdateSQLiteDatabase class to update database with user information
		UpdateSQLiteDatabase databaseObject = new UpdateSQLiteDatabase ();
		//Create instance of Add_User class to update database with additional users
		Add_User addUserObject = new Add_User();
		//Create instance of Setup_db class to setup the initial database
		Setup_db setUpObject = new Setup_db();
		
		setUpObject.setUpDatabase();
		addUserObject.addUser();
		
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
		System.out.print("Please Enter Username: ");
		String username = input.readLine();
		//Masked user eneter password and then hash user password
		String password = maskPWObject.getMaskedPassword ();
		String hashedpw = sha256Object.getSHA256(password);
		
		//Pass both the username and hashed user password into database to check against any existing results
		int correctLogin = databaseObject.is_valid_credentials(username, hashedpw);
		//If there is an exact match/success then output success message, otherwise output failure message
		if (correctLogin == 1) {
			System.out.println ("Deep Dark Secret");
		}
		else
			System.out.println("Get Lost!"); 

	}

}
