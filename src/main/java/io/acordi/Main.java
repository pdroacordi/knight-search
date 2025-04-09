package io.acordi;

import io.acordi.benchmark.JMHBenchmarkRunner;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

public class Main {
    public static void main(String[] args) throws RunnerException {

        Runner runner = JMHBenchmarkRunner.getRunner();

        runner.run();

    }
}