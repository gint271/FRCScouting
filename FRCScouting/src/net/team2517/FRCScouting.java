package net.team2517;
/*
 * Author: Ryan Milem
 * 
 * Date: 6/11/14
 * 
 * Purpose: The overall goal of the program is to make scouting easier for teams in FRC.
 * 	The program will store tags associated with a team number, as well as the teams win loss ratio.
 * 	It will also store all saved data in a fail, transfer the file to another computer over ethernet, 
 * 	and combine save files form multiple computers.  
 */

public class FRCScouting {

	public static void main(String[] args) 
	{
		try 
		{
			ScoutFrame frame = new ScoutFrame();
			frame.setVisible(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
