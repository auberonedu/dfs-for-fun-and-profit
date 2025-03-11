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
  
  public <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) {
    // Base case
    if (vertex == null) return;
    if (visited.contains(vertex)) return;
    
    visited.add(vertex);
    System.out.println(vertex.data);

    if (vertex.neighbors == null) return;

    for (Vertex<T> neighbor : vertex.neighbors) {
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
    Set<Vertex<T>> reachableVertices = new HashSet<>();
    reachableHelper(vertex, reachableVertices);
    return reachableVertices;
  }

  public <T> void reachableHelper(Vertex<T> vertex, Set<Vertex<T>> visited) {
    // Base case
    if (vertex == null) return;
    if (visited.contains(vertex)) return;

    visited.add(vertex);
    
    for (Vertex<T> neighbor : vertex.neighbors) {
      reachableHelper(neighbor, visited);
    }
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
    if (vertex == null) return Integer.MIN_VALUE;
    return maxHelper(vertex, new HashSet<Vertex<Integer>>());
  }
  
  public int maxHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    // Base case
    if (vertex == null) return Integer.MIN_VALUE;
    if (visited.contains(vertex)) return Integer.MIN_VALUE;

    visited.add(vertex);

    int maxValue = vertex.data;

    for (Vertex<Integer> neighbor : vertex.neighbors) {
      int neighborMaxValue = maxHelper(neighbor, visited);
      maxValue = Math.max(maxValue, neighborMaxValue);
    }

    return maxValue;
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
    Set<Vertex<T>> leaf = new HashSet<>();
    leaves(vertex, new HashSet<>(), leaf);
    return leaf;
  }
  
  public <T> void leaves(Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> leaf) {
    // Base case
    if (vertex == null || visited.contains(vertex)) return;

    visited.add(vertex);
    
  if (vertex.neighbors.isEmpty()) leaf.add(vertex);

    for(Vertex<T> neighbor : vertex.neighbors) {
      leaves(neighbor, visited, leaf);
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
    if (start == null || end == null) throw new NullPointerException("start or end vertex is null");
    Set<Vertex<Integer>> visited = new HashSet<>();
    return hasStrictlyIncreasingPath(start, end, visited);
  }

  private boolean hasStrictlyIncreasingPath(Vertex<Integer> curr, Vertex<Integer> end, Set<Vertex<Integer>> visited) {
    // Base case: if current vertex and end vertex match, we are done with operation
    if (curr.equals(end)) return true;

    visited.add(curr);

    for (Vertex<Integer> neighbor : curr.neighbors) {
      if (neighbor.data > curr.data) {
        boolean exists = hasStrictlyIncreasingPath(neighbor, end, visited);
        if (exists) return true;
      }
  }

    return false;
  }
}