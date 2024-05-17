import java.util.*;

public class UCS {
    private final int[] goalState;
    private int nodesExplored;

    public UCS(int[] goalState) {
        this.goalState = goalState;
        this.nodesExplored = 0;
    }

    public int getNodesExplored() {
        return nodesExplored;
    }

    public List<Node> solve(int[] initialState) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        Node startNode = new Node(initialState, 0, 0, null, null);
        pq.add(startNode);
        visited.add(Arrays.toString(startNode.state));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            nodesExplored++;
            if (Arrays.equals(currentNode.state, goalState)) {
                return constructPath(currentNode);
            }

            for (Node neighbor : MoveSet.getNeighbors(currentNode)) {
                if (!visited.contains(Arrays.toString(neighbor.state))) {
                    visited.add(Arrays.toString(neighbor.state));
                    neighbor.totalCost = neighbor.cost; // UCS uses cost only
                    pq.add(neighbor);
                }
            }
        }

        return null;  // Return null if no solution is found
    }

    private List<Node> constructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
