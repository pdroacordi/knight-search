package io.acordi;

import io.acordi.benchmark.JMHBenchmarkRunner;
import io.acordi.entities.Position;
import io.acordi.implementations.BreadthFirstSearchImpl;
import io.acordi.implementations.GreedySearchImpl;
import io.acordi.visual.BoardAnimatorFrame;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws RunnerException, InterruptedException {

        // Se quiser rodar o benchmark, remove os comentários dessas duas linhas aqui
        // Runner runner = JMHBenchmarkRunner.getRunner();
        // runner.run();

        animatePath(8, 0, 0, 2);

    }

    private static void animatePath(int boardSize, int originX, int originY, int algorithm) throws InterruptedException {
        List<Position> path = new LinkedList<>();
        switch (algorithm) {
            case 1:
                BreadthFirstSearchImpl.setBoardSize(boardSize);
                path = BreadthFirstSearchImpl.breadthFirstSearch(originX, originY);
                break;
            case 2:
                GreedySearchImpl.setBoardSize(boardSize);
                path = GreedySearchImpl.greedySearch(originX, originY);
                break;
        }
        BoardAnimatorFrame boardAnimatorFrame = new BoardAnimatorFrame(boardSize);
        boardAnimatorFrame.animate(path);
    }
}