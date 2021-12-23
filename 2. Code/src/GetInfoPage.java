import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GetInfoPage extends JFrame
{
	public GetInfoPage()
	{
		ImageIcon icon1=new ImageIcon("src/getInfoPage/info1.png");
		ImageIcon icon2=new ImageIcon("src/getInfoPage/b.jpg");
		JLabel jlb1=new JLabel("<html><ul type=fillround>" +
									"<li>The system contains many English vocabularies and the user can only type words which are being existed in database.</ul></html>");
		JLabel jlb2=new JLabel("<html><ul type=fillround><li>Initially, the system will generate a word in random order.<br><br>" +
									"<li>The user must enter a word , its first character must be matched with the last character of random word.</ul></html>",icon1,SwingConstants.CENTER);
		JLabel jlb3=new JLabel("<html><ul type=fillround><li>The system contains minus marking scheme for duplication, mismatch and words not existed in database.<br><br>" +
									"<li>One mark will be gained if the user enters a correct word which is consistent with the rules of the system.</ul></html>",icon2,SwingConstants.CENTER);
		JLabel jlb4=new JLabel("<html><ul type=fillround><li>The system has more than one level to test how much the user has known vocabularies in English.</ul></html>");
		jlb2.setHorizontalTextPosition(SwingConstants.RIGHT);
		jlb2.setVerticalTextPosition(SwingConstants.CENTER);
		jlb3.setHorizontalTextPosition(SwingConstants.LEFT);
		jlb3.setVerticalTextPosition(SwingConstants.CENTER);
		
		//Creating panel for backhome button
		JButton jbtBack=new JButton(new ImageIcon("src/getInfoPage/backhome2.png"));
		jbtBack.setPreferredSize(new Dimension(162,53));
		JPanel buttonPanel=new JPanel();
		buttonPanel.add(jbtBack);
		buttonPanel.setBackground(Color.white);
		
		Font font=new Font("Comic Sans MS",Font.BOLD,20);
		jlb1.setFont(font);
		jlb2.setFont(font);
		jlb3.setFont(font);
		jlb4.setFont(font);
		jlb1.setForeground(Color.decode("#000066"));
		jlb2.setForeground(Color.decode("#000066"));
		jlb3.setForeground(Color.decode("#000066"));
		jlb4.setForeground(Color.decode("#000066"));
		
		//Panel for label 2 and label 3 (p1)
		JPanel p1=new JPanel(new GridLayout(2,1,0,10));
		p1.add(jlb2);
		p1.add(jlb3);
		p1.setBackground(Color.white);
		
		//Combining all labels (p2)
		JPanel p2=new JPanel(new BorderLayout(0,12));
		p2.add(jlb1,BorderLayout.NORTH);
		p2.add(p1,BorderLayout.CENTER);
		p2.add(jlb4,BorderLayout.SOUTH);
		p2.setBackground(Color.white);
		
		//Combining p2 and backhome button (p3)
		JPanel p3=new JPanel(new BorderLayout(0,10));
		p3.add(p2,BorderLayout.CENTER);
		p3.add(buttonPanel,BorderLayout.SOUTH);
		p3.setBackground(Color.white);
		p3.setBorder(BorderFactory.createEmptyBorder(25,30,30,30));
		add(p3);
		
		
		jbtBack.addActionListener(new ActionListener()
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
		GetInfoPage frame=new GetInfoPage();
		frame.setTitle("Get Info: Page");
		frame.setSize(720,750);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}
}






