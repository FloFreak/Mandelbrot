package io.github.flofreak;

import java.awt.image.BufferedImage;

class Mandelbrot {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    private static final int MAX = 1000;

    //public static BufferedImage calculate(double pos_x, double pos_y, double zoom) {
    static BufferedImage calculate() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        int black = 0x000000, white = 0xFFFFFF;

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                double c_re = (col - WIDTH / 2) * 2.0 / WIDTH;
                double c_im = (row - HEIGHT / 2) * 2.0 / WIDTH;
                double x = 0, y = 0;
                int iterations = 0;
                while (x * x + y * y < 6 && iterations < MAX) {
                    double x_new = x * x - y * y + c_re;
                    y = 2 * x * y + c_im;
                    x = x_new;
                    iterations++;
                }
                if (iterations < MAX) image.setRGB(col, row, white);
                else image.setRGB(col, row, black);
            }
        }
        return image;
    }
}
