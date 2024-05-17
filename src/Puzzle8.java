import java.util.*;

public class Puzzle8 {
    private final int[] initialState;
    private final int[] goalState = {6, 5, 4, 7, 0, 3, 8, 1, 2};

    public Puzzle8(int[] initialState) {
        this.initialState = initialState;
    }

    public void solve(int algoChoice) {
        List<Node> solution = null;
        if (algoChoice == 1) {
            UCS ucs = new UCS(goalState);
            solution = ucs.solve(initialState);
            System.out.println("Nodes explored (UCS): " + ucs.getNodesExplored());
        } else if (algoChoice == 2) {
            AStar aStar = new AStar(goalState);
            solution = aStar.solve(initialState);
            System.out.println("Nodes explored (A*): " + aStar.getNodesExplored());
        } else {
            System.out.println("Invalid choice.");
            System.exit(1);
        }

        if (solution != null) {
            for (Node node : solution) {
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
            if (i % 3 == 0) System.out.println();
            System.out.print((state[i] == 0 ? " " : state[i]) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] initialStates = {
                {1, 2, 3, 4, 5, 6, 0, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 0, 8},
                {1, 2, 3, 4, 5, 0, 6, 7, 8},
                {1, 2, 3, 4, 0, 5, 6, 7, 8},
                {0, 1, 2, 3, 4, 5, 6, 7, 8}
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
        int algoChoice = scanner.nextInt();

        Puzzle8 puzzle = new Puzzle8(initialState);
        puzzle.solve(algoChoice);

        scanner.close();
    }
}
