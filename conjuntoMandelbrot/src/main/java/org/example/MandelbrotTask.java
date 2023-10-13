package org.example;


import java.awt.*;
import java.awt.image.BufferedImage;


class MandelbrotTask implements Runnable {
    private final BufferedImage image;
    private final int startY, endY, width, height, maxIterations;

    MandelbrotTask(BufferedImage image, int startY, int endY, int width, int height, int maxIterations) {
        this.image = image;
        this.startY = startY;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
    }

    @Override
    public void run() {
        for (int x = 0; x < width; x++) {
            for (int y = startY; y < endY; y++) {
                // Lógica de cálculo del conjunto de Mandelbrot
                double zx, zy, cX, cY;
                zx = zy = 0;
                cX = (x - width / 2.0) * 4.0 / width;
                cY = (y - height / 2.0) * 4.0 / height;
                int iter = maxIterations;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                int color = Color.HSBtoRGB((maxIterations / (float) iter) % 1, 1, iter > 0 ? 1 : 0);
                image.setRGB(x, y, color);
            }
        }
    }
}

