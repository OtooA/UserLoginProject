package userLogin;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSHA256 {
	//Method to hash passwords using SHA256 hash function
			public static String getSHA256 (String input) {
				try {
					//Specify what type of algorithm will be used to hash input
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					
					//calculates hash byte value by passing input as bytes into
					//SHA-256 hash function. Stores result in byte array.
					byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
					
					//convert hashed byte value into signum representation w/ BigInteger
					//takes the byte array and converts it into an integer
					//signum rep is 1 for pos, 0 for 0 , -1 for neg
					BigInteger no = new BigInteger(1, messageDigest);
					
					//Convert message digest into string by specifying what base number set 
					// will be used. Convert to hex by specifying base as 16 (hexadecimal)
					//use StringBuilder in order to create a mutuable string 
					StringBuilder hashtext = new StringBuilder (no.toString(16));
					
					//list hashtext as a hexadecimal constant no longer than 32 characters
					//pads string with 0s if shorter than 32 characters
					while (hashtext.length() < 32) {
						hashtext.insert(0, '0');
					}
					return hashtext.toString();
					
				}
				//Throw exception of hash algorithm does not exist/is invalid
				catch (NoSuchAlgorithmException e){ 
					throw new RuntimeException(e);
				}
				
			}

}
