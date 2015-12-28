import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class HospitalTour {
  private int V; // number of vertices in the graph (number of rooms in the hospital)
  private static int[][] AdjMatrix; // the graph (the hospital)
  private int[] RatingScore; // the weight of each vertex (rating score of each room)
  private static Vector <Integer> visited = new Vector <Integer> (8);

  public HospitalTour() {
  }
  
  public boolean CheckImportant(int j) {
	  visited = new Vector <Integer> (V);
      visited.addAll(Collections.nCopies(V, 0));
      
//	  System.out.println("" + visited.size());
 
	  visited.set(j, 1);
  	
  	DFSrec((j + 1) % V);
  	for (int i = 0; i < V ; i++)
  		if (visited.get(i) == 0 && i != j) {
//  		System.out.println("ERROR AT " + "-th/nd/st TESTCASE FROM END, GRAPH MUST BE CONNECTED");
  			return true;
  		}
  	return false;
  	} 

  private static void DFSrec(int u) {
	    visited.set(u, 1);	
	    for (int j = 0; j < AdjMatrix[u].length; j++)
	      if (AdjMatrix[u][j] > 0 && visited.get(j) == 0)
	        DFSrec(j);
	  }
  
  int Query() {
    int ans = -1;
    int temp = 100000;
 
    // You have to report the rating score of the important room (vertex)
    // with the lowest rating score in this hospital
    //
    // or report -1 if that hospital has no important room
    //
    // write your answer here
    
    for (int i = 0; i < V; i++) {
    if (CheckImportant(i)) {	
    	
    	if (RatingScore[i] < temp)
    	temp = RatingScore[i];
    	ans = temp;
    }
    }
    return ans;
  }

  void run() throws Exception {
    // for this PS3, you can alter this method as you see fit

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int TC = Integer.parseInt(br.readLine()); // there will be several test cases
    while (TC-- > 0) {
      br.readLine(); // ignore dummy blank line
      V = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
      RatingScore = new int[V];
      for (int i = 0; i < V; i++)
        RatingScore[i] = Integer.parseInt(st.nextToken());

      // clear the graph and read in a new graph as Adjacency Matrix
      AdjMatrix = new int[V][V];
      for (int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
          int j = Integer.parseInt(st.nextToken());
          AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
        }
      }
      

      pr.println(Query());
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    HospitalTour ps3 = new HospitalTour();
    ps3.run();
  }
}



class IntegerPair implements Comparable < IntegerPair > {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}
