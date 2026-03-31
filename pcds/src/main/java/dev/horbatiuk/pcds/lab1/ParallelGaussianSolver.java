package dev.horbatiuk.pcds.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelGaussianSolver extends SequentialGaussianSolver {

    private static final double EPS = 1e-12;
    private final int threadCount;

    public ParallelGaussianSolver(int threadCount) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException("Кількість потоків має бути більшою за 0.");
        }
        this.threadCount = threadCount;
    }

    @Override
    public double[] solve(LinearSystem system) {
        double[][] A = system.getMatrix();
        double[] b = system.getVector();
        int n = system.getSize();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        try {
            for (int k = 0; k < n; k++) {
                int pivotRow = findPivotRow(A, k, n);
                swapRows(A, b, k, pivotRow);

                if (Math.abs(A[k][k]) < EPS) {
                    throw new ArithmeticException("Матриця вироджена або майже вироджена.");
                }

                List<Callable<Void>> tasks = new ArrayList<>();

                for (int i = k + 1; i < n; i++) {
                    final int row = i;
                    final int pivot = k;

                    tasks.add(() -> {
                        double factor = A[row][pivot] / A[pivot][pivot];
                        A[row][pivot] = 0.0;

                        for (int j = pivot + 1; j < n; j++) {
                            A[row][j] -= factor * A[pivot][j];
                        }

                        b[row] -= factor * b[pivot];
                        return null;
                    });
                }

                if (!tasks.isEmpty()) {
                    executor.invokeAll(tasks);
                }
            }

            return backSubstitution(A, b, n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}