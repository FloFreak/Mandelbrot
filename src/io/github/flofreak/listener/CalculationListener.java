package io.github.flofreak.listener;

import io.github.flofreak.GUI;
import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.threads.CalculationThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationListener implements ActionListener {

    private final GUI gui;
    private final BaseAlgorithm algorithm;

    public CalculationListener(GUI gui, BaseAlgorithm algorithm) {
        this.gui = gui;
        this.algorithm = algorithm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Shows calculating label
        gui.jLabelLoading.setVisible(true);
        //Stats an anonymous thread, where the image is calculated and drawn
        new CalculationThread(gui, algorithm).start();
    }
}
