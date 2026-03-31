package dev.horbatiuk.pcds.lab1;

import java.util.Locale;

public class SolutionResult {
    private final double[] solution;
    private final long executionTimeNs;

    public SolutionResult(double[] solution, long executionTimeNs) {
        this.solution = solution;
        this.executionTimeNs = executionTimeNs;
    }

    public double[] getSolution() {
        return solution;
    }

    public long getExecutionTimeNs() {
        return executionTimeNs;
    }

    public double getExecutionTimeMs() {
        return executionTimeNs / 1_000_000.0;
    }

    public double getExecutionTimeSec() {
        return executionTimeNs / 1_000_000_000.0;
    }

    public String solutionAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < solution.length; i++) {
            sb.append(String.format(Locale.US, "x[%d] = %.6f%n", i, solution[i]));
        }
        return sb.toString();
    }
}