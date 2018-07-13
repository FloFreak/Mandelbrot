package io.github.flofreak.gui;

import io.github.flofreak.listener.CalculationListener;
import io.github.flofreak.utilities.GUIUtilities;
import io.github.flofreak.utilities.ImageUtilities;

import javax.swing.*;
import java.text.NumberFormat;

public class RightPanel extends JPanel {


    public final JLabel jLabelLoading;           //The label which shows the calculation is on going
    final JFormattedTextField jTextFieldMinReal; //The text field for the minimum on the real axis
    final JFormattedTextField jTextFieldMaxReal; //The text field for the maximum on the real axis
    final JFormattedTextField jTextFieldMinImag; //The text field for the minimum on the imaginary axis
    final JFormattedTextField jTextFieldMaxImag; //The text field for the maximum on the imaginary axis
    final JFormattedTextField jTextFieldZoom;    //The text field for the zoom

    RightPanel() {

        jTextFieldMaxImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinImag = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMaxReal = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldMinReal = new JFormattedTextField(NumberFormat.getNumberInstance());
        jTextFieldZoom = new JFormattedTextField(NumberFormat.getNumberInstance());

        setDefaultValues();

        JButton jButtonDraw = new JButton("Draw");
        JButton jButtonExport = new JButton("Export");
        JLabel jLabelInformation = new JLabel(
                "<html>" +
                        "You can click on the Image<br>" +
                        "and where you've clicked, will<br>" +
                        "be the new image center!<br>" +
                        "</html>"
        );

        jLabelLoading = new JLabel("Calculating...");

        createTextFields(this);

        jButtonDraw.addActionListener(
                new CalculationListener()
        );
        jButtonExport.addActionListener(e ->
                ImageUtilities.saveImageAsJPG(
                        ((JButton) e.getSource()),
                        GUI.getImage())
        );

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

        //Initialize an group layout for the input fields
        GroupLayout groupLayout = new GroupLayout(jPanel);
        GroupLayout.ParallelGroup verticalgroup = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE);

        JLabel jLabelMinImag = new JLabel("Min Imag");
        JLabel jLabelMaxImag = new JLabel("Max Imag");
        JLabel jLabelMinReal = new JLabel("Min Real");
        JLabel jLabelMaxReal = new JLabel("Max Real");
        JLabel jLabelZoom = new JLabel("Zoomfactor");

        GUIUtilities.changeFontSize(1.5, jLabelMinImag, jLabelMaxImag, jLabelMinReal, jLabelMaxReal, jLabelZoom);

        //Groups all input fields with its labels
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMinImag, jTextFieldMinImag);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMaxImag, jTextFieldMaxImag);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMinReal, jTextFieldMinReal);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelMaxReal, jTextFieldMaxReal);
        GUIUtilities.createVerticalGroup(groupLayout, verticalgroup, jLabelZoom, jTextFieldZoom);
    }

    void setDefaultValues() {
        GUI.setMaxImag(Double.parseDouble(GUI.cfg.getProperty("defaultMaxImag")));
        GUI.setMinImag(Double.parseDouble(GUI.cfg.getProperty("defaultMinImag")));
        GUI.setMaxReal(Double.parseDouble(GUI.cfg.getProperty("defaultMaxReal")));
        GUI.setMinReal(Double.parseDouble(GUI.cfg.getProperty("defaultMinReal")));
        GUI.setZoom(Double.parseDouble(GUI.cfg.getProperty("defaultZoom")));
    }
}
