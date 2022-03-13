package userLogin;
import java.io.Console;

public class ConsoleUtil {
	
    /**
     * Handles console input when running outside of Eclipse.
     * 
     * @param cons the console to use in order to receive input
     * @param msg the prompt message
     * @return the password input by the user
     */
    public static String getPasswordMasked(Console cons, String msg)
    {
        char[] passwd;
        while (true) {
            passwd = cons.readPassword("%s", msg);
            //Read in console input, mask password and store password in char array. 
            //Will terminate when it hits null character.
            if (passwd != null) {
                if (passwd.length > 0) {
                    return new String(passwd);
                } else {
                    System.out.println("Invalid input\n");
                }
            }
        }
    }
}