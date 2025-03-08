import java.util.Set;
import java.util.HashSet;

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
    // if (vertex == null) return;
    printVertexVals(vertex, new HashSet<Vertex<T>>());
  }

  private <T> void printVertexVals(Vertex<T> vertex, HashSet<Vertex<T>> record) {
    if (vertex == null) return;
    if (record.contains(vertex)) return;
    record.add(vertex);
    System.out.println(vertex.data);

    for (var neighbor: vertex.neighbors) {
      printVertexVals(neighbor, record);
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

  private <T> HashSet<Vertex<T>> reachable(Vertex<T> vertex, HashSet<Vertex<T>> record) {
    if (vertex == null) return record;
    if (record.contains(vertex)) return record;
    record.add(vertex);

    for (var neighbor: vertex.neighbors) {
      reachable(neighbor, record);
    }
    return record;
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
    return max(vertex, max, new HashSet<Vertex<Integer>>());
  }

  private int max(Vertex<Integer> vertex, int max, HashSet<Vertex<Integer>> record) {
    if (vertex == null) return max;
    if (record.contains(vertex)) return max;
    record.add(vertex);

    max = Math.max(max, vertex.data);

    for (var neighbor: vertex.neighbors) {
      max = Math.max(max, max(neighbor, max, record));
    }

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
    return leaves(vertex, new HashSet<Vertex<T>>(), new HashSet<Vertex<T>>());
  }

  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex, HashSet<Vertex<T>> record, HashSet<Vertex<T>> leaves) {
    if (vertex == null) return leaves;
    if (record.contains(vertex)) return leaves;
    record.add(vertex);

    if (vertex.neighbors.isEmpty()) { leaves.add(vertex);};

    for (var neighbor: vertex.neighbors) {
      leaves(neighbor, record, leaves);
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
    if (start == null || end == null) {
      throw new NullPointerException("start or end is null");
    }

    if (start.equals(end)) return true;

    if (start.data >= end.data) return false;

    boolean strictlyIncreasing = false;

    return hasStrictlyIncreasingPath(start, end, new HashSet<Vertex<Integer>>(), strictlyIncreasing);
  }

  public boolean hasStrictlyIncreasingPath(Vertex<Integer> current, Vertex<Integer> end, HashSet<Vertex<Integer>> record, boolean strictlyIncreasing) {
    if (current == null) return strictlyIncreasing;
    if (record.contains(current)) return strictlyIncreasing;
    record.add(current);
    if (current.equals(end)) return true;

    for (var neighbor: current.neighbors) {
      if (current.data < neighbor.data) {
        strictlyIncreasing = hasStrictlyIncreasingPath(neighbor, end, record, strictlyIncreasing);
      }
    }

    return strictlyIncreasing;
  }
}
