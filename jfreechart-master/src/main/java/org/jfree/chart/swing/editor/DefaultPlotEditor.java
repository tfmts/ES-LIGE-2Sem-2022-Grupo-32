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
 * ----------------------
 * DefaultPlotEditor.java
 * ----------------------
 * (C) Copyright 2005-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Andrzej Porebski;
 *                   Arnaud Lelievre;
 *                   Daniel Gredler;
 *
 */

package org.jfree.chart.swing.editor;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.api.RectangleInsets;

/**
 * A panel for editing the properties of a {@link Plot}.
 */
class DefaultPlotEditor extends JPanel implements ActionListener {

    private DefaultPlotEditorRefactoring4 defaultPlotEditorRefactoring4 = new DefaultPlotEditorRefactoring4();
	private DefaultPlotEditorRefactoring3 defaultPlotEditorRefactoring3 = new DefaultPlotEditorRefactoring3();
	private DefaultPlotEditorRefactoring2 defaultPlotEditorRefactoring2 = new DefaultPlotEditorRefactoring2();
	private DefaultPlotEditorRefactoring1 defaultPlotEditorRefactoring1 = new DefaultPlotEditorRefactoring1();
	/** Orientation constants. */
    private final static String[] orientationNames = {"Vertical", "Horizontal"};
    public final static int ORIENTATION_VERTICAL = 0;
    private final static int ORIENTATION_HORIZONTAL = 1;

    /** Whether or not to draw shapes at each data point (for
     * <tt>LineAndShapeRenderer</tt>s and <tt>StandardXYItemRenderer</tt>s).
     */
    private Boolean drawShapes;

    /**
     * The checkbox for whether or not to draw shapes at each data point.
     */
    private JCheckBox drawShapesCheckBox;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources
            = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");

    /**
     * Standard constructor - constructs a panel for editing the properties of
     * the specified plot.
     * <P>
     * In designing the panel, we need to be aware that subclasses of Plot will
     * need to implement subclasses of PlotPropertyEditPanel - so we need to
     * leave one or two 'slots' where the subclasses can extend the user
     * interface.
     *
     * @param plot  the plot, which should be changed.
     */
    public DefaultPlotEditor(Plot plot) {
        JPanel panel = createPlotPanel(plot);
        add(panel);
    }
    
    /**
     * Creates a panel for the plot.
     * 
     * @param plot  the plot.
     * 
     * @return The panel. 
     */
    protected JPanel createPlotPanel(Plot plot) {
        defaultPlotEditorRefactoring1.setPlotInsets(plot.getInsets());
        defaultPlotEditorRefactoring1.setBackgroundPaintSample(new PaintSample(plot.getBackgroundPaint()));
        defaultPlotEditorRefactoring1.setOutlineStrokeSample(new StrokeSample(plot.getOutlineStroke()));
        defaultPlotEditorRefactoring1.setOutlinePaintSample(new PaintSample(plot.getOutlinePaint()));
        if (plot instanceof CategoryPlot) {
            defaultPlotEditorRefactoring3.setPlotOrientation(((CategoryPlot) plot).getOrientation());
        }
        else if (plot instanceof XYPlot) {
            defaultPlotEditorRefactoring3.setPlotOrientation(((XYPlot) plot).getOrientation());
        }
        if (plot instanceof CategoryPlot) {
            CategoryItemRenderer renderer = ((CategoryPlot) plot).getRenderer();
            if (renderer instanceof LineAndShapeRenderer) {
                LineAndShapeRenderer r = (LineAndShapeRenderer) renderer;
                defaultPlotEditorRefactoring4.setDrawLines(r.getDefaultLinesVisible());
                this.drawShapes = r.getDefaultShapesVisible();
            }
        }
        else if (plot instanceof XYPlot) {
            XYItemRenderer renderer = ((XYPlot) plot).getRenderer();
            if (renderer instanceof StandardXYItemRenderer) {
                StandardXYItemRenderer r = (StandardXYItemRenderer) renderer;
                defaultPlotEditorRefactoring4.setDrawLines(r.getPlotLines());
                this.drawShapes = r.getBaseShapesVisible();
            }
        }

        setLayout(new BorderLayout());

        defaultPlotEditorRefactoring1.setAvailableStrokeSamples(new StrokeSample[4]);
        this.defaultPlotEditorRefactoring1.getAvailableStrokeSamples()[0] = new StrokeSample(null);
        this.defaultPlotEditorRefactoring1.getAvailableStrokeSamples()[1] = new StrokeSample(
                new BasicStroke(1.0f));
        this.defaultPlotEditorRefactoring1.getAvailableStrokeSamples()[2] = new StrokeSample(
                new BasicStroke(2.0f));
        this.defaultPlotEditorRefactoring1.getAvailableStrokeSamples()[3] = new StrokeSample(
                new BasicStroke(3.0f));

        // create a panel for the settings...
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), plot.getPlotType()
                + localizationResources.getString(":")));

        JPanel general = new JPanel(new BorderLayout());
        general.setBorder(BorderFactory.createTitledBorder(
                localizationResources.getString("General")));

        JPanel interior = new JPanel(new LCBLayout(7));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

