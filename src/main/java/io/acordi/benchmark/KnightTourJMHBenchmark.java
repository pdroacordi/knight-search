package io.acordi.benchmark;

import io.acordi.implementations.BreadthFirstSearchImpl;
import io.acordi.implementations.GreedySearchImpl;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class KnightTourJMHBenchmark {

    @Param({"8"})
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
        try {
            BreadthFirstSearchImpl.breadthFirstSearch(startX, startY);
        }catch (OutOfMemoryError e) {
            System.out.println("Estouro de memória durante benchmarking.");
        }
    }

    @Benchmark
    public void benchmarkGreedy() {
        GreedySearchImpl.greedySearch(startX, startY);
    }

}
