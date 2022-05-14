import java.util.Scanner;
import java.util.HashMap;

public class Dataconvert {
	/*"Dataconvert" should be used for creating new data file, irrelevant columns to this project will be removed.
	 * example : "cat Spruce_fingerprint_2017-03-10_16.48.olp.m4 | java -jar  Dataconvert.jar > converted.txt".
	 * First and second columns in converted.txt will be corresponding identifiers parsed to integer,
	 * third columns will be 0 if sequence overlapp do not reach at least 1000, if reached, third columns will be 1 instead,
	 * converted.txt will also have 1 aditional line taht stores value for total vertexes and total edges.
	 */

	public static void main(String[] args) {
		//creat pointer and counter
		int vertex_pointer = 0;
		int edge_count = 0;
		
		//new Hashmap with identifiers as key and vertex_pointer as value, (i.e parse ids from string to integer, started with 0).
		HashMap<String, Integer> Graph_map = new HashMap<String, Integer>();
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] line_split = line.split("\\s");
			
			String First_id = line_split[0];
			String Second_id = line_split[1];
			int First_overlapp = (Integer.parseInt(line_split[6])-Integer.parseInt(line_split[5]));
			int Second_overlapp = (Integer.parseInt(line_split[10])-Integer.parseInt(line_split[9]));
			
			//as mentioned above, will be stored in the third columns after convert, 0 for without edge in between and 1 for with edge.
			int True_or_False = 0;
			
			//as mentioned above, Str id as key, int id as value, when ever reach a new Str id, pointer +1.
			if(!Graph_map.containsKey(First_id)) {
				Graph_map.put(First_id, vertex_pointer);
				vertex_pointer += 1;
			}
			if(!Graph_map.containsKey(Second_id)) {
				Graph_map.put(Second_id, vertex_pointer);
				vertex_pointer += 1;
			}
			
			//overlap is "sufficiently large" if it has length at least 1000 in both sequences.
			if (First_overlapp >= 1000 && Second_overlapp >= 1000) {
				True_or_False = 1;
				edge_count += 1;
			}
			System.out.println(Graph_map.get(First_id) + " " + Graph_map.get(Second_id) + " " + True_or_False);
		}
		sc.close();
		
		//The extra line.
		System.out.println("There are total of " + vertex_pointer + " vertexes and " + edge_count +  " edges." );
	}

}
