import java.util.ArrayList;
//import java.util.Collection;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Graph_from_converted {
	

	private static void addEdge(ArrayList<ArrayList<Integer>> the_graph, int a, int b) {
	    the_graph.get(a).add(b);
	    the_graph.get(b).add(a);
	}
	
	private static void data_for_histogram(ArrayList<ArrayList<Integer>> the_graph) {
		//create filewriter, saves total number of edges for each node to data_for_histogram.txt
		//used in make_histogram.py to create the histogram.
		try {
		      FileWriter myWriter = new FileWriter("data_for_histogram.txt");
		      for (int i = 0; i < the_graph.size()-1; i++) {
					myWriter.write(the_graph.get(i).size()+",");
		      }
		      myWriter.write(Integer.toString(the_graph.get(the_graph.size()-1).size()));
		      myWriter.close();
		      System.out.println("Successfully wrote to file data_for_histogram.txt.");
		    } catch (IOException e) {
		      System.out.println("An error occurred during creation for data_for_histogram at Graph_from_converted.java.");
		      e.printStackTrace();
		    }
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String last_line = sc.nextLine();
		String[] last_line_split = last_line.split(" ");
		int total_V = Integer.parseInt(last_line_split[4]);
		int total_E = Integer.parseInt(last_line_split[7]);
		
		//System.out.println(total_V + " | " + total_E);
		ArrayList<ArrayList<Integer>> the_graph = new ArrayList<ArrayList<Integer>>(total_V);
		
		for (int i = 0; i < total_V; i++) {
		      the_graph.add(new ArrayList<Integer>());
		}
		
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] line_split = line.split(" ");
			
			int a = Integer.parseInt(line_split[0]);
			int b = Integer.parseInt(line_split[1]);
			int True_or_False = Integer.parseInt(line_split[2]);

			
			if(True_or_False == 1) {
				addEdge(the_graph, a, b);
			}
		}sc.close();
		
		System.out.println(" All  nodes and edges added.");
		System.out.println("The size of graph is " + the_graph.size());
		
		data_for_histogram(the_graph);
		
		
		//System.out.println("There are " + the_graph.get(0).size() + " edges for vertex 0, and they are : ");
		//for (int i = 0; i < the_graph.get(0).size(); i++) {
		//	System.out.print(the_graph.get(0).get(i)+ ", ");
		//}
		
	}

}
