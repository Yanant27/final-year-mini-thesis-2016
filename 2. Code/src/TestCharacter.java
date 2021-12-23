
public class TestCharacter {
	public static void main(String[] arg)
	{
		int a='a',z='z';
		System.out.println("a "+a);
		System.out.println("z "+z);
		a='A';
		z='Z';
		System.out.println("A "+a);
		System.out.println("Z "+z);
		char c1='b',c2='d',c3='e';
		if(c1>'a' && c1<'c')
			System.out.println("1 : "+c1);
		if(c2>'b')
			System.out.println("2 : "+c2);
	}
}
