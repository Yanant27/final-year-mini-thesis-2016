import javax.swing.JFrame;
import javax.swing.JLabel;


public class TestTable extends JFrame
{
	public TestTable()
	{
		String string="<html>thread,tbody{display: block;} tbody{height: 100px;overflow-y: auto;overflow-x: hidden;}</html>";
		JLabel jlb=new JLabel(string);
		
	}
	public static void main(String[] args) 
	{
		TestTable frame=new TestTable();
		frame.setTitle("Test Table");
		//frame.setSize(300,300);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
