import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IntroPage extends JFrame{
	Font font;
	Color color;
	public IntroPage()
	{		
		//ut-ycc logo & name
		font=new Font("Comic Sans MS",Font.BOLD,22);
		ImageIcon icon=new ImageIcon("src/IntroPage/ycc_logo.jpg");
		JLabel logo=new JLabel("University of Technology (Yatanarpon Cyber City)",icon,SwingConstants.CENTER);
		color=Color.decode("#3300FF");
		logo.setForeground(color);
		logo.setFont(font);
		logo.setHorizontalTextPosition(SwingConstants.RIGHT);
		logo.setVerticalTextPosition(SwingConstants.CENTER);
		logo.setIconTextGap(10);
		
		//Creating title label
		JLabel title=new JLabel("Hash-based Duplicate Words Checking System",SwingConstants.CENTER);
		font=new Font("Arial Black",Font.BOLD,22);
		color=Color.decode("#990000");
		title.setFont(font);
		title.setForeground(color);
		
		//Panel for student and supervisor names 
		JPanel p1=new JPanel();
		p1.setBackground(Color.decode("#99CCFF"));
		p1.setLayout(new GridLayout(1,2));
		font=new Font("Andy",Font.BOLD,25);
		color=Color.decode("#006600");
		JLabel studentName=new JLabel("<html>Ma Htoo Yanant Khin<br>&emsp;&emsp;&ensp;5IS-61</html>",SwingConstants.CENTER);
		studentName.setFont(font);
		studentName.setForeground(color);
		JLabel supervisorName=new JLabel("<html>&emsp;&ensp;Supervised by<br>Daw Su Sandar Phyo</html>",SwingConstants.CENTER);
		supervisorName.setFont(font);
		supervisorName.setForeground(color);
		p1.add(studentName);
		p1.add(supervisorName);
		
		//Creating continue button
		JButton jbtCont=new JButton(new ImageIcon("src/IntroPage/continue.jpg"));
		JPanel p=new JPanel();
		p.setBackground(Color.decode("#99CCFF"));
		jbtCont.setPreferredSize(new Dimension(200,58));
		p.add(jbtCont);
		
		//Combining logo,title and names of student and supervisor
		JPanel p2=new JPanel();
		p2.setBackground(Color.decode("#99CCFF"));
		p2.setLayout(new GridLayout(3,1));
		p2.add(logo);
		p2.add(title);
		p2.add(p1);
		
		//Combining all panels into one
		JPanel p3=new JPanel();
		p3.setBackground(Color.decode("#99CCFF"));
		p3.setLayout(new BorderLayout(5,10));
		p3.add(p2,BorderLayout.CENTER);
		p3.add(p,BorderLayout.SOUTH);
		//add space between JPanel and JFrame
		p3.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		add(p3);
		
		//Event for continue button
		jbtCont.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				HomePage page=new HomePage();
				page.main(null);
				setVisible(false);
			}
		});
	}
	public static void main(String[] args)
	{
		IntroPage ip=new IntroPage();
		ip.setTitle("Intro Page");
		ip.setSize(700,700);
		ip.setLocationRelativeTo(null);
		ip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ip.setVisible(true);
	}
}