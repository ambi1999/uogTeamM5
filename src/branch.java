
public class branch {

	private String id;
	private int votes;
	
	
	
	public branch(String id)
	{
		this.id = id;
		votes=0;
	}
	public void setName(String id)
	{
	this.id = id;
	}
	public void incrementVoteCount()
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
}
