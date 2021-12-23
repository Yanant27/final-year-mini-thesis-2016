import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.SwingConstants;


public class NewPlayerDialog extends JDialog{
	Font font=new Font("Comic Sans MS",Font.BOLD,16);
	Font jbtFont=new Font("Comic Sans MS",Font.BOLD,12);
	
	JLabel jlbName=new JLabel("Name : ",SwingConstants.RIGHT);
	JLabel jlbPassword=new JLabel("Password : ",SwingConstants.RIGHT);
	JLabel jlbConfirmPassword=new JLabel("Confirmed Password : ");
	JLabel jlbImage=new JLabel(new ImageIcon("src/inputandExitDialog/bunny.jpg"));
	
	JTextField jtfName=new JTextField();
	JPasswordField jtfPassword=new JPasswordField();
	JPasswordField jtfConfirmPassword=new JPasswordField();
	
	JButton jbtOk=new JButton("OK");
	JButton jbtCancel=new JButton("CANCEL");
	
	PreparedStatement statement=null;
	Connection con;
	ResultSet rs=null;
	
	boolean okPress=false;
	
	public NewPlayerDialog()
	{
		this(null,true);
	}
	public NewPlayerDialog(Frame parent, boolean modal)
	{
		super(parent,modal);
		
		jbtOk.setFont(jbtFont);
		jbtCancel.setFont(jbtFont);
		
		jlbName.setFont(font);
		jlbPassword.setFont(font);
		jlbConfirmPassword.setFont(font);
		
		jtfName.setFont(font);
		jtfPassword.setFont(font);
		jtfConfirmPassword.setFont(font);
		
		jtfName.setBackground(Color.decode("#FFFFCC"));
		jtfPassword.setBackground(Color.decode("#FFFFCC"));
		jtfConfirmPassword.setBackground(Color.decode("#FFFFCC"));
		
		JPanel inputPanel=new JPanel(new GridLayout(3,2,10,10));
		inputPanel.add(jlbName);
		inputPanel.add(jtfName);
		inputPanel.add(jlbPassword);
		inputPanel.add(jtfPassword);
		inputPanel.add(jlbConfirmPassword);
		inputPanel.add(jtfConfirmPassword);
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
		
		setTitle("New Player Dialog");
		setSize(515,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		jbtOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String password=jtfPassword.getText();
					String confirmPassword=jtfConfirmPassword.getText();
					
					//Check NULL for three inputs
					if(jtfName.equals("") || password.equals("") || confirmPassword.equals(""))
					{
						//jtfName.setText("");
						//jtfPassword.setText("");
						//jtfConfirmPassword .setText("");
						InvalidPlayerDialog dialog=new InvalidPlayerDialog();
						dialog.iconLabel.setText("<html>Three inputs are compulsory ! Null cannot be allowed<html>");
						dialog.setVisible(true);
					}
					else
					{
						//Check name whether it is existed in DB
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
						String query="select name from player_table where name=?";
						statement=con.prepareStatement(query);
						statement.setString(1, jtfName.getText());
						rs=statement.executeQuery();
						if(rs.next())
						{
							System.out.println(rs.getString(1));
							jtfName.setText("");
							jtfPassword.setText("");
							jtfConfirmPassword.setText("");
							InvalidPlayerDialog dialog=new InvalidPlayerDialog();
							dialog.iconLabel.setText("<html>Your name has been existed !<html>");
							dialog.setVisible(true);
						}
						//Condition for unique name
						else
						{
							//Condition for equal password (valid)
							if(password.equals(confirmPassword))
							{
								//Check password length(same length)
								if(password.length()>=7 && confirmPassword.length()<=10)
								{
									query="insert into player_table values(null,'"+jtfName.getText()+"','"+password+"',0,0,0,0,0,'fail','fail','fail','fail','fail')";
									statement=con.prepareStatement(query);
									statement.executeUpdate();
									setVisible(false);	
									con.close();
									okPress=true;
									LevelsPage page=new LevelsPage(jtfName.getText());								
								}
								//Condition for invalid length
								else
								{
									//jtfName.setText("");
									jtfPassword.setText("");
									jtfConfirmPassword.setText("");
									InvalidPlayerDialog dialog=new InvalidPlayerDialog();
									dialog.iconLabel.setText("<html>Password length must be between 7 and 10<html>");
									dialog.setVisible(true);
								}
							}
							//Condition for unequal password (invalid)
							else
							{
								//jtfName.setText("");
								jtfPassword.setText("");
								jtfConfirmPassword.setText("");
								InvalidPlayerDialog dialog=new InvalidPlayerDialog();
								dialog.iconLabel.setText("<html>Two passwords are not equal !<html>");
								dialog.setVisible(true);
							}
					
						}
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
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
		new NewPlayerDialog().setVisible(true);
	}
}
