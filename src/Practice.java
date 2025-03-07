import java.util.*;

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

    dfs(vertex, new HashSet<>());
  }

  private <T> void dfs(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (visited.contains(vertex)) return;

    System.out.println(vertex.data);
    visited.add(vertex);

    for (var neighbor : vertex.neighbors) {
      dfs(neighbor, visited);
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
    Set<Vertex<T>> visited = new HashSet<Vertex<T>>();

    if (vertex != null) dfs(vertex, visited); 
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
    if (vertex == null) return Integer.MIN_VALUE;
    
    return dfsMax(vertex, new HashSet<>());
  }

  private int dfsMax(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (visited.contains(vertex)) return Integer.MIN_VALUE;

    visited.add(vertex);
    int maxVal = vertex.data;

    for (Vertex<Integer> neighbor : vertex.neighbors) {
      maxVal = Math.max(maxVal, dfsMax(neighbor, visited));
    }
    return maxVal;
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
    Set<Vertex<T>> leafSet = new HashSet<>();
    if(vertex != null) dfsFindLeaves(vertex, new HashSet<>(), leafSet);
    return leafSet;
  }
  public <T> void dfsFindLeaves(Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> leafSet) {
    if(visited.contains(vertex)) return;

    visited.add(vertex);

    if(vertex.neighbors.isEmpty()) {
      leafSet.add(vertex);
    } else {
      for (Vertex<T> neighbor : vertex.neighbors) {
        dfsFindLeaves(neighbor, visited, leafSet);
      }
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
    if(start == null || end == null) throw new NullPointerException();

    return dfs(start, end, start.data, new HashSet<>());
  }

  public boolean dfs(Vertex<Integer> current, Vertex<Integer> end, int prevValue, Set<Vertex<Integer>> visited) {
    if(current == end) return true;
    visited.add(current);

    for (Vertex<Integer> neighbor : current.neighbors) {
      if(!visited.contains(neighbor) && neighbor.data > prevValue) {
        if(dfs(neighbor, end, neighbor.data, visited)) {
          return true;
        }
      }
    }
    return false;
  }
}
