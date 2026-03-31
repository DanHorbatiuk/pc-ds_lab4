package dev.horbatiuk.pcds.lab1;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class SystemFileManager {

    private SystemFileManager() {
    }

    public static void saveSystemToFile(LinearSystem system, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            int n = system.getSize();
            double[][] A = system.getMatrix();
            double[] b = system.getVector();

            writer.println(n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.printf(Locale.US, "%.10f ", A[i][j]);
                }
                writer.printf(Locale.US, "%.10f%n", b[i]);
            }
        }
    }

    public static LinearSystem readSystemFromFile(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.useLocale(Locale.US);

            int n = scanner.nextInt();
            LinearSystem system = new LinearSystem(n);

            double[][] A = system.getMatrix();
            double[] b = system.getVector();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextDouble();
                }
                b[i] = scanner.nextDouble();
            }

            return system;
        }
    }

    public static void saveResultsToFile(
            String fileName,
            SolutionResult sequential,
            SolutionResult parallel,
            double speedup
    ) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Sequential solution:");
            writer.print(sequential.solutionAsString());
            writer.printf(Locale.US, "Sequential time: %.6f ms%n%n", sequential.getExecutionTimeMs());

            writer.println("Parallel solution:");
            writer.print(parallel.solutionAsString());
            writer.printf(Locale.US, "Parallel time: %.6f ms%n", parallel.getExecutionTimeMs());
            writer.printf(Locale.US, "Speedup: %.4f%n", speedup);
        }
    }
}