package net.team2517;
/*
 * Author: Ryan Milem
 * 
 * Date: 6/11/14
 * 
 * Purpose: Stores all the TeamNodes, associated with their numbers.  Also handles saving and combining saves.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javax.swing.JLabel;

public class TeamHashMap extends HashMap <Integer, TeamNode>
{
	public void load(String path)
	{
		File saveFile = new File(path);
		BufferedReader saveReader;
		String newLine;
		String lineElements[] = null;
		int i;
		
		try
		{
			//TODO: Handle closing saveWriter when quitting unexpectedly.
			saveReader = new BufferedReader(new FileReader(saveFile));
		}
		catch (Exception e)
		{
			System.out.println("Failed to make save file reader.");
			return;
		}
		
		try
		{
			while(null != (newLine = saveReader.readLine()))
			{
				lineElements = newLine.split("\t");
				
				//TODO: Add checking for if team is already loaded.  Will be helpful for combining files.
				//Makes new entry in hash for team number.
				if(!this.containsKey(Integer.parseInt(lineElements[0])))
				{
					//Only makes new teamnode if team not already in memory.
					this.put(Integer.parseInt(lineElements[0]), new TeamNode());
				}
				
				//Stores wins and losses in entry.
				this.get(Integer.parseInt(lineElements[0])).setWins(Integer.parseInt(lineElements[1]));
				this.get(Integer.parseInt(lineElements[0])).setLosses(Integer.parseInt(lineElements[2]));
				
				for(i = 3; i < lineElements.length; i++)
				{
					//Checks if tag is already in memory.  Loads only if not.
					if(!this.get(Integer.parseInt(lineElements[0])).contains(lineElements[i]))
					{
						this.get(Integer.parseInt(lineElements[0])).addTag(lineElements[i]);
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Failed to read line from file: " + e);
			return;
		}
		
		try
		{
			saveReader.close();
		}
		catch (Exception e)
		{
			System.out.println("Failed to close save file reader.");
			return;
		}
	}
	
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
		    try
		    {
		    	saveWriter.write(entry.getKey() + "\t" + entry.getValue().getWins() + "\t" + entry.getValue().getLosses());
		    	
		    	entry.getValue().getReset();
		    	
		    	while(entry.getValue().hasNext())
		    	{
		    		saveWriter.write("\t" + entry.getValue().getNext());
		    	}
		    	
		    	saveWriter.newLine();
		    	
		    	saveWriter.flush();
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
