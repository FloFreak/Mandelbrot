/*
 * GUI
 * v3
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.gui;

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
 * @version v3
 */
public class GUI extends JFrame {
    private static final Configuration cfg = new Configuration(); //The configuration class
    //All static Classes
    public static GUI gui;                                        //The GUI
    public static RightPanel rightPanel;                          //The right part of the GUI
    private static BaseAlgorithm algorithm;                       //The algorithm to be run
    private static BufferedImage image;                           //The image, which will be drawn
    private static LeftPanel leftPanel;                           //The left part of the GUI
    private final Menu jMenuBar;                                  //The menu bar

    /**
     * Constructor for the GUI
     * <p>
     * It is the frame and adds all Panels to it
     * Instantiate and run the algorithm
     */
    private GUI() {
        //Initialize all global variables
        gui = this;
        algorithm = new Mandelbrot();

        leftPanel = new LeftPanel();
        rightPanel = new RightPanel();

        jMenuBar = new Menu();

        //Specifies the frame
        this.setTitle(cfg.getProperty("title"));                    //Sets the title from the properties file
        this.setSize(
                Integer.parseInt(cfg.getProperty("GUIWidth")),      //Sets the height from the properties file
                Integer.parseInt(cfg.getProperty("GUIHeight"))      //Sets the width from the properties file
        );
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        rightPanel.setToDefaultValues();

        //Loads the structure elements for the GUI
        initComponents();

        //Calculates the Image in a new Thread
        (new Thread(new CalculationThread())).start();

        this.setVisible(true);
    }

    /**
     * The main
     * instantiate GUI without reference
     *
     * @param args None
     */
    public static void main(String[] args) {
        new GUI();
    }

    /**
     * Getter for the image which is displayed
     *
     * @return BufferedImage which is displayed
     */
    static BufferedImage getImage() {
        return image;
    }

    /**
     * Setter for image
     * Stores the image to the global variable
     * Displays the image on the GUI
     *
     * @param newImage BufferedImage which should be displayed
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
     * Getter for the Configuration
     *
     * @return Configuration from the Project
     */
    public static Configuration getCfg() {
        return cfg;
    }

    /**
     * Instantiate all structure components
     * Arrange those components
     */
    private void initComponents() {
        //Initialize the first Pane
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        //Adds both panel to one split panel, for a split view
        jSplitPane.setDividerLocation(BaseAlgorithm.WIDTH);
        jSplitPane.setLeftComponent(leftPanel);
        jSplitPane.setRightComponent(rightPanel);

        //Adds the split panel and the menu bar to the frame
        this.add(jSplitPane);
        this.setJMenuBar(jMenuBar);

    }

    /**
     * Getter for the zoom textfield
     *
     * @return double
     */
    public double getZoom() {
        return (double) rightPanel.jTextFieldZoom.getValue();
    }

    /**
     * Setter for the zoom textfield
     *
     * @param value double
     */
    static void setZoom(double value) {
        rightPanel.jTextFieldZoom.setValue(value);
    }

    /**
     * Getter for the Min Imag textfield
     *
     * @return double
     */
    public double getMinImag() {
        return (double) rightPanel.jTextFieldMinImag.getValue();
    }

    /**
     * Setter for the Min Imag textfield
     *
     * @param value double
     */
    public static void setMinImag(double value) {
        rightPanel.jTextFieldMinImag.setValue(value);
    }

    /**
     * Getter for the Max Imag textfield
     *
     * @return double
     */
    public double getMaxImag() {
        return (double) rightPanel.jTextFieldMaxImag.getValue();
    }

    /**
     * Setter for the Max Imag textfield
     *
     * @param value double
     */
    public static void setMaxImag(double value) {
        rightPanel.jTextFieldMaxImag.setValue(value);
    }

    /**
     * Getter for the Min Real textfield
     *
     * @return double
     */
    public double getMinReal() {
        return (double) rightPanel.jTextFieldMinReal.getValue();
    }

    /**
     * Setter for the MinReal textfield
     *
     * @param value double
     */
    public static void setMinReal(double value) {
        rightPanel.jTextFieldMinReal.setValue(value);
    }

    /**
     * Getter for the Max Real textfield
     *
     * @return double
     */
    public double getMaxReal() {
        return (double) rightPanel.jTextFieldMaxReal.getValue();
    }

    /**
     * Setter for the Max Real textfield
     *
     * @param value double
     */
    public static void setMaxReal(double value) {
        rightPanel.jTextFieldMaxReal.setValue(value);
    }

    /**
     * Getter for the algortihm which is running
     *
     * @return BaseAlgorithm
     */
    public static BaseAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Setter for the algorithm to be executed
     * Execution for the algortihm
     *
     * @param newAlgorithm BaseAlgorithm
     */
    static void setAlgorithm(BaseAlgorithm newAlgorithm) {
        if (!algorithm.getClass().equals(newAlgorithm.getClass())) {
            algorithm = newAlgorithm;

            //Calculates the Image in a new Thread
            (new Thread(new CalculationThread())).start();
        }
    }

}
