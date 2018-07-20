/*
 * PictureClickListener
 * v2
 * 16. Juli 2018
 * Copyright (C) 2018 Max Dunger
 * All rights reserved.
 */
package io.github.flofreak.listener;

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.gui.GUI;
import io.github.flofreak.threads.CalculationThread;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener for clicking the picture
 *
 * @author max.dunger
 * @version v2
 */
public class PictureClickListener implements MouseListener {

    /**
     * Calculation of new center and axis on click
     *
     * @param e MouseEvent which is permormed on click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //Double click
        if (e.getClickCount() == 2) {
            //Coordinates
            double column = e.getX();
            double row = e.getY();

            //get all input data from the user
            double zoom = GUI.gui.getZoom();
            double maxReal = GUI.gui.getMaxReal();
            double minReal = GUI.gui.getMinReal();
            double maxImg = GUI.gui.getMaxImag();
            double minImg = GUI.gui.getMinImag();

            //calculate the width/height of one pixel
            double realFactor = (maxReal - minReal) / BaseAlgorithm.WIDTH;
            double imgFactor = (maxImg - minImg) / BaseAlgorithm.HEIGHT;

            //calculate the coordinate value of the clicked pixel
            double x = minReal + (column * realFactor);
            double y = maxImg - (row * imgFactor);

            //Zoom in or out
            double difY = 0;
            double difX = 0;
            //calculate the new distance of the coordinate min & max
            if (e.getButton() == 1) {
                difX = (maxReal - minReal) / zoom;
                difY = (maxImg - minImg) / zoom;
            } else if (e.getButton() == 3) {
                difX = (maxReal - minReal) * zoom;
                difY = (maxImg - minImg) * zoom;
            }

            //calculate the new coordinate min & max
            double NewMaxReal = x + (difX / 2);
            double NewMinReal = x - (difX / 2);
            double NewMaxImg = y + (difY / 2);
            double NewMinImg = y - (difY / 2);

            //write the new values in the GUI text boxes
            GUI.setMinReal(NewMinReal);
            GUI.setMaxReal(NewMaxReal);
            GUI.setMinImag(NewMinImg);
            GUI.setMaxImag(NewMaxImg);

            //Drawing the image
            (new Thread(new CalculationThread())).start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
