import java.awt.BorderLayout;
import java.awt.Color;
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


public class InvalidPlayerDialog extends JDialog{

	Font font=new Font("Comic Sans MS",Font.BOLD,16);
	Font jbtFont=new Font("Comic Sans MS",Font.BOLD,12);
	
	ImageIcon icon=new ImageIcon("src/inputAndExitDialog/fake.jpg");
	public JLabel iconLabel=new JLabel("<html>Invalid Player Name and Password ! Please type again<html>",icon,SwingConstants.CENTER);
	
	JButton jbtOk=new JButton("OK");
	
	public InvalidPlayerDialog()
	{
		this(null,true);
	}
	public InvalidPlayerDialog(Frame parent, boolean modal)
	{
		super(parent,modal);
		jbtOk.setFont(jbtFont);
		iconLabel.setFont(font);
		iconLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		iconLabel.setVerticalTextPosition(SwingConstants.CENTER);
		
		//Panel for OK button (okPanel)
		JPanel okPanel=new JPanel();
		okPanel.add(jbtOk);
		okPanel.setOpaque(false);
		
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(iconLabel,BorderLayout.CENTER);
		panel.add(okPanel,BorderLayout.SOUTH);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(15,10,10,5));
		add(panel);
		
		setTitle("Invalid Player Dialog");
		setSize(300,190);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		jbtOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
	}
	public static void main(String[] args) 
	{
		InvalidPlayerDialog dialog=new InvalidPlayerDialog();
		dialog.setVisible(true);
	}
	
}
