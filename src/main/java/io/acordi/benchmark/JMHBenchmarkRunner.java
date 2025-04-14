package io.acordi.benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHBenchmarkRunner {

    public static Runner getRunner(){
        Options opt = new OptionsBuilder()
                .include(KnightTourJMHBenchmark.class.getSimpleName())
                .addProfiler("gc")
                .forks(1)
                .warmupIterations(1 )     //Iterações de aquecimento pra estabilizar a JVM, a primeira costuma ser mais lenta
                .measurementIterations(10) // Medição do problema em si
                .result("knight_tour_benchmark_results.csv")
                .resultFormat(ResultFormatType.CSV)
                .build();

        return new Runner(opt);
    }
}
