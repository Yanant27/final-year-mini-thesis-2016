import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LevelsPage extends JFrame
{
	Font font=new Font("Comic Sans MS",Font.BOLD,22);
	JButton jbtLevel1,jbtLevel2,jbtLevel3,jbtLevel4,jbtLevel5;
	JLabel jlbName;
	public LevelsPage(String name)
	{
		
		ImageIcon icon=new ImageIcon("src/levelsPage/good luck.gif");
		JLabel jlb=new JLabel("<html>English vocabulary game can help both native and foreign speakers of English build their English language vocabulary skills. Try the best of you !</html>",icon,SwingConstants.CENTER);
		jlb.setVerticalTextPosition(SwingConstants.BOTTOM);
		jlb.setHorizontalTextPosition(SwingConstants.CENTER);
		jlb.setIconTextGap(30);
		jlb.setFont(font);
		jlb.setForeground(Color.decode("#006600"));
		
		//Creating welcome label
		jlbName=new JLabel(name);
		JLabel jlbWelcome=new JLabel("Welcome "+jlbName.getText(),SwingConstants.CENTER);
		jlbWelcome.setFont(font);
		jlbWelcome.setForeground(Color.decode("#006600"));
		
		//Creating backhome button 
		JButton jbtBack=new JButton(new ImageIcon("src/levelsPage/backhome.png"));
		JButton jbtExit=new JButton(new ImageIcon("src/levelsPage/exit.png"));
		jbtBack.setPreferredSize(new Dimension(149,49));
		jbtExit.setPreferredSize(new Dimension(119,49));
		JPanel panelBack=new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		panelBack.add(jbtBack);
		panelBack.add(jbtExit);
		panelBack.setBackground(Color.WHITE);
		
		//Creating panel for welcome, text and backhome button (p)
		JPanel p=new JPanel(new BorderLayout());
		p.add(jlbWelcome,BorderLayout.NORTH);
		p.add(jlb,BorderLayout.CENTER);
		p.add(panelBack,BorderLayout.SOUTH);
		p.setBackground(Color.WHITE);
		
		//Creating 5 levels buttons
		jbtLevel1=new JButton(new ImageIcon("src/levelsPage/level1.png"));
		jbtLevel1.setPreferredSize(new Dimension(119,49));
		JPanel panelLevel1=new JPanel();
		panelLevel1.add(jbtLevel1);
		panelLevel1.setBackground(Color.WHITE);
		
		jbtLevel2=new JButton(new ImageIcon("src/levelsPage/level2.png"));
		jbtLevel2.setPreferredSize(new Dimension(119,49));
		JPanel panelLevel2=new JPanel();
		panelLevel2.add(jbtLevel2);
		panelLevel2.setBackground(Color.WHITE);
		
		jbtLevel3=new JButton(new ImageIcon("src/levelsPage/level3.png"));
		jbtLevel3.setPreferredSize(new Dimension(119,49));
		JPanel panelLevel3=new JPanel();
		panelLevel3.add(jbtLevel3);
		panelLevel3.setBackground(Color.WHITE);
		
		jbtLevel4=new JButton(new ImageIcon("src/levelsPage/level4.png"));
		jbtLevel4.setPreferredSize(new Dimension(119,49));
		JPanel panelLevel4=new JPanel();
		panelLevel4.add(jbtLevel4);
		panelLevel4.setBackground(Color.WHITE);
		
		jbtLevel5=new JButton(new ImageIcon("src/levelsPage/level5.png"));
		jbtLevel5.setPreferredSize(new Dimension(119,49));
		JPanel panelLevel5=new JPanel();
		panelLevel5.add(jbtLevel5);
		panelLevel5.setBackground(Color.WHITE);
		
		//Creating panel for 5 level buttons (buttonPanel)
		JPanel buttonPanel=new JPanel(new GridLayout(5,1));
		buttonPanel.add(panelLevel1);
		buttonPanel.add(panelLevel2);
		buttonPanel.add(panelLevel3);
		buttonPanel.add(panelLevel4);
		buttonPanel.add(panelLevel5);
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,30));
		
		//Initially set enabled to false
		jbtLevel2.setEnabled(false);
		jbtLevel3.setEnabled(false);
		jbtLevel4.setEnabled(false);
		jbtLevel5.setEnabled(false);
		
		//Combining all panels (allPanel)
		JPanel allPanel=new JPanel(new BorderLayout());
		allPanel.add(p,BorderLayout.CENTER);
		allPanel.add(buttonPanel,BorderLayout.EAST);
		allPanel.setBackground(Color.WHITE);
		allPanel.setBorder(BorderFactory.createEmptyBorder(20,30,30,0));
		add(allPanel);
		
		chooseLevel(name);
				
		setTitle("Levels Page");
		setSize(600,550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
		//Event for backhome button
		jbtBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				HomePage page=new HomePage();
				page.main(null);
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
		jbtLevel1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelOne page=new LevelOne(jlbName.getText());
				setVisible(false);
			}
		});
		jbtLevel2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelTwo page=new LevelTwo(jlbName.getText());
				setVisible(false);
			}
		});
		jbtLevel3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelThree page=new LevelThree(jlbName.getText());
				setVisible(false);
			}
		});
		jbtLevel4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelFour page=new LevelFour(jlbName.getText());
				setVisible(false);
			}
		});
		jbtLevel5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelFive page=new LevelFive(jlbName.getText());
				setVisible(false);
			}
		});
	}
	
	//Method to choose level for player
	public void chooseLevel(String playerName)
	{
		String level1="",level2="",level3="",level4="",level5="";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
			String query="select level1,level2,level3,level4,level5 from player_table where name=?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1, playerName);
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				level1=rs.getString(1);
				level2=rs.getString(2);
				level3=rs.getString(3);
				level4=rs.getString(4);
				level5=rs.getString(5);
			}
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(level1.equals("pass"))
			jbtLevel2.setEnabled(true);
		if(level2.equals("pass"))
			jbtLevel3.setEnabled(true);
		if(level3.equals("pass"))
			jbtLevel4.setEnabled(true);
		if(level4.equals("pass"))
			jbtLevel5.setEnabled(true);
	}
	public static void main(String[] args) 
	{
		LevelsPage frame=new LevelsPage("Htoo yanant");
	}
}
