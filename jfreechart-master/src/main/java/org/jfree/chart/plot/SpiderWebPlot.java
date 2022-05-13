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
 * ------------------
 * SpiderWebPlot.java
 * ------------------
 * (C) Copyright 2005-2021, by Heaps of Flavour Pty Ltd and Contributors.
 *
 * Company Info:  http://www.i4-talent.com
 *
 * Original Author:  Don Elliott;
 * Contributor(s):   David Gilbert;
 *                   Nina Jeliazkova;
 *
 */

package org.jfree.chart.plot;

import org.jfree.chart.api.RectangleInsets;
import org.jfree.chart.api.Rotation;
import org.jfree.chart.api.TableOrder;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.internal.*;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.legend.LegendItem;
import org.jfree.chart.legend.LegendItemCollection;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.*;

/**
 * A plot that displays data from a {@link CategoryDataset} in the form of a
 * "spider web".  Multiple series can be plotted on the same axis to allow
 * easy comparison.  This plot doesn't support negative values at present.
 */
public class SpiderWebPlot extends Plot implements Cloneable, Serializable {

    private SpiderWebPlotProduct2 spiderWebPlotProduct2 = new SpiderWebPlotProduct2();

	/** For serialization. */
    private static final long serialVersionUID = -5376340422031599463L;

    /** The default head radius percent (currently 1%). */
    public static final double DEFAULT_HEAD = 0.01;

    /** The default axis label gap (currently 10%). */
    public static final double DEFAULT_AXIS_LABEL_GAP = 0.10;

    /** The default interior gap. */
    public static final double DEFAULT_INTERIOR_GAP = 0.25;

    /** The maximum interior gap (currently 40%). */
    public static final double MAX_INTERIOR_GAP = 0.40;

    /** The default starting angle for the radar chart axes. */
    public static final double DEFAULT_START_ANGLE = 90.0;

