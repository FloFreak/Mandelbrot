/*
 * GUI
 * v2.3.3
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.gui;

//Imports

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.algorithms.Mandelbrot;
import io.github.flofreak.threads.CalculationThread;
import io.github.flofreak.utilities.Configuration;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * The GUI class where everything is displayed
 * The main class
 *
 * @author florian.warnke
 * @version v2.3.3
 */
public class GUI extends JFrame {
    //Config
    public static final Configuration cfg = new Configuration(); //The configuration class

    //Declaration of all GUI elements
    public static GUI gui;                          //The GUI
    //Declaration of the algorithm parts
    private static BaseAlgorithm algorithm;           //The BaseAlgorithm algorithm
    private static BufferedImage image;                   //The image, in which will be drawn

    private final Menu jMenuBar;

    private static LeftPanel leftPanel;
    public static RightPanel rightPanel;

    /**
     * Constructor for the GUI
     * <p>
     * Creates a frame and adds all elements
     * Instantiate the algorithm algorithm
     */
    private GUI() {
        //Initialize all global variables
        gui = this;
        algorithm = new Mandelbrot();

        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();

        jMenuBar = new Menu();

        //Specifies the frame
        this.setTitle(cfg.getProperty("title"));
        //The height of the GUI
        int HEIGHT = Integer.parseInt(cfg.getProperty("GUIHeight"));//Constants for GUI size
//The width of the GUI
        int WIDTH = Integer.parseInt(cfg.getProperty("GUIWidth"));
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Adds all Components to the frame
        initComponents();

        //Calculates the Image in a new Thread
        Thread thread = new Thread(new CalculationThread());
        thread.start();

        this.setVisible(true);
    }

    /**
     * The MAIN
     *
     * @param args None
     */
    public static void main(String[] args) {
        new GUI();
    }

    /**
     * Instantiate all components
     * Specifies all components
     * Adds listeners
     * Arrange all components
     */
    private void initComponents() {
        //Initialize all components
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        //Adds both panel to one split panel, for a split view
        jSplitPane.setDividerLocation(BaseAlgorithm.WIDTH);
        jSplitPane.setLeftComponent(leftPanel);
        jSplitPane.setRightComponent(rightPanel);

        //Adds the split panel to the frame
        this.add(jSplitPane);
        this.setJMenuBar(jMenuBar);

    }

    /**
     * Saves the image to the global variable
     * Displays the image on the GUI
     *
     * @param newImage the image which should be processed
     */
    public static void setImage(BufferedImage newImage) {
        //Saves the image global
        image = newImage;
        //Shows the image on the GUI
        leftPanel.getJLabelPicture().setIcon(new ImageIcon(image));
        //Hides the calculating label
        rightPanel.jLabelLoading.setVisible(false);
    }

    /**
     * @return (double) the zoom
     */
    public double getZoom() {
        return (double) rightPanel.jTextFieldZoom.getValue();
    }

    /**
     * @return (double) the minimum of the imaginary axis
     */
    public double getMinImag() {
        return (double) rightPanel.jTextFieldMinImag.getValue();
    }

    /**
     * @return (double) the maximum of the imaginary axis
     */
    public double getMaxImag() {
        return (double) rightPanel.jTextFieldMaxImag.getValue();
    }

    /**
     * @return (double) the minimum of the real axis
     */
    public double getMinReal() {
        return (double) rightPanel.jTextFieldMinReal.getValue();
    }

    /**
     * @return (double) the maximum of the real axis
     */
    public double getMaxReal() {
        return (double) rightPanel.jTextFieldMaxReal.getValue();
    }

    public static void setMinReal(double value) {
        rightPanel.jTextFieldMinReal.setValue(value);
    }

    public static void setMaxReal(double value) {
        rightPanel.jTextFieldMaxReal.setValue(value);
    }

    public static void setMinImag(double value) {
        rightPanel.jTextFieldMinImag.setValue(value);
    }

    public static void setMaxImag(double value) {
        rightPanel.jTextFieldMaxImag.setValue(value);
    }

    static void setZoom(double value) {
        rightPanel.jTextFieldZoom.setValue(value);
    }

    static BufferedImage getImage() {return image;}

    public BaseAlgorithm getAlgorithm() {
        return algorithm;
    }

    static void setAlgorithm(BaseAlgorithm newAlgorithm) {
        if (!algorithm.getClass().equals(newAlgorithm.getClass())) {
            algorithm = newAlgorithm;

            //Calculates the Image in a new Thread
            Thread thread = new Thread(new CalculationThread());
            thread.start();

        }
    }

}
