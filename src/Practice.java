import com.sun.jdi.Value;
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

    Set<Vertex<T>> visited = new HashSet<>();
    printVertexVals(vertex, visited);
  }

  private <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return;

    System.out.println(vertex.data);
    visited.add(vertex);

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
    Set<Vertex<T>> visited = new HashSet<>();
    if (vertex == null) return visited;

    Stack<Vertex<T>> stack = new Stack<>();
    stack.push(vertex);

    while(!stack.isEmpty()){
      Vertex<T> current = stack.pop();
      if(!visited.contains(current)){
        visited.add(current);

        for(Vertex<T> neighbor : current.neighbors){
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
    if (vertex == null) return Integer.MIN_VALUE;

    Set<Vertex<Integer>> visited = new HashSet<>();
    Stack<Vertex<Integer>> stack = new Stack<>();
    stack.push(vertex);

    int maxVal = Integer.MIN_VALUE;
    while (!stack.isEmpty()) {
      Vertex<Integer> current = stack.pop();
      if (!visited.contains(current)) {
        visited.add(current);
        maxVal = Math.max(maxVal, current.data);

        for (Vertex<Integer> neighbor : current.neighbors) {
          if (!visited.contains(neighbor)) {
            stack.push(neighbor);
          }
        }
      }
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
    Set<Vertex<T>> visited = new HashSet<>();
    if (vertex == null) return visited;

    Stack<Vertex<T>> stack = new Stack<>();
    stack.push(vertex);
    Set<Vertex<T>> leafs = new HashSet<>();

    while(!stack.isEmpty()){
      Vertex<T> current = stack.pop();
      if(!visited.contains(current)){
        visited.add(current);

        if (current.neighbors == null || current.neighbors.isEmpty()) {
          leafs.add(current);
        } else {
          for(Vertex<T> neighbor : current.neighbors){
            if (!visited.contains(neighbor)) {
              stack.push(neighbor);
            }
          }
        }
      }
    }
    return leafs;
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
    if (start == null || end == null) throw new NullPointerException();
    Stack<Vertex<Integer>> stack = new Stack<>();
    stack.push(start);
    Set<Vertex<Integer>> visited = new HashSet<>();

    while(!stack.isEmpty()){
      Vertex<Integer> current = stack.pop();
      if (current.equals(end)) return true;
      if(!visited.contains(current)){
        visited.add(current);

        for(Vertex<Integer> neighbor : current.neighbors){
          if (neighbor.data > current.data) {
            stack.push(neighbor);
          }
        }
      }
    }
    return false;
  }
}
