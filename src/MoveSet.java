import java.util.ArrayList;
import java.util.List;

public class MoveSet {
    //MOVEMENT MAP
    private static final int[] rowMap = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] colMap = {0, 0, -1, 1, -1, 1, -1, 1};
    private static final int[] moveCostMap = {1, 1, 1, 1, 2, 2, 2, 2};
    private static final String[] directionNames = {
            "Up", "Down", "Left", "Right", "Up-Left", "Up-Right", "Down-Left", "Down-Right"
    };

    public static List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        //find the zero position
        int zeroPos = -1;
        for (int i = 0; i < node.state.length; i++) {
            if (node.state[i] == 0) {
                zeroPos = i;
                break;
            }
        }
        //Translate the coordinates of the 1d array to a 2-dimensional one
        int row = zeroPos / 3;
        int col = zeroPos % 3;
        //For loop for the possible moves
        for (int i = 0; i < rowMap.length; i++) {
            int newRow = row + rowMap[i];
            int newCol = col + colMap[i];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {  //Check if the selected position is in bounds
                int newZeroPos = newRow * 3 + newCol;
                int[] newState = node.state.clone();
                newState[zeroPos] = newState[newZeroPos];       //Swap the zero position and create the new node to add to neighbors
                newState[newZeroPos] = 0;
                neighbors.add(new Node(newState, node.cost + moveCostMap[i], 0, directionNames[i], node));
            }
        }

        return neighbors;
    }
}
