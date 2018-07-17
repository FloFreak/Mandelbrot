/*
 * Mandelbrot
 * v2
 * 16. Juli 2018
 * Copyright (C) 2018 Max Dunger
 * All rights reserved.
 */
package io.github.flofreak.algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Mandelbrot Algorithm
 *
 * @author max.dunger
 * @version v2
 */
public class Mandelbrot implements BaseAlgorithm {

    private final int[] colors = new int[MAX]; //The color for each iteration

    /**
     * Constructor for the Mandelbrot
     * <p>
     * Calculates and draws the Mandelbrot fractal
     */
    public Mandelbrot() {
        for (int i = 0; i < MAX; i++)
            colors[i] = Color.HSBtoRGB(i / 120f, 1, i / (i + 5f));
    }

    /**
     * Calculates the Mandelbrot set
     * Draws the set
     *
     * @return BufferedImage with the drawn Mandelbrot set
     */
    public BufferedImage calculate() {
        //get the Values of the coordinate system
        double maxReal = gui.getMaxReal();
        double minReal = gui.getMinReal();
        double maxImg = gui.getMaxImag();
        double minImg = gui.getMinImag();

        //calculate the width/height of one pixel
        double realFactor = (maxReal - minReal) / WIDTH;
        double imgFactor = (maxImg - minImg) / HEIGHT;


        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        //go threw every single pixel in the buffered image
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {

                //get the coordinate value of the special pixel
                double cReal = minReal + (column * realFactor);
                double cImg = maxImg - (row * imgFactor);


                // Iteration variables
                double zReal = 0;
                double zImg = 0;

                // Iterate with the mandelbrot abort condition
                int iterations = 0;
                while (iterations < MAX && (zReal * zReal + zImg * zImg <= 4)) {

                    //use of the mandelbrot formula
                    double newZReal = zReal * zReal - zImg * zImg + cReal;
                    double newZImg = 2 * zReal * zImg + cImg;

                    // Next iteration
                    zReal = newZReal;
                    zImg = newZImg;
                    iterations++;

                }

                // Get palette color based on the number of iterations
                if (iterations == MAX) image.setRGB(column, row, 0x000000); // Black
                else image.setRGB(column, row, colors[iterations]);
            }
        }
        return image;
    }
}
