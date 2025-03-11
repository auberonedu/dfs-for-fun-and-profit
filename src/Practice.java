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
    var visited = new HashSet<Vertex<T>>();
    printVertexValsHelper(vertex, visited);
  }

  public <T> void printVertexValsHelper(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null) {
      return;
    }

    // If we've already visited the neighbor, skip over it
    if (visited.contains(vertex)) {
      return;
    }

    // If visiting a 'new' neighbor, add it to the hashset and print its value
    visited.add(vertex);
    System.out.println(vertex.data);

    // Check for null neighbors
    if(vertex.neighbors == null) {
      return;
    }

    // For each loop to recursively visit all neighbors
    for (Vertex<T> neighbor : vertex.neighbors) {
      printVertexValsHelper(neighbor, visited);
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
    var visited = new HashSet<Vertex<T>>();
    return reachableHelper(vertex, visited);
  }

  public <T> Set<Vertex<T>> reachableHelper(Vertex<T> vertex, Set<Vertex<T>> visited) {

    // Check if null, return empty set
    if (vertex == null) {
      return new HashSet<>();
    }

    // If we've already visited the neighbor, skip over it and return unchanged set
    if (visited.contains(vertex)) {
      return visited;
    }

    // If a new nieghbor, add it to the set
    visited.add(vertex);

    // If neighbors are null, return current set
    if(vertex.neighbors == null) {
      return visited;
    }

    // For each loop to recursively visit all neighbors
    for(Vertex<T> neighbor : vertex.neighbors) {
      reachableHelper(neighbor, visited);
    }

    // Return finished set of reachable neighbors
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

    var visited = new HashSet<Vertex<Integer>>();

    if (vertex == null) {
      return Integer.MIN_VALUE;
    }

    return maxHelper(vertex, visited);
  }

  public int maxHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {

    if (visited.contains(vertex)) {
      return Integer.MIN_VALUE;
    }
    
    visited.add(vertex);

    int maxGraphValue = vertex.data;

    for(Vertex<Integer> neighbor : vertex.neighbors) {
  
      maxGraphValue = Math.max(maxGraphValue, maxHelper(neighbor, visited));
    }

    return maxGraphValue;
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
    Set<Vertex<T>> leavesSet = new HashSet<>();
    Set<Vertex<T>> visited = new HashSet<>();
    leavesHelper(vertex, leavesSet, visited);
    return leavesSet;
  }

  private <T> void leavesHelper(Vertex<T> vertex, Set<Vertex<T>> leaves, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return;

    visited.add(vertex);

    if (vertex.neighbors == null || vertex.neighbors.isEmpty()) {
      leaves.add(vertex);
      return;
    }

    for (Vertex<T> neighbor : vertex.neighbors) {
        leavesHelper(neighbor, leaves, visited);
    }
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
    if (start == null || end == null) throw new NullPointerException("Start or end cannot be null.");
    
    return increasingPathHelper(start, end, new HashSet<>(), Integer.MIN_VALUE);
  }

  private boolean increasingPathHelper(Vertex<Integer> current, Vertex<Integer> end, Set<Vertex<Integer>> visited, int lastValue) {
    if (current == null || visited.contains(current)) return false;

    if (current.data <= lastValue) return false;

    if (current == end) return true;

    visited.add(current);

    for (Vertex<Integer> neighbor : current.neighbors) {
      if (increasingPathHelper(neighbor, end, visited, current.data)) return true;
    }

    return false;
  }
}