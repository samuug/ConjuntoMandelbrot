package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MandelbrotPanel extends JPanel {
    private final int width = 800;
    private final int height = 600;
    private final int maxIterations = 1000;
    private BufferedImage image;

    MandelbrotPanel() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        generateMandelbrotConcurrent();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    private void generateMandelbrotConcurrent() {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);

        for (int i = 0; i < processors; i++) {
            int startY = i * height / processors;
            int endY = (i + 1) * height / processors;

            MandelbrotTask task = new MandelbrotTask(image, startY, endY, width, height, maxIterations);
            executorService.submit(task);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

