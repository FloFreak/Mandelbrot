/*
 * CoordinateListener
 * v1.5
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.listener;

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.gui.GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * MouseMotionListener to get coordinations
 *
 * @author florian.warnke
 * @version v1.5
 */
public class CoordinateListener implements MouseMotionListener {

    private static final int FACTOR = 100;      //Factor for the diggits after the comma

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Calculates on mouse move the coordinates
     * @param e MouseEvent which is triggered
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        //Shift pressed
        if (e.isShiftDown()) {
            //Get position on image
            double column = e.getX();
            double row = e.getY();

            //Gets all inputs from GUI
            double maxreal = GUI.gui.getMaxReal();
            double minreal = GUI.gui.getMinReal();
            double maximg = GUI.gui.getMaxImag();
            double minimg = GUI.gui.getMinImag();

            //Calculates the factor
            double realfactor = (maxreal - minreal) / BaseAlgorithm.WIDTH;
            double imgfactor = (maximg - minimg) / BaseAlgorithm.HEIGHT;

            //Calculates the actual x and y on image
            double x = Math.round((minreal + (column * realfactor)) * FACTOR);
            x /= FACTOR;
            double y = Math.round((maximg - (row * imgfactor)) * FACTOR);
            y /= FACTOR;

            //Displays the Coordinates as ToolTip
            ((JLabel) e.getSource()).setToolTipText(x + " | " + y);
        }
    }
}
