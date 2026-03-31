package dev.horbatiuk.pcds.lab1;

import java.util.concurrent.ThreadLocalRandom;

public class LinearSystemGenerator {

    private LinearSystemGenerator() {
    }

    public static LinearSystem generateRandomSystem(int size, int minValue, int maxValue) {
        LinearSystem system = new LinearSystem(size);
        double[][] A = system.getMatrix();
        double[] b = system.getVector();

        for (int i = 0; i < size; i++) {
            double rowAbsSum = 0.0;

            for (int j = 0; j < size; j++) {
                if (i != j) {
                    A[i][j] = ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
                    rowAbsSum += Math.abs(A[i][j]);
                }
            }

            A[i][i] = rowAbsSum + ThreadLocalRandom.current().nextInt(1, 10);
            b[i] = ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
        }

        return system;
    }

    public static LinearSystem createControlExample() {
        double[][] A = {
                {2, 1, -1},
                {-3, -1, 2},
                {-2, 1, 2}
        };
        double[] b = {8, -11, -3};

        return new LinearSystem(A, b);
    }
}