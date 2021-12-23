import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class testDB {
	private static long radNo=110910;
	public static void main(String[] args) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/entries","root","root");
			System.out.println("Database connected");
			String query1="Select distinct word from full_dictionary where word like 'z%'";
			PreparedStatement preparedStatement1=con.prepareStatement(query1);
			ResultSet rs=preparedStatement1.executeQuery();
			PreparedStatement preparedStatement2; 
			String noun=null;
			int len=0;
			String query2=null;
			while(rs.next())
			{
				noun=rs.getString(1);
				len=noun.length();
				char lastchar=noun.charAt(noun.length()-1);
				if(len>1 && lastchar!='1' && lastchar!='2' && lastchar!='3' && lastchar!='4' && lastchar!='5' && lastchar!='6' && lastchar!='7' && lastchar!='8' && lastchar!='9')
				{
					++radNo;
					query2="insert into minithesis.dictionary (RandomNo, Words, Length) values (?,?,?)";
					preparedStatement2=con.prepareStatement(query2);
					preparedStatement2.setLong(1,radNo);
					preparedStatement2.setString(2,noun);
					preparedStatement2.setInt(3,len);
					preparedStatement2.executeUpdate();
				}
				
			}
			
			System.out.println("Successfully updated !");
			con.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
}
