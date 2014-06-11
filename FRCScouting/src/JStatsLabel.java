/*
 * Author: Ryan Milem
 * 
 * Date: 6/11/14
 * 
 * Purpose: Used to display a team's win/loss ratio.
 */

import javax.swing.JLabel;


public class JStatsLabel extends JLabel
{
	public JStatsLabel(String startingLabel)
	{
		super(startingLabel);
	}
	
	public void update(int wins, int losses)
	{
		super.setText("Team Stats: " + wins + "/" + losses);
	}
}
