/*
 * Mandelbrot
 * v1.2
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak;

import java.awt.*;
import java.awt.image.BufferedImage;
/*Todo add all comments and other conventions*/
class Mandelbrot {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    private static final int MAX = 1000;

    private static final int[] colors = new int[MAX];

    private final GUI gui;

    Mandelbrot(GUI gui) {
        this.gui = gui;
        for (int i = 0; i < MAX; i++)
            colors[i] = Color.HSBtoRGB(i / 120f, 1, i / (i + 5f));
    }

    BufferedImage calculate() {
        /*todo add the min and max selections*/
        double zoom = gui.getZoom();
        double minImag = gui.getMinImag();
        double maxImag = gui.getMaxImag();
        double minReal = gui.getMinReal();
        double maxReal = gui.getMaxReal();
        System.out.println(minImag + " " + maxImag + " " + minReal + " " + maxReal);

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                double c_re = (col - WIDTH / 2) * zoom / WIDTH;
                double c_im = (row - HEIGHT / 2) * zoom / WIDTH;
                double x = 0, y = 0;
                int iterations = 0;
                while (x * x + y * y < zoom && iterations < MAX) {
                    double x_new = x * x - y * y + c_re;
                    y = 2 * x * y + c_im;
                    x = x_new;
                    iterations++;
                }
                if (iterations < MAX) image.setRGB(col, row, colors[iterations]);
                else image.setRGB(col, row, 0x000000);
            }
        }
        return image;
    }
}
