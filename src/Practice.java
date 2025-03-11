import java.util.HashSet;
import java.util.Set;

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
    printVertexVals(vertex, new HashSet<Vertex<T>>());
  }

  private <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited){
    if (vertex == null || !visited.add(vertex)) return;

    System.out.println(vertex.data);
    for (var neighbor : vertex.neighbors){
      printVertexVals(neighbor, visited);
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
    return reachable(vertex, new HashSet<Vertex<T>>());
  }

  private <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> reached){
    if (vertex == null || !reached.add(vertex)) return reached;

    for (var neighbor : vertex.neighbors){
      reached = reachable(neighbor, reached);
    }

    return reached;
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
    int max = Integer.MIN_VALUE;
    Set<Vertex<Integer>> visited = new HashSet<>();
    return max(vertex, max, visited);
  }

  private int max(Vertex<Integer> vertex, int max, Set<Vertex<Integer>> visited) {
    // base case
    if (vertex == null || !visited.add(vertex)) return max;


    // recurse
    for (var neighbor : vertex.neighbors){
      max = max(neighbor, max, visited);
    }

    // set max
    if(vertex.data > max) max = vertex.data;

    
    return max;


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
    Set<Vertex<T>> visited = new HashSet<>();
    return leaves(vertex, leaves, visited);
  }

  private <T> Set<Vertex<T>> leaves(Vertex<T> vertex, Set<Vertex<T>> leaves, Set<Vertex<T>> visited){
    // base case - vertex is null, vertex visited before
    if (vertex == null || !visited.add(vertex)) return leaves;

    // check and add to leaves
    if (vertex.neighbors.isEmpty()) leaves.add(vertex);
    
    // recurse
    for (var neighbor : vertex.neighbors){
      leaves = leaves(neighbor, leaves, visited);
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
    Set<Vertex<Integer>> visited = new HashSet<>();
    return hasStrictlyIncreasingPath(start, end, visited);
  }

  private boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> visited){
    boolean valid = false;
    // base case - start or end is null
    if (start == null || end == null) throw new NullPointerException();

    if (!visited.add(start)) return true;

    // base case - reached end
    if (start == end) return true;
      
    // check if current is smaller than neighbors, then recurse
    for (var neighbor : start.neighbors){
      if (start.data < neighbor.data) valid = true;
      valid = hasStrictlyIncreasingPath(neighbor, end, visited);
    }

    return valid;

  }

}
