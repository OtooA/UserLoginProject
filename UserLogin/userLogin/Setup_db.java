package userLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import org.sqlite.SQLiteDataSource;



public class Setup_db {
	public static void setUpDatabase () throws IOException {
		UpdateSQLiteDatabase databaseObject = new UpdateSQLiteDatabase ();
		String dbLocationName;
		dbLocationName = databaseObject.getdatabaseLocation();
		
		//Create a datasource object to use to connect to a database
				SQLiteDataSource ds = null;
				
				//Store file location of database file if available 
				File file = new File (dbLocationName);
				//If statement to check if database already exists
				if (file.exists())
				{
					System.out.println("This database name already exists");
					//Initialize Datasource object to use to access database
					try {
						ds = new SQLiteDataSource ();
						//Indicate location where database is located
						ds.setUrl("jdbc:sqlite:" + dbLocationName);
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(0);
					}
					
					//Obtain total number of tables and rows in database file then close database
					//First establish connection to the database then create an object to use to issue statements
					try (Connection conn = ds.getConnection(); Statement stmt = conn.createStatement(); ) 
							{
								//Create a result variable to store results from queries 
								ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table'"); 
								//Getting Number of Tables
								int tableCount = rs.getInt(1);
								System.out.println("Number Of Tables in database " + tableCount);
								rs = stmt.executeQuery("SELECT COUNT (*) FROM users");
								//Getting Number of Rows
								int rowCount = rs.getInt(1); 
								System.out.println("Number Of Rows in database " + rowCount);
								stmt.close();
								conn.close();
							} 
					catch (Exception e) {
						e.printStackTrace();
						System.exit(0);
					}
					
					//Give user option to either recreate database file or exit
					System.out.print("Would you like to delete and recreate this database? " +
					"enter yes to delete and no to exit : ");
					BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
					String answer = input.readLine();
					if (answer.equals("yes")) {
						file.delete();
						System.out.println("File Has Been Deleted");
						//Note to self: Database and table will not be deleted if open in terminal window
						//It will instead continue adding new rows to the table as previously written.
						//Must close database in terminal window if you wish to delete and recreate database.
						
					}
					else if (answer.equals("no")) {
						System.out.println("Skipped Deletion");
						return;
					}
					else {
						return;
					}
					
					
					
				}
				
				
				try {
					//Initialize Datasource object to use to access database
					ds = new SQLiteDataSource ();
					//Indicate location where database is located
					ds.setUrl("jdbc:sqlite:" + dbLocationName);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
				
				//Create query statements to create/update tables
				//Create table users if it does not already exist
				//Initialize users table with username row and password_hash row
				String query = "CREATE TABLE IF NOT EXISTS users ( " +
						"username VARCHAR, " +
						"password_hash VARCHAR " +
						")";
				
				//Establish connection to the database then create an object to use to issue statements
				try (Connection conn = ds.getConnection();
					 Statement stmt = conn.createStatement(); ) 
					{
						//Update table by executing 1st query and return number of rows affected
						int rv = stmt.executeUpdate(query);
						System.out.println("1st executeUpdate() returned " + rv);
						//Create a result variable to store results from queries 
						ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = 'table'"); 
						//Getting Number of Tables
						int tableCount = rs.getInt(1);
						System.out.println("Number Of Tables returned " + tableCount);
						rs = stmt.executeQuery("SELECT COUNT (*) FROM users");
						//Getting Number of Rows
						int rowCount = rs.getInt(1); 
						System.out.println("Number Of Rows returned " + rowCount);
					} 
				catch (SQLException e) {
						e.printStackTrace();
						System.exit(0);
					}
					
					System.out.println("Created database successfully");

	}

}
