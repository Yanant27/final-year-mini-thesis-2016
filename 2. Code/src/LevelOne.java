import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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


public class LevelOne extends JFrame
{
	Stopwatch stopwatch=new Stopwatch();
	
	Connection con;
	PreparedStatement prepareStatement;
	ResultSet rs;
	
	Timer timerforai,timeout;
	Boolean player_turn=true,initialState=true;
	int validCount=0, invalidCount=0;
	int levelOneScore=0;//int playerScoreFromDb=0;
	Font font=new Font("Comic Sans MS",Font.BOLD,25);
	
	SeparateChainingHashTable<String> scHash=new SeparateChainingHashTable<String>(50);
	
	JTextArea jta=new JTextArea();
	JScrollPane scroll=new JScrollPane(jta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	JTextField jtfPlayer=new JTextField(6);
	
	Icon iconPlay=new ImageIcon("src/levelOne/play.png");
	Icon iconPause=new ImageIcon("src/levelOne/pause.png");
	Icon iconHappy=new ImageIcon("src/levelOne/happy.gif");
	Icon iconSad=new ImageIcon("src/levelOne/sad.png");
	
	JLabel jlbTime=new JLabel("Elapsed Time");
	JLabel jlbPlayerName;
	JLabel jlbEmotion=new JLabel(iconHappy);
	JLabel jlbHello=new JLabel(new ImageIcon("src/levelOne/hello.gif"));
	JLabel jlbInvalidCount=new JLabel("Invalid Word Count:"+invalidCount);
	JLabel jlbState=new JLabel("Check Here.....",SwingConstants.CENTER);
	
	JButton jbtBack=new JButton(new ImageIcon("src/levelOne/back.png"));
	JButton jbtPlayPause=new JButton(iconPlay);
	
	public LevelOne(String name)
	{
		jlbPlayerName=new JLabel(name,SwingConstants.CENTER);
		
		//Panel for elapsed time (timePanel)
		JPanel timePanel=new JPanel();
		timePanel.add(jlbTime);
		timePanel.add(stopwatch);
		timePanel.setOpaque(false);
		
		//Panel for time & textarea (timeAreaPanel)
		JPanel timeAreaPanel=new JPanel(new BorderLayout(0,30));
		timeAreaPanel.add(timePanel,BorderLayout.NORTH);
		timeAreaPanel.add(scroll,BorderLayout.CENTER);
		timeAreaPanel.setOpaque(false);
		timeAreaPanel.setBorder(BorderFactory.createEmptyBorder(30,50,0,0));
		
		//Panel for player name & textfield (playerPanel)
		JPanel playerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		playerPanel.add(jlbPlayerName);
		playerPanel.add(jtfPlayer);
		playerPanel.setOpaque(false);
		playerPanel.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
		
		JPanel playPausePanel=new JPanel();
		playPausePanel.add(jbtPlayPause);
		playPausePanel.setOpaque(false);
		
		JPanel backPanel=new JPanel();
		backPanel.add(jbtBack);
		backPanel.setOpaque(false);
			
		//Panel for playPause & Sound button (buttonPanel)
		JPanel buttonPanel=new JPanel(new BorderLayout(0,20));
		buttonPanel.add(playPausePanel,BorderLayout.CENTER);
		buttonPanel.add(backPanel,BorderLayout.SOUTH);
		buttonPanel.setOpaque(false);
		
		//Panel for emotion icon& buttonPanel (emoButtonPanel)
		JPanel emoButtonPanel=new JPanel(new BorderLayout(30,0));
		emoButtonPanel.add(buttonPanel,BorderLayout.WEST);
		emoButtonPanel.add(jlbEmotion,BorderLayout.CENTER);
		emoButtonPanel.setOpaque(false);
		emoButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,10));
		
		//Panel for hello, invalid count & emobuttonPanel (topPanel)
		JPanel topPanel=new JPanel(new BorderLayout(0,10));
		topPanel.add(jlbHello,BorderLayout.NORTH);
		topPanel.add(jlbInvalidCount,BorderLayout.CENTER);
		topPanel.add(emoButtonPanel,BorderLayout.SOUTH);
		topPanel.setOpaque(false);
		topPanel.setBorder(BorderFactory.createEmptyBorder(30,40,0,40));
		
		
		//Panel for playerPanel & jbtBack (bottomPanel)
		JPanel bottomPanel=new JPanel();
		bottomPanel.add(playerPanel);
		bottomPanel.add(jlbState);
		bottomPanel.setOpaque(false);
		
		//Combining all panels (totalPanel)
		JPanel totalPanel=new JPanel(new BorderLayout());
		totalPanel.add(bottomPanel,BorderLayout.SOUTH);
		totalPanel.add(topPanel,BorderLayout.EAST);
		totalPanel.add(timeAreaPanel,BorderLayout.CENTER);
		totalPanel.setBackground(Color.decode("#CCFF99"));
		add(totalPanel);
		
		//discard textarea white background
		jtfPlayer.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		jta.setOpaque(false);
		
		//set cursor color
		jtfPlayer.setCaretColor(Color.decode("#006600"));
		
		//set border line
		jtfPlayer.setBorder(BorderFactory.createLineBorder(Color.decode("#006600"),3));
		scroll.setBorder(BorderFactory.createLineBorder(Color.decode("#006600"),3));
		
		//Set button size
		jlbState.setPreferredSize(new Dimension(190,40));
		jtfPlayer.setPreferredSize(new Dimension(10,40));
		jbtPlayPause.setPreferredSize(new Dimension(102,44));
		jbtBack.setPreferredSize(new Dimension(102,44));
		
		
		
