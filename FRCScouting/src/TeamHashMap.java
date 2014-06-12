/*
 * Author: Ryan Milem
 * 
 * Date: 6/11/14
 * 
 * Purpose: Stores all the TeamNodes, associated with their numbers.  Also handles saving and combining saves.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javax.swing.JLabel;

public class TeamHashMap extends HashMap <Integer, TeamNode>
{
	public void save(String path)
	{
		File saveFile = new File(path);
		BufferedWriter saveWriter;
		try
		{
			//TODO: Handle closing saveWriter when quitting unexpectedly.
			saveWriter = new BufferedWriter(new FileWriter(saveFile));
		}
		catch (Exception e)
		{
			System.out.println("Failed to make save file.");
			return;
		}
		
		for(java.util.Map.Entry<Integer, TeamNode> entry : this.entrySet())
		{
		    System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
		    
		    try
		    {
		    	saveWriter.write(entry.getKey() + "\t" + entry.getValue().getWins() + "\t" + entry.getValue().getLosses());
		    	
		    	entry.getValue().getReset();
		    	
		    	while(entry.getValue().hasNext())
		    	{
		    		saveWriter.write("\t" + entry.getValue().getNext());
		    	}
		    }
		    catch (Exception e)
		    {
		    	System.out.println("Failed to write to file.");
		    	return;
		    }
		}
		
		try
		{
			saveWriter.close();
		}
		catch (Exception e)
		{
			System.out.println("Failed to close save file.");
			return;
		}
	}
	
	
}
