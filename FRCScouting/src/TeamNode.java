import java.util.LinkedList;
import java.util.ListIterator;


public class TeamNode 
{
	private LinkedList <String> tagList = new LinkedList <String>();
	private ListIterator <String> conducter;
	private int wins, losses;
	
	public TeamNode()
	{
		wins = losses = 0;
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
	
	public void getReset()
	{
		conducter = tagList.listIterator();
	}
	
	public Boolean hasNext()
	{
		return conducter.hasNext();
	}
	
	public String getNext()
	{
		return conducter.next();
	}
	
	public void addWin()
	{
		wins++;
	}
	
	public void addLoss()
	{
		losses++;
	}
	
	public int getWins()
	{
		return wins;
	}
	
	public int getLosses()
	{
		return losses;
	}
}
