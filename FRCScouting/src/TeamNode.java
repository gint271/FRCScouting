/*
 * Author: Ryan Milem
 * 
 * Date: 6/11/14
 * 
 * Purpose: Stores the tags for a single team.
 */

import java.util.LinkedList;
import java.util.ListIterator;


public class TeamNode 
{
	private LinkedList <String> tagList = new LinkedList <String>();
	private ListIterator <String> conducter = null;
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
		if(conducter != null)
		{
			return conducter.hasNext();
		}
		else
		{
			return false;
		}
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
	
	public Boolean contains(String tag)
	{
		return tagList.contains(tag);
		
	}
}
