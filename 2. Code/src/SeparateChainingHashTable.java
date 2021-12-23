import java.util.LinkedList;
import java.util.List;


public class SeparateChainingHashTable<AnyType>
{
	private static final int DEFAULT_TABLE_SIZE = 101;
	int currentSize;
	private List<AnyType> [] theLists;

	public SeparateChainingHashTable()
	{
		this(DEFAULT_TABLE_SIZE);
	}
	public SeparateChainingHashTable(int size)
	{
		theLists=new LinkedList[size];
		for(int i=0; i<theLists.length; i++)
			theLists[i]=new LinkedList<AnyType>();
	}
	public void insert(AnyType x)
	{
		List<AnyType> whichList=theLists[myhash(x)];
		if(!whichList.contains(x))
		{
			whichList.add(x);
			if(++currentSize>theLists.length)
				rehash();
		}
	}
	public void remove(AnyType x)
	{
		List<AnyType> whichList=theLists[myhash(x)];
		whichList.remove(x);
		currentSize--;
	}
	public boolean isContained(AnyType x)
	{
		List<AnyType> whichList=theLists[myhash(x)];
		return whichList.contains(x);
	}
	public void makeEmpty()
	{
		for(int i=0;i<theLists.length;i++)
			theLists[i].clear();
		currentSize=0;
		
	}
	private void rehash()
	{
		List<AnyType>[] oldLists=theLists;
		theLists=new List[2*theLists.length];
		for(int j=0;j<theLists.length;j++)
			theLists[j]=new LinkedList<AnyType>();
		currentSize=0;
		for(int i=0;i<oldLists.length;i++)
			for(AnyType item:oldLists[i])
				insert(item);
	}
	public int myhash(AnyType x)
	{
		int hashVal=x.hashCode();
		hashVal%=theLists.length;
		if(hashVal<0)
			hashVal+=theLists.length;
		return hashVal;
	}
}
