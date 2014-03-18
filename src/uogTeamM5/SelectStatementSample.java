package uogTeamM5;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class SelectStatementSample 
{


		 public static void main(String[] args) throws Exception
		    {
			 
			 System.out.println("hello ");
		        Connection connection = null;
		        try
		        {
		            // Register MySQL JDBC driver to be known by 
		            // the DriverManager object.
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		 
		            // Get a connection to database. We prepare the 
		            // connection information here such as database 
		            // url, user and password.
		            
		            //String url = "jdbc:oracle://apollo01.glos.ac.uk/vote_results";
		            //String url = "jdbc:mysql://localhost/testdb";
		            
		            String url="jdbc:oracle:thin:@apollo01.glos.ac.uk:1521:4500:1001:9815834667312::NO:::";
		            
		            
		            String user = "s1308404@connect.glos.ac.uk";
		            String password = "Lol123";
		            connection = DriverManager.getConnection(url, user, password);
		 
		            // Create a statement object instance from the 
		            // connection
		            Statement stmt = connection.createStatement();
		 
		            // We are going to execute an insert statement. 
		            // First you have to create a table that has an 
		            // ID, NAME and ADDRESS field. For ID you can use 
		            // an auto number, while NAME and ADDRESS are 
		            // VARCHAR fields.
		            String sql = "INSERT INTO vote_results(ACOUNT,BCOUNT,CCOUNT,DCOUNT) " +
		                    "VALUES ('1', '2','4','6')";
		 
		            System.out.println("sql: ["+sql+ "]");
		            // Call an execute method in the statement object 
		            // and passed the sql or query string to it.
		            stmt.execute(sql);
		 
		            // After this statement is executed you'll have a 
		            // record in your users table.
		        } catch (Exception e)
		        {
		            e.printStackTrace();
		        } finally
		        {
		            if (connection != null)
		            {
		                connection.close();
		            }
		        }
		    }
		}








