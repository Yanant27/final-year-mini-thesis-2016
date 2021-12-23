import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class OldPlayerDialog extends JDialog
{
	Font font=new Font("Comic Sans MS",Font.BOLD,16);
	Font jbtFont=new Font("Comic Sans MS",Font.BOLD,12);
	JLabel jlbName=new JLabel("Name : ");
	JLabel jlbPassword=new JLabel("Password : ");
	JLabel jlbImage=new JLabel(new ImageIcon("src/inputandExitDialog/bunny.jpg"));
	JTextField jtfName=new JTextField();
	JPasswordField jtfPassword=new JPasswordField();
	JButton jbtOk=new JButton("OK");
	JButton jbtCancel=new JButton("CANCEL");
	
	PreparedStatement statement=null;
	Connection con;
	ResultSet rs=null;
	
	boolean okPress=false;
	
	public OldPlayerDialog()
	{
		this(null,true,null,null);
	}
	public OldPlayerDialog(Frame parent, boolean modal, String name, String password)
	{
		super(parent,modal);
		jbtOk.setFont(jbtFont);
		jbtCancel.setFont(jbtFont);
		
		jlbName.setFont(font);
		jlbPassword.setFont(font);
		jtfName.setFont(font);
		jtfPassword.setFont(font);
		
		jtfName.setBackground(Color.decode("#FFFFCC"));
		jtfPassword.setBackground(Color.decode("#FFFFCC"));
		
		JPanel inputPanel=new JPanel(new GridLayout(2,2,0,20));
		inputPanel.add(jlbName);
		inputPanel.add(jtfName);
		inputPanel.add(jlbPassword);
		inputPanel.add(jtfPassword);
		inputPanel.setOpaque(false);
		inputPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		//Panel for inputPanel & image icon (centerPanel)
		JPanel centerPanel=new JPanel(new BorderLayout());
		centerPanel.add(jlbImage,BorderLayout.WEST);
		centerPanel.add(inputPanel,BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		
		//Panel for ok and cancel button (buttonPanel)
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
		buttonPanel.add(jbtOk);
		buttonPanel.add(jbtCancel);
		buttonPanel.setOpaque(false);
		
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(centerPanel,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10,20,20,5));
		add(panel);
		
		setTitle("Old Player Dialog");
		setSize(350,220);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		jbtOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Boolean flag=true;
				String passFromDB = null;
				//Get password from DB
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
					
					String query="select password from player_table where name=?";
					statement=con.prepareStatement(query);
					statement.setString(1,jtfName.getText());
					rs=statement.executeQuery();
					if(rs.next())
						passFromDB=rs.getString(1);
					con.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				String passFromJtf=jtfPassword.getText();
				//System.out.println("Password from jtf : "+passFromJtf);
				//System.out.println("Password from DB : "+passFromDB);
				if(passFromJtf.equals(passFromDB) && !passFromJtf.equals("") && !passFromDB.equals(""))
				{
					okPress=true;
					LevelsPage page=new LevelsPage(jtfName.getText());
					setVisible(false);
				}
				else
				{
					InvalidPlayerDialog dialog=new InvalidPlayerDialog();
					dialog.setVisible(true);
					setVisible(false);
					new OldPlayerDialog().setVisible(true);
				}
			}
		});
		
		jbtCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
	}
	public static void main(String[] args) 
	{
		OldPlayerDialog dialog=new OldPlayerDialog();
		dialog.setVisible(true);
	}
}
