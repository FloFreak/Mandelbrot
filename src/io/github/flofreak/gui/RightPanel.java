/*
 * RightPanel
 * v4
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.gui;

import io.github.flofreak.listener.CalculationListener;
import io.github.flofreak.utilities.GUIUtilities;
import io.github.flofreak.utilities.ImageUtilities;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * Class for the RightPanel
 * Displays all Inputs, informations and Buttons
 *
 * @author florian.warnke
 * @version v4
 */
public class RightPanel extends JPanel {

    public final JLabel jLabelLoading;           //The label which shows the calculation is on going
    final JFormattedTextField jTextFieldMinReal; //The text field for the minimum on the real axis
    final JFormattedTextField jTextFieldMaxReal; //The text field for the maximum on the real axis
    final JFormattedTextField jTextFieldMinImag; //The text field for the minimum on the imaginary axis
    final JFormattedTextField jTextFieldMaxImag; //The text field for the maximum on the imaginary axis
    final JFormattedTextField jTextFieldZoom;    //The text field for the zoom

    /**
     * Constructor for RightPanel
     * Initialiese all components
     */
    RightPanel() {
        // All Textfields, only accept doubles
        jTextFieldMaxImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMaxReal = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinReal = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldZoom = new JFormattedTextField(NumberFormat.getNumberInstance());

        //Instantiate the Buttons
        JButton jButtonDraw = new JButton("Draw");
        JButton jButtonExport = new JButton("Export");

        //Instantiate the information label
        JLabel jLabelInformation = new JLabel(
                "<html>" +
                        "Double left clicks the image:<br>" +
                        "<ul><li>Zoom in by the<br>" +
                        "zoom factor</li>" +
                        "<li>Set center</li></ul>" +
                        "Double right click the image:<br>" +
                        "<ul><li>Zoom out by the<br>" +
                        "zoom factor</li>" +
                        "<li>Set center</li></ul>" +
                        "Press shift over the image<br>" +
                        "and wait for about 3 seconds:<br>" +
                        "<ul><li>Display the coordinate<br>" +
                        "(X | Y) on the image.<br>" +
                        "A little inconsistent!</li></ul>" +
                    "</html>"
        );

        jLabelLoading = new JLabel("Calculating...");

        //Generates the TextFields to double only and group them with Label
        createTextFields(this);

        //Add Listener to Draw Button
        jButtonDraw.addActionListener(
                new CalculationListener()
        );
        //Add Listener to Export image Button
        jButtonExport.addActionListener(e ->
                ImageUtilities.saveImageAsJPG(
                        ((JButton) e.getSource()),
                        GUI.getImage())
        );

        //Factorize the Font size by 1.5
        GUIUtilities.changeFontSize(
                1.5,
                jLabelLoading, jButtonDraw, jButtonExport
        );

        //Adds all menu components to the menu panel
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(jButtonExport);
        add(jButtonDraw);
        add(jLabelInformation);
        add(jLabelLoading);
    }

    /**
     * Creates all text fields
     * Group them with Labels
     *
     * @param jPanel the panel where ro add them
     */
    private void createTextFields(JPanel jPanel) {
        //Creates text fields, witch default value
        GUIUtilities.changeToDoubleOnlyJTextField(
                jTextFieldMinReal,
                jTextFieldMinImag,
                jTextFieldMaxReal,
                jTextFieldMaxImag,
                jTextFieldZoom
        );

        //Initialize an group layout and Labels for the input fields
        GroupLayout groupLayout = new GroupLayout(jPanel);
        GroupLayout.ParallelGroup verticalgroup = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE);

        JLabel jLabelMinImag = new JLabel("Min Imag");
        JLabel jLabelMaxImag = new JLabel("Max Imag");
        JLabel jLabelMinReal = new JLabel("Min Real");
        JLabel jLabelMaxReal = new JLabel("Max Real");
        JLabel jLabelZoom = new JLabel("Zoomfactor");

        //Factorize the Font size by 1.5
        GUIUtilities.changeFontSize(1.5, jLabelMinImag, jLabelMaxImag, jLabelMinReal, jLabelMaxReal, jLabelZoom);

        //Groups all input fields with its labels
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMinImag, jTextFieldMinImag);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMaxImag, jTextFieldMaxImag);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMinReal, jTextFieldMinReal);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMaxReal, jTextFieldMaxReal);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelZoom, jTextFieldZoom);
    }

    /**
     * Sets all input values to default, as defined in properties
     */
    void setToDefaultValues() {
        GUI.setMaxImag(Double.parseDouble(GUI.getCfg().getProperty("defaultMaxImag")));
        GUI.setMinImag(Double.parseDouble(GUI.getCfg().getProperty("defaultMinImag")));
        GUI.setMaxReal(Double.parseDouble(GUI.getCfg().getProperty("defaultMaxReal")));
        GUI.setMinReal(Double.parseDouble(GUI.getCfg().getProperty("defaultMinReal")));
        GUI.setZoom(Double.parseDouble(GUI.getCfg().getProperty("defaultZoom")));
    }
}
