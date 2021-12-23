import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTable;


public class RecentPlayer extends JFrame{
	JLabel jlbTable=new JLabel("table");
	String string="<html><table border=3 cellpadding=10 bgcolor=#CCFFCC><tr>" +
			"<th>Player No</th>" +
			"<th>Player Name</th>" +
			"<th>Level 5 Scores</th></tr>";
	public RecentPlayer()
	{	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
			String query="select player_no,name,five_score from player_table";
			PreparedStatement statement=con.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			while(rs.next())
			{
				string+="<tr align=center><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>";
			}
			string+="</table></html>";
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		jlbTable.setText(string);
		jlbTable.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		
		JLabel jlbRabbit=new JLabel(new ImageIcon("D:/Mini Thesis/Image for coding/recent player page/rabbit.jpg"));
		JButton jbtBack=new JButton(new ImageIcon("D:/Mini Thesis/Image for coding/recent player page/back.png"));
		jbtBack.setPreferredSize(new Dimension(133,59));
		JPanel buttonPanel=new JPanel();
		buttonPanel.add(jbtBack);
		buttonPanel.setBackground(Color.decode("#CCFFCC"));
		
		//Creating panel for rabbit imageicon and back button (P1)
		JPanel p1=new JPanel(new BorderLayout());
		p1.add(jlbRabbit,BorderLayout.CENTER);
		p1.add(buttonPanel,BorderLayout.SOUTH);
		p1.setBackground(Color.decode("#CCFFCC"));
		p1.setBorder(BorderFactory.createEmptyBorder(0,30,35,30));
		
		//Combining p1 & jlbTable (p2)
		JPanel p2=new JPanel(new BorderLayout());
		p2.add(jlbTable,BorderLayout.CENTER);
		p2.add(p1,BorderLayout.EAST);
		p2.setBackground(Color.decode("#CCFFCC"));
		p2.setBorder(BorderFactory.createEmptyBorder(30,30,30,0));
		add(p2);
		
		jbtBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				HomePage frame=new HomePage();
				frame.main(null);
				setVisible(false);
			}
		});
	}
	public static void main(String[] args) 
	{
		RecentPlayer frame=new RecentPlayer();
		frame.setTitle("Recent Player Page");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}
}
