package userLogin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Iterator;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.sqlite.SQLiteDataSource;

public class UpdateSQLiteDatabase {
	
	public static String getdatabaseLocation () {
		//Create JSON Array to store information read in from JSON File
        JSONArray userList = new JSONArray();
        String databaseLocation = null; 
		try (FileReader reader = new FileReader("C://Users//Otoo Andoh//Documents//JsonTest/test.json"))
        {
			
			
			//JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
			
			//Store contents of JSON file as an array to hold database information 
            Object obj1 =  jsonParser.parse(reader);
            
            userList = (JSONArray) obj1; 
            
            //Create iterator to iterate through JSON Array 
            Iterator<JSONObject> itr = userList.iterator(); 
			
            
            //Retrieve database object within list and store database location
            JSONObject userObject = (JSONObject) itr.next().get("databaseTag");
            
            databaseLocation = (String) userObject.get("DatabaseLocation");  
            
            
            
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return databaseLocation;
		
		
	}
	
	public static void updateDatabase (String username, String hashedPassword) {
		//Create a Datasource object to use to connect to a database
		SQLiteDataSource ds = null;
		String dbLocation = getdatabaseLocation();
		
		try {
			//Initialize Datasource object to use to access database
			ds = new SQLiteDataSource ();
			//Indicate location where database is located
			ds.setUrl("jdbc:sqlite:" + dbLocation);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Insert entered username as well as hashed password
		String query1 = "INSERT INTO users VALUES ('" + username + "', " + 
					"'" + hashedPassword + "')";
		
		//Establish connection to the database then create an object to use to issue statements
				try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement(); ) 
					{
						//Update table by executing 1st query and return number of rows affected
						int rv = stmt.executeUpdate(query1);	
						//Create a result variable to store results from queries 
						ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table'"); 
						//Getting Number of Tables
						int tableCount = rs.getInt(1);
						System.out.println("Number Of Tables returned " + tableCount);
						rs = stmt.executeQuery("SELECT COUNT (*) FROM users");
						//Getting Number of Rows
						int rowCount = rs.getInt(1); 
						System.out.println("Number Of Rows returned " + rowCount);
	
						stmt.close();
						conn.close();
					} 
				catch (SQLException e) {
						e.printStackTrace();
						System.exit(0);
					}
					
	}
	
	public static int checkExistingUsername (String username){
		//Create a Datasource object to use to connect to a database
		int userCount = 0;
		SQLiteDataSource ds = null;
		String dbLocation = getdatabaseLocation();
		
		try {
			//Initialize Datasource object to use to access database
			ds = new SQLiteDataSource ();
			//Indicate location where database is located
			ds.setUrl("jdbc:sqlite:" + dbLocation);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Establish connection to the database then create an object to use to issue statements
		try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement(); ) {
			
			//Create a result variable to store results from queries 
			//Getting number of users that match entered username
			ResultSet rs = stmt.executeQuery("SELECT COUNT (*) FROM users WHERE username = '" + username + "'");
			userCount = rs.getInt(1); 
			stmt.close();
			conn.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return userCount;
		
	}
	
	public static int is_valid_credentials (String username, String hashedpassword){
		//Create a datasource object to use to connect to a database
		int userCount = 0;
		SQLiteDataSource ds = null;
		String dbLocation = getdatabaseLocation();
		
		try {
			//Initialize Datasource object to use to access database
			ds = new SQLiteDataSource ();
			//Indicate location where database is located
			ds.setUrl("jdbc:sqlite:" + dbLocation);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Establish connection to the database then create an object to use to issue statements
		try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement(); ) {
			
			//Create a result variable to store results from queries 
			//Getting number of users that match entered username
			ResultSet rs = stmt.executeQuery("SELECT COUNT (*) FROM users WHERE username = '" + username + "'"
					+ "AND password_hash = '" + hashedpassword + "'");
			userCount = rs.getInt(1); 
			stmt.close();
			conn.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return userCount;
		
	}

}
