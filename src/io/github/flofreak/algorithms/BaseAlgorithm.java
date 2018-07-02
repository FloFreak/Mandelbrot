package io.github.flofreak.algorithms;

import io.github.flofreak.GUI;

import java.awt.image.BufferedImage;

public abstract class BaseAlgorithm {
    public static final int WIDTH = Integer.parseInt(GUI.cfg.getProperty("imgWidth"));
    public static final int HEIGHT = Integer.parseInt(GUI.cfg.getProperty("imgHeight"));

    public abstract BufferedImage calculate();
}
