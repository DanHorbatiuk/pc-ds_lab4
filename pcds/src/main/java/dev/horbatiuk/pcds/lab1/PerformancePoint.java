package dev.horbatiuk.pcds.lab1;

public class PerformancePoint {
    private final int threadCount;
    private final double speedup;
    private final double parallelTimeMs;

    public PerformancePoint(int threadCount, double speedup, double parallelTimeMs) {
        this.threadCount = threadCount;
        this.speedup = speedup;
        this.parallelTimeMs = parallelTimeMs;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public double getSpeedup() {
        return speedup;
    }

    public double getParallelTimeMs() {
        return parallelTimeMs;
    }
}