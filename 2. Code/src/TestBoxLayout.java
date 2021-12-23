import java.awt.*;
import javax.swing.*;

public class TestBoxLayout extends JFrame{
	public TestBoxLayout()
	{
		JButton jbtPause=new JButton(new ImageIcon("src/levelOne/pause.png"));
		JButton jbtPlay=new JButton(new ImageIcon("src/levelOne/play.png"));
		//jbtPause.setPreferredSize(new Dimension(102,49));
		//jbtPlay.setPreferredSize(new Dimension(81,49));
		
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(jbtPause);
		panel.add(jbtPlay);
		
		add(panel);
	}
	public static void main(String[] args) 
	{
		TestBoxLayout frame=new TestBoxLayout();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(200, 200);
	}
}
