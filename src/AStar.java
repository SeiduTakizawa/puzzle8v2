import java.util.*;

public class AStar {
    private final int[] goalState;
    private int exploredNodes;

    public AStar(int[] goalState) {
        this.goalState = goalState;
        this.exploredNodes = 0;
    }

    public int getExploredNodes() {
        return exploredNodes;
    }

    private int heuristic(int[] state) {
        int misplaced = 0;
        for (int i = 0; i < state.length; i++) {    //Heuristic to calculate the number of misplaced tiles in the given state
            if (state[i] != 0 && state[i] != goalState[i]) {
                misplaced++;
            }
        }
        return misplaced;
    }

    public List<Node> solve(int[] initialState) {
        PriorityQueue<Node> pq = new PriorityQueue<>();  // The queue is automatically sorted based on the totalCost of each node
                                                         // The comparison happens when we make the Node object comparable
        Set<String> visited = new HashSet<>();           // Visited States using HashSet for efficiency
        Node startNode = new Node(initialState, 0, heuristic(initialState), null, null);
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
                    neighbor.totalCost = neighbor.cost + heuristic(neighbor.state); // neighbor total cost = cost + heuristic
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
