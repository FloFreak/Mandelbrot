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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener for clicking the picture
 *
 * @author florian.warnke
 * @version v2
 */
public class PictureClickListener implements MouseListener {

    /**
     * Calculation of new center and axis on click
     * @param e MouseEvent which is permormed on click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //Double click
        if (e.getClickCount() == 2) {
            //Coordinates
            double column = e.getX();
            double row = e.getY();

            //All inputs from GUI
            double zoom = GUI.gui.getZoom();
            double maxreal = GUI.gui.getMaxReal();
            double minreal = GUI.gui.getMinReal();
            double maximg = GUI.gui.getMaxImag();
            double minimg = GUI.gui.getMinImag();

            //Calculation of the Factors
            double realfactor = (maxreal - minreal) / BaseAlgorithm.WIDTH;
            double imgfactor = (maximg - minimg) / BaseAlgorithm.HEIGHT;

            //New center in the Mandelbrot fractal
            double x = minreal + (column * realfactor);
            double y = maximg - (row * imgfactor);

            //Zoom in or out
            double difY = 0;
            double difX = 0;
            if (e.getButton() == 1) {
                difX = (maxreal - minreal) / (zoom * 2);
                difY = (maximg - minimg) / (zoom * 2);
            } else if (e.getButton() == 3) {
                difX = (maxreal - minreal) * (zoom / 2);
                difY = (maximg - minimg) * (zoom / 2);
            }

            //Calculation for new axis
            maxreal = x + difX;
            minreal = x - difX;
            maximg = y + difY;
            minimg = y - difY;

            //Setting the new axis
            GUI.setMinImag(minimg);
            GUI.setMaxImag(maximg);
            GUI.setMinReal(minreal);
            GUI.setMaxReal(maxreal);

            //Drawing the image
            GUI.setImage(GUI.getAlgorithm().calculate());
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
