import java.util.Scanner;
public class calculatevote {

	public static void main(String[] args) {
		

		
		{
		    // instance variables 
		    private Scanner keyboard;
		    private votemanager ballot;
		    private String votingMessage;

		
		    public calculatevote()
		    {
		        keyboard = new Scanner(System.in);
		    }

		    /**
		     * The top level menu
		     */
		    public void menu()    
		    {
		        int command = -1;
		        while(command != 0)        
		        {
		            displayMainMenu();
		            command = getCommand();
		            execute(command);
		        }
		    }

		    /**
		     * Displays the instructions for the top level menu
		     */
		    private void displayMainMenu()
		    {
		        System.out.println("Options are");
		        System.out.println("    To vote for Fred          Enter 1");
		        System.out.println("    To vote for Bill          Enter 2");
		        System.out.println("    To vote for Joe           Enter 3"); 
		        System.out.println("    To vote for Jane          Enter 4");
		        System.out.println("To close    Enter 0");
		    }

		    /**
		     * Calls the method appropriate to the command received
		     */
		    private void execute(int command)
		    {
		        if(command == 1)
		        {
		            branch1.incrementVoteCound();
		        }
		        else if(command == 2)
		        {
		            branch2.incrementVoteCound();
		        }
		        else if(command == 3)
		        {
		            branch3.incrementVoteCound();
		        }
		        else if(command == 4)
		        {
		            branch4.incrementVoteCound();
		        }            
		        else if(command == 0)
		        {
		            System.out.println(" Program closing down");
		        }
		        else
		        {
		            System.out.println("Unknown command");
		            branch.castVote();
		        }

		    }

		    /**
		     * Receives the specified command
		     */
		    private int getCommand()
		    {
		        System.out.print("Enter command: ");
		        return keyboard.nextInt();
		    }

		    /**
		     * Method printBookingDetails
		     * This method allows you to print the full booking details, including title, full name, booking number and room type.
		     */
		    private void setupBallot()
		    {
		        return displayMainMenu();
		    }

		    private void setupVoteMessage(String vote)
		    {
		    }
		}	

	}

}
