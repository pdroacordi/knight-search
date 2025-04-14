package io.acordi.implementations;

import io.acordi.entities.State;
import io.acordi.entities.Knight;
import io.acordi.entities.LongLinkedHashSet;
import io.acordi.entities.Position;

import java.util.*;

public class BreadthFirstSearchImpl {

    private static int BOARD_SIZE = 4;
    private static long FULL_MASK;

    public static void setBoardSize(int size) {
        BOARD_SIZE = size;
        FULL_MASK = (1L << (BOARD_SIZE * BOARD_SIZE)) - 1;
    }

    public static List<Position> breadthFirstSearch(int originX, int originY) {
        Queue<State> queue = new LinkedList<>();
        LongLinkedHashSet visitedStates = new LongLinkedHashSet(1 << 20);

        int startPos = originX * BOARD_SIZE + originY;
        long initialMask = 1L << startPos;
        State initialState = new State(startPos, initialMask, null);
        queue.add(initialState);
        visitedStates.add(getStateHash(initialState));

        Knight knight = new Knight();

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.visitedMask == FULL_MASK) {
                return reconstructPath(current);
            }

            int currentX = current.pos / BOARD_SIZE;
            int currentY = current.pos % BOARD_SIZE;

            for (int[] move : knight.getMoves()) {
                int newX = currentX + move[0];
                int newY = currentY + move[1];

                if (!isValidPosition(newX, newY))
                    continue;

                int newPos = newX * BOARD_SIZE + newY;
                long bitMask = 1L << newPos;

                if ((current.visitedMask & bitMask) != 0)
                    continue;

                long newMask = current.visitedMask | bitMask;
                State newState = new State(newPos, newMask, current);
                long stateHash = getStateHash(newState);

                if (visitedStates.contains(stateHash))
                    continue;

                visitedStates.add(stateHash);
                int availableMoves = countAvailableMoves(newX, newY, newMask, knight);

                if (availableMoves > 0 || newMask == FULL_MASK) {
                    queue.add(newState);
                }
            }
        }

        return null;

    }

    private static long getStateHash(State state) {
        return (((long) state.pos) << (BOARD_SIZE * BOARD_SIZE)) | state.visitedMask;
    }

    private static List<Position> reconstructPath(State state) {
        List<Position> path = new ArrayList<>();

        while (state != null) {
            int x = state.pos / BOARD_SIZE;
            int y = state.pos % BOARD_SIZE;
            path.add(new Position(x, y));
            state = state.parent;
        }

        Collections.reverse(path);
        return path;
    }

    private static boolean isValidPosition(int x, int y){
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    // Isso aqui é uma espécie de poda da árvore.
    // A gente não vai visitar casas onde o cavalo ficaria preso,
    // então, basicamente, casas que não tem pra onde ir.
    //
    // Antes que reclame, isso aqui não descaracteriza uma BFS,
    // NÃO É UMA BUSCA HEURÍSTICA!
    //
    // A gente só evita carregar na memória nós que não tem saída,
    // não estamos priorizando um nó melhor.
    // tudo em nome da otimização, tem que tentar rodar esse algoritmo pelo menos num 6x6.
    // - Pedro
    private static int countAvailableMoves(int x, int y, long visitedMask, Knight knight) {
        int count = 0;
        for (int[] move : knight.getMoves()) {
            int nx = x + move[0];
            int ny = y + move[1];

            if (isValidPosition(nx, ny)) {
                int pos = nx * BOARD_SIZE + ny;
                if ((visitedMask & (1L << pos)) == 0) {
                    count++;
                }
            }
        }
        return count;
    }


}
