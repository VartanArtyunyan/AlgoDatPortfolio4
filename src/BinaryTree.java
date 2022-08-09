// Vartan Artyunyan
// Martikelnummer 5120007


import java.util.ArrayList;

public class BinaryTree {

	Knoten root;
	
	class Knoten {
		Knoten left;
		Knoten right;
		int wert;

		public Knoten(int wert) {
			this.wert = wert;
		}

		public boolean hasLeft() {
			return left != null;
		}

		public boolean hasRight() {
			return right != null;
		}

		public void print() {
			System.out.println(wert);
		}
	}

	public void add(int wert) {
		Knoten neuerKnoten = new Knoten(wert);

		if (root == null) {
			root = neuerKnoten;
			printTree();
			return;
		}

		Knoten currentent = root;
		boolean run = true;
		while (run) {
			if (wert == currentent.wert) {
				printTree();
				return;}
			if (wert < currentent.wert) {
				if (currentent.hasLeft()) {
					currentent = currentent.left;
				} else {
					currentent.left = neuerKnoten;
					run = false;
				}
			} else {
				if (currentent.hasRight()) {
					currentent = currentent.right;
				} else {
					currentent.right = neuerKnoten;
					run = false;
				}
			}

		}
		printTree();
	}

	
	
	
	public void remove(int wert) {
		Knoten current = root;
		Knoten vorher = null;

		while (current != null && current.wert != wert) {

			vorher = current;

			if (wert < current.wert) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		if (current == null) {
			System.out.println("zahl ist nicht im baum enthalten");
			return;
		}

		if (current.left == null && current.right == null) {

			if (current != root) {
				if (vorher.left == current) {
					vorher.left = null;
				} else {
					vorher.right = null;
				}
			}

			else {
				root = null;
			}
		}

		else if (current.left != null && current.right != null) {

			Knoten temp = current.right;
			while (temp.left != null) {
				temp = temp.left;
			}
			Knoten successor = temp;
			int nachvolgerwert = successor.wert;

			remove(successor.wert);

			current.wert = nachvolgerwert;
		}

		else {
			Knoten nach;
			if (current.left != null)
				nach = current.left;
			else
				nach = current.right;
			if (current != root) {
				if (current == vorher.left) {
					vorher.left = nach;
				} else {
					vorher.right = nach;
				}
			} else {
				root = nach;
			}
		}
		printTree();
		return;
	}

	
	
	
	
	// alles ab hier ist nur fürs printen des Baums

	public int maxEbene(ArrayList<Integer[]> array) {
		int max = 0;
		for (Integer[] i : array) {
			if (i[0] > max)
				max = i[0];
		}
		return max;
	}

	public void printTree() {
		
		if(root.right == null && root.left == null) {
			System.out.println(root.wert);
			return;
		}

		ArrayList<Integer[]> array = new ArrayList<Integer[]>();
		getKnots(root, 0, 0, array);
		int maxEbene = maxEbene(array);
		int höhe = maxEbene + 1;
		int breite = (2 * ((int) (Math.pow(2, maxEbene)))+1);

		String[][] outputarray = new String[(maxEbene + 1)][breite];

		for (Integer[] i : array) {
			int reihe = i[0];

			int abstand = (int) Math.pow(2, (höhe - reihe));
			int start = (int) Math.pow(2, (maxEbene - reihe));
			int spalte = start + (i[1] * abstand);

			// int korrigierteReihe = reihe + (reihe* (höhe - reihe + 5));
			int korrigierteReihe = reihe + ((höhe - reihe + maxEbene) * reihe);
			outputarray[reihe][spalte] = i[2] + "";

		}

		for (int i = 0; i < outputarray.length; i++) {
			ArrayList<Integer> nodePos = new ArrayList<>();
			for (int j = 0; j < outputarray[i].length; j++) {
				if (outputarray[i][j] == null)
					outputarray[i][j] = " ";
				else
					nodePos.add(j);
				System.out.print(outputarray[i][j]);
			}
			System.out.println();
			printLines(höhe / (i + 1), breite, nodePos);

		}
		System.out.println("__________________________");
	}

	public void printLines(int hr, int wr, ArrayList<Integer> nodePos) {
		int h = hr;
		int w = wr;
		
		for (int i = 0; i < h; i++) {
			String line = "";
			for (int j = 0; j < w; j++) {
				if (nodePos.contains(j + i + 1))
					line = line + "/";
				else if (nodePos.contains(j - i - 1))
					line = line + "\\";
			
				else
					line = line + " ";
			}
			System.out.println(line);
		}
	}

	private void getKnots(Knoten start, int ebene, int pos, ArrayList<Integer[]> array) {

		Knoten right = start.right;

		if (right != null) {
			getKnots(right, (ebene + 1), ((pos * 2) + 1), array);
		}

		array.add(new Integer[] { ebene, pos, start.wert });

		Knoten left = start.left;

		if (left != null) {
			getKnots(left, (ebene + 1), (pos * 2), array);
		}

	}
}


