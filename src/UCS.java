import java.util.*;

public class UCS {
    private final int[] goalState;
    private int exploredNodes;

    public UCS(int[] goalState) {
        this.goalState = goalState;
        this.exploredNodes = 0;
    }

    public int getExploredNodes() {
        return exploredNodes;
    }

    public List<Node> solve(int[] initialState) {
        PriorityQueue<Node> pq = new PriorityQueue<>();  // The queue is automatically sorted based on the totalCost of each node
                                                         // The comparison happens when we make the Node object comparable
        Set<String> visited = new HashSet<>();           // Visited States using HashSet for efficiency
        Node startNode = new Node(initialState, 0, 0, null, null);
        pq.add(startNode);
        visited.add(Arrays.toString(startNode.state));

        while (!pq.isEmpty()) { // The loop continues as long as there are nodes left in the priority queue
            Node currentNode = pq.poll();
            exploredNodes++;
            if (Arrays.equals(currentNode.state, goalState)) { // Return the path if the current state isGoal
                return constructPath(currentNode);
            }

            for (Node neighbor : MoveSet.getNeighbors(currentNode)) { // Loop to iterate over the neighbors
                if (!visited.contains(Arrays.toString(neighbor.state))) {
                    visited.add(Arrays.toString(neighbor.state));
                    neighbor.totalCost = neighbor.cost; // // neighbor total cost = cost
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