		//Set font
		jtfPlayer.setFont(font);
		jta.setFont(font);
		jlbTime.setFont(font);
		jlbState.setFont(font);
		stopwatch.setFont(font);
		jlbPlayerName.setFont(font);
		jlbInvalidCount.setFont(font);
		
		jtfPlayer.setForeground(Color.decode("#006600"));
		stopwatch.setForeground(Color.decode("#006600"));
		jlbTime.setForeground(Color.decode("#006600"));
		jlbPlayerName.setForeground(Color.decode("#006600"));
		jlbState.setForeground(Color.decode("#006600"));
		jlbInvalidCount.setForeground(Color.decode("#006600"));
		jta.setForeground(Color.decode("#006600"));
		
		setTitle("Level One");
		setVisible(true);
		setSize(670,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//Initial state
		jta.setEditable(false);
		jtfPlayer.setEnabled(false);
		startDB();
		
		jtfPlayer.addActionListener(new PlayerListener());
		
		//Timer for AI to generate word
		timerforai=new Timer(1000,new ActionListener()
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
					jlbPlayerName.setForeground(Color.decode("#006600"));
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
					jta.append(getRandomWord().toLowerCase()+"\n");
					jtfPlayer.setEnabled(true);
					jlbPlayerName.setForeground(Color.decode("#000099"));
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
		boolean flag=true;
		String randomWord=null;
		while(flag)
		{
			int random=(int)(1+Math.random()*(111264-1+1));
			String query2="Select words from dictionary where RandomNo="+random;
			try
			{
				prepareStatement=con.prepareStatement(query2);
				rs=prepareStatement.executeQuery();
				if(rs.next())
					randomWord=rs.getString(1).toLowerCase();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			char last=randomWord.charAt(randomWord.length()-1);
			if(last>='a' && last<='z')
			{
				scHash.insert(randomWord);
				flag=false;
			}
		}
		return randomWord;
	}

	//Method for AI to generate word
	public String generateWord(char c)
	{
		int radNo=0;
		String string="";
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
		String query="select words from dictionary where randomno="+radNo;
		try
		{
			prepareStatement=con.prepareStatement(query);
			rs=prepareStatement.executeQuery();
			if(rs.next())
				string=rs.getString(1);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
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
		jlbPlayerName.setForeground(Color.decode("#000099"));
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
		int oneScoreFromDB = 0;	
		try
		{
			String query="select one_score from player_table where name=?";
			prepareStatement=con.prepareStatement(query);
			prepareStatement.setString(1, playerName);
			rs=prepareStatement.executeQuery();
			if(rs.next())
				oneScoreFromDB=rs.getInt(1);
			
			if(oneScoreFromDB>levelOneScore)
				levelOneScore=oneScoreFromDB;
				
			if(levelOneScore>=25)
			{
				query="update player_table set one_score=?,level1=? where name=?";
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, levelOneScore);
				prepareStatement.setString(2, "pass");
				prepareStatement.setString(3, playerName);
				prepareStatement.executeUpdate();
				stopwatch.stopTimer();
				LevelOneCongratsDialog dialog=new LevelOneCongratsDialog(playerName,levelOneScore);
				dialog.setVisible(true);
			}
			else
			{
				query="update player_table set one_score=? where name=?";
				prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, levelOneScore);
				prepareStatement.setString(2, playerName);
				prepareStatement.executeUpdate();
				stopwatch.stopTimer();
				LevelOneFailDialog dialog=new LevelOneFailDialog(playerName,levelOneScore);
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
			String textFromPlayer=jtfPlayer.getText();
			jtfPlayer.setText("");
			if(existInDB(textFromPlayer))
			{
				//Condition for different nouns
				if(!scHash.isContained(textFromPlayer))
				{
					lastchar=jta.getText().charAt(jta.getText().length()-2);
					firstchar=textFromPlayer.toLowerCase().charAt(0);
					//Condition for matching of last char and first char
					if(lastchar==firstchar)
					{
						++validCount;
						++levelOneScore;
						scHash.insert(textFromPlayer);
						jlbState.setForeground(Color.decode("#006600"));
						jlbState.setText("Valid Word !");
						jlbEmotion.setIcon(iconHappy);
						jta.append(textFromPlayer.toLowerCase()+"\n");
					}
					//Condition for mismatch of last char and first char
					else
					{
						if(levelOneScore>0)
						{
							--levelOneScore;
						}
						++invalidCount;
						jlbState.setForeground(Color.decode("#CC0033"));
						jlbState.setText("Mismatch !");
						jlbEmotion.setIcon(iconSad);
						jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
					}
					
				}
				//Condition for duplicate nouns
				else
				{
					if(levelOneScore>0)
					{
						levelOneScore-=2;
					}
					++invalidCount;
					jlbState.setForeground(Color.decode("#CC0033"));
					jlbState.setText("Duplication !");
					jlbEmotion.setIcon(iconSad);
					jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
					}
			}
			//Condition for not existing in DB
			else
			{
				if(levelOneScore>0)
				{
					--levelOneScore;
				}
				++invalidCount;
				jlbState.setForeground(Color.decode("#CC0033"));
				jlbState.setText("Not Existed !");
				jlbEmotion.setIcon(iconSad);
				jlbInvalidCount.setText("Invalid Word Count:"+invalidCount);
			}
			
			jtfPlayer.setEnabled(false);
				
			timerforai.start();
			jlbPlayerName.setForeground(Color.decode("#006600"));
			player_turn=false;
		}
	}
	
	/*public static void main(String[] args) 
	{
		LevelOne frame=new LevelOne("Htoo");
	}*/
}
