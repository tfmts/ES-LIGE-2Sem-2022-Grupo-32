package org.jfree.chart.plot;


import org.jfree.data.Range;
import java.awt.Paint;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.internal.Args;
import java.awt.geom.Arc2D;
import java.io.Serializable;

public class MeterPlotProduct implements Serializable, Cloneable {
	private DialShape shape;
	private int meterAngle;
	private Range range;
	private transient Paint tickPaint;
	private String units;
	private Font valueFont;
	private transient Paint valuePaint;
	private boolean valueVisible = true;
	private boolean drawBorder;
	private transient Paint dialOutlinePaint;
	private transient Paint dialBackgroundPaint;
	private transient Paint needlePaint;
	private boolean tickLabelsVisible;
	private Font tickLabelFont;
	private transient Paint tickLabelPaint;

	public DialShape getShape() {
		return shape;
	}

	public void setShape(DialShape shape) {
		this.shape = shape;
	}

	public int getMeterAngle() {
		return meterAngle;
	}

	public void setMeterAngle2(int meterAngle) {
		this.meterAngle = meterAngle;
	}

	public Range getRange() {
		return range;
	}

	public void setRange2(Range range) {
		this.range = range;
	}

	public Paint getTickPaint() {
		return tickPaint;
	}

	public void setTickPaint2(Paint tickPaint) {
		this.tickPaint = tickPaint;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits2(String units) {
		this.units = units;
	}

	public Font getValueFont() {
		return valueFont;
	}

	public void setValueFont2(Font valueFont) {
		this.valueFont = valueFont;
	}

	public Paint getValuePaint() {
		return valuePaint;
	}

	public void setValuePaint2(Paint valuePaint) {
		this.valuePaint = valuePaint;
	}

	public boolean getValueVisible() {
		return valueVisible;
	}

	public boolean getDrawBorder() {
		return drawBorder;
	}

	public Paint getDialOutlinePaint() {
		return dialOutlinePaint;
	}

	public void setDialOutlinePaint2(Paint dialOutlinePaint) {
		this.dialOutlinePaint = dialOutlinePaint;
	}

	public Paint getDialBackgroundPaint() {
		return dialBackgroundPaint;
	}

	public void setDialBackgroundPaint2(Paint dialBackgroundPaint) {
		this.dialBackgroundPaint = dialBackgroundPaint;
	}

	public Paint getNeedlePaint() {
		return needlePaint;
	}

	public void setNeedlePaint2(Paint needlePaint) {
		this.needlePaint = needlePaint;
	}

	public boolean getTickLabelsVisible() {
		return tickLabelsVisible;
	}

	public void setTickLabelsVisible2(boolean tickLabelsVisible) {
		this.tickLabelsVisible = tickLabelsVisible;
	}

	public Font getTickLabelFont() {
		return tickLabelFont;
	}

	public void setTickLabelFont2(Font tickLabelFont) {
		this.tickLabelFont = tickLabelFont;
	}

	public Paint getTickLabelPaint() {
		return tickLabelPaint;
	}

	public void setTickLabelPaint2(Paint tickLabelPaint) {
		this.tickLabelPaint = tickLabelPaint;
	}

	/**
	* Fills an arc on the dial between the given values.
	* @param g2   the graphics device.
	* @param area   the plot area.
	* @param minValue   the minimum data value.
	* @param maxValue   the maximum data value.
	* @param paint   the background paint ( {@code  null}  not permitted).
	* @param dial   a flag that indicates whether the arc represents the whole dial.
	*/
	public void fillArc(Graphics2D g2, Rectangle2D area, double minValue, double maxValue, Paint paint, boolean dial) {
		Args.nullNotPermitted(paint, "paint");
		double startAngle = valueToAngle(maxValue);
		double endAngle = valueToAngle(minValue);
		double extent = endAngle - startAngle;
		double x = area.getX();
		double y = area.getY();
		double w = area.getWidth();
		double h = area.getHeight();
		int joinType = Arc2D.OPEN;
		if (this.shape == DialShape.PIE) {
			joinType = Arc2D.PIE;
		} else if (this.shape == DialShape.CHORD) {
			if (dial && this.meterAngle > 180) {
				joinType = Arc2D.CHORD;
			} else {
				joinType = Arc2D.PIE;
			}
		} else if (this.shape == DialShape.CIRCLE) {
			joinType = Arc2D.PIE;
			if (dial) {
				extent = 360;
			}
		} else {
			throw new IllegalStateException("DialShape not recognised.");
		}
		g2.setPaint(paint);
		Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, joinType);
		g2.fill(arc);
	}

	public Rectangle2D meterArea(Rectangle2D area) {
		double gapHorizontal = (2 * MeterPlot.DEFAULT_BORDER_SIZE);
		double gapVertical = (2 * MeterPlot.DEFAULT_BORDER_SIZE);
		double meterX = area.getX() + gapHorizontal / 2;
		double meterY = area.getY() + gapVertical / 2;
		double meterW = area.getWidth() - gapHorizontal;
		double meterH = area.getHeight() - gapVertical
				+ ((this.meterAngle <= 180) && (this.shape != DialShape.CIRCLE) ? area.getHeight() / 1.25 : 0);
		double min = Math.min(meterW, meterH) / 2;
		meterX = (meterX + meterX + meterW) / 2 - min;
		meterY = (meterY + meterY + meterH) / 2 - min;
		meterW = 2 * min;
		meterH = 2 * min;
		Rectangle2D meterArea = new Rectangle2D.Double(meterX, meterY, meterW, meterH);
		return meterArea;
	}

