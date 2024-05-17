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
        return Integer.compare(this.totalCost , other.totalCost);
    }
}
