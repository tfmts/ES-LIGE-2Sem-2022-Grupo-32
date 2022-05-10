/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2022, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.]
 *
 * ---------------------------
 * DefaultValueAxisEditor.java
 * ---------------------------
 * (C) Copyright 2005-2022, by David Gilbert and Contributors.
 *
 * Original Author:  Martin Hoeller (base on DefaultNumberAxisEditor
 *                                   by David Gilbert);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.swing.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.ValueAxis;

/**
 * A panel for editing properties of a {@link ValueAxis}.
 */
class DefaultValueAxisEditor extends DefaultAxisEditor
    implements FocusListener {

    private DefaultValueAxisEditorRefactoring2 defaultValueAxisEditorRefactoring2;

	private DefaultValueAxisEditorRefactoring1 defaultValueAxisEditorRefactoring1 = new DefaultValueAxisEditorRefactoring1();

	/** A flag that indicates whether or not the axis range is determined
     *  automatically.
     */
    private boolean autoRange;

    /** The lowest value in the axis range. */
    private double minimumValue;

    /** The highest value in the axis range. */
    private double maximumValue;

    /** A checkbox that indicates whether or not the axis range is determined
     *  automatically.
     */
    private final JCheckBox autoRangeCheckBox;

    /** A text field for entering the minimum value in the axis range. */
    private final JTextField minimumRangeValue;

    /** A text field for entering the maximum value in the axis range. */
    private final JTextField maximumRangeValue;

    /** The paint selected for drawing the gridlines. */
    private final PaintSample gridPaintSample;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources
            = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");

    /**
     * Standard constructor: builds a property panel for the specified axis.
     *
     * @param axis  the axis, which should be changed.
     */
    public DefaultValueAxisEditor(ValueAxis axis) {

        super(axis);

        this.autoRange = axis.isAutoRange();
        this.minimumValue = axis.getLowerBound();
        this.maximumValue = axis.getUpperBound();
        defaultValueAxisEditorRefactoring1.setAutoTickUnitSelection(axis.isAutoTickUnitSelection());

        this.gridPaintSample = new PaintSample(Color.BLUE);
		this.defaultValueAxisEditorRefactoring2 = new DefaultValueAxisEditorRefactoring2(
				new StrokeSample(new BasicStroke(1.0f)));
        this.defaultValueAxisEditorRefactoring2.getAvailableStrokeSamples()[0] = new StrokeSample(
                new BasicStroke(1.0f));
        this.defaultValueAxisEditorRefactoring2.getAvailableStrokeSamples()[1] = new StrokeSample(
                new BasicStroke(2.0f));
        this.defaultValueAxisEditorRefactoring2.getAvailableStrokeSamples()[2] = new StrokeSample(
                new BasicStroke(3.0f));

        JTabbedPane other = getOtherTabs();

        JPanel range = new JPanel(new LCBLayout(3));
        range.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        range.add(new JPanel());
        this.autoRangeCheckBox = new JCheckBox(localizationResources.getString(
                "Auto-adjust_range"), this.autoRange);
        this.autoRangeCheckBox.setActionCommand("AutoRangeOnOff");
        this.autoRangeCheckBox.addActionListener(this);
        range.add(this.autoRangeCheckBox);
        range.add(new JPanel());

        range.add(new JLabel(localizationResources.getString(
                "Minimum_range_value")));
        this.minimumRangeValue = new JTextField(Double.toString(
                this.minimumValue));
        this.minimumRangeValue.setEnabled(!this.autoRange);
        this.minimumRangeValue.setActionCommand("MinimumRange");
        this.minimumRangeValue.addActionListener(this);
        this.minimumRangeValue.addFocusListener(this);
        range.add(this.minimumRangeValue);
        range.add(new JPanel());

        range.add(new JLabel(localizationResources.getString(
                "Maximum_range_value")));
        this.maximumRangeValue = new JTextField(Double.toString(
                this.maximumValue));
        this.maximumRangeValue.setEnabled(!this.autoRange);
        this.maximumRangeValue.setActionCommand("MaximumRange");
        this.maximumRangeValue.addActionListener(this);
        this.maximumRangeValue.addFocusListener(this);
        range.add(this.maximumRangeValue);
        range.add(new JPanel());

        other.add(localizationResources.getString("Range"), range);

        other.add(localizationResources.getString("TickUnit"),
                defaultValueAxisEditorRefactoring1.createTickUnitPanel(this));
    }

    /**
     * Creates a panel for the tick units.
     * 
     * @return A panel. 
     */
    protected JPanel createTickUnitPanel() {
        return defaultValueAxisEditorRefactoring1.createTickUnitPanel(this);
    }

    /**
     * Getter for the {@link #autoTickUnitSelection} flag.
     * 
     * @return The value of the flag for enabling auto-tickunit-selection.
     */
    protected boolean isAutoTickUnitSelection() {
        return defaultValueAxisEditorRefactoring1.getAutoTickUnitSelection();
    }

    /**
     * Setter for the {@link #autoTickUnitSelection} flag.
     * @param autoTickUnitSelection The new value for auto-tickunit-selection.
     */
    protected void setAutoTickUnitSelection(boolean autoTickUnitSelection) {
        defaultValueAxisEditorRefactoring1.setAutoTickUnitSelection(autoTickUnitSelection);
    }

    /**
     * Get the checkbox that enables/disables auto-tickunit-selection.
     * 
     * @return The checkbox.
     */
    protected JCheckBox getAutoTickUnitSelectionCheckBox() {
        return defaultValueAxisEditorRefactoring1.getAutoTickUnitSelectionCheckBox();
    }

    /**
     * Set the checkbox that enables/disables auto-tickunit-selection.
     *
     * @param autoTickUnitSelectionCheckBox The checkbox.
     */
    protected void setAutoTickUnitSelectionCheckBox(
            JCheckBox autoTickUnitSelectionCheckBox) {
        defaultValueAxisEditorRefactoring1.setAutoTickUnitSelectionCheckBox(autoTickUnitSelectionCheckBox);
    }

    /**
     * Returns the current setting of the auto-range property.
     *
     * @return {@code true} if auto range is enabled.
     */
    public boolean isAutoRange() {
        return this.autoRange;
    }

    /**
     * Returns the current setting of the minimum value in the axis range.
     *
     * @return The current setting of the minimum value in the axis range.
     */
    public double getMinimumValue() {
        return this.minimumValue;
    }

    /**
     * Returns the current setting of the maximum value in the axis range.
     *
     * @return The current setting of the maximum value in the axis range.
     */
    public double getMaximumValue() {
        return this.maximumValue;
    }

    /**
     * Handles actions from within the property panel.
     * @param event an event.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("GridStroke")) {
            defaultValueAxisEditorRefactoring2.attemptGridStrokeSelection(this);
        }
        else if (command.equals("GridPaint")) {
            attemptGridPaintSelection();
        }
        else if (command.equals("AutoRangeOnOff")) {
            toggleAutoRange();
        }
        else if (command.equals("MinimumRange")) {
            validateMinimum();
        }
        else if (command.equals("MaximumRange")) {
            validateMaximum();
        }
        else if (command.equals("AutoTickOnOff")) {
            defaultValueAxisEditorRefactoring1.toggleAutoTick();
        }
        else {
            // pass to the super-class for handling
            super.actionPerformed(event);
        }
    }

    /**
     * Handle a grid stroke selection.
     */
    protected void attemptGridStrokeSelection() {
        defaultValueAxisEditorRefactoring2.attemptGridStrokeSelection(this);
    }

    /**
     * Handle a grid paint selection.
     */
    protected void attemptGridPaintSelection() {
        Color c;
        c = JColorChooser.showDialog(this, localizationResources.getString(
                "Grid_Color"), Color.BLUE);
        if (c != null) {
            this.gridPaintSample.setPaint(c);
        }
    }

    /**
     * Does nothing.
     *
     * @param event  the event.
     */
    @Override
    public void focusGained(FocusEvent event) {
        // don't need to do anything
    }

    /**
     *  Revalidates minimum/maximum range.
     *
     *  @param event  the event.
     */
    @Override
    public void focusLost(FocusEvent event) {
        if (event.getSource() == this.minimumRangeValue) {
            validateMinimum();
        }
        else if (event.getSource() == this.maximumRangeValue) {
            validateMaximum();
        }
    }

    /**
     *  Toggle the auto range setting.
     */
    public void toggleAutoRange() {
        this.autoRange = this.autoRangeCheckBox.isSelected();
        if (this.autoRange) {
            this.minimumRangeValue.setText(Double.toString(this.minimumValue));
            this.minimumRangeValue.setEnabled(false);
            this.maximumRangeValue.setText(Double.toString(this.maximumValue));
            this.maximumRangeValue.setEnabled(false);
        }
        else {
            this.minimumRangeValue.setEnabled(true);
            this.maximumRangeValue.setEnabled(true);
        }
    }

    /**
     * Sets the auto-tick unit selection field to the value in the check box.
     */
    public void toggleAutoTick() {
        defaultValueAxisEditorRefactoring1.toggleAutoTick();
    }

    /**
     * Revalidate the range minimum.
     */
    public void validateMinimum() {
        double newMin;
        try {
            newMin = Double.parseDouble(this.minimumRangeValue.getText());
            if (newMin >= this.maximumValue) {
                newMin = this.minimumValue;
            }
        }
        catch (NumberFormatException e) {
            newMin = this.minimumValue;
        }

        this.minimumValue = newMin;
        this.minimumRangeValue.setText(Double.toString(this.minimumValue));
    }

    /**
     * Revalidate the range maximum.
     */
    public void validateMaximum() {
        double newMax;
        try {
            newMax = Double.parseDouble(this.maximumRangeValue.getText());
            if (newMax <= this.minimumValue) {
                newMax = this.maximumValue;
            }
        }
        catch (NumberFormatException e) {
            newMax = this.maximumValue;
        }

        this.maximumValue = newMax;
        this.maximumRangeValue.setText(Double.toString(this.maximumValue));
    }

    /**
     * Sets the properties of the specified axis to match the properties
     * defined on this panel.
     *
     * @param axis  the axis.
     */
    @Override
    public void setAxisProperties(Axis axis) {
        super.setAxisProperties(axis);
        ValueAxis valueAxis = (ValueAxis) axis;
        valueAxis.setAutoRange(this.autoRange);
        if (!this.autoRange) {
            valueAxis.setRange(this.minimumValue, this.maximumValue);
        }
        valueAxis.setAutoTickUnitSelection(this.defaultValueAxisEditorRefactoring1.getAutoTickUnitSelection());
    }
}
