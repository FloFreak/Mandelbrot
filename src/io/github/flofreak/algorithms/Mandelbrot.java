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
 */
public class Mandelbrot implements BaseAlgorithm {

    private final int[] colors = new int[MAX]; //The color for each iteration

    /**
     * Constructor for the Mandelbrot
     *
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
        //Factors for each axe
        double realfactor = (gui.getMaxReal() - gui.getMinReal()) / WIDTH ;
        double imgfactor = (gui.getMaxImag() - gui.getMinImag()) / HEIGHT ;

        //Image in which will be drawn
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        //Runs each pixel from the image
        for (int row = 0; row < HEIGHT; row ++) {
            for (int column = 0; column < WIDTH; column ++) {

                //Coordinate as complex number 'C'
                double c_real = gui.getMinReal() + (column * realfactor);
                double c_img = gui.getMaxImag() - (row * imgfactor);

                //Iteration complex number 'Z'
                double z_real = 0;
                double z_img = 0;

                // Iterate
                int iterations = 0;

                //Less than max iterations and Mandelbrot
                while (iterations < MAX && (z_real * z_real + z_img * z_img <= 4)) {

                    //Calculation of the new complex number
                    double new_z_real = z_real * z_real - z_img * z_img + c_real;
                    double new_z_img = 2 * z_real * z_img + c_img;

                    // Next iteration
                    z_real = new_z_real;
                    z_img = new_z_img;

                    iterations ++;
                }

                // Get color based on the number of iterations
                if (iterations == MAX) image.setRGB(column, row, 0x000000); // Black
                else image.setRGB(column, row, colors[iterations]);
            }
        }
        return image;
    }
}
