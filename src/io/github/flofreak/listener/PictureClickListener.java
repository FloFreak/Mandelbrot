/*
 * PictureClickListener
 * v1.0
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.listener;

import io.github.flofreak.GUI;
import io.github.flofreak.threads.CalculationThread;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The listener for clicking the picture
 * todo Comments
 */
public class PictureClickListener implements MouseListener {

    @Override
    /*todo add the ability to click on image to recenter*/
    public void mouseClicked(MouseEvent e) {
        int eX = e.getX();
        int eY = e.getY();

        double nX, nY;

        double yAxes = Double.parseDouble(GUI.cfg.getProperty("imgHeight")) / 2;
        double xAxes = Double.parseDouble(GUI.cfg.getProperty("imgWidth")) / 2;

        double percWidth = eX / Double.parseDouble(GUI.cfg.getProperty("imgWidth"));
        percWidth = percWidth * 2;
        if (eX < yAxes) {
            percWidth -= 1;
            nX = ((GUI.gui.getMaxReal() * percWidth) + GUI.gui.getAlgorithm().getRealCenter());
        } else {
            nX = (GUI.gui.getMinReal() + (GUI.gui.getMaxReal() * percWidth) + GUI.gui.getAlgorithm().getRealCenter());
        }

        double percHeight = eY / Double.parseDouble(GUI.cfg.getProperty("imgHeight"));
        percHeight = percHeight * 2;
        if (eY < xAxes) {
            percHeight -= 1;
            nY = ((GUI.gui.getMaxImag() * percHeight) + GUI.gui.getAlgorithm().getImagCenter());
        } else {
            nY = (GUI.gui.getMinImag() + (GUI.gui.getMaxImag() * percHeight) + GUI.gui.getAlgorithm().getImagCenter());
        }

        GUI.gui.getAlgorithm().setImagCenter(nY);
        GUI.gui.getAlgorithm().setRealCenter(nX);
        Thread thread = new Thread(new CalculationThread());
        thread.start();
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