//        interior.add(new JLabel(localizationResources.getString("Insets")));
//        JButton button = new JButton(
//            localizationResources.getString("Edit...")
//        );
//        button.setActionCommand("Insets");
//        button.addActionListener(this);
//
//        this.insetsTextField = new InsetsTextField(this.plotInsets);
//        this.insetsTextField.setEnabled(false);
//        interior.add(this.insetsTextField);
//        interior.add(button);

        interior.add(new JLabel(localizationResources.getString(
                "Outline_stroke")));
        JButton button = new JButton(localizationResources.getString(
                "Select..."));
        button.setActionCommand("OutlineStroke");
        button.addActionListener(this);
        interior.add(this.defaultPlotEditorRefactoring1.getOutlineStrokeSample());
        interior.add(button);

        interior.add(new JLabel(localizationResources.getString(
                "Outline_Paint")));
        button = new JButton(localizationResources.getString("Select..."));
        button.setActionCommand("OutlinePaint");
        button.addActionListener(this);
        interior.add(this.defaultPlotEditorRefactoring1.getOutlinePaintSample());
        interior.add(button);

        interior.add(new JLabel(localizationResources.getString(
                "Background_paint")));
        button = new JButton(localizationResources.getString("Select..."));
        button.setActionCommand("BackgroundPaint");
        button.addActionListener(this);
        interior.add(this.defaultPlotEditorRefactoring1.getBackgroundPaintSample());
        interior.add(button);

        createPlotPanelRefactoring1(interior);

        general.add(interior, BorderLayout.NORTH);

        JPanel appearance = new JPanel(new BorderLayout());
        appearance.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        appearance.add(general, BorderLayout.NORTH);

        JTabbedPane tabs = defaultPlotEditorRefactoring2.createPlotTabs(plot);
        tabs.add(localizationResources.getString("Appearance"), appearance);
        panel.add(tabs);
        
        return panel;
    }

	public void createPlotPanelRefactoring1(JPanel interior) {
		if (this.defaultPlotEditorRefactoring3.getPlotOrientation() != null) {
            boolean isVertical = this.defaultPlotEditorRefactoring3.getPlotOrientation().equals(
                    PlotOrientation.VERTICAL);
            int index = isVertical ? ORIENTATION_VERTICAL
                    : ORIENTATION_HORIZONTAL;
            interior.add(new JLabel(localizationResources.getString(
                    "Orientation")));
            defaultPlotEditorRefactoring3.setOrientationCombo(new JComboBox(orientationNames));
            this.defaultPlotEditorRefactoring3.getOrientationCombo().setSelectedIndex(index);
            this.defaultPlotEditorRefactoring3.getOrientationCombo().setActionCommand("Orientation");
            this.defaultPlotEditorRefactoring3.getOrientationCombo().addActionListener(this);
            interior.add(new JPanel());
            interior.add(this.defaultPlotEditorRefactoring3.getOrientationCombo());
        }

        if (this.defaultPlotEditorRefactoring4.getDrawLines() != null) {
            interior.add(new JLabel(localizationResources.getString(
                    "Draw_lines")));
            defaultPlotEditorRefactoring4.setDrawLinesCheckBox(new JCheckBox());
            this.defaultPlotEditorRefactoring4.getDrawLinesCheckBox().setSelected(this.defaultPlotEditorRefactoring4.getDrawLines());
            this.defaultPlotEditorRefactoring4.getDrawLinesCheckBox().setActionCommand("DrawLines");
            this.defaultPlotEditorRefactoring4.getDrawLinesCheckBox().addActionListener(this);
            interior.add(new JPanel());
            interior.add(this.defaultPlotEditorRefactoring4.getDrawLinesCheckBox());
        }

        if (this.drawShapes != null) {
            interior.add(new JLabel(localizationResources.getString(
                    "Draw_shapes")));
            this.drawShapesCheckBox = new JCheckBox();
            this.drawShapesCheckBox.setSelected(this.drawShapes);
            this.drawShapesCheckBox.setActionCommand("DrawShapes");
            this.drawShapesCheckBox.addActionListener(this);
            interior.add(new JPanel());
            interior.add(this.drawShapesCheckBox);
        }
	}

    /**
     * Creates a tabbed pane for the plot.
     * 
     * @param plot  the plot.
     * 
     * @return A tabbed pane. 
     */
    protected JTabbedPane createPlotTabs(Plot plot) {
        return defaultPlotEditorRefactoring2.createPlotTabs(plot);
    }

    /**
     * Returns the current plot insets.
     *
     * @return The current plot insets.
     */
    public RectangleInsets getPlotInsets() {
        return defaultPlotEditorRefactoring1.getPlotInsets();
    }

    /**
     * Returns the current background paint.
     *
     * @return The current background paint.
     */
    public Paint getBackgroundPaint() {
        return this.defaultPlotEditorRefactoring1.getBackgroundPaintSample().getPaint();
    }

    /**
     * Returns the current outline stroke.
     *
     * @return The current outline stroke (possibly {@code null}).
     */
    public Stroke getOutlineStroke() {
        return this.defaultPlotEditorRefactoring1.getOutlineStrokeSample().getStroke();
    }

    /**
     * Returns the current outline paint.
     *
     * @return The current outline paint.
     */
    public Paint getOutlinePaint() {
        return this.defaultPlotEditorRefactoring1.getOutlinePaintSample().getPaint();
    }

    /**
     * Returns a reference to the panel for editing the properties of the
     * domain axis.
     *
     * @return A reference to a panel.
     */
    public DefaultAxisEditor getDomainAxisPropertyEditPanel() {
        return this.defaultPlotEditorRefactoring2.getDomainAxisPropertyPanel();
    }

    /**
     * Returns a reference to the panel for editing the properties of the
     * range axis.
     *
     * @return A reference to a panel.
     */
    public DefaultAxisEditor getRangeAxisPropertyEditPanel() {
        return this.defaultPlotEditorRefactoring2.getRangeAxisPropertyPanel();
    }

    /**
     * Handles user actions generated within the panel.
     * @param event     the event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("BackgroundPaint")) {
            defaultPlotEditorRefactoring1.attemptBackgroundPaintSelection(this);
        }
        else if (command.equals("OutlineStroke")) {
            defaultPlotEditorRefactoring1.attemptOutlineStrokeSelection(this);
        }
        else if (command.equals("OutlinePaint")) {
            defaultPlotEditorRefactoring1.attemptOutlinePaintSelection(this);
        }
//        else if (command.equals("Insets")) {
//            editInsets();
//        }
        else if (command.equals("Orientation")) {
            defaultPlotEditorRefactoring3.attemptOrientationSelection();
        }
        else if (command.equals("DrawLines")) {
            defaultPlotEditorRefactoring4.attemptDrawLinesSelection();
        }
        else if (command.equals("DrawShapes")) {
            attemptDrawShapesSelection();
        }
    }

    /**
     * Allow the user to modify whether or not shapes are drawn at data points
     * by <tt>LineAndShapeRenderer</tt>s and <tt>StandardXYItemRenderer</tt>s.
     */
    private void attemptDrawShapesSelection() {
        this.drawShapes = this.drawShapesCheckBox.isSelected();
    }

    /**
     * Updates the plot properties to match the properties defined on the panel.
     *
     * @param plot  The plot.
     */
    public void updatePlotProperties(Plot plot) {

        // set the plot properties...
        plot.setOutlinePaint(getOutlinePaint());
        plot.setOutlineStroke(getOutlineStroke());
        plot.setBackgroundPaint(getBackgroundPaint());
        plot.setInsets(defaultPlotEditorRefactoring1.getPlotInsets());

        // then the axis properties...
        defaultPlotEditorRefactoring2.updatePlotPropertiesRefactoring1(plot);

        defaultPlotEditorRefactoring2.updatePlotPropertiesRefactoring2(plot);

        defaultPlotEditorRefactoring3.updatePlotPropertiesRefactoring3(plot);

        updatePlotPropertiesRefactoring4(plot);

        if (this.drawShapes != null) {
            if (plot instanceof CategoryPlot) {
                CategoryPlot p = (CategoryPlot) plot;
                CategoryItemRenderer r = p.getRenderer();
                if (r instanceof LineAndShapeRenderer) {
                    ((LineAndShapeRenderer) r).setDefaultShapesVisible(this.drawShapes);
                }
            }
            else if (plot instanceof XYPlot) {
                XYPlot p = (XYPlot) plot;
                XYItemRenderer r = p.getRenderer();
                if (r instanceof StandardXYItemRenderer) {
                    ((StandardXYItemRenderer) r).setBaseShapesVisible(
                        this.drawShapes);
                }
            }
        }

    }

	public void updatePlotPropertiesRefactoring4(Plot plot) {
		if (this.defaultPlotEditorRefactoring4.getDrawLines() != null) {
            if (plot instanceof CategoryPlot) {
                CategoryPlot p = (CategoryPlot) plot;
                CategoryItemRenderer r = p.getRenderer();
                if (r instanceof LineAndShapeRenderer) {
                    ((LineAndShapeRenderer) r).setDefaultLinesVisible(this.defaultPlotEditorRefactoring4.getDrawLines());
                }
            }
            else if (plot instanceof XYPlot) {
                XYPlot p = (XYPlot) plot;
                XYItemRenderer r = p.getRenderer();
                if (r instanceof StandardXYItemRenderer) {
                    ((StandardXYItemRenderer) r).setPlotLines(this.defaultPlotEditorRefactoring4.getDrawLines());
                }
            }
        }
	}

	public void updatePlotPropertiesRefactoring3(Plot plot) {
		defaultPlotEditorRefactoring3.updatePlotPropertiesRefactoring3(plot);
	}

	public void updatePlotPropertiesRefactoring2(Plot plot) {
		defaultPlotEditorRefactoring2.updatePlotPropertiesRefactoring2(plot);
	}

	public void updatePlotPropertiesRefactoring1(Plot plot) {
		defaultPlotEditorRefactoring2.updatePlotPropertiesRefactoring1(plot);
	}

}
