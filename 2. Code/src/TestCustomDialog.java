import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class TestCustomDialog extends JFrame{

	JButton fail=new JButton("fail");
	JButton pass=new JButton("pass");
	
	public TestCustomDialog()
	{
		add(fail,BorderLayout.CENTER);
		add(pass,BorderLayout.SOUTH);
		
		fail.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelOneFailDialog dialog=new LevelOneFailDialog("hello",00);
				dialog.setVisible(true);
			}
		});
		
		pass.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				LevelOneCongratsDialog dialog=new LevelOneCongratsDialog("hello",00);
				dialog.setVisible(true);
			}
		});
	}
	
	public static void main(String[] args)
	{
		TestCustomDialog frame=new TestCustomDialog();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.pack();
	}
}
