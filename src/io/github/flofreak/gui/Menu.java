package io.github.flofreak.gui;

import io.github.flofreak.algorithms.Julia;
import io.github.flofreak.algorithms.Mandelbrot;
import io.github.flofreak.threads.CalculationThread;
import io.github.flofreak.utilities.ImageUtilities;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class Menu extends JMenuBar {
    private final JMenuItem jMenuItemSave;
    private final JMenuItem jMenuItemClockwise;
    private final JMenuItem jMenuItemCounterClockwise;
    private final JMenuItem jMenuItemRecalculate;
    private final JMenuItem jMenuItemReset;
    private final JRadioButtonMenuItem jRadioButtonMenuItemMandel;
    private final JRadioButtonMenuItem jRadioButtonMenuItemJulia;

    Menu() {
        JMenu jMenuPicture = new JMenu("Picture");
        jMenuItemSave = new JMenuItem("Save");
        jMenuItemClockwise = new JMenuItem("Rotate Clockwise");
        jMenuItemCounterClockwise = new JMenuItem("Rotate Counterclockwise");

        JMenu jMenuAlgorithm = new JMenu("Algorithm");
        jMenuItemRecalculate = new JMenuItem("Recalculate");
        jMenuItemReset = new JMenuItem("Reset");
        jRadioButtonMenuItemMandel = new JRadioButtonMenuItem("Mandelbrot");
        jRadioButtonMenuItemJulia = new JRadioButtonMenuItem("Julia");

        //Build the first menu.
        jMenuPicture.add(jMenuItemSave);
        jMenuPicture.addSeparator();
        jMenuPicture.add(jMenuItemClockwise);
        jMenuPicture.add(jMenuItemCounterClockwise);
        add(jMenuPicture);

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

        initAccelerators();

        initActionListener();
    }

    private void initActionListener() {

        jMenuItemSave.addActionListener(e ->
                ImageUtilities.saveImageAsJPG(jMenuItemSave, GUI.getImage())
        );

        jMenuItemClockwise.addActionListener(e ->
                GUI.setImage(ImageUtilities.turnClockwise(GUI.getImage()))
        );

        jMenuItemCounterClockwise.addActionListener(e ->
                GUI.setImage(ImageUtilities.turnCounterClockwise(GUI.getImage()))
        );

        jMenuItemRecalculate.addActionListener(e -> {
            Thread thread = new Thread(new CalculationThread());
            thread.start();
        });

        jMenuItemReset.addActionListener(e -> {
            GUI.rightPanel.setDefaultValues();
            Thread thread = new Thread(new CalculationThread());
            thread.start();
        });

        jRadioButtonMenuItemMandel.addActionListener(e -> GUI.setAlgorithm(new Mandelbrot()));

        jRadioButtonMenuItemJulia.addActionListener(e -> GUI.setAlgorithm(new Julia()));
    }

    private void initAccelerators() {
        jMenuItemSave.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_MASK));

        jMenuItemClockwise.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_RIGHT,
                InputEvent.CTRL_MASK));

        jMenuItemCounterClockwise.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_LEFT,
                InputEvent.CTRL_MASK));

        jMenuItemRecalculate.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R,
                InputEvent.CTRL_MASK));

        jMenuItemReset.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N,
                InputEvent.CTRL_MASK));

        jRadioButtonMenuItemMandel.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_M,
                InputEvent.CTRL_MASK));

        jRadioButtonMenuItemJulia.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_J,
                InputEvent.CTRL_MASK));
    }
}
