package org.example;

import javax.swing.*;


public class MandelbrotViewer extends JFrame {

    public MandelbrotViewer() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Conjunto de Mandelbrot (Concurrente)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MandelbrotPanel mandelbrotPanel = new MandelbrotPanel();
        add(mandelbrotPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MandelbrotViewer::new);
    }
}