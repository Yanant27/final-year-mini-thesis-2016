import java.sql.*;


public class TestUpdate{
	public TestUpdate()
	{
		int totalScore=25;
		String playerName="war war";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/minithesis","root","root");
			/*String query="update player_table set score=?,level1=? where name=?";
			PreparedStatement prepareStatement=con.prepareStatement(query);
			prepareStatement.setInt(1,45);
			prepareStatement.setString(2,"pass");
			prepareStatement.setString(3,"war war");
			prepareStatement.executeUpdate();
			System.out.println("Successfully updated !");*/
			
			String query="update player_table set score=?,level1=? where name=?";
			PreparedStatement prepareStatement=con.prepareStatement(query);
				prepareStatement.setInt(1, totalScore);
				System.out.println("score set");
				prepareStatement.setString(2, "pass");
				System.out.println("level1 set");
				prepareStatement.setString(3, playerName);
				System.out.println("name set");
				prepareStatement.executeUpdate();
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static void main(String[] arg)
	{
		TestUpdate frame=new TestUpdate();
	}
}
