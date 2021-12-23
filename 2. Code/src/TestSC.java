
public class TestSC {
	public static void main(String[] args) {
		SeparateChainingHashTable<String> H=new SeparateChainingHashTable<String>(2);
		/*H.insert("apple");
		H.insert("orange");
		//same object will not be inserted into table
		H.insert("apple");
		H.insert("orange");
		System.out.println(H.isContained("orange"));
		//apple can get positive hash code !
		System.out.println("apple".hashCode());
		//orange can get negative hash code !
		System.out.println("orange".hashCode());
		//hash code can change when the word order changes 
		System.out.println("earnog".hashCode());
		System.out.println("gonrea".hashCode());
		System.out.println("rgenao".hashCode());
		System.out.println(H.currentSize);*/
		
		H.insert("apple");
		H.insert("orange");
		H.insert("orchid");
		//H.insert("mango");
		//H.insert("stream");
		//H.insert("human");
		//H.insert("earth");
		//H.insert("computer");
		System.out.println("Apple "+String.valueOf(H.myhash("apple")));
		System.out.println("Orchid hashcode "+"orchid".hashCode());
		System.out.println("Orange "+String.valueOf(H.myhash("orange")));
		System.out.println("Orchid "+String.valueOf(H.myhash("orchid")));
		//System.out.println("Mango"+String.valueOf(H.myhash("mango")));
		//System.out.println("Stream "+String.valueOf(H.myhash("stream")));
		//System.out.println("Human "+String.valueOf(H.myhash("human")));
		//System.out.println("Earth "+String.valueOf(H.myhash("earth")));
		//System.out.println("Computer "+String.valueOf(H.myhash("computer")));
		
	}

}
