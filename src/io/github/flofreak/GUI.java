/*
 * GUI
 * v2.3.3
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak;

//Imports

import io.github.flofreak.algorithms.BaseAlgorithm;
import io.github.flofreak.algorithms.Mandelbrot;
import io.github.flofreak.listener.CalculationListener;
import io.github.flofreak.listener.PictureClickListener;
import io.github.flofreak.threads.CalculationThread;
import io.github.flofreak.utilities.Configuration;
import io.github.flofreak.utilities.GUIUtilities;
import io.github.flofreak.utilities.ImageUtilities;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;

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

    //Constants for GUI size
    private static final int WIDTH = Integer.parseInt(cfg.getProperty("GUIWidth"));   //The width of the GUI
    private static final int HEIGHT = Integer.parseInt(cfg.getProperty("GUIHeight")); //The height of the GUI

    //Declaration of the algorithm parts
    private final BaseAlgorithm algorithm;           //The BaseAlgorithm algorithm
    private BufferedImage image;                   //The image, in which will be drawn

    //Declaration of all GUI elements
    private final GUI gui;                          //The GUI
    private JFormattedTextField jTextFieldMinReal; //The text field for the minimum on the real axis
    private JFormattedTextField jTextFieldMaxReal; //The text field for the maximum on the real axis
    private JFormattedTextField jTextFieldMinImag; //The text field for the minimum on the imaginary axis
    private JFormattedTextField jTextFieldMaxImag; //The text field for the maximum on the imaginary axis
    private JFormattedTextField jTextFieldZoom;    //The text field for the zoom
    private JLabel jLabelPicture;                  //The label in which the image will be displayed
    public JLabel jLabelLoading;                  //The label which shows the calculation is on going

    /**
     * Constructor for the GUI
     * <p>
     * Creates a frame and adds all elements
     * Instantiate the algorithm algorithm
     */
    private GUI() {
        //Initialize all global variables
        this.gui = this;
        this.algorithm = new Mandelbrot(gui);

        //Specifies the frame
        this.setTitle(cfg.getProperty("title"));
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Adds all Components to the frame
        initComponents();

        //Calculates the Image in a new Thread
        new CalculationThread(this, algorithm).start();

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
        JPanel jPanelLeft = new JPanel();
        JPanel jPanelRight = new JPanel();
        jLabelPicture = new JLabel();
        jLabelLoading = new JLabel("Calculating...");
        jTextFieldMaxImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMaxReal = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinReal = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldZoom = new JFormattedTextField(NumberFormat.getNumberInstance());
        JButton jButtonDraw = new JButton("Draw");
        JButton jButtonExport = new JButton("Export");

        //Sets the size to fit the BaseAlgorithm image size
        jPanelLeft.setSize(BaseAlgorithm.WIDTH, BaseAlgorithm.HEIGHT);

        //Adds the action listener to the button
        jButtonDraw.addActionListener(new CalculationListener(gui, algorithm));

        //Adds the action listener to the button
        jButtonExport.addActionListener(e -> ImageUtilities.saveImageAsJPG(((JButton) e.getSource()), image));

        //Sets the picture label visible, that the size is shown, even while it's empty
        jLabelPicture.setVisible(true);
        //Adds mouse listener to picture
        jLabelPicture.addMouseListener(new PictureClickListener(gui, algorithm));

        createTextFields(jPanelRight);

        //Adds all menu components to the menu panel
        jPanelRight.add(jButtonExport);
        jPanelRight.add(jButtonDraw);
        jPanelRight.add(jLabelLoading);

        //Adds the picture label to the picture panel
        jPanelLeft.add(jLabelPicture);

        //Adds both panel to one split panel, for a split view
        jSplitPane.setDividerLocation(BaseAlgorithm.WIDTH);
        jSplitPane.setLeftComponent(jPanelLeft);
        jSplitPane.setRightComponent(jPanelRight);

        //Adds the split panel to the frame
        this.add(jSplitPane);
    }

    /**
     * Saves the image to the global variable
     * Displays the image on the GUI
     *
     * @param image the image which should be processed
     */
    public void setImage(BufferedImage image) {
        //Saves the image global
        this.image = image;
        //Shows the image on the GUI
        jLabelPicture.setIcon(new ImageIcon(this.image));
        //Hides the calculating label
        jLabelLoading.setVisible(false);
    }


    /**
     * Creates all text fields
     * @param jPanel the panel where ro add them
     */
    private void createTextFields(JPanel jPanel) {
        //Creates text fields, witch default value
        GUIUtilities.createDoubleOnlyJTextField(-2.0, jTextFieldMinReal, jTextFieldMinImag);
        GUIUtilities.createDoubleOnlyJTextField(2.0, jTextFieldMaxReal, jTextFieldMaxImag);
        GUIUtilities.createDoubleOnlyJTextField(4.0, jTextFieldZoom);

        //Initialize an group layout for the input fields
        GroupLayout groupLayout = new GroupLayout(jPanel);
        GroupLayout.ParallelGroup horizontalGroup = groupLayout.createParallelGroup();

        //Groups all input fields with its labels
        GUIUtilities.createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Min Imag"), jTextFieldMinImag);
        GUIUtilities.createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Max Imag"), jTextFieldMaxImag);
        GUIUtilities.createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Min Real"), jTextFieldMinReal);
        GUIUtilities.createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Max Real"), jTextFieldMaxReal);
        GUIUtilities.createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Zoom"), jTextFieldZoom);
    }

    /**
     * @return (double) the zoom
     */
    public double getZoom() {
        return (double) jTextFieldZoom.getValue();
    }

    /**
     * @return (double) the minimum of the imaginary axis
     */
    public double getMinImag() {
        return (double) jTextFieldMinImag.getValue();
    }

    /**
     * @return (double) the maximum of the imaginary axis
     */
    public double getMaxImag() {
        return (double) jTextFieldMaxImag.getValue();
    }

    /**
     * @return (double) the minimum of the real axis
     */
    public double getMinReal() {
        return (double) jTextFieldMinReal.getValue();
    }

    /**
     * @return (double) the maximum of the real axis
     */
    public double getMaxReal() {
        return (double) jTextFieldMaxReal.getValue();
    }

}
