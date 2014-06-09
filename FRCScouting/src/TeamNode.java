import java.util.LinkedList;


public class TeamNode 
{
	LinkedList <String> tagList = new LinkedList <String>();
	
	public TeamNode()
	{
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
