/*
 * Author: Ryan Milem
 * 
 * Date: 6/11/14
 * 
 * Purpose: This is the main window for the scouter.  It takes in and displays almost all data.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;


public class ScoutFrame extends JFrame {

	private JPanel contentPane;
	private JTextField teamNumberField;
	private TeamHashMap teamHash;
	private JLabel lblTeam;
	private JTextField addTagField;
	private JLabel lblAddTag;
	private int teamNumber;
	private TeamNode selectedTeam;
	private JTagsField tagsListField;
	private JStatsLabel teamStatsLabel;
	private JButton btnAddWin;
	private JButton btnAddLoss;
	private JLabel lblNewLabel;
	private JTextField searchField;
	private JLabel lblTeamNum;
	
	public ScoutFrame() 
	{
		teamHash = new TeamHashMap();
		
		this.setTitle("Scouting Sheet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTeam = new JLabel("Editing Team: XXXX");
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setBounds(141, 25, 157, 33);
		contentPane.add(lblTeam);
		
		teamNumberField = new JTextField();
		teamNumberField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent arg0) 
			{
				if(arg0.getKeyChar() == '\n')
				{
					//TODO: Check to see if input is indeed a number.
					teamNumber = Integer.parseInt(teamNumberField.getText());
					teamNumberField.setText("");
					
					System.out.println("Team Number Entered. " + teamNumber);
					
					//If the entered number isn't stored, creates a new entry in hashmap.
					if(!teamHash.containsKey(teamNumber))
					{
						teamHash.put(teamNumber, new TeamNode());
						System.out.println("Made new node");
					}
					
					//Updates lblTeam to display the selected number and makes all the fields for data entry editable.
					lblTeam.setText("Editing Team: " + teamNumber);
					addTagField.setEditable(true);
					tagsListField.setEditable(true);
					btnAddLoss.setEnabled(true);
					btnAddWin.setEnabled(true);
					
					//Stores the selected TeamNode for future use.
					selectedTeam = teamHash.get(teamNumber);
					//Prints all tags associated with that team.
					tagsListField.writeTags(selectedTeam);
					//Reprints with selected teams win/loss ratio.
					teamStatsLabel.update(selectedTeam.getWins(), selectedTeam.getLosses());
				}
			}
		});
		teamNumberField.setBounds(75, 11, 86, 20);
		contentPane.add(teamNumberField);
		teamNumberField.setColumns(10);
		
		addTagField = new JTextField();
		addTagField.setEditable(false);
		addTagField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent arg0) 
			{	
				//Takes entered tag, stores it in linked list, and updates the display.
				if(arg0.getKeyChar() == '\n')
				{
					selectedTeam.addTag(addTagField.getText());
					
					addTagField.setText("");
					
					tagsListField.writeTags(selectedTeam);
				}
			}
		});
		addTagField.setBounds(212, 69, 86, 20);
		contentPane.add(addTagField);
		addTagField.setColumns(10);
		
		lblAddTag = new JLabel("Add Tag");
		lblAddTag.setBounds(231, 52, 46, 14);
		contentPane.add(lblAddTag);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 100, 210, 151);
		contentPane.add(scrollPane);
		
		tagsListField = new JTagsField();
		tagsListField.setEditable(false);
		scrollPane.setViewportView(tagsListField);
		tagsListField.setColumns(10);
		
		teamStatsLabel = new JStatsLabel("Team Stats: X/X");
		teamStatsLabel.setBounds(44, 100, 96, 14);
		contentPane.add(teamStatsLabel);
		
		btnAddWin = new JButton("Add Win");
		btnAddWin.setEnabled(false);
		btnAddWin.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedTeam.addWin();
				teamStatsLabel.update(selectedTeam.getWins(), selectedTeam.getLosses());
			}
		});
		btnAddWin.setBounds(20, 117, 120, 20);
		contentPane.add(btnAddWin);
		
		btnAddLoss = new JButton("Add Loss");
		btnAddLoss.setEnabled(false);
		btnAddLoss.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedTeam.addLoss();
				teamStatsLabel.update(selectedTeam.getWins(), selectedTeam.getLosses());
			}
		});
		btnAddLoss.setBounds(20, 144, 120, 20);
		contentPane.add(btnAddLoss);
		
		lblNewLabel = new JLabel("Search Tag");
		lblNewLabel.setBounds(321, 52, 73, 14);
		contentPane.add(lblNewLabel);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent arg0) 
			{
				String desiredTag;
				
				if(arg0.getKeyChar() == '\n')
				{
					desiredTag = searchField.getText();
					
					tagsListField.setText("Teams with " + desiredTag + "\n");
					
					//Iterates through hashmap and searches each linked list for tag.  If found, prints team's number.
					for(Entry<Integer, TeamNode> entry : teamHash.entrySet()){
					    System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
					    
					    if(entry.getValue().contains(desiredTag))
					    {
					    	tagsListField.setText(tagsListField.getText() + entry.getKey() + "\n");
					    }
					}
					
					searchField.setText("");
					
					//No team is selected anymore, so sets all data entry to uneditable.
					addTagField.setEditable(false);
					tagsListField.setEditable(false);
					btnAddLoss.setEnabled(false);
					btnAddWin.setEnabled(false);
					
					teamStatsLabel.setText("Team Stats: X/X");
					lblTeam.setText("Editing Team: XXXX");
				}
			}
		});
		searchField.setBounds(308, 69, 86, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		lblTeamNum = new JLabel("Team #");
		lblTeamNum.setBounds(19, 14, 46, 14);
		contentPane.add(lblTeamNum);
	}
}
