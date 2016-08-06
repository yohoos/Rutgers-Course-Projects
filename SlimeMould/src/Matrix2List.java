import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Matrix2List {
	private int[][] matrix;

	public Matrix2List(Scanner file, int d) throws FileNotFoundException { 		

		this.matrix = new int[d][d];
		for (int i=0; i<d; i++) { //copy in the matrix
			for (int j=0; j<d; j++) {
				if (file.hasNextInt()) {
					this.matrix[i][j] = file.nextInt();
				} else {
					this.matrix[i][j] = 0;
				}
			}
		}
		
		//makes each edge directed
		PrintWriter output = new PrintWriter("directedList.txt");
		output.print(d +" ");
		output.println((d*d - d)); //max # of edges -> should always be whole number
		output.println();
		for (int i=0; i<d; i++) {
			for (int j=0; j<d; j++) {
				if (i != j) {
					output.println(i +" "+ j +" "+ this.matrix[i][j]); //prints out coordinates and value
				}
			}
		}
		output.close();
	}
	
	public Matrix2List(Scanner file, int d, HashSet<Integer> set) throws FileNotFoundException { 		

		this.matrix = new int[d][d];
		for (int i=0; i<d; i++) { //copy in the matrix
			for (int j=0; j<d; j++) {
				if (file.hasNextInt()) {
					this.matrix[i][j] = file.nextInt();
				} else {
					this.matrix[i][j] = 0;
				}
			}
		}
		
		//turn the matrix into weighted adjacency list
		PrintWriter output = new PrintWriter("weightedList.txt");
		output.print(d +" ");
		output.println((set.size()*set.size() - set.size())/2); //max # of edges -> should always be whole number
		output.println();
		for (int i=0; i<d; i++) {
			for (int j=i+1; j<d; j++) {
				if (set.contains(i) && set.contains(j)) {
					output.println(i +" "+ j +" "+ this.matrix[i][j]); //prints out coordinates and value
				}
			}
		}
		output.close();
	}
		
	public int[][] getMatrix() {
		return this.matrix;
	}
//	public static void main(String[] args) throws FileNotFoundException { //test
//		Scanner sfile = new Scanner(new File("matrix.txt"));
//		Matrix2List parse = new Matrix2List(sfile, 33); //creates adjacency list in adjList.txt
//	}

}
