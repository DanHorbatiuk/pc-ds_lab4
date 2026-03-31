package dev.horbatiuk.pcds.lab1;

public class Benchmark {

    private Benchmark() {
    }

    public static SolutionResult measure(GaussianSolver solver, LinearSystem system) throws Exception {
        LinearSystem copy = system.copy();

        long start = System.nanoTime();
        double[] solution = solver.solve(copy);
        long end = System.nanoTime();

        return new SolutionResult(solution, end - start);
    }

    public static double calculateSpeedup(SolutionResult sequential, SolutionResult parallel) {
        return sequential.getExecutionTimeNs() / (double) parallel.getExecutionTimeNs();
    }
}