import javax.swing.JTextArea;


public class JTagsField extends JTextArea
{
	
	
	void writeTags(TeamNode selectedTeam)
	{
		selectedTeam.getReset();
		super.setText("");
		
		while(selectedTeam.hasNext())
		{
			super.setText(super.getText() + selectedTeam.getNext() + "\n");
		}
	}
}
