import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class TestPausePlay extends JFrame{
	static Icon icon1=new ImageIcon("src/levelOne/play.png");
	static Icon icon2=new ImageIcon("src/levelOne/pause.png");
	static JButton jbtPlay=new JButton(icon1);
	//JButton jbtPause=new JButton(new ImageIcon("src/levelOne/pause.png"));
	public TestPausePlay()
	{
		jbtPlay.setPreferredSize(new Dimension(81,49));
		//add(jbtPlay);
		jbtPlay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{if(jbtPlay.getIcon()==icon1)
						{
							jbtPlay.setIcon(icon2);
							jbtPlay.setPreferredSize(new Dimension(102,49));
						}
						else
						{
							jbtPlay.setIcon(icon1);
							jbtPlay.setPreferredSize(new Dimension(81,49));
						}
					
			}
		});
		
		//add(jbtPlay);
		JPanel buttonPanel=new JPanel();
		buttonPanel.add(jbtPlay);
		buttonPanel.setOpaque(false);
		JLabel background=new JLabel(new ImageIcon("src/background.gif"));
		background.setLayout(new BorderLayout());
		background.add(buttonPanel,BorderLayout.CENTER);
		background.add(new JLabel("Test Background"),BorderLayout.NORTH);
		add(background);
	}
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				TestPausePlay frame=new TestPausePlay();
				//frame.setSize(200,200);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//frame.getContentPane().add(jbtPlay);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
			}
		});
	}
}
