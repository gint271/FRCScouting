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
