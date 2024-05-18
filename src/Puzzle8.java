import java.util.*;

public class Puzzle8 {
    private final int[] initialState;
    private final int[] goalState = {6, 5, 4, 7, 0, 3, 8, 1, 2};
    private int nodesExplored = 0;
    public Puzzle8(int[] initialState) {
        this.initialState = initialState;
    }

    public void solve(int choice) {
        List<Node> solution = null;
        if (choice == 1) {
            UCS ucs = new UCS(goalState);
            solution = ucs.solve(initialState);
            nodesExplored = ucs.getExploredNodes();
        } else if (choice == 2) {
            AStar aStar = new AStar(goalState);
            solution = aStar.solve(initialState);
            nodesExplored = aStar.getExploredNodes();
        } else {
            System.out.println("Invalid choice.");
            System.exit(1);
        }

        if (solution != null) {
            for (Node node : solution) {  //Print the solution path
                printState(node.state);
                System.out.println("Move: " + (node.move == null ? "Start" : node.move));
                System.out.println("Cost: " + node.cost);
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    private static void printState(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println();
            }
            if (state[i] == 0) {
                System.out.print("  ");
            } else {
                System.out.print(state[i] + " ");
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[][] initialStates = {
                {7,6,5,8,3,4,1,0,2}, //easy
                {7,6,8,5,3,4,1,0,2}, //medium
                {0,1,2,3,4,5,6,7,8}, //hard
                {8,4,6,3,1,5,2,0,7}, //tryhard
                {2,8,1,0,4,3,7,6,5}, //expert

        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an initial state (1-5):");
        for (int i = 0; i < initialStates.length; i++) {
            System.out.println((i + 1) + ":");
            printState(initialStates[i]);
            System.out.println();
        }

        int choice = scanner.nextInt();
        int[] initialState = initialStates[choice - 1];

        System.out.println("Choose the algorithm (1 for UCS, 2 for A*):");
        int choice1 = scanner.nextInt();

        Puzzle8 puzzle = new Puzzle8(initialState);
        puzzle.solve(choice1);
        System.out.println("The puzzle explored: " + puzzle.nodesExplored+ " Nodes");
    }
}
