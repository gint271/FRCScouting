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
		//TODO: Use JFileChooser for saving, rather than user input.
		File saveFile = new File("F:\\teamNodeSave.txt");
		BufferedWriter saveWriter;
		try
		{
			saveWriter = new BufferedWriter(new FileWriter(saveFile));
		}
		catch (Exception e)
		{
			System.out.println("Failed to make save file.");
			return;
		}
		
		for(java.util.Map.Entry<Integer, TeamNode> entry : this.entrySet()){
		    System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
		    
		    
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
