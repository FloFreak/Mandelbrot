package io.github.flofreak.utilities;

import javax.swing.*;
import java.awt.*;

public class GUIUtilities {
    /**
     * Changes the text fields to only accept doubles
     *
     * @param value  the default value
     * @param fields the text fields
     */
    public static void createDoubleOnlyJTextField(double value, JFormattedTextField... fields) {

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
    public static void createHorizontalGroup(GroupLayout groupLayout, GroupLayout.ParallelGroup horizontalGroup,
                                             Component... components) {
        GroupLayout.SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();

        for (Component component : components) {
            sequentialGroup.addComponent(component);
        }

        horizontalGroup.addGroup(sequentialGroup);
    }

}
