package io.github.flofreak.listener;

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.gui.GUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CoordinateListener implements MouseMotionListener {

    private static final int FACTOR = 100;

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double column = e.getX();
        double row = e.getY();

        double maxreal = GUI.gui.getMaxReal();
        double minreal = GUI.gui.getMinReal();
        double maximg = GUI.gui.getMaxImag();
        double minimg = GUI.gui.getMinImag();


        double realfactor = (maxreal - minreal) / BaseAlgorithm.WIDTH;
        double imgfactor = (maximg - minimg) / BaseAlgorithm.HEIGHT;

        double x = Math.round((minreal + (column * realfactor)) * FACTOR);
        x /= FACTOR;
        double y = Math.round((maximg - (row * imgfactor)) * FACTOR);
        y /= FACTOR;

        ((JLabel) e.getSource()).setToolTipText(x + " | " + y);
    }
}
