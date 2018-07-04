package io.github.flofreak.algorithms;

import io.github.flofreak.GUI;

import java.awt.image.BufferedImage;

public interface BaseAlgorithm {
    int WIDTH = Integer.parseInt(GUI.cfg.getProperty("imgWidth"));
    int HEIGHT = Integer.parseInt(GUI.cfg.getProperty("imgHeight"));
    int MAX = Integer.parseInt(GUI.cfg.getProperty("imgMaxIterations"));
    GUI gui = GUI.gui;

    BufferedImage calculate();
}
