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
 * ---------------------
 * CrosshairOverlay.java
 * ---------------------
 * (C) Copyright 2011-2022, by David Gilbert.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   John Matthews, Michal Wozniak;
 *
 */

package org.jfree.chart.swing;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.text.TextUtils;
import org.jfree.chart.api.RectangleAnchor;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.text.TextAnchor;
import org.jfree.chart.internal.CloneUtils;
import org.jfree.chart.internal.Args;
import org.jfree.chart.api.PublicCloneable;

/**
 * An overlay for a {@link ChartPanel} that draws crosshairs on a chart.  If 
 * you are using the JavaFX extensions for JFreeChart, then you should use
 * the {@code CrosshairOverlayFX} class.
 */
public class CrosshairOverlay extends AbstractOverlay implements Overlay,
        PropertyChangeListener, PublicCloneable, Cloneable, Serializable {

    private CrosshairOverlayRefactoring2 crosshairOverlayRefactoring2 = new CrosshairOverlayRefactoring2();

	private transient CrosshairOverlayRefactoring1 crosshairOverlayRefactoring1 = new CrosshairOverlayRefactoring1();

	/**
     * Creates a new overlay that initially contains no crosshairs.
     */
    public CrosshairOverlay() {
        super();
        crosshairOverlayRefactoring2.setXCrosshairs(new ArrayList<>());
        crosshairOverlayRefactoring2.setYCrosshairs(new ArrayList<>());
    }

    /**
     * Adds a crosshair against the domain axis (x-axis) and sends an
     * {@link OverlayChangeEvent} to all registered listeners.
     *
     * @param crosshair  the crosshair ({@code null} not permitted).
     *
     * @see #removeDomainCrosshair(org.jfree.chart.plot.Crosshair)
     * @see #addRangeCrosshair(org.jfree.chart.plot.Crosshair)
     */
    public void addDomainCrosshair(Crosshair crosshair) {
        crosshairOverlayRefactoring2.addDomainCrosshair(crosshair, this);
    }

    /**
     * Removes a domain axis crosshair and sends an {@link OverlayChangeEvent}
     * to all registered listeners.
     *
     * @param crosshair  the crosshair ({@code null} not permitted).
     *
     * @see #addDomainCrosshair(org.jfree.chart.plot.Crosshair)
     */
    public void removeDomainCrosshair(Crosshair crosshair) {
        crosshairOverlayRefactoring2.removeDomainCrosshair(crosshair, this);
    }

    /**
     * Clears all the domain crosshairs from the overlay and sends an
     * {@link OverlayChangeEvent} to all registered listeners (unless there
     * were no crosshairs to begin with).
     */
    public void clearDomainCrosshairs() {
        crosshairOverlayRefactoring2.clearDomainCrosshairs(this);
    }

    /**
     * Returns a new list containing the domain crosshairs for this overlay.
     *
     * @return A list of crosshairs.
     */
    public List<Crosshair> getDomainCrosshairs() {
        return crosshairOverlayRefactoring2.getDomainCrosshairs();
    }

    /**
     * Adds a crosshair against the range axis and sends an
     * {@link OverlayChangeEvent} to all registered listeners.
     *
     * @param crosshair  the crosshair ({@code null} not permitted).
     */
    public void addRangeCrosshair(Crosshair crosshair) {
        crosshairOverlayRefactoring2.addRangeCrosshair(crosshair, this);
    }

    /**
     * Removes a range axis crosshair and sends an {@link OverlayChangeEvent}
     * to all registered listeners.
     *
     * @param crosshair  the crosshair ({@code null} not permitted).
     *
     * @see #addRangeCrosshair(org.jfree.chart.plot.Crosshair)
     */
    public void removeRangeCrosshair(Crosshair crosshair) {
        crosshairOverlayRefactoring2.removeRangeCrosshair(crosshair, this);
    }

    /**
     * Clears all the range crosshairs from the overlay and sends an
     * {@link OverlayChangeEvent} to all registered listeners (unless there
     * were no crosshairs to begin with).
     */
    public void clearRangeCrosshairs() {
        crosshairOverlayRefactoring2.clearRangeCrosshairs(this);
    }

    /**
     * Returns a new list containing the range crosshairs for this overlay.
     *
     * @return A list of crosshairs.
     */
    public List<Crosshair> getRangeCrosshairs() {
        return crosshairOverlayRefactoring2.getRangeCrosshairs();
    }

    /**
     * Receives a property change event (typically a change in one of the
     * crosshairs).
     *
     * @param e  the event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        fireOverlayChanged();
    }

    /**
     * Renders the crosshairs in the overlay on top of the chart that has just
     * been rendered in the specified {@code chartPanel}.  This method is
     * called by the JFreeChart framework, you won't normally call it from
     * user code.
     *
     * @param g2  the graphics target.
     * @param chartPanel  the chart panel.
     */
    @Override
    public void paintOverlay(Graphics2D g2, ChartPanel chartPanel) {
        Shape savedClip = g2.getClip();
        Rectangle2D dataArea = chartPanel.getScreenDataArea();
        g2.clip(dataArea);
        JFreeChart chart = chartPanel.getChart();
        XYPlot plot = (XYPlot) chart.getPlot();
        ValueAxis xAxis = plot.getDomainAxis();
        RectangleEdge xAxisEdge = plot.getDomainAxisEdge();
        for (Crosshair ch : crosshairOverlayRefactoring2.getDomainCrosshairs()) {
            if (ch.isVisible()) {
                double x = ch.getValue();
                double xx = xAxis.valueToJava2D(x, dataArea, xAxisEdge);
                if (plot.getOrientation() == PlotOrientation.VERTICAL) {
                    crosshairOverlayRefactoring1.drawVerticalCrosshair(g2, dataArea, xx, ch);
                } else {
                    crosshairOverlayRefactoring1.drawHorizontalCrosshair(g2, dataArea, xx, ch);
                }
            }
        }
        ValueAxis yAxis = plot.getRangeAxis();
        RectangleEdge yAxisEdge = plot.getRangeAxisEdge();
        for (Crosshair ch : crosshairOverlayRefactoring2.getRangeCrosshairs()) {
            if (ch.isVisible()) {
                double y = ch.getValue();
                double yy = yAxis.valueToJava2D(y, dataArea, yAxisEdge);
                if (plot.getOrientation() == PlotOrientation.VERTICAL) {
                    crosshairOverlayRefactoring1.drawHorizontalCrosshair(g2, dataArea, yy, ch);
                } else {
                    crosshairOverlayRefactoring1.drawVerticalCrosshair(g2, dataArea, yy, ch);
                }
            }
        }
        g2.setClip(savedClip);
    }

    /**
     * Draws a crosshair horizontally across the plot.
     *
     * @param g2  the graphics target.
     * @param dataArea  the data area.
     * @param y  the y-value in Java2D space.
     * @param crosshair  the crosshair.
     */
    protected void drawHorizontalCrosshair(Graphics2D g2, Rectangle2D dataArea,
            double y, Crosshair crosshair) {

        crosshairOverlayRefactoring1.drawHorizontalCrosshair(g2, dataArea, y, crosshair);
    }

    /**
     * Draws a crosshair vertically on the plot.
     *
     * @param g2  the graphics target.
     * @param dataArea  the data area.
     * @param x  the x-value in Java2D space.
     * @param crosshair  the crosshair.
     */
    protected void drawVerticalCrosshair(Graphics2D g2, Rectangle2D dataArea,
            double x, Crosshair crosshair) {

        crosshairOverlayRefactoring1.drawVerticalCrosshair(g2, dataArea, x, crosshair);
    }

    /**
     * Tests this overlay for equality with an arbitrary object.
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
        if (!(obj instanceof CrosshairOverlay)) {
            return false;
        }
        CrosshairOverlay that = (CrosshairOverlay) obj;
        if (!this.crosshairOverlayRefactoring2.getXCrosshairs().equals(that.crosshairOverlayRefactoring2.getXCrosshairs())) {
            return false;
        }
        if (!this.crosshairOverlayRefactoring2.getYCrosshairs().equals(that.crosshairOverlayRefactoring2.getYCrosshairs())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a clone of this instance.
     *
     * @return A clone of this instance.
     *
     * @throws java.lang.CloneNotSupportedException if there is some problem
     *     with the cloning.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        CrosshairOverlay clone = (CrosshairOverlay) super.clone();
		clone.crosshairOverlayRefactoring2 = (CrosshairOverlayRefactoring2) this.crosshairOverlayRefactoring2.clone();
		clone.crosshairOverlayRefactoring1 = (CrosshairOverlayRefactoring1) this.crosshairOverlayRefactoring1.clone();
        clone.crosshairOverlayRefactoring2
				.setXCrosshairs((List) CloneUtils.cloneList(this.crosshairOverlayRefactoring2.getXCrosshairs()));
        clone.crosshairOverlayRefactoring2
				.setYCrosshairs((List) CloneUtils.cloneList(this.crosshairOverlayRefactoring2.getYCrosshairs()));
        return clone;
    }

	private void readObject(java.io.ObjectInputStream stream)
			throws java.io.IOException, java.lang.ClassNotFoundException {
		stream.defaultReadObject();
		this.crosshairOverlayRefactoring1 = (CrosshairOverlayRefactoring1) stream.readObject();
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
		stream.defaultWriteObject();
		stream.writeObject(this.crosshairOverlayRefactoring1);
	}

}
