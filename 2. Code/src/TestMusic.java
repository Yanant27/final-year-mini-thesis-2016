import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class TestMusic 
{
	public TestMusic()
	{
		try
		{
			FileInputStream inputStream=new FileInputStream("src/audio/win.mp3");
			Player playMusic=new Player(inputStream);
			playMusic.play();
			System.out.println("Music Play");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		TestMusic music=new TestMusic();
	}
}
