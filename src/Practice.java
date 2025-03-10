import java.util.Collections;
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
  public <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if(vertex == null) return;
    if(visited.contains(vertex)) return;

    visited.add(vertex);

    System.out.println(vertex.data);

    if(vertex.neighbors == null)return;

    for(var neighbor : vertex.neighbors){
      printVertexVals(neighbor, visited);
    }

  }

  public <T> void printVertexVals(Vertex<T> vertex){
    var visited = new HashSet<Vertex<T>>();
    printVertexVals(vertex, visited);
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
  private <T> void reachable(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) {
        return;
    }
    
    visited.add(vertex);
    
    if (vertex.neighbors != null) {
        for (Vertex<T> neighbor : vertex.neighbors) {
            reachable(neighbor, visited);
        }
    }
  }

  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {
    if (vertex == null) {
        return Collections.emptySet();
    }
    Set<Vertex<T>> visited = new HashSet<>();
    reachable(vertex, visited);
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
    if (vertex == null) {
        return Integer.MIN_VALUE;
    }
    Set<Vertex<Integer>> visited = new HashSet<>();
    return max(vertex, visited);
}

private int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) {
        return Integer.MIN_VALUE;
    }
    visited.add(vertex);
    int maxValue = vertex.data;
    if (vertex.neighbors != null) {
        for (Vertex<Integer> neighbor : vertex.neighbors) {
            maxValue = Math.max(maxValue, max(neighbor, visited));
        }
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
    return null;
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
    return false;
  }
}
