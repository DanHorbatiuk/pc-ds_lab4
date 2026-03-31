package dev.horbatiuk.pcds.lab1;

public class SequentialGaussianSolver implements GaussianSolver {

    private static final double EPS = 1e-12;

    @Override
    public double[] solve(LinearSystem system) {
        double[][] A = system.getMatrix();
        double[] b = system.getVector();
        int n = system.getSize();

        for (int k = 0; k < n; k++) {
            int pivotRow = findPivotRow(A, k, n);
            swapRows(A, b, k, pivotRow);

            if (Math.abs(A[k][k]) < EPS) {
                throw new ArithmeticException("Матриця вироджена або майже вироджена.");
            }

            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                A[i][k] = 0.0;

                for (int j = k + 1; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }

                b[i] -= factor * b[k];
            }
        }

        return backSubstitution(A, b, n);
    }

    protected int findPivotRow(double[][] A, int column, int n) {
        int pivotRow = column;
        for (int i = column + 1; i < n; i++) {
            if (Math.abs(A[i][column]) > Math.abs(A[pivotRow][column])) {
                pivotRow = i;
            }
        }
        return pivotRow;
    }

    protected void swapRows(double[][] A, double[] b, int row1, int row2) {
        if (row1 == row2) {
            return;
        }

        double[] tempRow = A[row1];
        A[row1] = A[row2];
        A[row2] = tempRow;

        double temp = b[row1];
        b[row1] = b[row2];
        b[row2] = temp;
    }

    protected double[] backSubstitution(double[][] A, double[] b, int n) {
        double[] x = new double[n];

        for (int i = n - 1; i >= 0; i--) {
            double sum = b[i];

            for (int j = i + 1; j < n; j++) {
                sum -= A[i][j] * x[j];
            }

            x[i] = sum / A[i][i];
        }

        return x;
    }
}