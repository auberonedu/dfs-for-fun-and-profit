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

  public static <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return;

    visited.add(vertex);

    System.out.println(vertex.data);

    for (var neighbor: vertex.neighbors) {
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

  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return visited;

    visited.add(vertex);

    if (vertex.neighbors == null) return visited;

    for (var neighbor: vertex.neighbors) {
      reachable(neighbor, visited);
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
    return max(vertex, new HashSet<Vertex<Integer>>());
  }

  public <T> int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) return Integer.MIN_VALUE;

    int maxValue = vertex.data;

    visited.add(vertex);

    for (var neighbor: vertex.neighbors) {
      maxValue = Math.max(maxValue, max(neighbor, visited));
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
    return leaves(vertex, new HashSet<Vertex<T>>(), new HashSet<Vertex<T>>());
  }

  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> leafNodes) {
    if (vertex == null || visited.contains(vertex)) return leafNodes;

    visited.add(vertex);

    if(vertex.neighbors == null || vertex.neighbors.isEmpty()) {
      leafNodes.add(vertex);
    } 

    for (var neighbor: vertex.neighbors) {
      leafNodes.addAll(leaves(neighbor, visited, leafNodes));
    }

    return leafNodes;
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
