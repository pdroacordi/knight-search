package io.acordi.benchmark;

import io.acordi.implementations.BreadthFirstSearchImpl;
import io.acordi.implementations.GreedySearchImpl;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class KnightTourJMHBenchmark {

    @Param({"5"})
    public int boardSize;

    @Param({"0"})
    public int startX;

    @Param({"0"})
    public int startY;

    @Setup(Level.Trial)
    public void setUp() {
        BreadthFirstSearchImpl.setBoardSize(boardSize);
        GreedySearchImpl.setBoardSize(boardSize);
    }

    @Benchmark
    public void benchmarkBFS() {
        BreadthFirstSearchImpl.resetExploredNodes();
        BreadthFirstSearchImpl.breadthFirstSearch(startX, startY);
    }

    @Benchmark
    public void benchmarkGreedy() {
        GreedySearchImpl.resetExploredNodes();
        GreedySearchImpl.greedySearch(startX, startY);
    }

}
