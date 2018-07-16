/*
 * BaseAlgorithm
 * v1
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.algorithms;

import io.github.flofreak.gui.GUI;

import java.awt.image.BufferedImage;

/**
 * Interface for algorithms to fits the GUI
 */
public interface BaseAlgorithm {
    //Constants
    int WIDTH = Integer.parseInt(GUI.getCfg().getProperty("imgWidth"));
    int HEIGHT = Integer.parseInt(GUI.getCfg().getProperty("imgHeight"));
    int MAX = Integer.parseInt(GUI.getCfg().getProperty("imgMaxIterations"));

    GUI gui = GUI.gui;

    BufferedImage calculate();
}
