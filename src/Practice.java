import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */
  public <T> void printVertexVals(Vertex<T> vertex) {
    if (vertex == null) return;

    Set<Vertex<T>> visited = new HashSet<>(); // Set to track each vertice
    Stack<Vertex<T>> stack = new Stack<>(); // Stack to help traverse through the vertices without overflow issues
    
    stack.push(vertex); // The starting point of the traversal

    // While the stack is NOT empty, traverse through all the vertices
    while(!stack.isEmpty()) {
      Vertex<T> current = stack.pop(); // Setting a pointer for the current vertex 

      // If the current vertex isn't visited, we'll add to the Set of visited vertices
      if(!visited.contains(current)) {
        visited.add(current);
        
        System.out.println(current.data); // printing the current vertex

        // Push all the neighbors to the stack
        for (Vertex<T> neighbor : current.neighbors) {
          stack.push(neighbor);
        }
      }
    }
  }

  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {
    Set<Vertex<T>> visited = new HashSet<>(); // Set to store the reachable vertices
    if (vertex == null) return visited; // If null, return the visited vertex;
    
    Stack<Vertex<T>> stack = new Stack<>(); // Stack for DFS traversal
    stack.push(vertex); // Stack traversal from the given vertex

    // Continue to traverse through the reachable vertices
    while(!stack.isEmpty()) {
      // Get the next vertex, by setting a pointer
      Vertex<T> current = stack.pop();

      // if not visited add the next vertex
      if (!visited.contains(current)) {
        
        visited.add(current);

        // Push all the neighbors to the stack
        for (Vertex<T> neighbor : current.neighbors) {
          stack.push(neighbor);
        }
      }
    }

    return visited;
  }

  /**
   * Returns the maximum value among all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if vertex is null.
   */
  public int max(Vertex<Integer> vertex) {
    if (vertex == null) return Integer.MIN_VALUE; // Returning the minimum possible int

    Set<Vertex<Integer>> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();

    stack.push(vertex);

    int maxValue = Integer.MIN_VALUE; // Setting the smallest possible int as the max value

    // Traverse through all the reachable vertices
    while(!stack.isEmpty()) { 
      Vertex<Integer> current = stack.pop(); // get the next vertex

      // If not visited, add the current to the set
      if (!visited.contains(current)) {
        visited.add(current);

        // update the max value
        maxValue = Math.max(maxValue, current.data);

        // Push all the neighbors on the stack
        for (Vertex<Integer> neighbor : current.neighbors) {
          stack.push(neighbor);
        }
      }
    }
    
    return maxValue; // return the maximum value found
  }
  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    Set<Vertex<T>> leaves = new HashSet<>();

    if (vertex == null) return leaves;
    if (vertex.neighbors.isEmpty()) { leaves.add(vertex); return leaves; }

    Set<Vertex<T>> visited = new HashSet<>(); // Set to track each vertice
    Stack<Vertex<T>> stack = new Stack<>(); // Stack to help traverse through the vertices without overflow issues
    
    stack.push(vertex); // The starting point of the traversal

    // While the stack is NOT empty, traverse through all the vertices
    while(!stack.isEmpty()) {
      Vertex<T> current = stack.pop(); // Setting a pointer for the current vertex 

      // If the current vertex isn't visited, we'll add to the Set of visited vertices
      if(!visited.contains(current)) {
        visited.add(current);
        
        if(current.neighbors.isEmpty()) {
          leaves.add(current);
        }

        // Push all the neighbors to the stack
        for (Vertex<T> neighbor : current.neighbors) {
          stack.push(neighbor);
        }
      }
    }
    return leaves;
  }

  /**
   * Determines whether there exists a strictly increasing path from the given start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) {
    if (start == null || end == null) throw new NullPointerException(); // Throw this exception if the start or end are null
    
    Stack<Vertex<Integer>> stack = new Stack<>(); // create a stack for the traversal of DFS 
    stack.push(start);                            // push the starting vertice
    Set<Vertex<Integer>> visited = new HashSet<>(); // store the visited vertices

    // if the stack is not empty, traverse
    while(!stack.isEmpty()) {
      Vertex<Integer> current = stack.pop(); // pointer for the stack

      if (current == end) return true; // if the current path is strictly longer as the end return true
      
      // if the current visited doesn't contain the current vertice, add to the visited
      if (!visited.contains(current)) { 
        visited.add(current);

        // For each neighbor of the current pointer neighbors. Check if the not visited vertices contains the neighbor,
        // and the neighbor data is greater than the current data. Push that neighbor 
        for (Vertex<Integer> neighbor : current.neighbors) {
          if (!visited.contains(neighbor) && neighbor.data > current.data) {
            stack.push(neighbor);
          }
        }
      }
    }

    return false;
  }
}
