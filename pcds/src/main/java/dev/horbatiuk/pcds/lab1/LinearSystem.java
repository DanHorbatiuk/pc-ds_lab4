package dev.horbatiuk.pcds.lab1;

import java.util.Arrays;

public class LinearSystem {
    private final int size;
    private final double[][] matrix;
    private final double[] vector;

    public LinearSystem(int size) {
        this.size = size;
        this.matrix = new double[size][size];
        this.vector = new double[size];
    }

    public LinearSystem(double[][] matrix, double[] vector) {
        this.size = matrix.length;
        this.matrix = new double[size][size];
        this.vector = new double[size];

        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, size);
        }
        System.arraycopy(vector, 0, this.vector, 0, size);
    }

    public int getSize() {
        return size;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getVector() {
        return vector;
    }

    public LinearSystem copy() {
        return new LinearSystem(matrix, vector);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(Arrays.toString(matrix[i]))
                    .append(" | ")
                    .append(vector[i])
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}