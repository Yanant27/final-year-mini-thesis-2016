import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class RulesPage extends JFrame{
	public RulesPage()
	{
		Font font=new Font("Comic Sans MS",Font.BOLD,18);
		JLabel rule1=new JLabel("<html><ul type=fillround>" +
										"<li>No duplicate words can be allowed. If you enter duplicate word, <u>-2</u> points will be added to your scores.<br><br>" +
										"<li>The system allows only words included in eight parts of speech. If you enter a word except from these, <u>-1</u> point will be gained.<br><br>" +
										"</ul></html>"); 
		JLabel rule2=new JLabel("<html><ul type=fillround>" +
											"<li>The last character of previous word must be matched with the first character of next word. If any mismatch is found, you will get <u>-1</u> point.<br><br>" +
											"<li>In higher level, if the length of entered word is less or exceeded than the limited length, <u>-1</u> mark will be gained.<br><br>"+
											"</ul></html>");
		JLabel rule3=new JLabel("<html><ul type=fillround><li>Single words are not allowed to enter, for example a,b,c,etc.</ul></html>");
		rule1.setFont(font);
		rule2.setFont(font);
		rule3.setFont(font);
		rule1.setForeground(Color.decode("#000099"));
		rule2.setForeground(Color.decode("#000099"));
		rule3.setForeground(Color.decode("#000099"));
		
		//Creating rules, question mark imageicons & backhome button
		JLabel ruleIcon=new JLabel(new ImageIcon("src/rulesPage/5rules.jpg"));
		JLabel questionIcon=new JLabel(new ImageIcon("src/rulesPage/que.jpg"));
		JButton jbtHome=new JButton(new ImageIcon("src/rulesPage/backhome.png"));
		jbtHome.setPreferredSize(new Dimension(146,60));
		JPanel panelHome=new JPanel();
		panelHome.add(jbtHome);
		panelHome.setBackground(Color.WHITE);
		
		//Creating panel for rules imageicons, rule2 label & backhome button (p1)
		JPanel p1=new JPanel(new BorderLayout());
		p1.add(ruleIcon,BorderLayout.NORTH);
		p1.add(rule2,BorderLayout.CENTER);
		p1.add(panelHome,BorderLayout.SOUTH);
		p1.setBackground(Color.WHITE);
		
		//Creating panel for rule1 label, question mark icon & rule3 label (p2)
		JPanel p2=new JPanel(new BorderLayout());
		p2.add(rule1,BorderLayout.NORTH);
		p2.add(questionIcon,BorderLayout.CENTER);
		p2.add(rule3,BorderLayout.SOUTH);
		p2.setBackground(Color.WHITE);
		add(p2);
		
		//Combining all panels (p3)
		JPanel p3=new JPanel(new GridLayout(1,2));
		p3.add(p1);
		p3.add(p2);
		p3.setBackground(Color.WHITE);
		p3.setBorder(BorderFactory.createEmptyBorder(30,0,30,10));
		add(p3);
		
		//Event for back home button
		jbtHome.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				HomePage home=new HomePage();
				home.main(null);
				setVisible(false);
			}
		});
	}
	public static void main(String[] args) 
	{	
		RulesPage frame=new RulesPage();
		frame.setTitle("Rules Page");
		frame.setSize(700,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}
}
