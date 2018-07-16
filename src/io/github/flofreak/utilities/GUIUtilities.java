/*
 * GUIUtillities
 * v1
 * 16. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.utilities;

import javax.swing.*;
import java.awt.*;

/**
 * Class with usefull GUI Utilities
 *
 * @author florian.warnke
 * @version v1
 */
public class GUIUtilities {
    /**
     * Changes the text fields to only accept doubles
     *
     * @param fields the text fields
     */
    public static void changeToDoubleOnlyJTextField(JFormattedTextField... fields) {

        //Loops all text fields
        for (JFormattedTextField field : fields) {
            //Sets prefered size and formate
            field.setPreferredSize(new Dimension(100, 20));
            field.setHorizontalAlignment(JTextField.CENTER);
            //Adds listener which converts, if possible all inputs to an double
            field.addPropertyChangeListener("value",
                    e -> ((JFormattedTextField) e.getSource()).setValue(((Number) e.getNewValue()).doubleValue()));
        }
    }

    /**
     * Adds to a horizontal group in the group layout
     *
     * @param groupLayout   the group layout where to add to
     * @param verticalgroup the horizontal group
     * @param components    the components to add to one horizontal group
     */
    public static void createVerticalGroup(GroupLayout groupLayout, GroupLayout.ParallelGroup verticalgroup,
                                           Component... components) {
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();

        for (Component component : components) {
            sequentialGroup.addComponent(component);
        }

        verticalgroup.addGroup(sequentialGroup);
    }

    /**
     * Changes the font size by the factor value
     * @param value the sizing factor
     * @param components the components which should be factoriesed
     */
    public static void changeFontSize(double value, Component... components) {
        for (Component component : components) {
            //Gets font and multiply size by value and then sets it
            component.setFont(new Font("Multiplied by " + value, component.getFont().getStyle(),
                    (int) (component.getFont().getSize() * value)));
        }
    }

}
