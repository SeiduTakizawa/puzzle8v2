import java.util.ArrayList;
import java.util.List;

public class MoveSet {
    private static final int[][] directions = {
            {-1, 0, 1, 0}, {1, 0, 1, 1}, {0, -1, 1, 2}, {0, 1, 1, 3}, // Horizontal and vertical moves with cost 1
            {-1, -1, 2, 4}, {-1, 1, 2, 5}, {1, -1, 2, 6}, {1, 1, 2, 7} // Diagonal moves with cost 2
    };

    private static final String[] directionNames = {
            "Up", "Down", "Left", "Right", "Up-Left", "Up-Right", "Down-Left", "Down-Right"
    };

    public static List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int zeroIndex = -1;

        for (int i = 0; i < node.state.length; i++) {
            if (node.state[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        int row = zeroIndex / 3;
        int col = zeroIndex % 3;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            int moveCost = direction[2];
            String moveDirection = directionNames[direction[3]];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int newZeroIndex = newRow * 3 + newCol;
                int[] newState = node.state.clone();
                newState[zeroIndex] = newState[newZeroIndex];
                newState[newZeroIndex] = 0;
                neighbors.add(new Node(newState, node.cost + moveCost, 0, moveDirection, node)); // Total cost is set later
            }
        }

        return neighbors;
    }
}
