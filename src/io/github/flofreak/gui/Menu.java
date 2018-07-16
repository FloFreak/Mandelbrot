/*
 * Menu
 * v2
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.gui;

import io.github.flofreak.algorithms.Julia;
import io.github.flofreak.algorithms.Mandelbrot;
import io.github.flofreak.threads.CalculationThread;
import io.github.flofreak.utilities.ImageUtilities;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The class for the MenuBar
 *
 * @author florian.warnke
 * @version v2
 */
class Menu extends JMenuBar {
    //All Menu items
    private final JMenuItem jMenuItemSave;
    private final JMenuItem jMenuItemClockwise;
    private final JMenuItem jMenuItemCounterClockwise;
    private final JMenuItem jMenuItemRecalculate;
    private final JMenuItem jMenuItemReset;
    private final JRadioButtonMenuItem jRadioButtonMenuItemMandel;
    private final JRadioButtonMenuItem jRadioButtonMenuItemJulia;

    /**
     * Constructor for the Menu
     * Generates the menu
     */
    Menu() {
        //The picture menu
        JMenu jMenuPicture = new JMenu("Picture");
        //Submenu
        jMenuItemSave = new JMenuItem("Save");
        jMenuItemClockwise = new JMenuItem("Rotate Clockwise");
        jMenuItemCounterClockwise = new JMenuItem("Rotate Counterclockwise");

        //The algorithm menu
        JMenu jMenuAlgorithm = new JMenu("Algorithm");
        //Submenu
        jMenuItemRecalculate = new JMenuItem("Recalculate");
        jMenuItemReset = new JMenuItem("Reset");
        jRadioButtonMenuItemMandel = new JRadioButtonMenuItem("Mandelbrot");
        jRadioButtonMenuItemJulia = new JRadioButtonMenuItem("Julia");

        //Build the picture menu.
        jMenuPicture.add(jMenuItemSave);
        jMenuPicture.addSeparator();
        jMenuPicture.add(jMenuItemClockwise);
        jMenuPicture.add(jMenuItemCounterClockwise);
        add(jMenuPicture);

        //Algorithm group
        ButtonGroup group = new ButtonGroup();
        jRadioButtonMenuItemMandel.setSelected(true);

        group.add(jRadioButtonMenuItemMandel);
        group.add(jRadioButtonMenuItemJulia);

        //Build second menu in the menu bar.
        jMenuAlgorithm.add(jMenuItemRecalculate);
        jMenuAlgorithm.add(jMenuItemReset);
        jMenuAlgorithm.addSeparator();
        jMenuAlgorithm.add(jRadioButtonMenuItemMandel);
        jMenuAlgorithm.add(jRadioButtonMenuItemJulia);
        add(jMenuAlgorithm);

        //Adds shortcuts
        initAccelerators();

        //Adds Listener
        initActionListener();
    }

    /**
     * Sets for each menu item its Event
     */
    private void initActionListener() {

        //Save
        jMenuItemSave.addActionListener(e ->
                ImageUtilities.saveImageAsJPG(jMenuItemSave, GUI.getImage())
        );

        //Rotate clockwise
        jMenuItemClockwise.addActionListener(e ->
                GUI.setImage(ImageUtilities.turnClockwise(GUI.getImage()))
        );

        //Rotate counter clockwise
        jMenuItemCounterClockwise.addActionListener(e ->
                GUI.setImage(ImageUtilities.turnCounterClockwise(GUI.getImage()))
        );

        //Recalculate
        jMenuItemRecalculate.addActionListener(e ->
                GUI.setImage(GUI.getAlgorithm().calculate())
        );

        //Reset
        jMenuItemReset.addActionListener(e -> {
            GUI.rightPanel.setToDefaultValues();
            (new Thread(new CalculationThread())).start();
        });

        //Mandelbrot
        jRadioButtonMenuItemMandel.addActionListener(e -> GUI.setAlgorithm(new Mandelbrot()));

        //Julia
        jRadioButtonMenuItemJulia.addActionListener(e -> GUI.setAlgorithm(new Julia()));
    }

    /**
     * Adds all Keyboard shortcuts
     */
    private void initAccelerators() {
        //Save CTRL-S
        jMenuItemSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_MASK));

        //Rotate Clockwise CTRL-RIGHT
        jMenuItemClockwise.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_RIGHT,
                InputEvent.CTRL_MASK));

        //Roate counter clockwise CTRL-LEFT
        jMenuItemCounterClockwise.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_LEFT,
                InputEvent.CTRL_MASK));

        //Recalculate CTRL-R
        jMenuItemRecalculate.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R,
                InputEvent.CTRL_MASK));

        //Reset CTRL-N
        jMenuItemReset.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N,
                InputEvent.CTRL_MASK));

        //Mandelbrot CTRL-M
        jRadioButtonMenuItemMandel.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_M,
                InputEvent.CTRL_MASK));

        //Julia CTRL-J
        jRadioButtonMenuItemJulia.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_J,
                InputEvent.CTRL_MASK));
    }
}
