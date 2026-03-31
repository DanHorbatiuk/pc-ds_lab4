package dev.horbatiuk.pcds.lab1;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final int MAX_ROWS_TO_PRINT = 8;
    private static final int MAX_COLS_TO_PRINT = 8;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        printHeader("Gaussian Elimination Laboratory Work");

        while (true) {
            printMenu();
            int option = readInt(scanner, "Choose option: ");

            if (option == 0) {
                printHeader("Program finished");
                break;
            }

            try {
                if (option == 4) {
                    runChartExperiment(scanner);
                    continue;
                }

                LinearSystem system = switch (option) {
                    case 1 -> createGeneratedSystem(scanner);
                    case 2 -> createSystemFromFile(scanner);
                    case 3 -> LinearSystemGenerator.createControlExample();
                    default -> {
                        printWarning("Invalid option.");
                        yield null;
                    }
                };

                if (system == null) {
                    continue;
                }

                printHeader("Input system");
                printSystemPreview(system);

                SolutionResult sequentialResult = Benchmark.measure(
                        new SequentialGaussianSolver(),
                        system
                );

                int threads = readInt(scanner, "Enter number of threads: ");

                SolutionResult parallelResult = Benchmark.measure(
                        new ParallelGaussianSolver(threads),
                        system
                );

                double speedup = Benchmark.calculateSpeedup(sequentialResult, parallelResult);

                printHeader("Computation results");
                printResultBlock("Sequential", sequentialResult);
                printResultBlock("Parallel", parallelResult);
                printPerformanceSummary(sequentialResult, parallelResult, speedup);

                int saveResults = readInt(scanner, "\nWrite results to file? (1-yes, 0-no): ");
                if (saveResults == 1) {
                    System.out.print("Enter output file name: ");
                    String fileName = scanner.next();
                    SystemFileManager.saveResultsToFile(fileName, sequentialResult, parallelResult, speedup);
                    printSuccess("Results saved to file: " + fileName);
                }

            } catch (Exception e) {
                printError("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void runChartExperiment(Scanner scanner) throws Exception {
        printHeader("Speedup chart experiment");

        int n = readInt(scanner, "Enter system size n: ");
        int maxThreads = readInt(scanner, "Enter maximum number of threads: ");

        if (maxThreads <= 0) {
            printWarning("Maximum number of threads must be greater than 0.");
            return;
        }

        LinearSystem system = LinearSystemGenerator.generateRandomSystem(n, -10, 10);

        printHeader("Input system preview");
        printSystemPreview(system);

        SolutionResult sequentialResult = Benchmark.measure(
                new SequentialGaussianSolver(),
                system
        );

        System.out.printf("Sequential baseline time: %.6f ms%n", sequentialResult.getExecutionTimeMs());
        System.out.println();

        java.util.List<PerformancePoint> points = new java.util.ArrayList<>();

        System.out.println("Threads | Parallel time (ms) | Speedup");
        System.out.println("--------------------------------------");

        for (int threads = 1; threads <= maxThreads; threads++) {
            SolutionResult parallelResult = Benchmark.measure(
                    new ParallelGaussianSolver(threads),
                    system
            );

            double speedup = Benchmark.calculateSpeedup(sequentialResult, parallelResult);
            points.add(new PerformancePoint(
                    threads,
                    speedup,
                    parallelResult.getExecutionTimeMs()
            ));

            System.out.printf(Locale.US, "%7d | %18.6f | %7.4f%n",
                    threads,
                    parallelResult.getExecutionTimeMs(),
                    speedup
            );
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            ChartFrame frame = new ChartFrame(points, sequentialResult.getExecutionTimeMs());
            frame.setVisible(true);
        });
    }

    private static LinearSystem createGeneratedSystem(Scanner scanner) throws Exception {
        printHeader("Random system generation");

        int n = readInt(scanner, "Enter system size n: ");
        LinearSystem system = LinearSystemGenerator.generateRandomSystem(n, -10, 10);

        int save = readInt(scanner, "Save generated system to file? (1-yes, 0-no): ");
        if (save == 1) {
            System.out.print("Enter file name: ");
            String fileName = scanner.next();
            SystemFileManager.saveSystemToFile(system, fileName);
            printSuccess("System saved to file: " + fileName);
        }

        return system;
    }

    private static LinearSystem createSystemFromFile(Scanner scanner) throws Exception {
        printHeader("Read system from file");

        System.out.print("Enter file name: ");
        String fileName = scanner.next();

        LinearSystem system = SystemFileManager.readSystemFromFile(fileName);
        printSuccess("System loaded from file: " + fileName);

        return system;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("                     MAIN MENU                    ");
        System.out.println("==================================================");
        System.out.println("1 - Generate random system");
        System.out.println("2 - Read system from file");
        System.out.println("3 - Use control example");
        System.out.println("4 - Build speedup chart");
        System.out.println("0 - Exit");
        System.out.println("--------------------------------------------------");
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(centerText(title, 50));
        System.out.println("==================================================");
    }

    private static void printSystemPreview(LinearSystem system) {
        int n = system.getSize();
        double[][] A = system.getMatrix();
        double[] b = system.getVector();

        System.out.printf("System size: %d x %d%n", n, n);

        int rowsToPrint = Math.min(n, MAX_ROWS_TO_PRINT);
        int colsToPrint = Math.min(n, MAX_COLS_TO_PRINT);

        System.out.println();
        for (int i = 0; i < rowsToPrint; i++) {
            System.out.print("| ");

            for (int j = 0; j < colsToPrint; j++) {
                System.out.printf("%10.3f ", A[i][j]);
            }

            if (colsToPrint < n) {
                System.out.print("... ");
            }

            System.out.printf("| %10.3f%n", b[i]);
        }

        if (rowsToPrint < n) {
            System.out.println("|        ...                                         |        ...");
        }
    }

    private static void printResultBlock(String label, SolutionResult result) {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println(label.toUpperCase() + " SOLUTION");
        System.out.println("--------------------------------------------------");

        double[] solution = result.getSolution();
        int valuesToPrint = Math.min(solution.length, 10);

        for (int i = 0; i < valuesToPrint; i++) {
            System.out.printf("x[%-4d] = %12.6f%n", i, solution[i]);
        }

        if (solution.length > valuesToPrint) {
            System.out.println("...");
            System.out.printf("x[%-4d] = %12.6f%n", solution.length - 1, solution[solution.length - 1]);
        }

        System.out.printf("%s time: %.6f ms%n", label, result.getExecutionTimeMs());
    }

    private static void printPerformanceSummary(
            SolutionResult sequentialResult,
            SolutionResult parallelResult,
            double speedup
    ) {
        double sequentialMs = sequentialResult.getExecutionTimeMs();
        double parallelMs = parallelResult.getExecutionTimeMs();
        double slowdown = parallelMs / sequentialMs;

        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("PERFORMANCE SUMMARY");
        System.out.println("--------------------------------------------------");
        System.out.printf("Sequential time : %12.6f ms%n", sequentialMs);
        System.out.printf("Parallel time   : %12.6f ms%n", parallelMs);
        System.out.printf("Speedup         : %12.4f%n", speedup);

        if (speedup > 1.0) {
            System.out.printf("Result          : parallel version is faster by %.2f times%n", speedup);
        } else if (Math.abs(speedup - 1.0) < 1e-9) {
            System.out.println("Result          : both versions have nearly identical speed");
        } else {
            System.out.printf("Result          : parallel version is slower by %.2f times%n", slowdown);
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            scanner.next();
            printWarning("Please enter an integer value.");
        }
    }

    private static void printSuccess(String message) {
        System.out.println("[OK] " + message);
    }

    private static void printWarning(String message) {
        System.out.println("[WARN] " + message);
    }

    private static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int leftPadding = (width - text.length()) / 2;
        return " ".repeat(leftPadding) + text;
    }
}