    /** The default series label font. */
    public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif",
            Font.PLAIN, 10);

    /** The default series label paint. */
    public static final Paint  DEFAULT_LABEL_PAINT = Color.BLACK;

    /** The default series label background paint. */
    public static final Paint  DEFAULT_LABEL_BACKGROUND_PAINT
            = new Color(255, 255, 192);

    /** The default series label outline paint. */
    public static final Paint  DEFAULT_LABEL_OUTLINE_PAINT = Color.BLACK;

    /** The default series label outline stroke. */
    public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE
            = new BasicStroke(0.5f);

    /** The default series label shadow paint. */
    public static final Paint  DEFAULT_LABEL_SHADOW_PAINT = Color.LIGHT_GRAY;

    /**
     * The default maximum value plotted - forces the plot to evaluate
     *  the maximum from the data passed in
     */
    public static final double DEFAULT_MAX_VALUE = -1.0;

    /** The gap between the labels and the axes as a %age of the radius. */
    private double axisLabelGap;

    /**
     * Creates a default plot with no dataset.
     */
    public SpiderWebPlot() {
        this(null);
    }

    /**
     * Creates a new spider web plot with the given dataset, with each row
     * representing a series.
     *
     * @param dataset  the dataset ({@code null} permitted).
     */
    public SpiderWebPlot(CategoryDataset dataset) {
        this(dataset, TableOrder.BY_ROW);
    }

    /**
     * Creates a new spider web plot with the given dataset.
     *
     * @param dataset  the dataset.
     * @param extract  controls how data is extracted ({@link TableOrder#BY_ROW}
     *                 or {@link TableOrder#BY_COLUMN}).
     */
    public SpiderWebPlot(CategoryDataset dataset, TableOrder extract) {
        super();
        Args.nullNotPermitted(extract, "extract");
        spiderWebPlotProduct2.getSpiderWebPlotProduct().setDataset(dataset);
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        spiderWebPlotProduct2.getSpiderWebPlotProduct().setDataExtractOrder2(extract);
        spiderWebPlotProduct2.setHeadPercent2(DEFAULT_HEAD);
        this.axisLabelGap = DEFAULT_AXIS_LABEL_GAP;
        spiderWebPlotProduct2.setAxisLinePaint2(Color.BLACK);
        spiderWebPlotProduct2.setAxisLineStroke2(new BasicStroke(1.0f));

        spiderWebPlotProduct2.setInteriorGap2(DEFAULT_INTERIOR_GAP);
        spiderWebPlotProduct2.setStartAngle2(DEFAULT_START_ANGLE);
        spiderWebPlotProduct2.setDirection2(Rotation.CLOCKWISE);
        spiderWebPlotProduct2.setMaxValue2(DEFAULT_MAX_VALUE);

        spiderWebPlotProduct2.setSeriesPaints(new HashMap<>());
        spiderWebPlotProduct2.setDefaultSeriesPaint2(null);

        spiderWebPlotProduct2.setSeriesOutlinePaints(new HashMap<>());
        spiderWebPlotProduct2.setDefaultSeriesOutlinePaint2(DEFAULT_OUTLINE_PAINT);

        spiderWebPlotProduct2.setSeriesOutlineStrokes(new HashMap<>());
        spiderWebPlotProduct2.setDefaultSeriesOutlineStroke2(DEFAULT_OUTLINE_STROKE);

        spiderWebPlotProduct2.setLabelFont2(DEFAULT_LABEL_FONT);
        spiderWebPlotProduct2.setLabelPaint2(DEFAULT_LABEL_PAINT);
        spiderWebPlotProduct2.setLabelGenerator2(new StandardCategoryItemLabelGenerator());

        spiderWebPlotProduct2.setLegendItemShape2(DEFAULT_LEGEND_ITEM_CIRCLE);
    }

    /**
     * Returns a short string describing the type of plot.
     *
     * @return The plot type.
     */
    @Override
    public String getPlotType() {
        // return localizationResources.getString("Radar_Plot");
        return ("Spider Web Plot");
    }

    /**
     * Returns the dataset.
     *
     * @return The dataset (possibly {@code null}).
     *
     * @see #setDataset(CategoryDataset)
     */
    public CategoryDataset getDataset() {
        return this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset();
    }

    /**
     * Sets the dataset used by the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param dataset  the dataset ({@code null} permitted).
     *
     * @see #getDataset()
     */
    public void setDataset(CategoryDataset dataset) {
        // if there is an existing dataset, remove the plot from the list of
        // change listeners...
        if (this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset() != null) {
            this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().removeChangeListener(this);
        }

        // set the new dataset, and register the chart as a change listener...
        spiderWebPlotProduct2.getSpiderWebPlotProduct().setDataset(dataset);
        if (dataset != null) {
            dataset.addChangeListener(this);
        }

        // send a dataset change event to self to trigger plot change event
        datasetChanged(new DatasetChangeEvent(this, dataset));
    }

    /**
     * Method to determine if the web chart is to be filled.
     *
     * @return A boolean.
     *
     * @see #setWebFilled(boolean)
     */
    public boolean isWebFilled() {
        return this.spiderWebPlotProduct2.getWebFilled();
    }

    /**
     * Sets the webFilled flag and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param flag  the flag.
     *
     * @see #isWebFilled()
     */
    public void setWebFilled(boolean flag) {
        spiderWebPlotProduct2.setWebFilled(flag, this);
    }

    /**
     * Returns the data extract order (by row or by column).
     *
     * @return The data extract order (never {@code null}).
     *
     * @see #setDataExtractOrder(TableOrder)
     */
    public TableOrder getDataExtractOrder() {
        return this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder();
    }

    /**
     * Sets the data extract order (by row or by column) and sends a
     * {@link PlotChangeEvent}to all registered listeners.
     *
     * @param order the order ({@code null} not permitted).
     *
     * @throws IllegalArgumentException if {@code order} is
     *     {@code null}.
     *
     * @see #getDataExtractOrder()
     */
    public void setDataExtractOrder(TableOrder order) {
        spiderWebPlotProduct2.getSpiderWebPlotProduct().setDataExtractOrder(order, this);
    }

    /**
     * Returns the head percent (the default value is 0.01).
     *
     * @return The head percent (always > 0).
     *
     * @see #setHeadPercent(double)
     */
    public double getHeadPercent() {
        return this.spiderWebPlotProduct2.getHeadPercent();
    }

    /**
     * Sets the head percent and sends a {@link PlotChangeEvent} to all
     * registered listeners.  Note that 0.10 is 10 percent.
     *
     * @param percent  the percent (must be greater than zero).
     *
     * @see #getHeadPercent()
     */
    public void setHeadPercent(double percent) {
        spiderWebPlotProduct2.setHeadPercent(percent, this);
    }

    /**
     * Returns the start angle for the first radar axis.
     * <BR>
     * This is measured in degrees starting from 3 o'clock (Java Arc2D default)
     * and measuring anti-clockwise.
     *
     * @return The start angle.
     *
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.spiderWebPlotProduct2.getStartAngle();
    }

    /**
     * Sets the starting angle and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * <P>
     * The initial default value is 90 degrees, which corresponds to 12 o'clock.
     * A value of zero corresponds to 3 o'clock... this is the encoding used by
     * Java's Arc2D class.
     *
     * @param angle  the angle (in degrees).
     *
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        spiderWebPlotProduct2.setStartAngle(angle, this);
    }

    /**
     * Returns the maximum value any category axis can take.
     *
     * @return The maximum value.
     *
     * @see #setMaxValue(double)
     */
    public double getMaxValue() {
        return this.spiderWebPlotProduct2.getMaxValue();
    }

    /**
     * Sets the maximum value any category axis can take and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param value  the maximum value.
     *
     * @see #getMaxValue()
     */
    public void setMaxValue(double value) {
        spiderWebPlotProduct2.setMaxValue(value, this);
    }

    /**
     * Returns the direction in which the radar axes are drawn
     * (clockwise or anti-clockwise).
     *
     * @return The direction (never {@code null}).
     *
     * @see #setDirection(Rotation)
     */
    public Rotation getDirection() {
        return this.spiderWebPlotProduct2.getDirection();
    }

    /**
     * Sets the direction in which the radar axes are drawn and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param direction  the direction ({@code null} not permitted).
     *
     * @see #getDirection()
     */
    public void setDirection(Rotation direction) {
        spiderWebPlotProduct2.setDirection(direction, this);
    }

    /**
     * Returns the interior gap, measured as a percentage of the available
     * drawing space.
     *
     * @return The gap (as a percentage of the available drawing space).
     *
     * @see #setInteriorGap(double)
     */
    public double getInteriorGap() {
        return this.spiderWebPlotProduct2.getInteriorGap();
    }

    /**
     * Sets the interior gap and sends a {@link PlotChangeEvent} to all
     * registered listeners. This controls the space between the edges of the
     * plot and the plot area itself (the region where the axis labels appear).
     *
     * @param percent  the gap (as a percentage of the available drawing space).
     *
     * @see #getInteriorGap()
     */
    public void setInteriorGap(double percent) {
        spiderWebPlotProduct2.setInteriorGap(percent, this);
    }

    /**
     * Returns the axis label gap.
     *
     * @return The axis label gap.
     *
     * @see #setAxisLabelGap(double)
     */
    public double getAxisLabelGap() {
        return this.axisLabelGap;
    }

    /**
     * Sets the axis label gap and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param gap  the gap.
     *
     * @see #getAxisLabelGap()
     */
    public void setAxisLabelGap(double gap) {
        this.axisLabelGap = gap;
        fireChangeEvent();
    }

    /**
     * Returns the paint used to draw the axis lines.
     *
     * @return The paint used to draw the axis lines (never {@code null}).
     *
     * @see #setAxisLinePaint(Paint)
     * @see #getAxisLineStroke()
     */
    public Paint getAxisLinePaint() {
        return this.spiderWebPlotProduct2.getAxisLinePaint();
    }

    /**
     * Sets the paint used to draw the axis lines and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     *
     * @see #getAxisLinePaint()
     */
    public void setAxisLinePaint(Paint paint) {
        spiderWebPlotProduct2.setAxisLinePaint(paint, this);
    }

    /**
     * Returns the stroke used to draw the axis lines.
     *
     * @return The stroke used to draw the axis lines (never {@code null}).
     *
     * @see #setAxisLineStroke(Stroke)
     * @see #getAxisLinePaint()
     */
    public Stroke getAxisLineStroke() {
        return this.spiderWebPlotProduct2.getAxisLineStroke();
    }

    /**
     * Sets the stroke used to draw the axis lines and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke ({@code null} not permitted).
     *
     * @see #getAxisLineStroke()
     */
    public void setAxisLineStroke(Stroke stroke) {
        spiderWebPlotProduct2.setAxisLineStroke(stroke, this);
    }

    //// SERIES PAINT /////////////////////////

    /**
     * Returns the paint for the specified series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The paint (never {@code null}).
     *
     * @see #setSeriesPaint(int, Paint)
     */
    public Paint getSeriesPaint(int series) {
        return spiderWebPlotProduct2.getSeriesPaint(series, this);
    }

    /**
     * Sets the paint used to fill a series of the radar and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     *
     * @see #getSeriesPaint(int)
     */
    public void setSeriesPaint(int series, Paint paint) {
        spiderWebPlotProduct2.setSeriesPaint(series, paint, this);
    }

    /**
     * Returns the default series paint, used when no other paint is
     * available.
     *
     * @return The paint (never {@code null}).
     *
     * @see #setDefaultSeriesPaint(Paint)
     */
    public Paint getDefaultSeriesPaint() {
      return this.spiderWebPlotProduct2.getDefaultSeriesPaint();
    }

    /**
     * Sets the default series paint.
     *
     * @param paint  the paint ({@code null} not permitted).
     *
     * @see #getDefaultSeriesPaint()
     */
    public void setDefaultSeriesPaint(Paint paint) {
        spiderWebPlotProduct2.setDefaultSeriesPaint(paint, this);
    }

    //// SERIES OUTLINE PAINT ////////////////////////////

    /**
     * Returns the paint for the specified series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The paint (never {@code null}).
     */
    public Paint getSeriesOutlinePaint(int series) {
        return spiderWebPlotProduct2.getSeriesOutlinePaint(series);
    }

    /**
     * Sets the paint used to fill a series of the radar and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param paint  the paint ({@code null} permitted).
     */
    public void setSeriesOutlinePaint(int series, Paint paint) {
        spiderWebPlotProduct2.setSeriesOutlinePaint(series, paint, this);
    }

    /**
     * Returns the base series paint. This is used when no other paint is
     * available.
     *
     * @return The paint (never {@code null}).
     */
    public Paint getDefaultSeriesOutlinePaint() {
        return this.spiderWebPlotProduct2.getDefaultSeriesOutlinePaint();
    }

    /**
     * Sets the base series paint and sends a change event to all registered
     * listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     */
    public void setDefaultSeriesOutlinePaint(Paint paint) {
        spiderWebPlotProduct2.setDefaultSeriesOutlinePaint(paint, this);
    }

    //// SERIES OUTLINE STROKE /////////////////////

    /**
     * Returns the stroke for the specified series.
     *
     * @param series  the series index (zero-based).
     *
     * @return The stroke (never {@code null}).
     */
    public Stroke getSeriesOutlineStroke(int series) {
        return spiderWebPlotProduct2.getSeriesOutlineStroke(series);

    }

    /**
     * Sets the stroke used to fill a series of the radar and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param stroke  the stroke ({@code null} permitted).
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke) {
        spiderWebPlotProduct2.setSeriesOutlineStroke(series, stroke, this);
    }

    /**
     * Returns the default series stroke. This is used when no other stroke is
     * available.
     *
     * @return The stroke (never {@code null}).
     */
    public Stroke getDefaultSeriesOutlineStroke() {
        return this.spiderWebPlotProduct2.getDefaultSeriesOutlineStroke();
    }

    /**
     * Sets the default series stroke and sends a change event to all 
     * registered listeners.
     *
     * @param stroke  the stroke ({@code null} not permitted).
     */
    public void setDefaultSeriesOutlineStroke(Stroke stroke) {
        spiderWebPlotProduct2.setDefaultSeriesOutlineStroke(stroke, this);
    }

    /**
     * Returns the shape used for legend items.
     *
     * @return The shape (never {@code null}).
     *
     * @see #setLegendItemShape(Shape)
     */
    public Shape getLegendItemShape() {
        return this.spiderWebPlotProduct2.getLegendItemShape();
    }

    /**
     * Sets the shape used for legend items and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param shape  the shape ({@code null} not permitted).
     *
     * @see #getLegendItemShape()
     */
    public void setLegendItemShape(Shape shape) {
        spiderWebPlotProduct2.setLegendItemShape(shape, this);
    }

    /**
     * Returns the series label font.
     *
     * @return The font (never {@code null}).
     *
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.spiderWebPlotProduct2.getLabelFont();
    }

    /**
     * Sets the series label font and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param font  the font ({@code null} not permitted).
     *
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
        spiderWebPlotProduct2.setLabelFont(font, this);
    }

    /**
     * Returns the series label paint.
     *
     * @return The paint (never {@code null}).
     *
     * @see #setLabelPaint(Paint)
     */
    public Paint getLabelPaint() {
        return this.spiderWebPlotProduct2.getLabelPaint();
    }

    /**
     * Sets the series label paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param paint  the paint ({@code null} not permitted).
     *
     * @see #getLabelPaint()
     */
    public void setLabelPaint(Paint paint) {
        spiderWebPlotProduct2.setLabelPaint(paint, this);
    }

    /**
     * Returns the label generator.
     *
     * @return The label generator (never {@code null}).
     *
     * @see #setLabelGenerator(CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getLabelGenerator() {
        return this.spiderWebPlotProduct2.getLabelGenerator();
    }

    /**
     * Sets the label generator and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param generator  the generator ({@code null} not permitted).
     *
     * @see #getLabelGenerator()
     */
    public void setLabelGenerator(CategoryItemLabelGenerator generator) {
        spiderWebPlotProduct2.setLabelGenerator(generator);
    }

    /**
     * Returns the tool tip generator for the plot.
     *
     * @return The tool tip generator (possibly {@code null}).
     *
     * @see #setToolTipGenerator(CategoryToolTipGenerator)
     */
    public CategoryToolTipGenerator getToolTipGenerator() {
        return this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getToolTipGenerator();
    }

    /**
     * Sets the tool tip generator for the plot and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param generator  the generator ({@code null} permitted).
     *
     * @see #getToolTipGenerator()
     */
    public void setToolTipGenerator(CategoryToolTipGenerator generator) {
        spiderWebPlotProduct2.getSpiderWebPlotProduct().setToolTipGenerator(generator, this);
    }

    /**
     * Returns the URL generator for the plot.
     *
     * @return The URL generator (possibly {@code null}).
     *
     * @see #setURLGenerator(CategoryURLGenerator)
     */
    public CategoryURLGenerator getURLGenerator() {
        return this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getUrlGenerator();
    }

    /**
     * Sets the URL generator for the plot and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param generator  the generator ({@code null} permitted).
     *
     * @see #getURLGenerator()
     */
    public void setURLGenerator(CategoryURLGenerator generator) {
        spiderWebPlotProduct2.getSpiderWebPlotProduct().setURLGenerator(generator, this);
    }

    /**
     * Returns a collection of legend items for the spider web chart.
     *
     * @return The legend items (never {@code null}).
     */
    @Override
    public LegendItemCollection getLegendItems() {
        LegendItemCollection result = new LegendItemCollection();
        if (getDataset() == null) {
            return result;
        }
        List keys = null;
        if (this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder() == TableOrder.BY_ROW) {
            keys = this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().getRowKeys();
        }
        else if (this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder() == TableOrder.BY_COLUMN) {
            keys = this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().getColumnKeys();
        }
        if (keys == null) {
            return result;
        }

        int series = 0;
        Iterator iterator = keys.iterator();
        Shape shape = getLegendItemShape();
        while (iterator.hasNext()) {
            Comparable key = (Comparable) iterator.next();
            String label = key.toString();
            String description = label;
            Paint paint = spiderWebPlotProduct2.getSeriesPaint(series, this);
            Paint outlinePaint = spiderWebPlotProduct2.getSeriesOutlinePaint(series);
            Stroke stroke = spiderWebPlotProduct2.getSeriesOutlineStroke(series);
            LegendItem item = new LegendItem(label, description,
                    null, null, shape, paint, stroke, outlinePaint);
            item.setDataset(getDataset());
            item.setSeriesKey(key);
            item.setSeriesIndex(series);
            result.add(item);
            series++;
        }
        return result;
    }

    /**
     * Returns a cartesian point from a polar angle, length and bounding box
     *
     * @param bounds  the area inside which the point needs to be.
     * @param angle  the polar angle, in degrees.
     * @param length  the relative length. Given in percent of maximum extend.
     *
     * @return The cartesian point.
     */
    protected Point2D getWebPoint(Rectangle2D bounds,
                                  double angle, double length) {

        double angrad = Math.toRadians(angle);
        double x = Math.cos(angrad) * length * bounds.getWidth() / 2;
        double y = -Math.sin(angrad) * length * bounds.getHeight() / 2;

        return new Point2D.Double(bounds.getX() + x + bounds.getWidth() / 2,
                bounds.getY() + y + bounds.getHeight() / 2);
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     * @param anchor  the anchor point ({@code null} permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects info about the drawing.
     */
    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
            PlotState parentState, PlotRenderingInfo info) {

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);

        info(area, info);
		drawBackground(g2, area);
        drawOutline(g2, area);

        Shape savedClip = g2.getClip();

        g2.clip(area);
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                getForegroundAlpha()));

        if (!DatasetUtils.isEmptyOrNull(this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset())) {
            int seriesCount, catCount;

            if (this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder() == TableOrder.BY_ROW) {
                seriesCount = this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().getRowCount();
                catCount = this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().getColumnCount();
            }
            else {
                seriesCount = this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().getColumnCount();
                catCount = this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().getRowCount();
            }

            // ensure we have a maximum value to use on the axes
            if (this.spiderWebPlotProduct2.getMaxValue() == DEFAULT_MAX_VALUE) {
                spiderWebPlotProduct2.calculateMaxValue(seriesCount, catCount);
            }

            // Next, setup the plot area

            // adjust the plot area by the interior spacing value

            double gapHorizontal = area.getWidth() * getInteriorGap();
            double gapVertical = area.getHeight() * getInteriorGap();

            double X = area.getX() + gapHorizontal / 2;
            double Y = area.getY() + gapVertical / 2;
            double W = area.getWidth() - gapHorizontal;
            double H = area.getHeight() - gapVertical;

            double headW = area.getWidth() * this.spiderWebPlotProduct2.getHeadPercent();
            double headH = area.getHeight() * this.spiderWebPlotProduct2.getHeadPercent();

            // make the chart area a square
            double min = Math.min(W, H) / 2;
            X = (X + X + W) / 2 - min;
            Y = (Y + Y + H) / 2 - min;
            W = 2 * min;
            H = 2 * min;

            Point2D  centre = new Point2D.Double(X + W / 2, Y + H / 2);
            Rectangle2D radarArea = new Rectangle2D.Double(X, Y, W, H);

            // draw the axis and category label
            for (int cat = 0; cat < catCount; cat++) {
                double angle = getStartAngle()
                        + (getDirection().getFactor() * cat * 360 / catCount);

                Point2D endPoint = getWebPoint(radarArea, angle, 1);
                                                     // 1 = end of axis
                Line2D  line = new Line2D.Double(centre, endPoint);
                g2.setPaint(this.spiderWebPlotProduct2.getAxisLinePaint());
                g2.setStroke(this.spiderWebPlotProduct2.getAxisLineStroke());
                g2.draw(line);
                drawLabel(g2, radarArea, 0.0, cat, angle, 360.0 / catCount);
            }

            // Now actually plot each of the series polygons..
            for (int series = 0; series < seriesCount; series++) {
                drawRadarPoly(g2, radarArea, centre, info, series, catCount,
                        headH, headW);
            }
        } else {
            drawNoDataMessage(g2, area);
        }
        g2.setClip(savedClip);
        g2.setComposite(originalComposite);
        drawOutline(g2, area);
    }

	private void info(Rectangle2D area, PlotRenderingInfo info) {
		if (info != null) {
			info.setPlotArea(area);
			info.setDataArea(area);
		}
	}

    /**
     * Draws a radar plot polygon.
     *
     * @param g2 the graphics device.
     * @param plotArea the area we are plotting in (already adjusted).
     * @param centre the centre point of the radar axes
     * @param info chart rendering info.
     * @param series the series within the dataset we are plotting
     * @param catCount the number of categories per radar plot
     * @param headH the data point height
     * @param headW the data point width
     */
    protected void drawRadarPoly(Graphics2D g2, Rectangle2D plotArea,
            Point2D centre, PlotRenderingInfo info, int series, int catCount,
            double headH, double headW) {

        Polygon polygon = new Polygon();

        EntityCollection entities = entities(info);
		// plot the data...
        for (int cat = 0; cat < catCount; cat++) {

            Number dataValue = spiderWebPlotProduct2.getSpiderWebPlotProduct().getPlotValue(series, cat);

            if (dataValue != null) {
                double value = dataValue.doubleValue();

                if (value >= 0) { // draw the polygon series...

                    // Finds our starting angle from the centre for this axis

                    Ellipse2D head = head(plotArea, catCount, headH, headW, cat, value);
					double angle = getStartAngle()
                        + (getDirection().getFactor() * cat * 360 / catCount);

                    // The following angle calc will ensure there isn't a top
                    // vertical axis - this may be useful if you don't want any
                    // given criteria to 'appear' move important than the
                    // others..
                    //  + (getDirection().getFactor()
                    //        * (cat + 0.5) * 360 / catCount);

                    // find the point at the appropriate distance end point
                    // along the axis/angle identified above and add it to the
                    // polygon

                    Point2D point = getWebPoint(plotArea, angle,
                            value / this.spiderWebPlotProduct2.getMaxValue());
                    polygon.addPoint((int) point.getX(), (int) point.getY());

                    // put an elipse at the point being plotted..

                    Paint paint = spiderWebPlotProduct2.getSeriesPaint(series, this);
                    Paint outlinePaint = spiderWebPlotProduct2.getSeriesOutlinePaint(series);
                    Stroke outlineStroke = spiderWebPlotProduct2.getSeriesOutlineStroke(series);

                    g2.setPaint(paint);
                    g2.fill(head);
                    g2.setStroke(outlineStroke);
                    g2.setPaint(outlinePaint);
                    g2.draw(head);

                    if (entities != null) {
                        CategoryItemEntity entity = spiderWebPlotProduct2.getSpiderWebPlotProduct().entity(series, headH, headW, cat, point);
						entities.add(entity);
                    }

                }
            }
        }
        // Plot the polygon

        Paint paint = spiderWebPlotProduct2.getSeriesPaint(series, this);
        g2.setPaint(paint);
        g2.setStroke(spiderWebPlotProduct2.getSeriesOutlineStroke(series));
        g2.draw(polygon);

        // Lastly, fill the web polygon if this is required

        if (this.spiderWebPlotProduct2.getWebFilled()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.1f));
            g2.fill(polygon);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    getForegroundAlpha()));
        }
    }

	private Ellipse2D head(Rectangle2D plotArea, int catCount, double headH, double headW, int cat, double value) {
		double angle = getStartAngle() + (getDirection().getFactor() * cat * 360 / catCount);
		Point2D point = getWebPoint(plotArea, angle, value / this.spiderWebPlotProduct2.getMaxValue());
		Ellipse2D head = new Ellipse2D.Double(point.getX() - headW / 2, point.getY() - headH / 2, headW, headH);
		return head;
	}

	private EntityCollection entities(PlotRenderingInfo info) {
		EntityCollection entities = null;
		if (info != null) {
			entities = info.getOwner().getEntityCollection();
		}
		return entities;
	}

    /**
     * Returns the value to be plotted at the intersection of the
     * series and the category.  This allows us to plot
     * {@code BY_ROW} or {@code BY_COLUMN} which basically is just
     * reversing the definition of the categories and data series being
     * plotted.
     *
     * @param series the series to be plotted.
     * @param cat the category within the series to be plotted.
     *
     * @return The value to be plotted (possibly {@code null}).
     *
     * @see #getDataExtractOrder()
     */
    protected Number getPlotValue(int series, int cat) {
        return spiderWebPlotProduct2.getSpiderWebPlotProduct().getPlotValue(series, cat);
    }

    /**
     * Draws the label for one axis.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area
     * @param value  the value of the label (ignored).
     * @param cat  the category (zero-based index).
     * @param startAngle  the starting angle.
     * @param extent  the extent of the arc.
     */
    protected void drawLabel(Graphics2D g2, Rectangle2D plotArea, double value,
                             int cat, double startAngle, double extent) {
        FontRenderContext frc = g2.getFontRenderContext();

        String label;
        if (this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder() == TableOrder.BY_ROW) {
            // if series are in rows, then the categories are the column keys
            label = this.spiderWebPlotProduct2.getLabelGenerator().generateColumnLabel(this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset(), cat);
        } else {
            // if series are in columns, then the categories are the row keys
            label = this.spiderWebPlotProduct2.getLabelGenerator().generateRowLabel(this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset(), cat);
        }

        Rectangle2D labelBounds = getLabelFont().getStringBounds(label, frc);
        LineMetrics lm = getLabelFont().getLineMetrics(label, frc);
        double ascent = lm.getAscent();

        Point2D labelLocation = calculateLabelLocation(labelBounds, ascent,
                plotArea, startAngle);

        Composite saveComposite = g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.setPaint(getLabelPaint());
        g2.setFont(getLabelFont());
        g2.drawString(label, (float) labelLocation.getX(),
                (float) labelLocation.getY());
        g2.setComposite(saveComposite);
    }

    /**
     * Returns the location for a label
     *
     * @param labelBounds the label bounds.
     * @param ascent the ascent (height of font).
     * @param plotArea the plot area
     * @param startAngle the start angle for the pie series.
     *
     * @return The location for a label.
     */
    protected Point2D calculateLabelLocation(Rectangle2D labelBounds,
            double ascent, Rectangle2D plotArea, double startAngle) {
        Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0, Arc2D.OPEN);
        Point2D point1 = arc1.getEndPoint();

        double deltaX = -(point1.getX() - plotArea.getCenterX())
                        * this.axisLabelGap;
        double deltaY = -(point1.getY() - plotArea.getCenterY())
                        * this.axisLabelGap;

        double labelX = point1.getX() - deltaX;
        double labelY = point1.getY() - deltaY;

        if (labelX < plotArea.getCenterX()) {
            labelX -= labelBounds.getWidth();
        }

        if (labelX == plotArea.getCenterX()) {
            labelX -= labelBounds.getWidth() / 2;
        }

        if (labelY > plotArea.getCenterY()) {
            labelY += ascent;
        }

        return new Point2D.Double(labelX, labelY);
    }

    /**
     * Tests this plot for equality with an arbitrary object.
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
        if (!(obj instanceof SpiderWebPlot)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        SpiderWebPlot that = (SpiderWebPlot) obj;
        if (!this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder().equals(that.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataExtractOrder())) {
            return false;
        }
        if (this.spiderWebPlotProduct2.getHeadPercent() != that.spiderWebPlotProduct2.getHeadPercent()) {
            return false;
        }
        if (this.spiderWebPlotProduct2.getInteriorGap() != that.spiderWebPlotProduct2.getInteriorGap()) {
            return false;
        }
        if (this.spiderWebPlotProduct2.getStartAngle() != that.spiderWebPlotProduct2.getStartAngle()) {
            return false;
        }
        if (!this.spiderWebPlotProduct2.getDirection().equals(that.spiderWebPlotProduct2.getDirection())) {
            return false;
        }
        if (this.spiderWebPlotProduct2.getMaxValue() != that.spiderWebPlotProduct2.getMaxValue()) {
            return false;
        }
        if (this.spiderWebPlotProduct2.getWebFilled() != that.spiderWebPlotProduct2.getWebFilled()) {
            return false;
        }
        if (this.axisLabelGap != that.axisLabelGap) {
            return false;
        }
        if (!PaintUtils.equal(this.spiderWebPlotProduct2.getAxisLinePaint(), that.spiderWebPlotProduct2.getAxisLinePaint())) {
            return false;
        }
        if (!this.spiderWebPlotProduct2.getAxisLineStroke().equals(that.spiderWebPlotProduct2.getAxisLineStroke())) {
            return false;
        }
        if (!ShapeUtils.equal(this.spiderWebPlotProduct2.getLegendItemShape(), that.spiderWebPlotProduct2.getLegendItemShape())) {
            return false;
        }
        if (!PaintUtils.equal(this.spiderWebPlotProduct2.getSeriesPaints(), that.spiderWebPlotProduct2.getSeriesPaints())) {
            return false;
        }
        if (!PaintUtils.equal(this.spiderWebPlotProduct2.getDefaultSeriesPaint(), that.spiderWebPlotProduct2.getDefaultSeriesPaint())) {
            return false;
        }
        if (!PaintUtils.equal(this.spiderWebPlotProduct2.getSeriesOutlinePaints(), that.spiderWebPlotProduct2.getSeriesOutlinePaints())) {
            return false;
        }
        if (!PaintUtils.equal(this.spiderWebPlotProduct2.getDefaultSeriesOutlinePaint(),
                that.spiderWebPlotProduct2.getDefaultSeriesOutlinePaint())) {
            return false;
        }

        if (!this.spiderWebPlotProduct2.getSeriesOutlineStrokes().equals(that.spiderWebPlotProduct2.getSeriesOutlineStrokes())) {
            return false;
        }
        if (!this.spiderWebPlotProduct2.getDefaultSeriesOutlineStroke().equals(that.spiderWebPlotProduct2.getDefaultSeriesOutlineStroke())) {
            return false;
        }
        if (!this.spiderWebPlotProduct2.getLabelFont().equals(that.spiderWebPlotProduct2.getLabelFont())) {
            return false;
        }
        if (!PaintUtils.equal(this.spiderWebPlotProduct2.getLabelPaint(), that.spiderWebPlotProduct2.getLabelPaint())) {
            return false;
        }
        if (!this.spiderWebPlotProduct2.getLabelGenerator().equals(that.spiderWebPlotProduct2.getLabelGenerator())) {
            return false;
        }
        if (!Objects.equals(this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getToolTipGenerator(), that.spiderWebPlotProduct2.getSpiderWebPlotProduct().getToolTipGenerator())) {
            return false;
        }
        if (!Objects.equals(this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getUrlGenerator(), that.spiderWebPlotProduct2.getSpiderWebPlotProduct().getUrlGenerator())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a clone of this plot.
     *
     * @return A clone of this plot.
     *
     * @throws CloneNotSupportedException if the plot cannot be cloned for
     *         any reason.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        SpiderWebPlot clone = (SpiderWebPlot) super.clone();
		clone.spiderWebPlotProduct2 = (SpiderWebPlotProduct2) this.spiderWebPlotProduct2.clone();
		clone.spiderWebPlotProduct2.setSpiderWebPlotProduct(
				(SpiderWebPlotProduct) this.spiderWebPlotProduct2.getSpiderWebPlotProduct().clone());
        clone.spiderWebPlotProduct2
				.setLegendItemShape2(CloneUtils.clone(this.spiderWebPlotProduct2.getLegendItemShape()));
        clone.spiderWebPlotProduct2
				.setSeriesPaints(CloneUtils.cloneMapValues(this.spiderWebPlotProduct2.getSeriesPaints()));
        clone.spiderWebPlotProduct2
				.setSeriesOutlinePaints(CloneUtils.cloneMapValues(this.spiderWebPlotProduct2.getSeriesOutlinePaints()));
        clone.spiderWebPlotProduct2.setSeriesOutlineStrokes(
				CloneUtils.cloneMapValues(this.spiderWebPlotProduct2.getSeriesOutlineStrokes()));
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

        SerialUtils.writeShape(this.spiderWebPlotProduct2.getLegendItemShape(), stream);
        SerialUtils.writeMapOfPaint(this.spiderWebPlotProduct2.getSeriesPaints(), stream);
        SerialUtils.writePaint(this.spiderWebPlotProduct2.getDefaultSeriesPaint(), stream);
        SerialUtils.writeMapOfPaint(this.spiderWebPlotProduct2.getSeriesOutlinePaints(), stream);
        SerialUtils.writePaint(this.spiderWebPlotProduct2.getDefaultSeriesOutlinePaint(), stream);
        SerialUtils.writeMapOfStroke(this.spiderWebPlotProduct2.getSeriesOutlineStrokes(), stream);
        SerialUtils.writeStroke(this.spiderWebPlotProduct2.getDefaultSeriesOutlineStroke(), stream);
        SerialUtils.writePaint(this.spiderWebPlotProduct2.getLabelPaint(), stream);
        SerialUtils.writePaint(this.spiderWebPlotProduct2.getAxisLinePaint(), stream);
        SerialUtils.writeStroke(this.spiderWebPlotProduct2.getAxisLineStroke(), stream);
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();

        spiderWebPlotProduct2.setLegendItemShape2(SerialUtils.readShape(stream));
        spiderWebPlotProduct2.setSeriesPaints(SerialUtils.readMapOfPaint(stream));
        spiderWebPlotProduct2.setDefaultSeriesPaint2(SerialUtils.readPaint(stream));
        spiderWebPlotProduct2.setSeriesOutlinePaints(SerialUtils.readMapOfPaint(stream));
        spiderWebPlotProduct2.setDefaultSeriesOutlinePaint2(SerialUtils.readPaint(stream));
        spiderWebPlotProduct2.setSeriesOutlineStrokes(SerialUtils.readMapOfStroke(stream));
        spiderWebPlotProduct2.setDefaultSeriesOutlineStroke2(SerialUtils.readStroke(stream));
        spiderWebPlotProduct2.setLabelPaint2(SerialUtils.readPaint(stream));
        spiderWebPlotProduct2.setAxisLinePaint2(SerialUtils.readPaint(stream));
        spiderWebPlotProduct2.setAxisLineStroke2(SerialUtils.readStroke(stream));
        if (this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset() != null) {
            this.spiderWebPlotProduct2.getSpiderWebPlotProduct().getDataset().addChangeListener(this);
        }
    }

}
