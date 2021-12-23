import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class LevelFiveCongratsDialog extends JDialog{
	String playerName="";
	int playerScore=0;
	
	Font font=new Font("Comic Sans MS",Font.BOLD,20);
	
	JButton jbtLevelPage=new JButton(new ImageIcon("src/congratulation/levels page.png"));
	JButton jbtNextLevel=new JButton(new ImageIcon("src/congratulation/next level.png"));
	JButton jbtRetry=new JButton(new ImageIcon("src/congratulation/retry.png"));
	
	JLabel jlbSmile=new JLabel(new ImageIcon("src/congratulation/ribbon.png"));
	JLabel jlbCongrats=new JLabel(new ImageIcon("src/congratulation/congrat_5.jpg"));
	JLabel jlbText=new JLabel("You have passed all levels !");
	JLabel jlbTargetScore=new JLabel("Target Score : 150");
	JLabel jlbPlayerScore=new JLabel();
	
	public LevelFiveCongratsDialog(String name, int score)
	{
		this(null,true,name,score);
	}
	public LevelFiveCongratsDialog(Frame parent, boolean modal, String name, int score)
	{
		super(parent,modal);
		jlbText.setFont(font);
		
		jlbText.setForeground(Color.decode("#990000"));
		jlbTargetScore.setFont(font);
		jlbTargetScore.setForeground(Color.decode("#990000"));
		setTitle("Congratulation");
		jlbPlayerScore.setText("Player Score : "+score);
		jlbPlayerScore.setFont(font);
		jlbPlayerScore.setForeground(Color.decode("#990000"));
		
		jbtRetry.setPreferredSize(new Dimension(65,27));
		jbtLevelPage.setPreferredSize(new Dimension(122,27));
		jbtNextLevel.setPreferredSize(new Dimension(115,27));
		
		//Panel for congratulation (congratsPanel)
		JPanel congratsPanel=new JPanel(new BorderLayout(0,0));
		congratsPanel.add(jlbCongrats,BorderLayout.CENTER);
		congratsPanel.add(jlbText,BorderLayout.SOUTH);
		congratsPanel.setOpaque(false);
		congratsPanel.setBorder(BorderFactory.createEmptyBorder(20,0,10,30));
		
		//Panel for image & congrats (rightPanel)
		JPanel rightPanel=new JPanel(new BorderLayout());
		rightPanel.add(jlbSmile,BorderLayout.CENTER);
		rightPanel.add(congratsPanel,BorderLayout.EAST);
		rightPanel.setOpaque(false);
		
		//Panel for two panels (buttonPanel)
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		buttonPanel.add(jbtLevelPage);
		buttonPanel.add(jbtRetry);
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
		
		//Panel for score text (scorePanel)
		JPanel scorePanel=new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
		scorePanel.add(jlbTargetScore);
		scorePanel.add(jlbPlayerScore);
		scorePanel.setOpaque(false);
		
		//Combining all panel (panel)
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(rightPanel,BorderLayout.NORTH);
		panel.add(scorePanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		panel.setBackground(Color.white);
		add(panel);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(490,320);
		setLocationRelativeTo(null);
		
		final String playerName=name;
		
		jbtLevelPage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				LevelsPage page=new LevelsPage(playerName);
			}
		});
		
		jbtRetry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				LevelFive page=new LevelFive(playerName);
			}
		});
		
		jbtNextLevel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				LevelTwo page=new LevelTwo(playerName);
			}
		});
	}
	
	public static void main(String[] arg)
	{
		LevelFiveCongratsDialog dialog=new LevelFiveCongratsDialog("hello",00);
		dialog.setVisible(true);
	}
}
