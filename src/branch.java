
public class branch {

	private String id;
	private static int votes;
	
	
	
	public branch(String id)
	{
		this.id = id;
		votes=0;
	}
	public void setName(String id)
	{
	this.id = id;
	}
	public static void incrementVoteCount()
	{
		votes++;
	}
	public String getid()
	{
		return id;
	}
	public int getNumberOfVotes()
	{
		return votes;
	}
	public static void castVote() {
		// TODO Auto-generated method stub
		
	}
}
