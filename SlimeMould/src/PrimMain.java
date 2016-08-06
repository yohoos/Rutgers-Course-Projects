import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFrame;

public class PrimMain {

	public static void main(String[] args) throws FileNotFoundException {
		
		String fileName = askFile();
		int matrixSize = askMatrixSize();
		int source = askSource();
		int[][] matrix = readMatrix(fileName, matrixSize);
		int partitionNum = askPartition()/2;
		
		//Partitioning Phase //find the farthest points from within the recent two partitions from the source
		ArrayList<HashSet<Integer>> setOfSets = new ArrayList<HashSet<Integer>>();
		HashSet<Integer> used = new HashSet<Integer>();
		partition(setOfSets, source, used);
		HashSet<Integer> intersect = new HashSet<Integer>(); //keeps intersection of sets so far
		intersect.addAll(setOfSets.get(0)); //copies first set partition in order to get at least the whole MST in output
		intersect.retainAll(setOfSets.get(1));
		intersect.retainAll(setOfSets.get(2));
		for (int i=1; i<partitionNum; i++) {
			intersect.removeAll(used);
			partition(setOfSets, source, intersect, used);
			intersect.clear();
			intersect.addAll(setOfSets.get(0));
			for (int j = i*2+1; j<setOfSets.size(); j++) {
				intersect.retainAll(setOfSets.get(j));
			}
		}

		//combines all the mini-MSTs to make final graph
		HashSet<Edge> combine = new HashSet<Edge>();
		for (HashSet<Integer> x: setOfSets) {
			Queue<Edge> q = makeMST(fileName, matrix, x, source);
//			Point2D[] points = points(matrixSize); //only if you want to draw the graph of each partition
//			Iterable<Line2D> list = makeLine(q, points);
//			drawGraph(list, points);
			for (Edge y: q) {
				combine.add(y);
			}
		}
		//adjacency matrix to be used for bridges analysis
		//also calculates mean distance here
		double[][] resultMatrix = new double[matrixSize][matrixSize];
		for (Edge x: combine) {
			int v = x.either();
			int w = x.other(v);
			resultMatrix[v][w] = x.weight();
		}
		Graph bridgeGraph = new Graph(resultMatrix);
		Bridge bridges = new Bridge(bridgeGraph);
		System.out.println("The number of bridge edges is: " + bridges.components() + " out of " +bridgeGraph.E()+ " edges.");
		
		
		double totalWeight = 0;
		for (Edge x: combine) {
			totalWeight += x.weight();
		}
		System.out.println("Total Weight of Graph is: " + totalWeight);
		System.out.println("The number of partitions is: " +combine.size());
		
		System.out.println("The mean distance of an edge is: " + totalWeight/combine.size());
		
		Point2D[] points = points(matrixSize);
		Iterable<Line2D> list = makeLine(combine, points);
		drawGraph(list, points);
		
	}
	
	private static void printEdges(HashSet<Edge> combine) throws FileNotFoundException { 
		//This produces a txt output of the resulting MST... may not be necessary
		
		PrintWriter output = new PrintWriter("output.txt");
		for (Edge x: combine) {
			output.println(x);
		}
		output.close();
	}

