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
		// Assignment 2
		//create filewriter, saves total number of edges for each node to data_for_histogram.txt
		//used in make_histogram.py to create the histogram.
		try {
			System.out.println("Creating data_for_histogram.txt...");// for showing process.
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
	
	private static void DFS_check_cirkle(int vertex, boolean[] visited, ArrayList<ArrayList<Integer>> the_graph, int connected_pointer, ArrayList<ArrayList<Integer>> connected){
        // Mark the current vertex as visited and store all adjacent vertes in same circle in the same arraylist inside arraylist.
		
        visited[vertex] = true;
        connected.get(connected_pointer).add(vertex);
        for (int neighbor : the_graph.get(vertex)) {
            if (!visited[neighbor])
                DFS_check_cirkle(neighbor, visited, the_graph, connected_pointer, connected);
        }
    }
	
    private static void connectedComponents(int total_V, ArrayList<ArrayList<Integer>> the_graph){
    	
		// Mark all the vertices as not visited
        boolean[] visited = new boolean[total_V];
        
        // Create Arraylist of arraylist, each inner arraylist will contain a circle.
        ArrayList<ArrayList<Integer>> connected = new ArrayList<ArrayList<Integer>>();
        
        int connected_pointer = 0;//index pointer at outer arraylist
        
        int over_3 = 0;//counter for assignment 3.
        int over_3_and_cliques = 0;//counter for assignment 4.
        
        
        for (int v = 0; v < total_V; v++) {
            if (!visited[v]) {
            	//for each not visited vertex, add first an inner arraylist to store circle.
            	connected.add(new ArrayList<Integer>());
            	//then call DFS method
                DFS_check_cirkle(v, visited, the_graph, connected_pointer, connected);
                //will come back when vertex went throw the circle, that means time for finding next circle.
                connected_pointer ++;
            }
        }
        for (int i = 0; i < connected.size()-1; i++) {
        	
        	if (connected.get(i).size() >= 3) {
        		over_3 ++;
        		for (int j =0; j < connected.get(i).size()-1; j++) {
            		if ( the_graph.get(connected.get(i).get(j)).size() != the_graph.get(connected.get(i).get(j+1)).size()) {
            			break;
            		}if (j +1 == connected.get(i).size()-1) {
            			over_3_and_cliques ++;
            		}
            	}
        	}
        	
        	
        }
        System.out.println("There are total of " + Integer.toString(over_3) + " circles that contains 3 or more vertexs.");
        double fraction =(double) over_3_and_cliques/over_3;
        System.out.println("And " + Integer.toString(over_3_and_cliques) + " of them are cliques, the fraction of them is " + fraction + ".");
        
    }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String last_line = sc.nextLine();
		String[] last_line_split = last_line.split(" ");
		int total_V = Integer.parseInt(last_line_split[4]);
		int total_E = Integer.parseInt(last_line_split[7]);
		
		System.out.println("There are total of " + total_V + " vertexes and  "+ total_E + " edges.");
		ArrayList<ArrayList<Integer>> the_graph = new ArrayList<ArrayList<Integer>>(total_V);
		System.out.println("Storing vertexes and edges to the graph...");
		
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
		
		//Show result of assignment 3 and 4 first, then do the histogram.
		connectedComponents(total_V, the_graph);
		data_for_histogram(the_graph);
		
		
	}

}
