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
 * -------------------------
 * XYDifferenceRenderer.java
 * -------------------------
 * (C) Copyright 2003-2022, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Richard West, Advanced Micro Devices, Inc. (major rewrite
 *                   of difference drawing algorithm);
 *                   Patrick Schlott
 *                   Christoph Schroeder
 *                   Martin Hoeller
 *
 */

package org.jfree.chart.renderer.xy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.LinkedList;

import org.jfree.chart.legend.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.internal.PaintUtils;
import org.jfree.chart.internal.Args;
import org.jfree.chart.api.PublicCloneable;
import org.jfree.chart.internal.CloneUtils;
import org.jfree.chart.internal.SerialUtils;
import org.jfree.chart.internal.ShapeUtils;
import org.jfree.data.xy.XYDataset;

/**
 * A renderer for an {@link XYPlot} that highlights the differences between two
 * series.  The example shown here is generated by the
 * {@code DifferenceChartDemo1.java} program included in the JFreeChart
 * demo collection:
 * <br><br>
 * <img src="doc-files/XYDifferenceRendererSample.png"
 * alt="XYDifferenceRendererSample.png">
 */
public class XYDifferenceRenderer extends AbstractXYItemRenderer
        implements XYItemRenderer, PublicCloneable {

    private transient XYDifferenceRenderer_refactoring xYDifferenceRenderer_refactoring = new XYDifferenceRenderer_refactoring();

	/** For serialization. */
    private static final long serialVersionUID = -8447915602375584857L;

    /** The paint used to highlight positive differences (y(0) > y(1)). */
    private transient Paint positivePaint;

    /** The paint used to highlight negative differences (y(0) < y(1)). */
    private transient Paint negativePaint;

    /** Display shapes at each point? */
    private boolean shapesVisible;

    /** The shape to display in the legend item. */
    private transient Shape legendLine;

    /**
     * This flag controls whether or not the x-coordinates (in Java2D space)
     * are rounded to integers.  When set to true, this can avoid the vertical
     * striping that anti-aliasing can generate.  However, the rounding may not
     * be appropriate for output in high resolution formats (for example,
     * vector graphics formats such as SVG and PDF).
     */
    private boolean roundXCoordinates;

    /**
     * Creates a new renderer with default attributes.
     */
    public XYDifferenceRenderer() {
        this(Color.GREEN, Color.RED, false);
    }

    /**
     * Creates a new renderer.
     *
     * @param positivePaint  the highlight color for positive differences
     *                       ({@code null} not permitted).
     * @param negativePaint  the highlight color for negative differences
     *                       ({@code null} not permitted).
     * @param shapes  draw shapes?
     */
    public XYDifferenceRenderer(Paint positivePaint, Paint negativePaint,
                                boolean shapes) {
        Args.nullNotPermitted(positivePaint, "positivePaint");
        Args.nullNotPermitted(negativePaint, "negativePaint");
        this.positivePaint = positivePaint;
        this.negativePaint = negativePaint;
        this.shapesVisible = shapes;
        this.legendLine = new Line2D.Double(-7.0, 0.0, 7.0, 0.0);
        this.roundXCoordinates = false;
    }

    /**
     * Returns the paint used to highlight positive differences.
     *
     * @return The paint (never {@code null}).
     *
     * @see #setPositivePaint(Paint)
     */
    public Paint getPositivePaint() {
        return this.positivePaint;
    }

    /**
     * Sets the paint used to highlight positive differences and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     *
     * @see #getPositivePaint()
     */
    public void setPositivePaint(Paint paint) {
        Args.nullNotPermitted(paint, "paint");
        this.positivePaint = paint;
        fireChangeEvent();
    }

    /**
     * Returns the paint used to highlight negative differences.
     *
     * @return The paint (never {@code null}).
     *
     * @see #setNegativePaint(Paint)
     */
    public Paint getNegativePaint() {
        return this.negativePaint;
    }

    /**
     * Sets the paint used to highlight negative differences.
     *
     * @param paint  the paint ({@code null} not permitted).
     *
     * @see #getNegativePaint()
     */
    public void setNegativePaint(Paint paint) {
        Args.nullNotPermitted(paint, "paint");
        this.negativePaint = paint;
        notifyListeners(new RendererChangeEvent(this));
    }

    /**
     * Returns a flag that controls whether or not shapes are drawn for each
     * data value.
     *
     * @return A boolean.
     *
     * @see #setShapesVisible(boolean)
     */
    public boolean getShapesVisible() {
        return this.shapesVisible;
    }

    /**
     * Sets a flag that controls whether or not shapes are drawn for each
     * data value, and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param flag  the flag.
     *
     * @see #getShapesVisible()
     */
    public void setShapesVisible(boolean flag) {
        this.shapesVisible = flag;
        fireChangeEvent();
    }

    /**
     * Returns the shape used to represent a line in the legend.
     *
     * @return The legend line (never {@code null}).
     *
     * @see #setLegendLine(Shape)
     */
    public Shape getLegendLine() {
        return this.legendLine;
    }

    /**
     * Sets the shape used as a line in each legend item and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param line  the line ({@code null} not permitted).
     *
     * @see #getLegendLine()
     */
    public void setLegendLine(Shape line) {
        Args.nullNotPermitted(line, "line");
        this.legendLine = line;
        fireChangeEvent();
    }

    /**
     * Returns the flag that controls whether or not the x-coordinates (in
     * Java2D space) are rounded to integer values.
     *
     * @return The flag.
     *
     * @see #setRoundXCoordinates(boolean)
     */
    public boolean getRoundXCoordinates() {
        return this.roundXCoordinates;
    }

    /**
     * Sets the flag that controls whether or not the x-coordinates (in
     * Java2D space) are rounded to integer values, and sends a
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param round  the new flag value.
     *
     * @see #getRoundXCoordinates()
     */
    public void setRoundXCoordinates(boolean round) {
        this.roundXCoordinates = round;
        fireChangeEvent();
    }

    /**
     * Initialises the renderer and returns a state object that should be
     * passed to subsequent calls to the drawItem() method.  This method will
     * be called before the first item is rendered, giving the renderer an
     * opportunity to initialise any state information it wants to maintain.
     * The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param data  the data.
     * @param info  an optional info collection object to return data back to
     *              the caller.
     *
     * @return A state object.
     */
    @Override
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea,
            XYPlot plot, XYDataset data, PlotRenderingInfo info) {
        XYItemRendererState state = super.initialise(g2, dataArea, plot, data,
                info);
        state.setProcessVisibleItemsOnly(false);
        return state;
    }

    /**
     * Returns {@code 2}, the number of passes required by the renderer.
     * The {@link XYPlot} will run through the dataset this number of times.
     *
     * @return The number of passes required by the renderer.
     */
    @Override
    public int getPassCount() {
        return 2;
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color
     *              information etc).
     * @param domainAxis  the domain (horizontal) axis.
     * @param rangeAxis  the range (vertical) axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot
     *                        ({@code null} permitted).
     * @param pass  the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
            int series, int item, CrosshairState crosshairState, int pass) {

        if (pass == 0) {
            xYDifferenceRenderer_refactoring.drawItemPass0(g2, dataArea, info, plot, domainAxis, rangeAxis,
                    dataset, series, item, crosshairState, this);
        }
        else if (pass == 1) {
            drawItemPass1(g2, dataArea, info, plot, domainAxis, rangeAxis,
                    dataset, series, item, crosshairState);
        }

    }

    /**
     * Draws the visual representation of a single data item, first pass.
     *
     * @param x_graphics  the graphics device.
     * @param x_dataArea  the area within which the data is being drawn.
     * @param x_info  collects information about the drawing.
     * @param x_plot  the plot (can be used to obtain standard color
     *                information etc).
     * @param x_domainAxis  the domain (horizontal) axis.
     * @param x_rangeAxis  the range (vertical) axis.
     * @param x_dataset  the dataset.
     * @param x_series  the series index (zero-based).
     * @param x_item  the item index (zero-based).
     * @param x_crosshairState  crosshair information for the plot
     *                          ({@code null} permitted).
     */
    protected void drawItemPass0(Graphics2D x_graphics,
                                 Rectangle2D x_dataArea,
                                 PlotRenderingInfo x_info,
                                 XYPlot x_plot,
                                 ValueAxis x_domainAxis,
                                 ValueAxis x_rangeAxis,
                                 XYDataset x_dataset,
                                 int x_series,
                                 int x_item,
                                 CrosshairState x_crosshairState) {

        xYDifferenceRenderer_refactoring.drawItemPass0(x_graphics, x_dataArea, x_info, x_plot, x_domainAxis,
				x_rangeAxis, x_dataset, x_series, x_item, x_crosshairState, this);
    }

    /**
     * Draws the visual representation of a single data item, second pass.  In
     * the second pass, the renderer draws the lines and shapes for the
     * individual points in the two series.
     *
     * @param x_graphics  the graphics device.
     * @param x_dataArea  the area within which the data is being drawn.
     * @param x_info  collects information about the drawing.
     * @param x_plot  the plot (can be used to obtain standard color
     *         information etc).
     * @param x_domainAxis  the domain (horizontal) axis.
     * @param x_rangeAxis  the range (vertical) axis.
     * @param x_dataset  the dataset.
     * @param x_series  the series index (zero-based).
     * @param x_item  the item index (zero-based).
     * @param x_crosshairState  crosshair information for the plot
     *                          ({@code null} permitted).
     */
    protected void drawItemPass1(Graphics2D x_graphics,
                                 Rectangle2D x_dataArea,
                                 PlotRenderingInfo x_info,
                                 XYPlot x_plot,
                                 ValueAxis x_domainAxis,
                                 ValueAxis x_rangeAxis,
                                 XYDataset x_dataset,
                                 int x_series,
                                 int x_item,
                                 CrosshairState x_crosshairState) {

        Shape l_entityArea = null;
        EntityCollection l_entities = null;
        if (null != x_info) {
            l_entities = x_info.getOwner().getEntityCollection();
        }

        Paint l_seriesPaint   = getItemPaint(x_series, x_item);
        Stroke l_seriesStroke = getItemStroke(x_series, x_item);
        x_graphics.setPaint(l_seriesPaint);
        x_graphics.setStroke(l_seriesStroke);

        PlotOrientation l_orientation      = x_plot.getOrientation();
        RectangleEdge l_domainAxisLocation = x_plot.getDomainAxisEdge();
        RectangleEdge l_rangeAxisLocation  = x_plot.getRangeAxisEdge();

        double l_x0 = x_dataset.getXValue(x_series, x_item);
        double l_y0 = x_dataset.getYValue(x_series, x_item);
        double l_x1 = x_domainAxis.valueToJava2D(l_x0, x_dataArea,
                l_domainAxisLocation);
        double l_y1 = x_rangeAxis.valueToJava2D(l_y0, x_dataArea,
                l_rangeAxisLocation);

        if (getShapesVisible()) {
            Shape l_shape = getItemShape(x_series, x_item);
            if (l_orientation == PlotOrientation.HORIZONTAL) {
                l_shape = ShapeUtils.createTranslatedShape(l_shape,
                        l_y1, l_x1);
            }
            else {
                l_shape = ShapeUtils.createTranslatedShape(l_shape,
                        l_x1, l_y1);
            }
            if (l_shape.intersects(x_dataArea)) {
                x_graphics.setPaint(getItemPaint(x_series, x_item));
                x_graphics.fill(l_shape);
            }
            l_entityArea = l_shape;
        }

        // add an entity for the item...
        if (null != l_entities) {
            if (null == l_entityArea) {
                l_entityArea = new Rectangle2D.Double((l_x1 - 2), (l_y1 - 2),
                        4, 4);
            }
            String l_tip = null;
            XYToolTipGenerator l_tipGenerator = getToolTipGenerator(x_series,
                    x_item);
            if (null != l_tipGenerator) {
                l_tip = l_tipGenerator.generateToolTip(x_dataset, x_series,
                        x_item);
            }
            String l_url = null;
            XYURLGenerator l_urlGenerator = getURLGenerator();
            if (null != l_urlGenerator) {
                l_url = l_urlGenerator.generateURL(x_dataset, x_series,
                        x_item);
            }
            XYItemEntity l_entity = new XYItemEntity(l_entityArea, x_dataset,
                    x_series, x_item, l_tip, l_url);
            l_entities.add(l_entity);
        }

        // draw the item label if there is one...
        if (isItemLabelVisible(x_series, x_item)) {
            drawItemLabel(x_graphics, l_orientation, x_dataset, x_series,
                          x_item, l_x1, l_y1, (l_y1 < 0.0));
        }

        int datasetIndex = x_plot.indexOf(x_dataset);
        updateCrosshairValues(x_crosshairState, l_x0, l_y0, datasetIndex,
                              l_x1, l_y1, l_orientation);

        if (0 == x_item) {
            return;
        }

        double l_x2 = x_domainAxis.valueToJava2D(x_dataset.getXValue(x_series,
                (x_item - 1)), x_dataArea, l_domainAxisLocation);
        double l_y2 = x_rangeAxis.valueToJava2D(x_dataset.getYValue(x_series,
                (x_item - 1)), x_dataArea, l_rangeAxisLocation);

        Line2D l_line = null;
        if (PlotOrientation.HORIZONTAL == l_orientation) {
            l_line = new Line2D.Double(l_y1, l_x1, l_y2, l_x2);
        }
        else if (PlotOrientation.VERTICAL == l_orientation) {
            l_line = new Line2D.Double(l_x1, l_y1, l_x2, l_y2);
        }

        if ((null != l_line) && l_line.intersects(x_dataArea)) {
            x_graphics.setPaint(getItemPaint(x_series, x_item));
            x_graphics.setStroke(getItemStroke(x_series, x_item));
            x_graphics.draw(l_line);
        }
    }

    /**
     * Draws the visual representation of a polygon
     *
     * @param x_graphics  the graphics device.
     * @param x_dataArea  the area within which the data is being drawn.
     * @param x_plot  the plot (can be used to obtain standard color
     *                information etc).
     * @param x_domainAxis  the domain (horizontal) axis.
     * @param x_rangeAxis  the range (vertical) axis.
     * @param x_positive  indicates if the polygon is positive (true) or
     *                    negative (false).
     * @param x_xValues  a linked list of the x values (expects values to be
     *                   of type Double).
     * @param x_yValues  a linked list of the y values (expects values to be
     *                   of type Double).
     */
    public void createPolygon (Graphics2D x_graphics,
                                Rectangle2D x_dataArea,
                                XYPlot x_plot,
                                ValueAxis x_domainAxis,
                                ValueAxis x_rangeAxis,
                                boolean x_positive,
                                LinkedList x_xValues,
                                LinkedList x_yValues) {

        PlotOrientation l_orientation      = x_plot.getOrientation();
        RectangleEdge l_domainAxisLocation = x_plot.getDomainAxisEdge();
        RectangleEdge l_rangeAxisLocation  = x_plot.getRangeAxisEdge();

        Object[] l_xValues = x_xValues.toArray();
        Object[] l_yValues = x_yValues.toArray();

        GeneralPath l_path = new GeneralPath();

        if (PlotOrientation.VERTICAL == l_orientation) {
            double l_x = x_domainAxis.valueToJava2D((
                    (Double) l_xValues[0]), x_dataArea,
                    l_domainAxisLocation);
            if (this.roundXCoordinates) {
                l_x = Math.rint(l_x);
            }

            double l_y = x_rangeAxis.valueToJava2D((
                    (Double) l_yValues[0]), x_dataArea,
                    l_rangeAxisLocation);

            l_path.moveTo((float) l_x, (float) l_y);
            for (int i = 1; i < l_xValues.length; i++) {
                l_x = x_domainAxis.valueToJava2D((
                        (Double) l_xValues[i]), x_dataArea,
                        l_domainAxisLocation);
                if (this.roundXCoordinates) {
                    l_x = Math.rint(l_x);
                }

                l_y = x_rangeAxis.valueToJava2D((
                        (Double) l_yValues[i]), x_dataArea,
                        l_rangeAxisLocation);
                l_path.lineTo((float) l_x, (float) l_y);
            }
            l_path.closePath();
        }
        else {
            double l_x = x_domainAxis.valueToJava2D((
                    (Double) l_xValues[0]), x_dataArea,
                    l_domainAxisLocation);
            if (this.roundXCoordinates) {
                l_x = Math.rint(l_x);
            }

            double l_y = x_rangeAxis.valueToJava2D((
                    (Double) l_yValues[0]), x_dataArea,
                    l_rangeAxisLocation);

            l_path.moveTo((float) l_y, (float) l_x);
            for (int i = 1; i < l_xValues.length; i++) {
                l_x = x_domainAxis.valueToJava2D((
                        (Double) l_xValues[i]), x_dataArea,
                        l_domainAxisLocation);
                if (this.roundXCoordinates) {
                    l_x = Math.rint(l_x);
                }

                l_y = x_rangeAxis.valueToJava2D((
                        (Double) l_yValues[i]), x_dataArea,
                        l_rangeAxisLocation);
                l_path.lineTo((float) l_y, (float) l_x);
            }
            l_path.closePath();
        }

        if (l_path.intersects(x_dataArea)) {
            x_graphics.setPaint(x_positive ? getPositivePaint()
                    : getNegativePaint());
            x_graphics.fill(l_path);
        }
    }

    /**
     * Returns a default legend item for the specified series.  Subclasses
     * should override this method to generate customised items.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series.
     */
    @Override
    public LegendItem getLegendItem(int datasetIndex, int series) {
        LegendItem result = null;
        XYPlot p = getPlot();
        if (p != null) {
            XYDataset dataset = p.getDataset(datasetIndex);
            if (dataset != null) {
                if (getItemVisible(series, 0)) {
                    String label = getLegendItemLabelGenerator().generateLabel(
                            dataset, series);
                    String description = label;
                    String toolTipText = null;
                    if (getLegendItemToolTipGenerator() != null) {
                        toolTipText
                            = getLegendItemToolTipGenerator().generateLabel(
                                    dataset, series);
                    }
                    String urlText = null;
                    if (getLegendItemURLGenerator() != null) {
                        urlText = getLegendItemURLGenerator().generateLabel(
                                dataset, series);
                    }
                    Paint paint = lookupSeriesPaint(series);
                    Stroke stroke = lookupSeriesStroke(series);
                    Shape line = getLegendLine();
                    result = new LegendItem(label, description,
                            toolTipText, urlText, line, stroke, paint);
                    result.setLabelFont(lookupLegendTextFont(series));
                    Paint labelPaint = lookupLegendTextPaint(series);
                    if (labelPaint != null) {
                        result.setLabelPaint(labelPaint);
                    }
                    result.setDataset(dataset);
                    result.setDatasetIndex(datasetIndex);
                    result.setSeriesKey(dataset.getSeriesKey(series));
                    result.setSeriesIndex(series);
                }
            }

        }

        return result;

    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object ({@code null} permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYDifferenceRenderer)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        XYDifferenceRenderer that = (XYDifferenceRenderer) obj;
        if (!PaintUtils.equal(this.positivePaint, that.positivePaint)) {
            return false;
        }
        if (!PaintUtils.equal(this.negativePaint, that.negativePaint)) {
            return false;
        }
        if (this.shapesVisible != that.shapesVisible) {
            return false;
        }
        if (!ShapeUtils.equal(this.legendLine, that.legendLine)) {
            return false;
        }
        if (this.roundXCoordinates != that.roundXCoordinates) {
            return false;
        }
        return true;
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        XYDifferenceRenderer clone = (XYDifferenceRenderer) super.clone();
		clone.xYDifferenceRenderer_refactoring = (XYDifferenceRenderer_refactoring) this.xYDifferenceRenderer_refactoring
				.clone();
        clone.legendLine = CloneUtils.clone(this.legendLine);
        return clone;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
		stream.writeObject(this.xYDifferenceRenderer_refactoring);
        SerialUtils.writePaint(this.positivePaint, stream);
        SerialUtils.writePaint(this.negativePaint, stream);
        SerialUtils.writeShape(this.legendLine, stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
		this.xYDifferenceRenderer_refactoring = (XYDifferenceRenderer_refactoring) stream.readObject();
        this.positivePaint = SerialUtils.readPaint(stream);
        this.negativePaint = SerialUtils.readPaint(stream);
        this.legendLine = SerialUtils.readShape(stream);
    }

}
