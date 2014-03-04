
public class votemanager {
private branch branch1;
private branch branch2;
private branch branch3;
private branch branch4;

public votemanager(String branch1,String branch2,String branch3,String branch4)
{
this.branch1 = new branch (branch1);
this.branch2 = new branch (branch2);
this.branch3 = new branch (branch3);
this.branch4 = new branch (branch4);

}
public void castVote(int vote)
{
	if (vote==1)
		branch1.incrementVoteCount();
	else if (vote==2)
		branch2.incrementVoteCount();
	else if (vote==3)
		branch3.incrementVoteCount();
	else if (vote==4)
		branch4.incrementVoteCount();
}
}