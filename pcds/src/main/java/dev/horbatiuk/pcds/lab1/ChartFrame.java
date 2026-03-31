package dev.horbatiuk.pcds.lab1;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;

public class ChartFrame extends JFrame {

    public ChartFrame(List<PerformancePoint> points, double sequentialTimeMs) {
        setTitle("Speedup chart");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new ChartPanel(points, sequentialTimeMs));
    }

    private static class ChartPanel extends JPanel {
        private final List<PerformancePoint> points;
        private final double sequentialTimeMs;

        public ChartPanel(List<PerformancePoint> points, double sequentialTimeMs) {
            this.points = points;
            this.sequentialTimeMs = sequentialTimeMs;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (points == null || points.isEmpty()) {
                g.drawString("No data to display", 50, 50);
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int left = 70;
            int right = 40;
            int top = 50;
            int bottom = 70;

            int width = getWidth() - left - right;
            int height = getHeight() - top - bottom;

            int x0 = left;
            int y0 = getHeight() - bottom;

            g2.setColor(Color.BLACK);
            g2.drawLine(x0, y0, x0 + width, y0);
            g2.drawLine(x0, y0, x0, top);

            double maxY = 1.0;
            for (PerformancePoint p : points) {
                maxY = Math.max(maxY, p.getSpeedup());
            }
            maxY *= 1.15;

            int maxThreads = points.stream()
                    .mapToInt(PerformancePoint::getThreadCount)
                    .max()
                    .orElse(1);

            for (int i = 0; i <= 10; i++) {
                double value = maxY * i / 10.0;
                int y = y0 - (int) (height * i / 10.0);

                g2.setColor(new Color(220, 220, 220));
                g2.drawLine(x0, y, x0 + width, y);

                g2.setColor(Color.BLACK);
                g2.drawString(String.format(Locale.US, "%.2f", value), 20, y + 5);
            }

            for (int t = 1; t <= maxThreads; t++) {
                int x = x0 + (int) ((t - 1) * (width / (double) Math.max(1, maxThreads - 1)));

                g2.setColor(new Color(230, 230, 230));
                g2.drawLine(x, y0, x, top);

                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(t), x - 5, y0 + 20);
            }

            g2.drawString("Threads", x0 + width / 2 - 20, getHeight() - 25);
            g2.drawString("Speedup", 10, top - 10);

            g2.setColor(Color.RED);
            int yIdeal = y0 - (int) (height * (1.0 / maxY));
            g2.drawLine(x0, yIdeal, x0 + width, yIdeal);
            g2.drawString("Speedup = 1", x0 + width - 90, yIdeal - 5);

            g2.setColor(new Color(40, 90, 180));

            int prevX = -1;
            int prevY = -1;

            for (PerformancePoint p : points) {
                int x = x0 + (int) ((p.getThreadCount() - 1) * (width / (double) Math.max(1, maxThreads - 1)));
                int y = y0 - (int) (height * (p.getSpeedup() / maxY));

                if (prevX != -1) {
                    g2.drawLine(prevX, prevY, x, y);
                }

                g2.fillOval(x - 4, y - 4, 8, 8);
                g2.drawString(String.format(Locale.US, "%.2f", p.getSpeedup()), x - 12, y - 10);

                prevX = x;
                prevY = y;
            }

            g2.setColor(Color.BLACK);
            g2.drawString(String.format(Locale.US, "Sequential time: %.6f ms", sequentialTimeMs), x0, 25);
        }
    }
}