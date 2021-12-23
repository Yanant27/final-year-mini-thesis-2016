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


public class LevelFourCongratsDialog extends JDialog{
	
	Font font=new Font("Comic Sans MS",Font.BOLD,20);
	
	JButton jbtLevelPage=new JButton(new ImageIcon("src/congratulation/levels page.png"));
	JButton jbtNextLevel=new JButton(new ImageIcon("src/congratulation/next level.png"));
	JButton jbtRetry=new JButton(new ImageIcon("src/congratulation/retry.png"));
	
	JLabel jlbSmile=new JLabel(new ImageIcon("src/congratulation/smile.png"));
	JLabel jlbCongrats=new JLabel(new ImageIcon("src/congratulation/congrats.png"));
	JLabel jlbText=new JLabel("Level Four Passed !");
	JLabel jlbTargetScore=new JLabel("Target Score : 120");
	JLabel jlbPlayerScore=new JLabel();
	
	public LevelFourCongratsDialog(String name, int score)
	{
		this(null,true,name,score);
	}
	public LevelFourCongratsDialog(Frame parent, boolean modal, String name, int score)
	{
		super(parent,modal);
		setTitle("Congratulation");
		
		jlbText.setFont(font);
		jlbText.setForeground(Color.decode("#990000"));
		jlbTargetScore.setFont(font);
		jlbTargetScore.setForeground(Color.decode("#990000"));
		jlbPlayerScore.setText("Player Score : "+score);
		jlbPlayerScore.setFont(font);
		jlbPlayerScore.setForeground(Color.decode("#990000"));
		
		jbtRetry.setPreferredSize(new Dimension(65,27));
		jbtLevelPage.setPreferredSize(new Dimension(122,27));
		jbtNextLevel.setPreferredSize(new Dimension(115,27));
		
		//Panel for congratulation (congratsPanel)
		JPanel congratsPanel=new JPanel(new BorderLayout(0,20));
		congratsPanel.add(jlbCongrats,BorderLayout.CENTER);
		congratsPanel.add(jlbText,BorderLayout.SOUTH);
		congratsPanel.setOpaque(false);
		congratsPanel.setBorder(BorderFactory.createEmptyBorder(20,0,30,30));
		
		//Panel for image & congrats (rightPanel)
		JPanel rightPanel=new JPanel(new BorderLayout());
		rightPanel.add(jlbSmile,BorderLayout.CENTER);
		rightPanel.add(congratsPanel,BorderLayout.EAST);
		rightPanel.setOpaque(false);
		
		//Panel for two panels (buttonPanel)
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		buttonPanel.add(jbtLevelPage);
		buttonPanel.add(jbtRetry);
		buttonPanel.add(jbtNextLevel);
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,30,0));
		
		//Panel for score text (scorePanel)
		JPanel scorePanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
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
		setSize(500,350);
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
				LevelFour page=new LevelFour(playerName);
			}
		});
		
		jbtNextLevel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				LevelFive page=new LevelFive(playerName);
			}
		});
	}
	
	public static void main(String[] arg)
	{
		LevelFourCongratsDialog dialog=new LevelFourCongratsDialog("hello",00);
		dialog.setVisible(true);
	}
}
