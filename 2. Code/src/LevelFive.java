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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.Timer;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class LevelFive extends JFrame
{
	Stopwatch stopwatch=new Stopwatch();
	
	Connection con;
	PreparedStatement prepareStatement;
	ResultSet rs;
	
	Timer timerforai,timeout;
	Boolean player_turn=true,initialState=true;
	int validCount=0, invalidCount=0;
	int levelFiveScore=0;
	int randomLength=0;
	Font font=new Font("Comic Sans MS",Font.BOLD,22);
	
	SeparateChainingHashTable<String> scHash=new SeparateChainingHashTable<String>(50);
	
	JTextArea jta=new JTextArea();
	JScrollPane scroll=new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JTextField jtfPlayer=new JTextField(8);
	
	Icon iconPlay=new ImageIcon("src/levelFive/play.png");
	Icon iconPause=new ImageIcon("src/levelFive/pause.png");
	Icon iconHappy=new ImageIcon("src/levelFive/happy.png");
	Icon iconSad=new ImageIcon("src/levelFive/cry.png");
	
	JLabel jlbTime=new JLabel("Elapsed Time ");
	JLabel jlbPlayerName;
	JLabel jlbHello=new JLabel(new ImageIcon("src/levelOne/hello.gif"));
	JLabel jlbInvalidCount=new JLabel("Invalid Word Count:"+invalidCount);
	JLabel jlbState=new JLabel("Check Here...",SwingConstants.CENTER);
	JLabel jlbWordLength=new JLabel("Limited Length : 0");
	JLabel jlbFlower=new JLabel(new ImageIcon("src/LevelFive/flower3.gif"));
	JLabel jlbEmotion1=new JLabel(iconHappy);
	JLabel jlbEmotion2=new JLabel(iconHappy);
	
	JButton jbtBack=new JButton(new ImageIcon("src/levelFive/back.png"));
	JButton jbtPlayPause=new JButton(iconPlay);
	
	public LevelFive(String name)
	{
		jlbPlayerName=new JLabel(name,SwingConstants.CENTER);
		
		//Panel for happy, sad & flower (northPanel)
		JPanel northPanel=new JPanel();
		northPanel.add(jlbEmotion1);
		northPanel.add(jlbFlower);
		northPanel.add(jlbEmotion2);
		northPanel.setOpaque(false);		
		
		//Panel for elapsed time (timePanel)
		JPanel timePanel=new JPanel();
		timePanel.add(jlbTime);
		timePanel.add(stopwatch);
		timePanel.setOpaque(false);
		
		//Panel for jlbPlayerName & jtfPlayer (playerPanel)
		JPanel playerPanel=new JPanel(new BorderLayout(0,10));
		playerPanel.add(jlbPlayerName,BorderLayout.CENTER);
		playerPanel.add(jtfPlayer,BorderLayout.SOUTH);
		playerPanel.setOpaque(false);
		playerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));		
		
		//Panel for timePanel, playerPanel & jlbState (eastPanel)
		JPanel eastPanel=new JPanel(new BorderLayout(0,10));
		eastPanel.add(timePanel,BorderLayout.NORTH);
		eastPanel.add(playerPanel,BorderLayout.CENTER);
		eastPanel.add(jlbState,BorderLayout.SOUTH);
		eastPanel.setOpaque(false);
		eastPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		
		//Panel for buttonPanel, jta & eastPanel (centerPanel)
		JPanel centerPanel=new JPanel(new BorderLayout(10,0));
		centerPanel.add(scroll,BorderLayout.CENTER);
		centerPanel.add(eastPanel,BorderLayout.EAST);
		centerPanel.setOpaque(false);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10,50,0,10));
		
		//Panel for jbtPlayPause & jlbLimitedLength (playPauseLengthPanel)
		JPanel playPauseLengthPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		playPauseLengthPanel.add(jbtPlayPause);
		playPauseLengthPanel.add(jlbWordLength);
		playPauseLengthPanel.setOpaque(false);
		
		//Panel for jbtBack & jlbInvalidCount (backCountPanel)
		JPanel backCountPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		backCountPanel.add(jlbInvalidCount);
		backCountPanel.add(jbtBack);
		backCountPanel.setOpaque(false);
		
		//Panel for playPauseLengthPanel & backCountPanel (southPanel)
		JPanel southPanel=new JPanel(new GridLayout(2,1,0,20));
		southPanel.add(playPauseLengthPanel);
		southPanel.add(backCountPanel);
		southPanel.setOpaque(false);
		southPanel.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(centerPanel,BorderLayout.CENTER);
		panel.add(southPanel,BorderLayout.SOUTH);
		//panel.setOpaque(false);
		panel.setBackground(Color.decode("#CCFFFF"));
		panel.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
		add(panel);
		
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
		
		//set border line
		jtfPlayer.setBorder(BorderFactory.createLineBorder(Color.decode("#660099"),3));
		scroll.setBorder(BorderFactory.createLineBorder(Color.decode("#660099"),3));
				
		//set cursor color
		jtfPlayer.setCaretColor(Color.decode("#660099"));
		
		//set font color
		jtfPlayer.setForeground(Color.decode("#660099"));
		jlbPlayerName.setForeground(Color.decode("#660099"));
		jta.setForeground(Color.decode("#660099"));
		jlbTime.setForeground(Color.decode("#660099"));
		stopwatch.setForeground(Color.decode("#660099"));
		jlbState.setForeground(Color.decode("#660099"));
		jlbInvalidCount.setForeground(Color.decode("#660099"));
		jlbWordLength.setForeground(Color.decode("#660099"));
		
		//Set font
		jtfPlayer.setFont(font);
		jta.setFont(font);
		jlbTime.setFont(font);
		jlbState.setFont(font);
		stopwatch.setFont(font);
		jlbPlayerName.setFont(font);
		jlbInvalidCount.setFont(font);
		jlbWordLength.setFont(font);
		
		//Initial state
		startDB();
		getRandomLength();
		jta.setEditable(false);
		jtfPlayer.setEnabled(false);
		
		setTitle("Level Five");
		setVisible(true);
		setSize(580,550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
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
					stopwatch.restartTimer();
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
	
	//Method to generate two random limited length
	public void getRandomLength()
	{
		//get random number between 3 & 14
		randomLength=(int)(3+Math.random()*(14-3+1));
		jlbWordLength.setText("Limited Length : "+randomLength);
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
			String query="Select words from dictionary where Length=? and RandomNo=?";
			try
			{
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, randomLength);
				prepareStatement.setInt(2, random);
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
		Boolean flag=true;
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
			String query="Select words from dictionary where Length=? and RandomNo=?";
			try
			{
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, randomLength);
				prepareStatement.setInt(2, radNo);
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
			int fourScoreFromDB = 0;
			int fiveScoreFromDB=0;
			String level4="";
			String query="select four_score,five_score,level4 from player_table where name=?";
			prepareStatement=con.prepareStatement(query);
			prepareStatement.setString(1, playerName);
			rs=prepareStatement.executeQuery();
			
			if(rs.next())
			{
				fourScoreFromDB=rs.getInt(1);
				fiveScoreFromDB=rs.getInt(2);
				level4=rs.getString(3);
			}
			int totalScore=fourScoreFromDB+levelFiveScore;

			if(fiveScoreFromDB>totalScore)
				totalScore=fiveScoreFromDB;
			if(totalScore>=150 && level4.equals("pass"))
			{
				query="update player_table set five_score=?,level5=? where name=?";
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, totalScore);
				prepareStatement.setString(2, "pass");
				prepareStatement.setString(3, playerName);
				prepareStatement.executeUpdate();
				stopwatch.stopTimer();
				LevelFiveCongratsDialog dialog=new LevelFiveCongratsDialog(playerName,totalScore);
				dialog.setVisible(true);				
			}
			else
			{
				query="update player_table set five_score=? where name=?";	
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, totalScore);
				prepareStatement.setString(2, playerName);
				prepareStatement.executeUpdate();
				stopwatch.stopTimer();
				LevelFiveFailDialog dialog=new LevelFiveFailDialog(playerName,totalScore);			
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
						if(length==randomLength)
						{
							++validCount;
							++levelFiveScore;
							scHash.insert(jtfPlayer.getText());
							jlbState.setForeground(Color.decode("#660099"));
							jlbState.setText("Valid Word !");
							jlbEmotion1.setIcon(iconHappy);
							jlbEmotion2.setIcon(iconHappy);
							jta.append(jtfPlayer.getText().toLowerCase()+"\n");
							jtfPlayer.setText("");	
						}
						//Condition for invalid word length
						else
						{
							if(levelFiveScore>0)
							{
								--levelFiveScore;
							}
							++invalidCount;
							jtfPlayer.setText("");	
							jlbState.setForeground(Color.decode("#CC0000"));
							jlbState.setText("Invalid Length !");
							jlbEmotion1.setIcon(iconSad);
							jlbEmotion2.setIcon(iconSad);
							jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
						}
					}
					//Condition for mismatch of last char and first char
					else
					{
						if(levelFiveScore>0)
						{
							--levelFiveScore;
						}
						++invalidCount;
						jtfPlayer.setText("");	
						jlbState.setForeground(Color.decode("#CC0000"));
						jlbState.setText("Mismatch !");
						jlbEmotion1.setIcon(iconSad);
						jlbEmotion2.setIcon(iconSad);
						jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
					}
					
				}
				//Condition for duplicate nouns
				else
				{
					if(levelFiveScore>0)
					{
						levelFiveScore-=2;
					}
					++invalidCount;
					jtfPlayer.setText("");	
					jlbState.setForeground(Color.decode("#CC0000"));
					jlbState.setText("Duplication !");
					jlbEmotion1.setIcon(iconSad);
					jlbEmotion2.setIcon(iconSad);
					jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
				}
			}
			//Condition for not existing in DB
			else
			{
				if(levelFiveScore>0)
				{
					--levelFiveScore;
				}
				++invalidCount;
				jtfPlayer.setText("");	
				jlbState.setForeground(Color.decode("#CC0000"));
				jlbState.setText("Not Existed !");
				jlbEmotion1.setIcon(iconSad);
				jlbEmotion2.setIcon(iconSad);
				jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
			}
			
			jtfPlayer.setEnabled(false);
			timerforai.start();
			jlbPlayerName.setForeground(Color.decode("#660099"));
			player_turn=false;
			getRandomLength();
			jlbWordLength.setText("Limited Length : "+randomLength);
		}
	}
	
	/*public static void main(String[] args) 
	{
		LevelFive frame=new LevelFive("Htoo Yanant");
	}*/
}
