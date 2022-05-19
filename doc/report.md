###The repository will contain :

1. A doc folder that contains this projectreport and the lab notebook.

2. "Dataconvert.java" and "Dataconvert.jar", that takes the original "Spruce_fingerprint_2017-03-10_16.48.olp.m4" file and create a "converted.txt" file.
	During process, the unrelated columns will be ignored and wont be stored, the identifiers will be converted to integers from 0 to the total number of vertexes -1.
	Each line in "converted.txt" will contain 3 columns, first and second colums are integer identifiers, and the third columns stores whither there is an edge between them (0 for no edge and 1 for with edge).
	The last line will be a simple string that shows the total vertexes and counted edges, which solves the first assignment.

3. "Graph_from_converted.java" and "G_f_c.jar" will read from "converted.txt" instead of the original ".m4" file, based on vertexes and edges, creat an undirected Graph.
	A "Data_for_histogram.txt" will be created for later use to solve assignment 2, which contains node degree using comma as seperator (first number stands for the node degree for id0 and second for id1 etc.)
	Methods inside "Graph_from_converted.java" will also solve assignment 3 and 4.

4. A python script "make_histogram.py" that reads from "Data_for_histogram.txt" that was created earlier and shows the histogram for assignment 2, the python script will also save it as "Node_degree_histogram.png"

5. Finally "bash.txt" which should be bashed in unix to perform all above functions.

###Algorithm details :

Dataconvert : The convert process is based on creating a HashMap, where the original identifiers will be stored as String type keys and coresponse integer from 0 as values.
	For each line in the original ".m4" file, "First_id" and "Second_id" will be checked using ".containsKey()" method, if they havent be stored yet, they will be created with ".put()" method.
	An edge in between exist or not will be based on the number of overlapp.
	For every ".containsKey()" and ".put()" the time complexity is O(1), the space comlexity for hashMap is O(n) where n will total of new vertexs need to be stored in each line.
	In this case a total of 11393435 vertexes are being stored to the HashMap so the space complexity should be O(11393435).
	There are also a total of 64056772 lines in ".m4" file, so the averege time complexity should be O(64056772).

Biuld Graph : The idea behind is to creat an adjecency list, an ArrayList that contains ArrayList of integer.
	I choosed Adjecency List over Adjecency Matrix was that space complexity for Adjecency Matrix is O(V^2), in our case which is very large.
	Adjecency istead will take O(V+E) in average case.
	Time complexity for adjecency list is also in over favor, for adding a vertex or an edges is O(1) time, and we dont really need to remove an edge in our case.
	
	An ArrayList "the_graph" will be created at Main method in "Graph_from_converted.java", this size for the arraylist will be same as the number of total vertexes, and int id for vertexes will also be their index.
	At each index there will be an empty ArrayList with integer, whenever an edge is detected (third columns in "converted.txt"), addEdge method will be called and store int id of one vetex to another for both vetexes.
	Time complexity for adding vertex and edges to graph should be O(63962895) and space complexity will be O(11393435+63962895).
	
Traverse throw Graph : Algorithm used here is Depth first search, we start from int id 0 and mark it as visited, using a recursive call we check all neighbors of 0 and mark called vetexes as visited, i.e all vertexes that has edge with 0, and with recursive call we will also check all neighbors to those vertexes and so on, till we have no more connected components to 0.
	We now have a cirkle or a connected componnent of graph, we goes to next not yet visited vertex and start there instead.

	With method connedctedComponents(), a boolean list "visited" with size of total vertexes will be created, and they are false by default, we will then creat a ArrayList "connected" that contains connected componnets as an arraylist inside.
	Each time we use dfs to traverse in the graph, we will add the conncted componnents of graph to "connected". We could then check there size to solve assignment 3.
	Space complexity for dfs is O(1139345) because we need a list to store visited, and time complexity should be O(1139345+63972895), beacause we need to visit each vertex and edge once.
	
	For assignment 4 we need to check for all connectedComponents with at least 3 vertexes, which of them are all connected to each other, that means a vetex should be conect to all other vertexes in the same componentes, which means they should all share the same node degree, which is stored in Arraylist "the_graph" as the size for vetex int id.

###Uses:
Download everything in repository and throw in ".m4" file under same directory, then run "$ bash bash.txt", if everything worked out fine, the result should be:

"
There are total of 11393435 vertexes and  63962895 edges.
Storing vertexes and edges to the graph...
 All  nodes and edges added.
There are total of 273187 circles that contains 3 or more vertexs.
And 35750 of them are cliques, the fraction of them is 0.1308627423706106.
Creating data_for_histogram.txt...
Successfully wrote to file data_for_histogram.txt.
Creating histogram...
The histogram is saved as Node_degree_histogram.png.
"
Additionaly, "converted.txt" and "data_for_histogram.txt" will be created and will take around 1.1 Gb.
"Node_degree_histogram.png" will also be created to show ploted diagram in case the one in unix didnt show up.
