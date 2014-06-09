import java.util.LinkedList;


public class TeamNode 
{
	int number;
	LinkedList <String> tagList = new LinkedList <String>();
	
	public TeamNode(int passNumber)
	{
		this.number = passNumber;
	}
	
	public void addTag(String newTag)
	{
		tagList.add(newTag);
	}
	
	public Boolean searchTag(String soughtTag)
	{
		return tagList.contains(soughtTag);
	}
	
	public Boolean removeTag(String soughtTag)
	{
		Boolean found;
		
		found = this.searchTag(soughtTag);
		
		tagList.remove(soughtTag);
		return found;
	}
}
