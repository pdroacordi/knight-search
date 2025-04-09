package implementations;

import entities.Knight;
import entities.Position;
import entities.State;

import java.util.*;

public class BreadthFirstSearchImpl {

    private static int BOARD_SIZE = 4;
    private static long FULL_MASK;
    private static int exploredNodes = 0;

    public static void setBoardSize(int size) {
        BOARD_SIZE = size;
        FULL_MASK = (1L << (BOARD_SIZE * BOARD_SIZE)) - 1;
    }

    public static void resetExploredNodes() {
        exploredNodes = 0;
    }

    public static int getExploredNodes() {
        return exploredNodes;
    }


    public static List<Position> breadthFirstSearch(int originX, int originY) {
        Queue<State> queue = new LinkedList<>();
        Set<Long> visitedStates = new HashSet<>();

        int startPositionBit = originX * BOARD_SIZE + originY;
        long initialMask = 1L << startPositionBit;

        List<Position> initialPath = new ArrayList<>();
        initialPath.add(new Position(originX, originY));

        var initialState = new State(new Position(originX, originY), initialMask, initialPath);
        queue.add(initialState);
        visitedStates.add(getStateHash(initialState));

        Knight knight = new Knight();

        int maxQueueSize = 1;

        while (!queue.isEmpty()) {
            maxQueueSize = Math.max(maxQueueSize, queue.size());
            State current = queue.poll();
            exploredNodes++;

            if (current.visitedMask == FULL_MASK) {
                System.out.println("[BFS] Solution found after exploring: " + exploredNodes);
                System.out.println("[BFS] Maximum queue size: " + maxQueueSize);
                return current.path;
            }

            for (int[] move : knight.getMoves()) {
                int newX = current.position.getX() + move[0];
                int newY = current.position.getY() + move[1];

                if (!isValidPosition(newX, newY)) continue;

                int positionBit = newX * BOARD_SIZE + newY;
                long bitMask = 1L << positionBit;

                if ((current.visitedMask & bitMask) != 0) continue;

                long newMask = current.visitedMask | bitMask;
                Position newPosition = new Position(newX, newY);

                List<Position> newPath = new ArrayList<>(current.path);
                newPath.add(newPosition);

                State newState = new State(newPosition, newMask, newPath);
                long stateHash = getStateHash(newState);

                if (visitedStates.contains(stateHash)) continue;

                visitedStates.add(stateHash);
                queue.add(newState);
            }
        }

        System.out.println("[BFS] No solution found after exploring " + exploredNodes + " states");
        return null;

    }

    private static long getStateHash(State state) {
        return ((long)state.position.getX() << 59) |
                ((long)state.position.getY() << 56) |
                (state.visitedMask & ((1L << 56) - 1));
    }

    private static boolean isValidPosition(int x, int y){
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }


}
