import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//Left to check for minutes greater than 9
public class Stopwatch extends JLabel
{
	Timer timer;
	int counter=1;
	public int minute=0;
	public boolean timeOut=false;
	public Stopwatch()
	{
		setText("00:00");
		timer=new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (minute<1)
				{
					if(counter==60)
					{
						++minute;
						counter=0;
						setText("0"+String.valueOf(minute)+":00");
					}
					else if(counter<10)
						setText("00:0"+String.valueOf(counter));
					else
						setText("00:"+String.valueOf(counter));
					++counter;
				}
				else if(minute<10)
				{
					if(counter==60)
					{
						++minute;
						counter=0;
						setText("0"+String.valueOf(minute)+":00");
					}
					else if(counter<10)
						setText("0"+String.valueOf(minute)+":0"+counter);
					else
						setText("0"+String.valueOf(minute)+":"+counter);
					++counter;
				}
				else 
				{
					if(counter==60)
					{
						++minute;
						counter=0;
						setText(String.valueOf(minute)+":00");
					}
					else if(counter<10)
						setText(String.valueOf(minute)+":0"+counter);
					else
						setText(String.valueOf(minute)+":"+counter);
					++counter;
				}
			}
		});	
	}
	public void startTimer()
	{
		timer.start();
	}
	public void stopTimer()
	{
		timer.stop();
	}
	public boolean isStarted()
	{
		return timer.isRunning();
	}
	public void restartTimer()
	{
		timer.restart();
	}
	public static void main(String[] args)
	{
		Stopwatch stop=new Stopwatch();
		JFrame frame=new JFrame();
		//frame.setSize(60,70);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(stop);
		frame.setVisible(true);
		/*SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Stopwatch(0);
			}
		});*/

	}
}