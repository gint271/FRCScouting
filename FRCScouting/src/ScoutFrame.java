import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;


public class ScoutFrame extends JFrame {

	private JPanel contentPane;
	private JTextField teamNumberField;
	private HashMap <Integer, TeamNode> teamHash;
	private JLabel lblTeam;
	private JTextField addTagField;
	private JLabel lblAddTag;
	private int teamNumber;
	private TeamNode selectedTeam;
	private JTagsField tagsListField;
	
	public ScoutFrame() 
	{
		teamHash = new HashMap <Integer, TeamNode>();
		
		this.setTitle("Scouting Sheet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTeam = new JLabel("Editing Team: XXXX");
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setBounds(141, 42, 157, 33);
		contentPane.add(lblTeam);
		
		teamNumberField = new JTextField();
		teamNumberField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent arg0) 
			{
				if(arg0.getKeyChar() == '\n')
				{
					
					
					teamNumber = Integer.parseInt(teamNumberField.getText());
					teamNumberField.setText("");
					
					System.out.println("Team Number Entered. " + teamNumber);
					
					if(!teamHash.containsKey(teamNumber))
					{
						teamHash.put(teamNumber, new TeamNode());
						System.out.println("Made new node");
					}
					
					lblTeam.setText("Editing Team: " + teamNumber);
					addTagField.setEditable(true);
					tagsListField.setEditable(true);
					
					selectedTeam = teamHash.get(teamNumber);
					tagsListField.writeTags(selectedTeam);
				}
			}
		});
		teamNumberField.setBounds(75, 11, 86, 20);
		contentPane.add(teamNumberField);
		teamNumberField.setColumns(10);
		
		JLabel lblTeam = new JLabel("Team #");
		lblTeam.setBounds(19, 14, 46, 14);
		contentPane.add(lblTeam);
		
		addTagField = new JTextField();
		addTagField.setEditable(false);
		addTagField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent arg0) 
			{	
				if(arg0.getKeyChar() == '\n')
				{
					selectedTeam.addTag(addTagField.getText());
					
					addTagField.setText("");
					
					tagsListField.writeTags(selectedTeam);
				}
			}
		});
		addTagField.setBounds(75, 75, 86, 20);
		contentPane.add(addTagField);
		addTagField.setColumns(10);
		
		lblAddTag = new JLabel("Add Tag");
		lblAddTag.setBounds(19, 78, 46, 14);
		contentPane.add(lblAddTag);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 73, 210, 178);
		contentPane.add(scrollPane);
		
		tagsListField = new JTagsField();
		tagsListField.setEditable(false);
		scrollPane.setViewportView(tagsListField);
		tagsListField.setColumns(10);
	}
}