	private static int askPartition() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the number of partitions you want (multiple of 2s)...");
		int partition = input.nextInt();
		return partition;
	}

	private static void partition(ArrayList<HashSet<Integer>> setOfSets, int source, HashSet<Integer> intersect, HashSet<Integer> used) {
		EdgeWeightedDigraph EG = new EdgeWeightedDigraph(new In("directedList.txt")); //directedList.txt should be formatted by now
		DijkstraSP dij = new DijkstraSP(EG, source); //we use dijkstra's to find distances because not all inputs are complete graphs
		int furthest = furthestFromSource(dij, EG.V(), intersect);
		DijkstraSP dij2 = new DijkstraSP(EG, furthest);
		int furthest2 = furthestFromSource(dij2, EG.V(), intersect);
		DijkstraSP dij3 = new DijkstraSP(EG, furthest2);
		used.add(furthest); used.add(furthest2); //updates used
		double partition = dij2.distTo(source);
		double midPart = partition/2;
		double partition2 = dij3.distTo(source);
		double midPart2 = partition2/2;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i=0; i<EG.V(); i++) {
			if (dij2.distTo(i) < partition + midPart2) { //furthest one to source plus a bit more get overlap
				set.add(i);
			}
		}
		HashSet<Integer> set2 = new HashSet<Integer>();
		for (int i=0; i<EG.V(); i++) {
			if (dij3.distTo(i) < partition2 + midPart) { //furthest one to source plus a bit more get overlap
				set2.add(i);
			}
		}
		setOfSets.add(set);
		setOfSets.add(set2);
	}

	private static void partition(ArrayList<HashSet<Integer>> setOfSets, int source, HashSet<Integer> used) {
		EdgeWeightedDigraph EG = new EdgeWeightedDigraph(new In("directedList.txt")); //directedList.txt should be formatted by now
		DijkstraSP dij = new DijkstraSP(EG, source); //we use dijkstra's to find distances because not all inputs are complete graphs
		int furthest = furthestFromSource(dij, EG.V());
		DijkstraSP dij2 = new DijkstraSP(EG, furthest);
		int furthest2 = furthestFromSource(dij2, EG.V());
		DijkstraSP dij3 = new DijkstraSP(EG, furthest2);
		used.add(furthest); used.add(furthest2); //updates used
		//using these two dij MSTs we can find vertices that are a certain distance away and remove those outside this distance
		double partition = dij2.distTo(source);
		double midPart = partition/2; //parameters for overlap
		double partition2 = dij3.distTo(source);
		double midPart2 = partition2/2; //parameters for overlap
		HashSet<Integer> orig = new HashSet<Integer>();
		for (int i=0; i<EG.V(); i++) {
			orig.add(i);
		}
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i=0; i<EG.V(); i++) {
			if (dij2.distTo(i) < partition + midPart2) { //furthest one to source plus a bit more get overlap
				set.add(i);
			}
		}
		HashSet<Integer> set2 = new HashSet<Integer>();
		for (int i=0; i<EG.V(); i++) {
			if (dij3.distTo(i) < partition2 + midPart) { //furthest one to source plus a bit more get overlap
				set2.add(i);
			}
		}
		setOfSets.add(orig);
		setOfSets.add(set);
		setOfSets.add(set2);
	}

	private static int askMatrixSize() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the number of vertices of graph/dimension of matrix...");
		int vertex = input.nextInt();
		return vertex;
	}


	private static String askFile() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter filename...");
		String fileName = input.next();
		return fileName;
	}


	private synchronized static Queue<Edge> makeMST(String fileName, int[][] matrix, HashSet<Integer> set, int source) throws FileNotFoundException {
		int[][] copyMatrix = new int[matrix.length][matrix.length]; //default value is 0 which is helpful
		for (int i=0; i<matrix.length; i++) {
			if (!set.contains(i)) continue;
			for (int j=0; j<matrix.length; j++) {
				if (!set.contains(j)) {
					continue;
				} else {
					copyMatrix[i][j] = matrix[i][j];
				}
			}
		}
		EdgeWeightedGraph G = new EdgeWeightedGraph(copyMatrix, matrix.length);
		CustomPrim prim = new CustomPrim(G, source);
		Queue<Edge> adj = (Queue<Edge>) prim.edges();
		return adj;
	}


	private static int askSource() {
		System.out.println("Which node is the source for the graph? (Using 0-based indexing)");
		Scanner input = new Scanner(System.in);
		int source = input.nextInt();
		return source;
	}

	private static int furthestFromSource(DijkstraSP dij, int V, HashSet<Integer> intersect) {
		int maxIndex = 0;
		double max = 0;
		for (int i: intersect) {
			if (dij.distTo(i) > max) {
				maxIndex = i;
				max = dij.distTo(i);
			}
		}
		return maxIndex;
	}

	private static int furthestFromSource(DijkstraSP dij, int V) {
		int maxIndex = 0;
		double max = 0;
		for (int i=0; i<V; i++) {
			if (dij.distTo(i) > max) {
				maxIndex = i;
				max = dij.distTo(i);
			}
		}
		return maxIndex;
	}

	private static void drawGraph(Iterable<Line2D> list, Point2D[] points) {
		JFrame frame = new JFrame();
		Draw panel = new Draw(list, points);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.add(panel);
		frame.setVisible(true);
	}

	private static int[][] readMatrix(String fileName, int matrixSize) throws FileNotFoundException {
		Scanner input = new Scanner(new File(fileName));
		Matrix2List parse = new Matrix2List(input, matrixSize); //formats directedList.txt
		return parse.getMatrix();
	}
	
	private static void readMatrix(String fileName, int matrixSize, HashSet<Integer> set) throws FileNotFoundException { //formats the weightedList.txt
		Scanner input = new Scanner(new File(fileName));
		Matrix2List parse = new Matrix2List(input, matrixSize, set); //formats a list of edges
	}

	private static Iterable<Line2D> makeLine(Iterable<Edge> adj, Point2D[] points) {
    	LinkedList<Line2D> lines = new LinkedList<Line2D>();
    	for (Edge x: adj) {
    		int v = x.either();
    		int w = x.other(v);
    		Line2D line = new Line2D.Double(points[v], points[w]);
    		lines.add(line);
    	}
    	return lines;
	}
	
	private static Point2D[] points(int vertices) throws FileNotFoundException {
		Scanner input = new Scanner(new File("coordinates.txt")); //takes in file with coordinates in order
    	Point2D[] points = new Point2D[vertices];
    	int count = 0;
    	
    	while (input.hasNext()) {
    		double x = 3*input.nextDouble(); //multiplier of 3 to expand output graph size
    		double y = 3*input.nextDouble();
    		points[count] = new Point2D.Double(x, y);
    		count++;
    	}
    	input.close();
    	
    	return points;
	}
}
