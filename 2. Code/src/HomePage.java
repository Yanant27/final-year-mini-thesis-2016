import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.sql.*;

public class HomePage extends JFrame
{
	Font font;
	Connection con;
	PreparedStatement statement;
	ResultSet rs;
	
	JLabel score=new JLabel("0");
	JLabel jlbScore=new JLabel("Best Score : ");
	JLabel jlbText=new JLabel("<html>How many words have you collected? Let's Check your skill playing game !</html>",new ImageIcon("src/homePage/icon1.jpg"),SwingConstants.CENTER);
	
	//JButton jbtSound=new JButton(iconMute);
	JButton jbtExit=new JButton(new ImageIcon("src/homePage/exit.png"));
	JButton jbtInfo=new JButton(new ImageIcon("src/homePage/get info.png"));
	JButton jbtRules=new JButton(new ImageIcon("src/homePage/rules.png"));
	JButton jbtBack=new JButton(new ImageIcon("src/homePage/back.png"));
	JButton jbtNewPlayer=new JButton(new ImageIcon("src/homePage/new player.png"));
	JButton jbtOldPlayer=new JButton(new ImageIcon("src/homePage/old player.png"));
	JButton jbtRecentPlayer=new JButton(new ImageIcon("src/homePage/recent player.png"));
	
	public  HomePage()
	{
		//Creating welcome label
		ImageIcon welcomeIcon=new ImageIcon("src/homePage/welcome2.gif");
		JLabel jlbWelcome=new JLabel(welcomeIcon,SwingConstants.CENTER);
		
		font=new Font("Century Gothic",Font.BOLD,25);
		jlbScore.setFont(font);		
		score.setFont(font);
		jlbScore.setForeground(Color.decode("#990000"));
		score.setForeground(Color.decode("#006600"));
		
		font=new Font("Arial Narrow",Font.BOLD,30);
		jlbText.setFont(font);
		jlbText.setForeground(Color.decode("#000066"));
		jlbText.setHorizontalTextPosition(SwingConstants.RIGHT);
		jlbText.setVerticalTextPosition(SwingConstants.CENTER);
		jlbText.setIconTextGap(50);
		jlbText.setOpaque(false);
		
		jbtInfo.setPreferredSize(new Dimension(136,49));
		jbtRules.setPreferredSize(new Dimension(94,49));
		jbtExit.setPreferredSize(new Dimension(85,49));
		jbtBack.setPreferredSize(new Dimension(90,50));
		jbtNewPlayer.setPreferredSize(new Dimension(150,50));
		jbtOldPlayer.setPreferredSize(new Dimension(150,50));
		jbtRecentPlayer.setPreferredSize(new Dimension(195,50));
		
		//Creating panel for best score (scorePanel)
		JPanel scorePanel=new JPanel();
		scorePanel.add(jlbScore);
		scorePanel.add(score);
		scorePanel.setOpaque(false);
		
		//Creating panel for best score, exit, get info & rules (topButtonPanel)
		JPanel topButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		topButtonPanel.add(scorePanel);
		topButtonPanel.add(jbtRules);
		topButtonPanel.add(jbtInfo);
		topButtonPanel.add(jbtExit);
		topButtonPanel.setOpaque(false);
		topButtonPanel.setBorder(BorderFactory.createEmptyBorder(5,0,20,0));
		
		//Creating panel for back, new player, old player & recent players buttons (bottomButtonPanel)
		JPanel bottomButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
		bottomButtonPanel.add(jbtBack);
		bottomButtonPanel.add(jbtNewPlayer);
		bottomButtonPanel.add(jbtOldPlayer);
		bottomButtonPanel.add(jbtRecentPlayer);
		bottomButtonPanel.setOpaque(false);
		bottomButtonPanel.setBorder(BorderFactory.createEmptyBorder(45,0,0,0));
		
		//Combining welcome,topButtonPanel and text panels (centerPanel)
		JPanel centerPanel=new JPanel(new BorderLayout(0,10));
		centerPanel.add(jlbWelcome,BorderLayout.NORTH);
		centerPanel.add(topButtonPanel,BorderLayout.CENTER);
		centerPanel.add(jlbText,BorderLayout.SOUTH);
		centerPanel.setOpaque(false);
		
		//Combining all panels (panel)
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(centerPanel,BorderLayout.CENTER);
		panel.add(bottomButtonPanel,BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(0,40,50,30));
		panel.setBackground(Color.decode("#FFFFCC"));
		getBestScore();
		add(panel);
		
		//Event for Get Info: button
		jbtInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GetInfoPage page=new GetInfoPage();
				page.main(null);
				setVisible(false);
			}
		});
		
		//Event for rules button
		jbtRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RulesPage page=new RulesPage();
				page.main(null);
				setVisible(false);
			}
		});
			
		//Event for back button
		jbtBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				IntroPage intro=new IntroPage();
				intro.main(null);
				setVisible(false);
			}
		});
		
		//Event for exit button
		jbtExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ExitDialog dialog=new ExitDialog();
				dialog.setVisible(true);
			}
		});
		
		//Event for new player button
		jbtNewPlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				NewPlayerDialog dialog=new NewPlayerDialog();
				dialog.setVisible(true);
				if(dialog.okPress)
					setVisible(false);
			}
		});
		
		//Event for old player button
		jbtOldPlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				OldPlayerDialog dialog=new OldPlayerDialog();
				dialog.setVisible(true);
				 if(dialog.okPress)
					setVisible(false);
			}
		});
		
		//Event for recent player button
		jbtRecentPlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RecentPlayer page=new RecentPlayer();
				page.main(null);
				setVisible(false);
			}
		});
	}

	
	//Method to get best score
	public void getBestScore()
	{
		int bestscore=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
			
			//Select maximum score from player table
			String query="select max(five_score) from player_table";
			PreparedStatement statement=con.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				bestscore=rs.getInt(1);
				score.setText(String.valueOf(bestscore));
			}
			//con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		HomePage frame=new HomePage();
		frame.setTitle("Home Page");
		frame.setSize(740,680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}
}