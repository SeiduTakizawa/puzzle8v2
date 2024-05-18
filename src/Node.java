public class Node implements Comparable<Node> {
    int[] state;
    int cost;
    int totalCost;
    String move;
    Node parent;

    public Node(int[] state, int cost, int totalCost, String move, Node parent) {
        this.state = state.clone();
        this.cost = cost;
        this.totalCost = totalCost;
        this.move = move;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        if (this.totalCost < other.totalCost) {
            return -1; // less than other
        } else if (this.totalCost > other.totalCost) {
            return 1;  // greater than other
        } else {
            return 0;   // equal
        }
    }
}
