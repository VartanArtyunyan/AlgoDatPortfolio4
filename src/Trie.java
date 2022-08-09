// Vartan Artyunyan
// Martikelnummer 5120007


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Stack;

public class Trie {

	Knoten root = new Knoten(':');
	
	
	class Knoten {
		 HashMap<Character, Knoten> children;
		 char inhalt;
		 boolean wortEnde;
		 
		 public Knoten(char c) {
			 inhalt = c;
			 wortEnde=false;
			 children = new HashMap<>();
		 }
		 

		 
		 public void Print(String abstand, boolean last)
		   {
			 String indent = abstand;
		     System.out.print(indent);
		       if (last){
		    	   System.out.print("\\-");
		           indent = indent +  "  ";
		       }
		       else{
		    	   System.out.print("|-");
		           indent = indent + "| ";
		       }
		       System.out.println(inhalt);
		       
		       Iterator<Entry<Character, Knoten>> iterator = children.entrySet().iterator();
		       while (iterator.hasNext()) {
		    	   Entry<Character,Knoten> e = iterator.next();
		    	   Knoten k = e.getValue();
		    	   
		           if (iterator.hasNext()) {
		        	   k.Print(indent, false);
		           }
		           else k.Print(indent, true);
		        }
		       
		   
		  
		       
		       

		   }
	}
	
	public void insert(String wort) {
		
		char[] wortArray = wort.toCharArray();
		int länge = wort.length();
		Knoten currentKnoten = root;
		
		for(int i = 0; i < länge; i++) {
		char currentBuchstabe = wortArray[i];
		Knoten neuerKnoten = new Knoten(currentBuchstabe);
			if(!currentKnoten.children.containsKey(currentBuchstabe)) {
				currentKnoten.children.put(currentBuchstabe, neuerKnoten);
			}
			currentKnoten = currentKnoten.children.get(currentBuchstabe);
		}
		currentKnoten.wortEnde = true;
	}
	
	 boolean search(String wort)
    {
		char[] wortArray = wort.toCharArray();
		int länge = wort.length();
		Knoten currentKnoten = root;
      
        for (int i = 0; i < länge; i++)
        {
        	char currentBuchstabe = wortArray[i];
      
            if (!currentKnoten.children.containsKey(currentBuchstabe))
                return false;
      
            currentKnoten = currentKnoten.children.get(currentBuchstabe);
        }
      
        return currentKnoten.wortEnde;
    }
	 
	 
	 
	  void remove(String wort) {
		  char[] wortArray = wort.toCharArray();
		  int länge = wort.length();
		  Stack<Knoten> stack = new Stack<>();
		  Knoten currentKnoten = root;
		  for(int i = 0; i < länge; i++) {
			  char currentBuchstabe = wortArray[i];
			  if(!currentKnoten.children.containsKey(currentBuchstabe)) return;
			  stack.push(currentKnoten);
			  currentKnoten = currentKnoten.children.get(currentBuchstabe);
		  }
		  
		  if(currentKnoten.wortEnde && currentKnoten.children.size() > 1) currentKnoten.wortEnde = false;
		  else {
			  while(currentKnoten.children.size() < 2) {
				  currentKnoten = stack.pop();
				  länge--;
			  }
			  currentKnoten.children.remove(wortArray[länge]);
		  }
		  
	  }
	  
	  public void print() {
		  root.Print(" ", true);
	  }
	
	
	
	
	
	
}

