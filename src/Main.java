// Vartan Artyunyan
// Martikelnummer 5120007




public class Main {

	public static void main(String[] args) {
		
		
		
		
		Trie trie = new Trie();
		
		trie.insert("wermann");
		trie.insert("werfrau");
		trie.insert("werwolf");
		trie.insert("vartan");
		
		trie.print();
		
		System.out.println(trie.search("wermann"));
		
		trie.remove("wermann");
		
		trie.print();
		
		BinaryTree baum = new BinaryTree();
		
		
		
		
		baum.add(1);
		System.out.println("__________________________");
		baum.add(1);
		System.out.println("__________________________");
		baum.add(2);
		System.out.println("__________________________");
		baum.add(4);
		System.out.println("__________________________");
		baum.add(3);
		System.out.println("__________________________");
		baum.add(5);
		System.out.println("__________________________");
		baum.add(7);
		System.out.println("__________________________");
		baum.add(0);
		System.out.println("__________________________");
		
		baum.remove(5);
		System.out.println("__________________________");
		baum.remove(5);
		System.out.println("__________________________");
		baum.remove(1);
		System.out.println("__________________________");
		baum.remove(1);
		System.out.println("__________________________");
	}

}
