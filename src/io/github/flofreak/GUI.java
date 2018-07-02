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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private JLabel jLabelLoading;                  //The label which shows the calculation is on going

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
     * Opens a File Chooser on button click
     * Saves the image to the selected path
     *
     * @param parent the button on which click the save is performed
     * @param image  the image which should be saved
     */
    private void saveImageAsJPG(JButton parent, BufferedImage image) {
        //Instantiate the file chooser
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle(cfg.getProperty("title") + " - Export");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JPG Files", "jpg"));
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setSelectedFile(new File(cfg.getProperty("title") + ".jpg"));

        //On submit from the file chooser
        if (jFileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                //Writes the image as 'BaseAlgorithm.jpg' to the selected path
                File file = (jFileChooser.getSelectedFile().getName().endsWith(".jpg")
                        ? jFileChooser.getSelectedFile()
                        : new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".jpg"));
                ImageIO.write(image, "jpg", file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
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
        jButtonDraw.addActionListener(e -> {
            //Shows calculating label
            jLabelLoading.setVisible(true);
            //Stats an anonymous thread, where the image is calculated and drawn
            new CalculationThread(gui, algorithm).start();
        });

        //Adds the action listener to the button
        jButtonExport.addActionListener(e -> saveImageAsJPG(((JButton) e.getSource()), image));

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
    void setImage(BufferedImage image) {
        //Saves the image global
        this.image = image;
        //Shows the image on the GUI
        jLabelPicture.setIcon(new ImageIcon(this.image));
        //Hides the calculating label
        jLabelLoading.setVisible(false);
    }

    /**
     * Changes the text fields to only accept doubles
     *
     * @param value  the default value
     * @param fields the text fields
     */
    private void createDoubleOnlyJTextField(double value, JFormattedTextField... fields) {

        //Loops all text fields
        for (JFormattedTextField field : fields) {
            //Sets default value
            field.setValue(value);
            //Sets columns
            field.setColumns(10);
            //Adds listener which converts, if possible all inputs to an double
            field.addPropertyChangeListener("value",
                    e -> ((JFormattedTextField) e.getSource()).setValue(((Number) e.getNewValue()).doubleValue()));
        }
    }

    /**
     * Adds to a horizontal group in the group layout
     *
     * @param groupLayout     the group layout where to add to
     * @param horizontalGroup the horizontal group
     * @param components      the components to add to one horizontal group
     */
    private void createHorizontalGroup(GroupLayout groupLayout, GroupLayout.ParallelGroup horizontalGroup,
                                       Component... components) {
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();

        for (Component component : components) {
            sequentialGroup.addComponent(component);
        }

        horizontalGroup.addGroup(sequentialGroup);
    }

    /**
     * Creates all text fields
     * @param jPanel the panel where ro add them
     */
    private void createTextFields(JPanel jPanel) {
        //Creates text fields, witch default value
        createDoubleOnlyJTextField(-2.0, jTextFieldMinReal, jTextFieldMinImag);
        createDoubleOnlyJTextField(2.0, jTextFieldMaxReal, jTextFieldMaxImag);
        createDoubleOnlyJTextField(4.0, jTextFieldZoom);

        //Initialize an group layout for the input fields
        GroupLayout groupLayout = new GroupLayout(jPanel);
        GroupLayout.ParallelGroup horizontalGroup = groupLayout.createParallelGroup();

        //Groups all input fields with its labels
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Min Imag"), jTextFieldMinImag);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Max Imag"), jTextFieldMaxImag);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Min Real"), jTextFieldMinReal);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Max Real"), jTextFieldMaxReal);
        createHorizontalGroup(groupLayout, horizontalGroup, new JLabel("Zoom"), jTextFieldZoom);
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
