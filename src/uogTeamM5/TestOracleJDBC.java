package uogTeamM5;



	
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		 
		public class TestOracleJDBC {
		    public static void main1(String[] args) {
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		        try {
		            Class.forName("oracle.jdbc.driver.OracleDriver");
		            con = DriverManager.getConnection("jdbc:oracle:thin:testuser/testpass@localhost");
		            stmt = con.createStatement();
		            rs = stmt.executeQuery("SELECT * FROM person");
		            while(rs.next()) {
		                System.out.print(rs.getInt(1) + "\t");
		                System.out.println(rs.getString(2));
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                rs.close();
		                stmt.close();
		                con.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		
		public static void main(String[] args) throws Exception
	    {
	        Connection connection = null;
	        try
	        {
	            // Register MySQL JDBC driver to be known by 
	            // the DriverManager object.
	            Class.forName("com.Oracle.jdbc.Driver");
	 
	            // Get a connection to database. We prepare the 
	            // connection information here such as database 
	            // url, user and password.
	            String url = "jdbc:mysql://localhost/testdb";
	            String user = "root";
	            String password = "";
	            connection = DriverManager.getConnection(url, 
	                user, password);
	 
	            // Create a statement object instance from the 
	            // connection
	            Statement stmt = connection.createStatement();
	 
	            // We are going to execute an insert statement. 
	            // First you have to create a table that has an 
	            // ID, NAME and ADDRESS field. For ID you can use 
	            // an auto number, while NAME and ADDRESS are 
	            // VARCHAR fields.
	            String sql = "INSERT INTO users (name, address) " +
	                    "VALUES ('Foo Bar', 'Some Street')";
	 
	            // Call an execute method in the statement object 
	            // and passed the sql or query string to it.
	            stmt.execute(sql);
	 
	            // After this statement is executed you'll have a 
	            // record in your users table.
	        } catch (ClassNotFoundException e)
	        {
	            System.err.println("Could not load database driver!");
	        } catch (SQLException e)
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
	















