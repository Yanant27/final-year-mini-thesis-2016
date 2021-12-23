import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
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


public class LevelFourFailDialog extends JDialog
{
	Font font=new Font("Comic Sans MS",Font.BOLD,20);
	
	JButton jbtLevelPage=new JButton(new ImageIcon("src/fail/levels page.png"));
	JButton jbtRetry=new JButton(new ImageIcon("src/fail/retry.png"));
	
	JLabel jlbText=new JLabel("Level Four Failed !",SwingConstants.CENTER);
	JLabel jlbCry1=new JLabel(new ImageIcon("src/fail/cry.png"));
	JLabel jlbCry2=new JLabel(new ImageIcon("src/fail/cry.png"));
	JLabel jlbCry3=new JLabel(new ImageIcon("src/fail/cry.png"));
	JLabel jlbCry4=new JLabel(new ImageIcon("src/fail/cry.png"));
	JLabel jlbCry5=new JLabel(new ImageIcon("src/fail/cry.png"));
	JLabel jlbTargetScore=new JLabel("Target Score : 120");
	JLabel jlbPlayerScore=new JLabel();
	
	public LevelFourFailDialog(String name, int score)
	{
		this(null,true,name,score);
	}
	public LevelFourFailDialog(Frame parent, boolean modal, String name, int score)
	{
		super(parent,modal);
		setTitle("Level Four Fail");
		
		jlbText.setFont(font);
		jlbText.setBackground(Color.white);
		jlbText.setForeground(Color.decode("#CC6600"));
		jlbPlayerScore.setFont(font);
		jlbPlayerScore.setText("Player Score : "+score);
		jlbPlayerScore.setForeground(Color.decode("#CC6600"));
		jlbTargetScore.setFont(font);
		jlbTargetScore.setForeground(Color.decode("#CC6600"));
		
		jbtRetry.setPreferredSize(new Dimension(65,27));
		jbtLevelPage.setPreferredSize(new Dimension(122,27));
		
		//Panel for cry label (iconPanel)
		JPanel iconPanel=new JPanel(new GridLayout(1,5,10,0));
		iconPanel.add(jlbCry1);
		iconPanel.add(jlbCry2);
		iconPanel.add(jlbCry3);
		iconPanel.add(jlbCry4);
		iconPanel.add(jlbCry5);
		iconPanel.setOpaque(false);
		iconPanel.setBorder(BorderFactory.createEmptyBorder(30,20,10,20));
		
		//Panel for two panels (buttonPanel)
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		buttonPanel.add(jbtRetry);
		buttonPanel.add(jbtLevelPage);
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,30,30,30));
		
		//Panel for score text (scorePanel)
		JPanel scorePanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		scorePanel.add(jlbTargetScore);
		scorePanel.add(jlbPlayerScore);
		scorePanel.setOpaque(false);
		
		//Panel for score& text (scoreTextPanel)
		JPanel scoreTextPanel=new JPanel(new GridLayout(2,1,0,15));
		scoreTextPanel.add(jlbText);
		scoreTextPanel.add(scorePanel);
		scoreTextPanel.setOpaque(false);
		
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(iconPanel,BorderLayout.NORTH);
		panel.add(scoreTextPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		panel.setBackground(Color.white);
		add(panel);
		
		setSize(420,290);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
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
	}
	
	/*public static void main(String[] arg)
	{
		LevelFourFailDialog dialog=new LevelFourFailDialog("hello",00);
		dialog.setVisible(true);
	}*/
}