	/**
	* Sets the dial shape and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param shape   the shape ( {@code  null}  not permitted).
	* @see #getDialShape()
	*/
	public void setDialShape(DialShape shape, MeterPlot meterPlot) {
		Args.nullNotPermitted(shape, "shape");
		this.shape = shape;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the angle (in degrees) for the whole range of the dial and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param angle   the angle (in degrees, in the range 1-360).
	* @see #getMeterAngle()
	*/
	public void setMeterAngle(int angle, MeterPlot meterPlot) {
		if (angle < 1 || angle > 360) {
			throw new IllegalArgumentException("Invalid 'angle' (" + angle + ")");
		}
		this.meterAngle = angle;
		meterPlot.fireChangeEvent();
	}

	/**
	* Translates a data value to an angle on the dial.
	* @param value   the value.
	* @return  The angle on the dial.
	*/
	public double valueToAngle(double value) {
		value = value - this.range.getLowerBound();
		double baseAngle = 180 + ((this.meterAngle - 180) / 2.0);
		return baseAngle - ((value / this.range.getLength()) * this.meterAngle);
	}

	/**
	* Sets the range for the dial and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param range   the range ( {@code  null}  not permitted and zero-length ranges not permitted).
	* @see #getRange()
	*/
	public void setRange(Range range, MeterPlot meterPlot) {
		Args.nullNotPermitted(range, "range");
		if (!(range.getLength() > 0.0)) {
			throw new IllegalArgumentException("Range length must be positive.");
		}
		this.range = range;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the flag that controls whether or not a rectangular border is drawn around the plot area and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param draw   the flag.
	* @see #getDrawBorder()
	*/
	public void setDrawBorder(boolean draw, MeterPlot meterPlot) {
		this.drawBorder = draw;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the paint used to draw the tick labels around the dial and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getTickPaint()
	*/
	public void setTickPaint(Paint paint, MeterPlot meterPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.tickPaint = paint;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the paint used to display the needle and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getNeedlePaint()
	*/
	public void setNeedlePaint(Paint paint, MeterPlot meterPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.needlePaint = paint;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the tick label paint and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getTickLabelPaint()
	*/
	public void setTickLabelPaint(Paint paint, MeterPlot meterPlot) {
		Args.nullNotPermitted(paint, "paint");
		if (!this.tickLabelPaint.equals(paint)) {
			this.tickLabelPaint = paint;
			meterPlot.fireChangeEvent();
		}
	}

	/**
	* Sets the paint used to display the value label and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getValuePaint()
	*/
	public void setValuePaint(Paint paint, MeterPlot meterPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.valuePaint = paint;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the paint used to fill the dial background.  Set this to {@code  null}  for no background.
	* @param paint   the paint ( {@code  null}  permitted).
	* @see #getDialBackgroundPaint()
	*/
	public void setDialBackgroundPaint(Paint paint, MeterPlot meterPlot) {
		this.dialBackgroundPaint = paint;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the dial outline paint and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint.
	* @see #getDialOutlinePaint()
	*/
	public void setDialOutlinePaint(Paint paint, MeterPlot meterPlot) {
		this.dialOutlinePaint = paint;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the units for the dial and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param units   the units ( {@code  null}  permitted).
	* @see #getUnits()
	*/
	public void setUnits(String units, MeterPlot meterPlot) {
		this.units = units;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the font used to display the value label and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param font   the font ( {@code  null}  not permitted).
	* @see #getValueFont()
	*/
	public void setValueFont(Font font, MeterPlot meterPlot) {
		Args.nullNotPermitted(font, "font");
		this.valueFont = font;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the flag that controls whether or not the value is visible and sends a change event to all registered listeners.
	* @param valueVisible   the new flag value.
	* @see #isValueVisible()
	* @since  1.5.4
	*/
	public void setValueVisible(boolean valueVisible, MeterPlot meterPlot) {
		this.valueVisible = valueVisible;
		meterPlot.fireChangeEvent();
	}

	/**
	* Sets the flag that controls whether or not the tick labels are visible and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param visible   the flag.
	* @see #getTickLabelsVisible()
	*/
	public void setTickLabelsVisible(boolean visible, MeterPlot meterPlot) {
		if (this.tickLabelsVisible != visible) {
			this.tickLabelsVisible = visible;
			meterPlot.fireChangeEvent();
		}
	}

	/**
	* Sets the tick label font and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param font   the font ( {@code  null}  not permitted).
	* @see #getTickLabelFont()
	*/
	public void setTickLabelFont(Font font, MeterPlot meterPlot) {
		Args.nullNotPermitted(font, "font");
		if (!this.tickLabelFont.equals(font)) {
			this.tickLabelFont = font;
			meterPlot.fireChangeEvent();
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return (MeterPlotProduct) super.clone();
	}
}