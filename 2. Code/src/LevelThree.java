import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class LevelThree extends JFrame
{
	Stopwatch stopwatch=new Stopwatch();
	
	Connection con;
	PreparedStatement prepareStatement;
	ResultSet rs;
	
	Timer timerforai,timeout;
	Boolean player_turn=true,initialState=true;
	int validCount=0, invalidCount=0;
	int levelThreeScore=0;//int playerScoreFromDb=0;
	Font font=new Font("Comic Sans MS",Font.BOLD,22);
	
	SeparateChainingHashTable<String> scHash=new SeparateChainingHashTable<String>(50);
	
	JTextArea jta=new JTextArea();
	JScrollPane scroll=new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JTextField jtfPlayer=new JTextField(8);
	
	Icon iconPlay=new ImageIcon("src/levelThree/play.png");
	Icon iconPause=new ImageIcon("src/levelThree/pause.png");
	
	JLabel jlbTime=new JLabel("Elapsed Time ");
	JLabel jlbPlayerName;
	JLabel jlbHello=new JLabel(new ImageIcon("src/levelOne/hello.gif"));
	JLabel jlbInvalidCount=new JLabel("Invalid Word Count:"+invalidCount);
	JLabel jlbState=new JLabel("Check Here...",SwingConstants.CENTER);
	JLabel jlbWordLength=new JLabel("Limited Length:4,5,6");
	
	JButton jbtBack=new JButton(new ImageIcon("src/levelThree/back.png"));
	JButton jbtPlayPause=new JButton(iconPlay);
	
	public LevelThree(String name)
	{
		jlbPlayerName=new JLabel(name,SwingConstants.CENTER);
		
		//Panel for elapsed time (timePanel)
		JPanel timePanel=new JPanel();
		timePanel.add(jlbTime);
		timePanel.add(stopwatch);
		timePanel.setOpaque(false);
		
		//Panel for jbtPlayPause (playPausePanel)
		JPanel playPausePanel=new JPanel();
		playPausePanel.add(jbtPlayPause);
		playPausePanel.setOpaque(false);
		
		//Panel for jbtBack (backPanel)
		JPanel backPanel=new JPanel();
		backPanel.add(jbtBack);
		backPanel.setOpaque(false);

		//Panel for playPause & back button (buttonPanel)
		JPanel buttonPanel=new JPanel(new GridLayout(2,1,0,30));
		buttonPanel.add(playPausePanel);
		buttonPanel.add(backPanel);
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,0));
		
		//Panel for jlbPlayerName & jtfPlayer (playerPanel)
		JPanel playerPanel=new JPanel(new BorderLayout(0,10));
		playerPanel.add(jlbPlayerName,BorderLayout.CENTER);
		playerPanel.add(jtfPlayer,BorderLayout.SOUTH);
		playerPanel.setOpaque(false);
		playerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));		
		
		//Panel for timePanel, playerPanel & jlbState (eastPanel)
		JPanel eastPanel=new JPanel(new BorderLayout(0,10));
		eastPanel.add(timePanel,BorderLayout.NORTH);
		eastPanel.add(playerPanel,BorderLayout.CENTER);
		eastPanel.add(jlbState,BorderLayout.SOUTH);
		eastPanel.setOpaque(false);
		eastPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));
		
		//Panel for buttonPanel, jta & eastPanel (centerPanel)
		JPanel centerPanel=new JPanel(new BorderLayout(20,0));
		centerPanel.add(buttonPanel,BorderLayout.WEST);
		centerPanel.add(scroll,BorderLayout.CENTER);
		centerPanel.add(eastPanel,BorderLayout.EAST);
		centerPanel.setOpaque(false);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		
		//Panel for two label (labelPanel)
		JPanel labelPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
		labelPanel.add(jlbWordLength);
		labelPanel.add(jlbInvalidCount);
		labelPanel.setOpaque(false);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(0,10,30,10));
		
		JLabel background=new JLabel(new ImageIcon("src/levelThree/snow.jpg"));
		background.setLayout(new BorderLayout());
		background.add(centerPanel,BorderLayout.CENTER);
		background.add(labelPanel,BorderLayout.SOUTH);
		add(background);
		
		//Set button size
		jlbState.setPreferredSize(new Dimension(180,40));
		jtfPlayer.setPreferredSize(new Dimension(10,40));
		jbtPlayPause.setPreferredSize(new Dimension(102,44));
		jbtBack.setPreferredSize(new Dimension(102,44));
		
		//discard textarea white background
		jtfPlayer.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		jta.setOpaque(false);
		
		//set cursor color
		jtfPlayer.setCaretColor(Color.decode("#330099"));
		
		//set border line
		jtfPlayer.setBorder(BorderFactory.createLineBorder(Color.decode("#330099"),3));
		scroll.setBorder(BorderFactory.createLineBorder(Color.decode("#330099"),3));
		
		//set font color
		jtfPlayer.setForeground(Color.decode("#330099"));
		jlbPlayerName.setForeground(Color.decode("#330099"));
		jta.setForeground(Color.decode("#330099"));
		jlbTime.setForeground(Color.decode("#330099"));
		stopwatch.setForeground(Color.decode("#330099"));
		jlbState.setForeground(Color.decode("#330099"));
		jlbInvalidCount.setForeground(Color.decode("#330099"));
		jlbWordLength.setForeground(Color.decode("#330099"));
		
		//Set font
		jtfPlayer.setFont(font);
		jta.setFont(font);
		jlbTime.setFont(font);
		jlbState.setFont(font);
		stopwatch.setFont(font);
		jlbPlayerName.setFont(font);
		jlbInvalidCount.setFont(font);
		jlbWordLength.setFont(font);
		
		setTitle("Level Three");
		setVisible(true);
		setSize(615,370);
		setLocationRelativeTo(null);
		
		//Initial state
		jta.setEditable(false);
		jtfPlayer.setEnabled(false);
		startDB();
		//stopwatch.startTimer();
		
		jtfPlayer.addActionListener(new PlayerListener());
		
		//Timer for AI to generate word
		timerforai=new Timer(2000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				doAI();
			}
		});
		
		//Timer to check time out
		timeout=new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(stopwatch.minute==2)
				{
					updatePlayerScores(jlbPlayerName.getText());
					jtfPlayer.setEnabled(false);
					jtfPlayer.setText("");
					timerforai.stop();
					timeout.stop();
				}
			}
		});
		
		//Event for play & pause button 
		jbtPlayPause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(initialState)
				{
					jta.append(getRandomWord()+"\n");
					jtfPlayer.setEnabled(true);
					jlbPlayerName.setForeground(Color.decode("#006600"));
					timeout.start();
					initialState=false;
				}
				if(jbtPlayPause.getIcon()==iconPlay)
				{
					jbtPlayPause.setIcon(iconPause);
					stopwatch.startTimer();
					if(player_turn)
					{
						jtfPlayer.setEnabled(true);
					}
					else
					{
						timerforai.start();
					}
				}
				else
				{
					jbtPlayPause.setIcon(iconPlay);
					jtfPlayer.setEnabled(false);
					stopwatch.stopTimer();
					timerforai.stop();
				}
			}
		});
		
		jbtBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				scHash.makeEmpty(); 
				stopwatch.stopTimer();
				updatePlayerScores(jlbPlayerName.getText());
			}
		});
		
	}
	
	//Start database
	public void startDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//Method for initially generate a random word from DB
	public String getRandomWord()
	{
		String randomWord="";
		int random=0;
		boolean flag=true;
		while(flag)
		{
			random=(int)(1+Math.random()*(111264-1+1));
			String query="Select words from dictionary where (Length=4 or Length=5 or Length=6) and RandomNo="+random;
			try
			{
				prepareStatement=con.prepareStatement(query);
				rs=prepareStatement.executeQuery();
				if(rs.next())
				{
					randomWord=rs.getString(1).toLowerCase();
					char last=randomWord.charAt(randomWord.length()-1);
					if(last>='a' && last<='z')
					{
						scHash.insert(randomWord);
						flag=false;
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		scHash.insert(randomWord);
		//System.out.println("Initial Random No "+random);
		//System.out.println("Initial Random word "+randomWord);
		//System.out.println("Successfully generate random word !");
		return randomWord;
	}

	//Method for AI to generate word
	public String generateWord(char c)
	{
		int radNo=0;
		String string="";
		boolean flag=true;
		while(flag)
		{
			switch(c)
			{
				case 'a' : radNo=(int)(1+Math.random()*(7845-1+1));break;
				case 'b' : radNo=(int)(7846+Math.random()*(13503-7846+1));break;
				case 'c' : radNo=(int)(13504+Math.random()*(23849-13504+1));break;
				case 'd' : radNo=(int)(23850+Math.random()*(30795-23850+1));break;
				case 'e' : radNo=(int)(30796+Math.random()*(36141-30796+1));break;
				case 'f' : radNo=(int)(36142+Math.random()*(40355-36142+1));break;
				case 'g' : radNo=(int)(40356+Math.random()*(43778-40356+1));break;
				case 'h' : radNo=(int)(43779+Math.random()*(47878-43779+1));break;
				case 'i' : radNo=(int)(47879+Math.random()*(53404-47879+1));break;
				case 'j' : radNo=(int)(53405+Math.random()*(54225-53405+1));break;
				case 'k' : radNo=(int)(54226+Math.random()*(55097-54226+1));break;
				case 'l' : radNo=(int)(55098+Math.random()*(58566-55098+1));break;
				case 'm' : radNo=(int)(58567+Math.random()*(64575-58567+1));break;
				case 'n' : radNo=(int)(64576+Math.random()*(66620-64576+1));break;
				case 'o' : radNo=(int)(66621+Math.random()*(69800-66621+1));break;
				case 'p' : radNo=(int)(69801+Math.random()*(79884-69801+1));break;
				case 'q' : radNo=(int)(79885+Math.random()*(80529-79885+1));break;
				case 'r' : radNo=(int)(80530+Math.random()*(85498-80530+1));break;
				case 's' : radNo=(int)(85499+Math.random()*(98032-85499+1));break;
				case 't' : radNo=(int)(98033+Math.random()*(103706-98033+1));break;
				case 'u' : radNo=(int)(103707+Math.random()*(106185-103707+1));break;
				case 'v' : radNo=(int)(106186+Math.random()*(107991-106186+1));break;
				case 'w' : radNo=(int)(107992+Math.random()*(110457-107992+1));break;
				case 'x' : radNo=(int)(110458+Math.random()*(110581-110458+1));break;
				case 'y' : radNo=(int)(110582+Math.random()*(110910-110582+1));break;
				case 'z' : radNo=(int)(110911+Math.random()*(111264-110911+1));break;
			}
			String query="select words from dictionary where (Length=4 or Length=5 or Length=6) and RandomNo="+radNo;
			try
			{
				prepareStatement=con.prepareStatement(query);
				rs=prepareStatement.executeQuery();
				if(rs.next())
				{
					string=rs.getString(1);
					flag=false;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		//System.out.println("Random No is "+radNo);
		//System.out.println("Generated word is "+string);
		return string.toLowerCase();
	}
	
	//Method for AI to run after two second later player turn 
	public void doAI()
	{
		Boolean flag=true;
		char lastchar=jta.getText().charAt(jta.getText().length()-2);
		String word=generateWord(lastchar);
		char last=word.charAt(word.length()-1);
		while(scHash.isContained(word) && last>='a' && last<='z')
		{
			word=generateWord(lastchar);
			last=word.charAt(word.length()-1);
		}
			
		scHash.insert(word);
		jta.append(word+"\n");
		jtfPlayer.setEnabled(true);
		jlbPlayerName.setForeground(Color.decode("#006600"));
		timerforai.stop();
		player_turn=true;
	}
	
	//Checking word the player entered exits in DB
	public boolean existInDB(String word)
	{
		boolean isExisted=false;
		try
		{
			String query1="Select  words from dictionary where words=?";
			prepareStatement=con.prepareStatement(query1);
			prepareStatement.setString(1, word);
			ResultSet result=prepareStatement.executeQuery();
			if(result.next())
				isExisted=true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return isExisted;
	}
	
	//Method for inserting score into DB
	public void updatePlayerScores(String playerName)
	{
		try
		{
			int twoScoreFromDB = 0;
			int threeScoreFromDB=0;
			String level2="";
			String query="select two_score,three_score,level2 from player_table where name=?";
			prepareStatement=con.prepareStatement(query);
			prepareStatement.setString(1, playerName);
			rs=prepareStatement.executeQuery();
			
			if(rs.next())
			{
				twoScoreFromDB=rs.getInt(1);
				threeScoreFromDB=rs.getInt(2);
				level2=rs.getString(3);
			}
			int totalScore=twoScoreFromDB+levelThreeScore;

			if(threeScoreFromDB>totalScore)
				totalScore=threeScoreFromDB;
			if(totalScore>=90 && level2.equals("pass"))
			{
				query="update player_table set three_score=?,level3=? where name=?";
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, totalScore);
				prepareStatement.setString(2, "pass");
				prepareStatement.setString(3, playerName);
				prepareStatement.executeUpdate();
				stopwatch.stopTimer();
				LevelThreeCongratsDialog dialog=new LevelThreeCongratsDialog(playerName,totalScore);
				dialog.setVisible(true);
			}
			else
			{
				query="update player_table set three_score=? where name=?";	
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, totalScore);
				prepareStatement.setString(2, playerName);
				prepareStatement.executeUpdate();
				stopwatch.stopTimer();
				LevelThreeFailDialog dialog=new LevelThreeFailDialog(playerName,totalScore);			
				dialog.setVisible(true);
			}
			con.close();
			setVisible(false);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	
	//Inner class : listener for jtfPlayer
	class PlayerListener implements ActionListener
	{
		char lastchar,firstchar;
		public void actionPerformed(ActionEvent e) 
		{
			if(existInDB(jtfPlayer.getText()))
			{
				//Condition for different nouns
				if(!scHash.isContained(jtfPlayer.getText()))
				{
					lastchar=jta.getText().charAt(jta.getText().length()-2);
					firstchar=jtfPlayer.getText().toLowerCase().charAt(0);
					//Condition for matching of last char and first char
					if(lastchar==firstchar)
					{
						int length=jtfPlayer.getText().length();
						//Condition for checking word length (4,5,6)
						if(length>=4 && length <=6)
						{
							++validCount;
							++levelThreeScore;
							scHash.insert(jtfPlayer.getText());
							jlbState.setForeground(Color.decode("#330099"));
							jlbState.setText("Valid Word !");
							jta.append(jtfPlayer.getText().toLowerCase()+"\n");
						}
						//Condition for invalid word length
						else
						{
							if(levelThreeScore>0)
							{
								--levelThreeScore;
							}
							++invalidCount;
							jlbState.setForeground(Color.decode("#CC0000"));
							jlbState.setText("Invalid Length !");
							jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
						}
					}
					//Condition for mismatch of last char and first char
					else
					{
						if(levelThreeScore>0)
						{
							--levelThreeScore;
						}
						++invalidCount;
						jlbState.setForeground(Color.decode("#CC0000"));
						jlbState.setText("Mismatch !");
						jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
					}
					
				}
				//Condition for duplicate nouns
				else
				{
					if(levelThreeScore>0)
					{
						levelThreeScore-=2;
					}
					++invalidCount;
					jlbState.setForeground(Color.decode("#CC0000"));
					jlbState.setText("Duplication !");
					jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
				}
			}
			//Condition for not existing in DB
			else
			{
				if(levelThreeScore>0)
				{
					--levelThreeScore;
				}
				++invalidCount;
				jlbState.setForeground(Color.decode("#CC0000"));
				jlbState.setText("Not Existed !");
				jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
			}
			
			jtfPlayer.setEnabled(false);
			jtfPlayer.setText("");	
			timerforai.start();
			jlbPlayerName.setForeground(Color.decode("#330099"));
			player_turn=false;
		}
	}
	
	/*public static void main(String[] args) 
	{
		LevelThree frame=new LevelThree("Htoo Yanant");
	}*/
}
