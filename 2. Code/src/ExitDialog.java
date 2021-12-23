import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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


public class ExitDialog extends JDialog
{
	Font font=new Font("Comic Sans MS",Font.BOLD,16);
	Font jbtFont=new Font("Comic Sans MS",Font.BOLD,12);
	ImageIcon icon=new ImageIcon("src/inputAndExitDialog/sad.jpg");
	JLabel jlb=new JLabel("Are you sure to exit ?",icon,SwingConstants.CENTER);
	
	JButton jbtYes=new JButton("YES");
	JButton jbtNo=new JButton("NO");
	
	public ExitDialog()
	{
		jlb.setHorizontalTextPosition(SwingConstants.RIGHT);
		jlb.setVerticalTextPosition(SwingConstants.CENTER);
		jlb.setIconTextGap(10);
		jlb.setFont(font);
		
		jbtYes.setFont(jbtFont);
		jbtNo.setFont(jbtFont);
		
		//Panel for ok and cancel button (buttonPanel)
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		buttonPanel.add(jbtYes);
		buttonPanel.add(jbtNo);
		buttonPanel.setOpaque(false);
		
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(jlb,BorderLayout.CENTER);
		panel.add(buttonPanel,BorderLayout.SOUTH);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(panel);
		
		jbtYes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		jbtNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		
		setTitle("Exit Dialog");
		setSize(300,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}
	public static void main(String[] args) 
	{
		ExitDialog dialog=new ExitDialog();
		dialog.setVisible(true);
	}
}
